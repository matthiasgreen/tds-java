package com.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class GreeterRunnable implements Runnable {
    private String toGreet;

    public GreeterRunnable(String toGreet) {
        this.toGreet = toGreet;
    }

    @Override
    public void run() {
        Concurrency.log("Greeting " + toGreet);
    }

    private static void greetAll(List<String> names) {
        names.forEach(
            (String name) -> {
                Thread t = new Thread(new GreeterRunnable(name));
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } 
        );
    }

    public static void main(String[] args) {
        greetAll(Arrays.asList("Alice", "Bob", "Charlie"));
    }
}
