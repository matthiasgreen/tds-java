package com.example;

import java.io.IOException;
import java.net.*;

public class Net {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4445);
        boolean running = true;
        byte[] buf = new byte[256];

        while (running) {
            DatagramPacket inPacket  = new DatagramPacket(buf, buf.length);
            socket.receive(inPacket);
            String received = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("Received: " + received);

            if (received.equals("end")) {
                running = false;
                continue;
            }
            
            InetAddress senderAddress = inPacket.getAddress();
            int senderPort = inPacket.getPort();
            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, senderAddress, senderPort);
            socket.send(outPacket);
        }
        socket.close();
    }
}