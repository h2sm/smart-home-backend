package com.h2sm.smarthomebackend.api.connections;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import javax.net.ssl.SSLContext;

import com.h2sm.smarthomebackend.api.auth.configuration.ContextFactory;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import tlschannel.NeedsReadException;
import tlschannel.NeedsWriteException;
import tlschannel.ServerTlsChannel;
import tlschannel.TlsChannel;

@Component
public class TlsSocketServer implements Runnable {

    private static final Charset utf8 = StandardCharsets.UTF_8;

    @SneakyThrows
    @Override
    public void run() {
        SSLContext sslContext = ContextFactory.authenticatedContext("TLSv1.2");

        // connect server socket channel and register it in the selector
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.socket().bind(new InetSocketAddress(10000));
            serverSocket.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                // loop blocks here
                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {

                        // we have a new connection

                        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

                        // accept new connection
                        SocketChannel rawChannel = serverChannel.accept();
                        rawChannel.configureBlocking(false);

                        // wrap raw channel in TlsChannel
                        TlsChannel tlsChannel = ServerTlsChannel.newBuilder(rawChannel, sslContext)
                                .build();

                        // Note that the raw channel is registered in the selector (and now the wrapped ont),
                        // the TlsChannel is put as an attachment. Additionally, the channel is registered for
                        // reading, because TLS connections are initiated by clients.
                        SelectionKey newKey = rawChannel.register(selector, SelectionKey.OP_READ);
                        newKey.attach(tlsChannel);

                    } else if (key.isReadable() || key.isWritable()) {

                        // we have data (or buffer space) in existing connections

                        ByteBuffer buffer = ByteBuffer.allocate(10000);

                        // recover the TlsChannel from the attachment
                        TlsChannel tlsChannel = (TlsChannel) key.attachment();
                        try {
                            // write received bytes in stdout
                            int c = tlsChannel.read(buffer);
                            if (c > 0) {
                                buffer.flip();
                                System.out.print(utf8.decode(buffer));
                            }
                            if (c < 0) {
                                tlsChannel.close();
                            }
                        } catch (NeedsReadException e) {
                            key.interestOps(SelectionKey.OP_READ); // overwrites previous value
                        } catch (NeedsWriteException e) {
                            key.interestOps(SelectionKey.OP_WRITE); // overwrites previous value
                        }

                    } else {
                        throw new IllegalStateException();
                    }
                }
            }
        }

    }
}
