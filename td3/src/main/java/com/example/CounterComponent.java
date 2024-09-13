package com.example;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CounterComponent extends JTextField {
    private int count;
    public CounterComponent() {
        super();
        setPreferredSize(new Dimension(300, 100));
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.RED);
        setCounter(0);
        
        getDocument().addDocumentListener(new DocumentListener() {

            private void updateText() {
                String text = getText();
                try {
                    count = Integer.valueOf(text);
                    
                } catch (NumberFormatException e) {
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateText();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateText();
            }
            
        });
    }

    void setCounter(int newValue) {
        count = newValue > 0 ? newValue : 0;;
        setText(String.valueOf(count));
    }

    void increase() {
        setCounter(count + 1);
    }

    void decrease() {
        setCounter(count - 1);
    }
}
