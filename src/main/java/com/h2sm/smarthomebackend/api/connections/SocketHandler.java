package com.h2sm.smarthomebackend.api.connections;

import javax.net.ssl.SSLSocket;
import java.io.*;

public class SocketHandler extends Thread {
    private SSLSocket socket = null;
    private BufferedReader br = null;
    private PrintWriter pw = null;

    /**
     * Constructor - initialize variables
     *
     * @param s - an ssl socket created by SocketListener
     */
    public SocketHandler(SSLSocket s) {
        socket = s;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        while (true) {
            try {
                //read a line
                String str = br.readLine();
                //if it is "END", disconnect
                if (str.equals("END")) {
                    System.out.println("close......");
                    br.close();
                    pw.close();
                    socket.close();
                    break;
                }
                //else, send "Message Received"
                pw.println("Message Received");
                pw.flush();
                System.out.println("Client Socket Message:" + str);
            } catch (Exception e) {
                try {
                    System.out.println("close......");
                    br.close();
                    pw.close();
                    socket.close();
                    break;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}