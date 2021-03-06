package com.nsga.generation;

import java.text.DecimalFormat;
import com.nsga.NSGADisplay;

/**
 * Organism Class
 * Represents each Dot
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 22, 2021
 */
public class Organism {

    /** Gene Array, determines an Organisms movements */
    private Gene[] genes;

    /** Positioning */
    private int xPos;
    private int yPos;

    /** Index, marks where in the gene's sequence the organism is */
    private int geneIndex;

    /** Fitness of the organism */
    private double fitness;

    /** Number of genes/movements each organism has */
    private static final int GENE_LENGTH = 100;

    /**
     * Organism Class Constructor
     * Creates an Organism at the specificed coordinates
     * @param initialX Initial X position of the Organism
     * @param initialY Initial Y position of the Organism
     */
    public Organism(int initialX, int initialY) {
        xPos = initialX;
        yPos = initialY;
        geneIndex = 0;
        fitness = 0.0;

        // Fill genes
        genes = new Gene[GENE_LENGTH];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = new Gene();
        }
    }


    /**
     * Organism Class Secondary Constructor
     * @param initialX Initial X position of the Organism
     * @param initialY Initial Y position of the Organism
     * @param geneArr Predefined Gene array for crossing over
     */
    public Organism(int initialX, int initialY, Gene[] geneArr) {
        xPos = initialX;
        yPos = initialY;
        geneIndex = 0;
        genes = geneArr;

    }


    /**
     * Gets the Organism's current X position
     * @return the Organism's current X position
     */
    public int getX() {
        return xPos;
    }


    /**
     * Gets the Organism's current Y position
     * @return the Organism's current Y position
     */
    public int getY() {
        return yPos;
    }


    /**
     * Sets the next X position
     * @param newX the next X position
     */
    public void setX(int newX) {
        xPos = newX;
    }


    /**
     * Sets the new Y position
     * @param newY the new Y position
     */
    public void setY(int newY) {
        yPos = newY;
    }


    /**
     * Returns whether or not there are more genes to apply
     * @return {@code true} if there are more genes to apply,
     *         and {@code false} if there aren't.
     */
    public boolean hasNextGene() {
        return geneIndex < genes.length - 1;
    }


    /**
     * Applies the next gene, which determines the next point in the path
     * the Organism walks
     */
    public void applyNextGene() {
        xPos += genes[geneIndex].getXGene();
        yPos += genes[geneIndex].getYGene();
        geneIndex++;
    }


    /**
     * Returns the genes of a Organism
     * @return the genes of a Organism
     */
    public Gene[] getGenes() {
        return genes;
    }


    /**
     * Returns the fitness of a Organism
     * @return the fitness of a Organism
     */
    public double getFitness() {
        return fitness;
    }


    /**
     * Calculates and returns the fitness of an organism
     * @param x Organism's current x position
     * @param y Organism's current x position
     * @return The fitness of the organism. 1 is best, 0 is worst
     */
    public double calculateFitness() {
        double distanceToGoal = distance(xPos, yPos, NSGADisplay.GOAL_X,
            NSGADisplay.GOAL_Y);
        double normalised = distanceToGoal / (NSGADisplay.STARTING_Y
            - NSGADisplay.GOAL_Y);
        fitness = 1 - normalised;
        if (fitness < 0) {
            fitness = 0.0;
        }
        return fitness;

    }


    /**
     * Helper function for calculateFitness(),
     * Gets the distance between two points using distance formula
     * @param x1 Initial X Position
     * @param y1 Initial Y Position
     * @param x2 Final X Position
     * @param y2 Final Y Position
     * @return the distance between two points
     */
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((Math.pow(x2 - x1, 2)) + (Math.pow(y2 - y1, 2)));
    }


    /**
     * Crossovers the genes between two parents to form the child
     * @param partner Partner to crossover with
     * @return the resultant child organism
     */
    public Organism crossover(Organism partner) {
        Gene[] childGenes = new Gene[genes.length];
        for (int i = 0; i < genes.length; i++) {

            // Crossover pattern: [M, D, M, D, M, ... ]
            if (i % 2 == 0) {
                childGenes[i] = genes[i];
            }
            else {
                childGenes[i] = partner.genes[i];
            }
        }

        // Return the child
        return new Organism(NSGADisplay.STARTING_X, NSGADisplay.STARTING_Y,
            childGenes);

    }


    /**
     * Mutates the gene depending on the mutation rate
     * @param mutationRate Mutation rate of the population
     */
    public void mutate(double mutationRate) {
        for (int i = 0; i < genes.length; i++) {
            if (Math.random() <= mutationRate) {
                genes[i] = new Gene();
            }
        }
    }


    /**
     * Returns a String representation of a Organism
     * @return a String representation of a Organism
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");

        return "[x: " + xPos + ", y: " + yPos + "] | Fitness : " + df.format(
            fitness);
    }

}
