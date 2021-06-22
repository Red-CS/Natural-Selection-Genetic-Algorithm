package com.nsga;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Main Window of the program
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 22, 2021
 */
@SuppressWarnings("serial")
public class NSGAWindow extends JFrame {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    /**
     * NSGAWindow Constructor
     */
    public NSGAWindow() {
        // TODO Auto-generated constructor stub
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new NSGADisplay(WINDOW_WIDTH, WINDOW_HEIGHT));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        new NSGAWindow();

    }

}
