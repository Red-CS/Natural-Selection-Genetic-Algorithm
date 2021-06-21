package com.nsga.generation;

import com.nsga.NSGADisplay;

/**
 * Population Class
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 21, 2021
 */
public class Population {

    private Organism[] organisms;
    private double averagePopulation;
    private static final int POPULATION_SIZE = 2;
    private static final double MUTATION_RATE = 0.02;

    /**
     * Population Class Constructor
     * Each Population represents a generation,
     * and holds an array of Organisms
     */
    public Population() {
        // Create new Population of Organisms
        organisms = new Organism[POPULATION_SIZE];

        // Create new Organisms
        for (int i = 0; i < organisms.length; i++) {
            organisms[i] = new Organism(NSGADisplay.STARTING_X,
                NSGADisplay.STARTING_Y);
        }
    }


    /**
     * Returns the Organsims in the Population
     * @return the Organisms in the Population
     */
    public Organism[] getOrganisms() {
        return organisms;
    }

}
