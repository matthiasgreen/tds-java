package com.example;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ticker implements Runnable {

    private AtomicBoolean stop = new AtomicBoolean(false);

    @Override
    public void run() {
        while (!stop.get()) {
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
        Ticker ticker = new Ticker();
        Thread t = new Thread(ticker);
        // t.setDaemon(true);
        System.out.println("Press enter to stop.");
        t.start();
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
            ticker.stop.set(true);
        }
    }
}
