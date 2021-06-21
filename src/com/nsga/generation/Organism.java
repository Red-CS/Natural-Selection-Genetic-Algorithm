package com.nsga.generation;

import com.nsga.NSGADisplay;

public class Organism {

    private Gene[] genes;
    private int xPos;
    private int yPos;
    private int geneIndex;
    private double fitness;

    private static final int GENE_LENGTH = 100;

    /**
     * Default Constructor for the Organism class
     */
    public Organism(int initialX, int initialY) {
        xPos = initialX;
        yPos = initialY;
        geneIndex = 0;

        // Fill genes
        genes = new Gene[GENE_LENGTH];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = new Gene();
        }
    }


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


    public void setGenes(Gene[] newGenes) {
        genes = newGenes;
    }


    /**
     * Returns the fitness of a Organism
     * @return the fitness of a Organism
     */
    public double getFitness() {
        return fitness;
    }


    /**
     * Calculates the fitness of a Organism, based on how close
     * they got to the goal. The closer you are, the larger
     * your fitness score
     * @param goalX X position of the goal
     * @param goalY Y position of the goal
     */
    public double calculateFitness(int x, int y) {
        double d = distance(x, y, NSGADisplay.STARTING_X,
            NSGADisplay.STARTING_Y);
        fitness = d / Math.sqrt(Math.pow(NSGADisplay.STARTING_Y, 2) + Math.pow(
            y, 2));
        fitness = Math.round(fitness * 100.0) / 100.0;
        return fitness;
    }


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
     * Returns a String representation of a Organism
     * @return a String representation of a Organism
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < genes.length; i++) {
            sb.append(genes[i].toString());
            if (i != genes.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }


    /**
     * Helper function for calculateFitness(),
     * Gets the distance between two points using distance formula
     * @return the distance between two points
     */
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(

            (Math.pow(x2, 2) - Math.pow(x1, 2)) + (Math.pow(y2, 2) - Math.pow(
                y1, 2))

        );
    }

}
