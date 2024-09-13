package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPInput {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 4445;

    public static void main(String[] args) {
        try (Socket socket = new Socket()) {
            // Turn terminal input into TCP out
            socket.connect(new InetSocketAddress(ADDRESS, PORT));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader remoteIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create thread to read from server and print to terminal
            Thread reader = new Thread(() -> {
                try {
                    String remoteLine;
                    while ((remoteLine = remoteIn.readLine()) != null) {
                        System.out.println(remoteLine);
                    }
                    System.exit(0);
                } catch (IOException e) {
                    System.err.println("Warning: unable to read from server");
                    e.printStackTrace();
                }
            });
            reader.start();

            // Create thread to read from terminal and send to server
            Thread writer = new Thread(() -> {
                try {
                    String localLine;
                    while ((localLine = in.readLine()) != null) {
                        out.println(localLine);
                    }
                } catch (IOException e) {
                    System.err.println("Warning: unable to write to server");
                    e.printStackTrace();
                }
            });
            writer.start();

            // Wait for threads to finish
            try {
                reader.join();
                writer.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Fatal: unable to reach host, exiting");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Warning: unable to open socket");
            System.err.println(e);
        }
        
    }
}
