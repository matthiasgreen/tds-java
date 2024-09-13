package com.example;

class GreeterThread extends Thread{
    private String toGreet;

    public GreeterThread(String toGreet) {
        this.toGreet = toGreet;
    }

    @Override
    public void run() {
        Concurrency.log("Greetings " + toGreet);
    }

    public static void main(String[] args) {
        Thread t = new GreeterThread("Alice");
        t.setName("GreeterThread");
        t.start();
    }
}
