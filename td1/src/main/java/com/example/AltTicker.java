package com.example;

import java.util.Scanner;

public class AltTicker implements Runnable {

    @Override
    public void run() {
        while (true) {
            Concurrency.log("tick");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Ticker interrupted, exiting.");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        AltTicker ticker = new AltTicker();
        Thread thread = new Thread(ticker);
        thread.start();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Press enter to stop.");
            scanner.nextLine();
            try {
                thread.interrupt();
            } catch (SecurityException e) {
                System.err.println("Security exception for some reason.");
            }
        }
    }
    
}
