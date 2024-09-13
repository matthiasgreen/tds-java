package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Counter {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */


    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Hej hej");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CounterComponent counter = new CounterComponent();
        
        frame.getContentPane().add(counter, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton minusButton = new JButton("-");
        minusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                counter.decrease();
            }
            
        });

        JButton plusButton = new JButton("+");
        plusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                counter.increase();
            }
        });

        bottomPanel.add(minusButton);
        bottomPanel.add(plusButton);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // make window's dimension fit its content
        frame.pack();
        // Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
