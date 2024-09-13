package com.example;

import java.awt.*;
import javax.swing.*;

public class FrameDemo {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Hej hej");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("Greetings Swinger");
        emptyLabel.setPreferredSize(new Dimension(300, 100));
        emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emptyLabel.setForeground(Color.RED);
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        JButton button = new JButton("Click me!");
        frame.getContentPane().add(button, BorderLayout.SOUTH);

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