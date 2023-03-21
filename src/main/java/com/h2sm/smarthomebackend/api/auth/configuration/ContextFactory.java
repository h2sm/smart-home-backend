package com.h2sm.smarthomebackend.api.auth.configuration;

import com.h2sm.smarthomebackend.api.connections.TlsSocketServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

public class ContextFactory {

    public static SSLContext authenticatedContext(String protocol) throws GeneralSecurityException, IOException {
        SSLContext sslContext = SSLContext.getInstance(protocol);
        KeyStore ks = KeyStore.getInstance("JKS");
        try (InputStream keystoreFile =
                     TlsSocketServer.class.getClassLoader().getResourceAsStream("keystore.jks")) {
            ks.load(keystoreFile, "password".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, "password".toCharArray());
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            return sslContext;
        }
    }
}