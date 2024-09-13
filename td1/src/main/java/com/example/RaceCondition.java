package com.example;

import java.util.ArrayList;

public class RaceCondition implements Runnable{
    private static int counter = 0;
    private static int correctCounter = 0;
    
    @Override
    public void run() {
        counter += 1;
        synchronized (RaceCondition.class) {
            correctCounter += 1;
        }
    }

    public static void main(String[] args) {
        // Create and start 10000 threads
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10000; i++) {
            threads.add(new Thread(new RaceCondition()));
        }

        for (Thread thread : threads) {
            thread.setPriority(1);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Unprotected counter final value: " + counter);
        System.out.println("Protected counter final value: " + correctCounter);
        System.out.println("Expected output: 10000");
    }

}
