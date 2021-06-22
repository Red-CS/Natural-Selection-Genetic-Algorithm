package com.nsga.generation;

import java.util.ArrayList;
import com.nsga.NSGADisplay;

/**
 * Population Class
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 21, 2021
 */
public class Population {

    /** The organisms in the population */
    private Organism[] organisms;

    /**
     * Average fitness of the population
     * Calculated after the GUI updates
     */
    private double averageFitness;

    /** Population size, larger size for better results */
    public static final int POPULATION_SIZE = 100;

    /** Mutation rate of the population */
    public static final double MUTATION_RATE = 0.02;

    /**
     * Population Class Constructor
     * Each Population represents a Population,
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
     * Population Secondary Class Constructor
     * This Population Class Constructor is used for all
     * subsequent generations
     * @param newPopulation New Population representing the next generation
     */
    public Population(Organism[] newPopulation) {
        // Set next population of organisms based on the previous
        organisms = newPopulation;
    }


    /**
     * Creates a new population based on the perfomance of the previous
     * @return The next population
     */
    public Population naturalSelection() {

        // Create mating pool
        ArrayList<Organism> matingPool = new ArrayList<Organism>();
        for (Organism o : organisms) {

            // Each organism is represented in the mating pool
            // n * 1000 times, where n is the fitness of that organism
            for (int i = 0; i < (int)(o.getFitness() * 1000); i++) {
                matingPool.add(o);
            }
        }

        // Choose Parents

        // Make new Population
        Organism[] newPopulation = new Organism[POPULATION_SIZE];

        for (int i = 0; i < organisms.length; i++) {
            Organism mother = matingPool.get((int)(Math.random() * matingPool
                .size()));
            Organism father = matingPool.get((int)(Math.random() * matingPool
                .size()));

            // TODO: Add different crossover types
            Organism child = mother.crossover(father);

            // Mutate child
            child.mutate(MUTATION_RATE);

            // Add to array
            newPopulation[i] = child;
        }

        return new Population(newPopulation);

    }


    /**
     * Returns the average fitness of the population
     * @return the average fitness of the population
     */
    public double getAverageFitness() {
        return averageFitness;
    }


    /**
     * Calculates and returns the average fitness of the population
     * @return the average fitness of the population, after calculating it
     */
    public double calculateAverageFitness() {
        double sum = 0;
        for (Organism o : organisms) {
            sum += o.getFitness();
        }
        averageFitness = sum / organisms.length;
        return averageFitness;
    }


    /**
     * Returns a String representation of a Population
     * @return a String representation of a Population
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------\n");
        for (int i = 0; i < organisms.length; i++) {
            sb.append("Organism  # " + i + "   :  " + organisms[i].toString()
                + "\n");
        }
        sb.append("-----------------------------------------------------\n");
        return sb.toString();

    }


    /**
     * Returns the Organsims in the Population
     * @return the Organisms in the Population
     */
    public Organism[] getOrganisms() {
        return organisms;
    }

}
