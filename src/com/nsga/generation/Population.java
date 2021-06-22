package com.nsga.generation;

import java.util.ArrayList;
import com.nsga.NSGADisplay;

/**
 * Population Class
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 21, 2021
 */
public class Population {

    private Organism[] organisms;
    private double averageFitness;
    private static final int POPULATION_SIZE = 100;
    private static final double MUTATION_RATE = 0.02;

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


    public Population(Organism[] newPopulation) {
        // Create new Population of Organisms based on the previous
        organisms = newPopulation;
    }


    /**
     * Creates a new population based on the perfomance of the previous
     */
    public Population naturalSelection() {

        // Create mating pool
        ArrayList<Organism> matingPool = new ArrayList<Organism>();
        for (Organism o : organisms) {
            for (int i = 0; i < (int)(o.getFitness() * 1000); i++) {
                matingPool.add(o);
            }
        }

// System.out.println(matingPool.size());

        // Choose Parents
        Organism[] newPopulation = new Organism[POPULATION_SIZE];

        for (int i = 0; i < organisms.length; i++) {
            Organism mother = matingPool.get((int)(Math.random() * matingPool
                .size()));
            Organism father = matingPool.get((int)(Math.random() * matingPool
                .size()));

            Organism child = mother.crossover(father);
            child.mutate(MUTATION_RATE);
            newPopulation[i] = child;
        }

        return new Population(newPopulation);

    }


    public double getAverageFitness() {
        return averageFitness;
    }


    public double calculateAverageFitness() {
        double sum = 0;
        for (Organism o : organisms) {
            sum += o.getFitness();
        }
        System.out.println(sum);
        averageFitness = sum / organisms.length;
        System.out.println("Average Fitness: " + averageFitness);
        return averageFitness;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(
            "----------------------------------------------------------------------------------\n");
        for (int i = 0; i < organisms.length; i++) {
            sb.append("Organism  # " + i + "   :  " + organisms[i].toString()
                + "\n");
        }
        sb.append(
            "----------------------------------------------------------------------------------\n");
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
