package com.nsga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.nsga.generation.Organism;
import com.nsga.generation.Population;

/**
 * Panel for the Natural Selection Genetic Algorithm
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 21, 2021
 */
public class NSGADisplay extends JPanel {

    private int count = 0;
    private int generationNumber = 1;
    private Timer generationIncTimer;
    private Population currentPopulation;
    private Organism[] currentOrganisms;

    /** The tick delay for the Timer (ms) */
    private final int TIMER_DELAY = 50;

    /** Size of the circles that represent People */
    private static final int CIRCLE_SIZE = 10;

    /** Size of the destination pad */
    private static final int DESTINATION_RECT_SIZE = 25;

    /** Starting X position of the dots */
    public static final int STARTING_X = (NSGAWindow.WINDOW_WIDTH / 2)
        - (DESTINATION_RECT_SIZE / 2);

    /** Starting Y position of the dots */
    public static final int STARTING_Y = NSGAWindow.WINDOW_HEIGHT - 100;

    /**
     * NSGADisplay Class Constructor
     * @param width
     * @param height
     */
    public NSGADisplay(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(66, 134, 244));
        setFocusable(true);
        requestFocus();

// init();
        currentPopulation = new Population();
        currentOrganisms = currentPopulation.getOrganisms();
        repaint();

        System.out.println("Finished");
    }


    /**
     * Paints each Organisms on the screen
     * @param g Painting Graphics Object
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Paint the starting circle
        g.setColor(new Color(253, 201, 200)); // Coral Candy
        g.fillOval(STARTING_X, STARTING_Y, CIRCLE_SIZE, CIRCLE_SIZE);

        // Paint the destination square
        g.setColor(Color.WHITE);
        g.fillRect(STARTING_X, 10, DESTINATION_RECT_SIZE,
            DESTINATION_RECT_SIZE);

        // Paint window information
        g.drawString("Generation: " + generationNumber, 20, STARTING_Y);
// g.drawString("Average Fitness: " + currentGen.getFitness(), 20,
// STARTING_Y + 20);

        // Otherwise, paint population's next step
        for (int j = 0; j < currentOrganisms.length; j++) {
            g.fillOval(currentOrganisms[j].getX(), currentOrganisms[j].getY(),
                CIRCLE_SIZE, CIRCLE_SIZE);
        }

    }


    /**
     * Initializes the loop
     */
    private void init() {
        generationIncTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                System.out.println(count++);
                if (count == 5) {
                    generationIncTimer.stop();
                    count = 0;
                    init();
                }
            }
        });
        generationIncTimer.start();
    }

}
