package com.nsga.generation;

import java.text.DecimalFormat;

/**
 * Gene Class
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 21, 2021
 */
public class Gene {

    private double xGene;
    private double yGene;
    private DecimalFormat df;

    /** Multiplies the predetermined movement to increase visual effect */
    private static final int MOVEMENT_MULTIPLIER = 15;

    /**
     * Gene Constructor
     */
    public Gene() {
        xGene = Math.random() * MOVEMENT_MULTIPLIER * (((int)(Math.random()
            * 2) == 0 ? -1 : 1));
        yGene = Math.random() * MOVEMENT_MULTIPLIER * (((int)(Math.random()
            * 2) == 0 ? -1 : 1));
        df = new DecimalFormat("#.##");
    }


    /**
     * Returns the X Gene
     * @return the X Gene
     */
    public double getXGene() {
        return xGene;
    }


    /**
     * Returns the Y Gene
     * @return the Y Gene
     */
    public double getYGene() {
        return yGene;
    }


    /**
     * Returns a String representation of the Gene
     * @return a String representation of the Gene
     */
    @Override
    public String toString() {

        return "(" + df.format(xGene) + ", " + df.format(yGene) + ")";
    }

}
