package com.nsga.generation;

import com.nsga.NSGADisplay;

/**
 * @author jpw91
 *
 */
public class Population {

    private Organism[] organisms;
    private static final int POPULATION_SIZE = 30;
    private static final double MUTATION_RATE = 0.02;

    /**
     * Population Constructor
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
