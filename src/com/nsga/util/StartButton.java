package com.nsga.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 22, 2021
 */
public class StartButton extends JButton {

    /**
     * StartButton Class Constructor
     */
    public StartButton(String buttonText) {
        setText(buttonText);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
            }
        });
    }

}
