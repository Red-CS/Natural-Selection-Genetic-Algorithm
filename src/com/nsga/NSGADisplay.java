package com.nsga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.nsga.generation.Organism;
import com.nsga.generation.Population;

/**
 * Panel for the Natural Selection Genetic Algorithm
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 21, 2021
 */
@SuppressWarnings("serial")
public class NSGADisplay extends JPanel {

    private double lastPopAvgFitness;
    private int generationNumber = 1;
    private Timer generationIncTimer;
    private Population currentPopulation;
    private Organism[] currentOrganisms;
    private DecimalFormat df;

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

    /** Goal X position */
    public static final int GOAL_X = STARTING_X;

    /** Goal Y position */
    public static final int GOAL_Y = 10;

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

        currentPopulation = new Population();
        currentOrganisms = currentPopulation.getOrganisms();
        lastPopAvgFitness = currentPopulation.getAverageFitness();

        df = new DecimalFormat("#.##");

        init();
    }


    /**
     * Initializes the loop. This method is recursive.
     * The idea is that after each organisms movements have been
     * displayed, the next population is made, the instace variables
     * are reset and incremented, and the process starts over again
     */
    private void init() {
        generationIncTimer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                /*
                 * For each iteration,
                 * apply the next gene for each organism
                 */
                for (int i = 0; i < currentOrganisms.length; i++) {
                    currentOrganisms[i].applyNextGene();
                }
                repaint();
                if (!currentOrganisms[0].hasNextGene()) {
                    generationIncTimer.stop();

                    // Calculate fitnesses
                    for (int i = 0; i < currentOrganisms.length; i++) {
                        currentOrganisms[i].calculateFitness();
                    }
                    lastPopAvgFitness = currentPopulation
                        .calculateAverageFitness();

                    // Create new Generations
                    System.out.println(
                        "\n-----------------------------------------------------");
                    System.out.println("Generation # " + generationNumber + "\t"
                        + "|| Average Fitness: " + df.format(
                            lastPopAvgFitness));
                    System.out.println(currentPopulation.toString());
                    currentPopulation = currentPopulation.naturalSelection();
                    currentOrganisms = currentPopulation.getOrganisms();
                    generationNumber++;
                    init();
                }
            }
        });
        generationIncTimer.start();
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
        g.fillRect(GOAL_X, GOAL_Y, DESTINATION_RECT_SIZE,
            DESTINATION_RECT_SIZE);

        // Paint window information
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 18));
        g.drawString("Generation: " + generationNumber, 20, STARTING_Y);
        g.drawString("Average Fitness: " + df.format(lastPopAvgFitness), 20,
            STARTING_Y + 20);
        g.drawString("Mutation Rate: " + Population.MUTATION_RATE * 100 + "%",
            20, STARTING_Y + 40);
        g.drawString("Population Size: " + Population.POPULATION_SIZE, 20,
            STARTING_Y + 60);

        // Otherwise, paint population's next step
        for (int j = 0; j < currentOrganisms.length; j++) {

            g.fillOval(currentOrganisms[j].getX(), currentOrganisms[j].getY(),
                CIRCLE_SIZE, CIRCLE_SIZE);
        }

    }

}
