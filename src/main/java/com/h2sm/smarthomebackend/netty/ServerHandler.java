package com.h2sm.smarthomebackend.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.*;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    static List<Channel> channels = new ArrayList<>();
    static Map<String, Channel> channelMap = new HashMap<>();

    public static List getChannels() {
        return channels;
    }

    public static Map<String,Channel> getChannelsMap(){
        return channelMap;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("Client joined - " + ctx);
        ctx.channel().writeAndFlush("AUTH:");
//        channels.add(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println("Message received: " + msg);
        var receivedMessage = parseMessage(msg);

        switch (receivedMessage.get(0)) {
            case "AUTH_HUB":
                authHub(ctx, msg);
                break;
            case "REGISTER":
                authHub(ctx, msg);
                break;
            default:
                System.out.println("Unknown message type: " + msg);
                break;
        }
//        for (Channel c : channels) {
//            c.writeAndFlush(msg + '\n');
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Closing connection for client - " + ctx);
        ctx.close();
    }

    private List<String> parseMessage(String msg) {
        return List.of(msg.split(":"));
    }

    private void authHub(ChannelHandlerContext ctx, String msg) {
        System.out.println("CTX: " + ctx);
        System.out.println("msg: " + msg);
        channelMap.put(parseMessage(msg).get(1), ctx.channel());
    }

//    public static void startChat(Channel channel) {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            var text = scanner.nextLine();
//            channel.writeAndFlush(text);
//            if (text.equals("q")) System.exit(0);
//        }
//
//    }
}