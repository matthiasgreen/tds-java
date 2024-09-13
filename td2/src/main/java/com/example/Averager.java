package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Averager implements Runnable {
    private static final int PORT = 4445;

    private Socket clientSocket;
    private int sum = 0;
    private int n = 0;

    public Averager(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            boolean end = false;
            while (!end) {
                out.println("Enter a number or 'q' to quit");
                out.flush();
                String line = in.readLine();

                if (line.toLowerCase().strip().equals("q")) {
                    end = true;
                    out.println("Goodbye for now!");
                    out.flush();
                } else {
                    try {
                        int value = Integer.parseInt(line);
                        sum += value;
                        n++;
                        out.println("New average :" + (double) sum / n);
                        out.flush();
                    } catch (NumberFormatException e) {
                        out.println("Invalid number");
                        out.flush();
                    }
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Averager averager = new Averager(clientSocket);
                Thread thread = new Thread(averager);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
