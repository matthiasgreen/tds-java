package com.example;

public class Concurrency {

    public static void log(Object o) {
        Thread thread = Thread.currentThread();
        System.out.println("[" + thread.getName() + "] " + o);
    }

    public static void main(String[] args) {
        log("hello");
    }
}