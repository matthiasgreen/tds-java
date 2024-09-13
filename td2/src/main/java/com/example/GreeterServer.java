package com.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GreeterServer extends Thread {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 4445;


    @Override
    public void run() {
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

    private static void sendName(String name) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(ADDRESS);
            byte[] buffer = name.getBytes();
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

    public static void main(String[] args) {
        GreeterServer server = new GreeterServer();
        server.start();

        sendName("Alice");
        sendName("Bob");
        sendName("Charlie");
    }
}
