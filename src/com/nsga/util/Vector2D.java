package com.nsga.util;

/**
 * Vector2D Class
 * Represents an entity's possition in float values
 * @author Red Williams (Red-CS)
 * @since 6/21/2021
 *
 */
public class Vector2D {

    private float x;
    private float y;

    /**
     * Vector2D Constructor
     */
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Sets the X position of the object
     * @param x X position to be set
     */
    public void setX(float x) {
        this.x = x;
    }


    /**
     * Sets the Y position of the object
     * @param y Y position to be set
     */
    public void setY(float y) {
        this.y = y;
    }


    /**
     * Returns the X position of the object
     * @return the X position of the object
     */
    public float getX() {
        return x;
    }


    /**
     * Returns the Y position of the object
     * @return the Y position of the object
     */
    public float getY() {
        return y;
    }

}
