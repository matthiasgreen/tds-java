package com.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Greeter {
    private static final int PORT = 4445;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.err.println("Greetings, " + new String(packet.getData(), 0, packet.getLength()));
            }
        } catch (SocketException e) {
            System.err.println("Fatal: unable to open socket, exiting");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Warning: unable to receive packet");
        }
    }
}
