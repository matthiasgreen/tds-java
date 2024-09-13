package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class ConcurrentQueue<T> {
    /**
    * Thread safe FIFO queue supporting multiple producers and consumers
    */
    private static final int MAX_SIZE = 10;
    private Queue<T> queue = new LinkedList<T>();
    
    public synchronized void add(T item) {
        while (queue.size() >= MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(item);
        notifyAll();
        System.out.println("Added item: " + item);
        System.out.println("Length of queue: " + queue.size());
    }

    public synchronized T pop() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T item = queue.poll();
        notifyAll();
        System.out.println("Removed item: " + item);
        System.out.println("Length of queue: " + queue.size());
        return item;
    }

    public static void main(String[] args) {
        final int N_PRODUCERS = 10;
        final int N_TO_PRODUCE = 101;

        ConcurrentQueue<Integer> concurrentQueue = new ConcurrentQueue<Integer>();
        Runnable producerTask = new Runnable() {

            @Override
            public void run() {
                IntStream.range(0, N_TO_PRODUCE).forEach(i -> {
                    concurrentQueue.add(i);
                });
            }
            
        };
        ArrayList<Thread> producerThreads = new ArrayList<Thread>();
        IntStream.range(0, N_PRODUCERS).forEach(i -> {
            producerThreads.add(new Thread(producerTask));
        });
        
        Thread readerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                int sum = 0;
                int n = 0;
                for (int i = 0; i < N_PRODUCERS*N_TO_PRODUCE; i++) {
                    sum += concurrentQueue.pop();
                    n++;
                }
                System.out.println((float)sum/n);
            }
        });

        
        // Start all threads with low priority
        readerThread.setPriority(1);
        readerThread.start();
        for (Thread t : producerThreads) {
            t.setPriority(1);
            t.start();
        }
    }
}
