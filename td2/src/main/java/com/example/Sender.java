package com.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Sender {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 4445;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(ADDRESS);
            String message = "Alice";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);
        } catch (UnknownHostException e) {
            System.err.println("Fatal: unable to reach host, exiting");
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("Fatal: unable to open socket, exiting");
            System.err.println(e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Warning: unable to receive packet");
        }
    }
}
