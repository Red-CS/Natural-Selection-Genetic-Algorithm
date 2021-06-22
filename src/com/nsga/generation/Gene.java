package com.nsga.generation;

/**
 * Gene Class
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 21, 2021
 */
public class Gene {

    private double[] geneVector;

    /** Multiplies the predetermined movement to increase visual effect */
    private static final int MOVEMENT_MULTIPLIER = 15;

    /**
     * Gene Constructor
     */
    public Gene() {
        geneVector = new double[2];
        geneVector[0] = Math.random() * MOVEMENT_MULTIPLIER * (((int)(Math
            .random() * 2) == 0 ? -1 : 1));
        geneVector[1] = Math.random() * MOVEMENT_MULTIPLIER * (((int)(Math
            .random() * 2) == 0 ? -1 : 1));
    }


    public Gene(int xGene, int yGene) {
        geneVector = new double[2];
        geneVector[0] = xGene;
        geneVector[1] = yGene;
    }


    public double getXGene() {
        return geneVector[0];
    }


    public double getYGene() {
        return geneVector[1];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(geneVector[0]);
        sb.append(", ");
        sb.append(geneVector[1]);
        sb.append(")");

        return sb.toString();
    }

}
