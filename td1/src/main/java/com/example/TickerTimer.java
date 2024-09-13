package com.example;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TickerTimer extends TimerTask {
    Date startDate;

    @Override
    public void run() {
        if (startDate == null) {
            startDate = new Date();
        }
        long elapsedTime = new Date().getTime() - startDate.getTime();
        Concurrency.log("Tick, elapsed time: " + Math.round((float)elapsedTime / 1000));
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TickerTimer(), 0, 1000);
        try (Scanner scanner = new Scanner(System.in)) {
            System.err.println("Press enter to cancel.");
            scanner.nextLine();
            timer.cancel();
        }
        
    }



}
