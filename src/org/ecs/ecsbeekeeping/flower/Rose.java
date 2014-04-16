package org.ecs.ecsbeekeeping.flower;

import org.ecs.ecsbeekeeping.event.flower.FlowerPollenEvent;

import java.awt.*;

/**
 * A class to represent a Rose Flower
 */
public class Rose extends Flower{

    /**
     * Default constructor to create a new Rose with no pollen
     */
    public Rose() {
        this(0);
    }

    /**
     * Creates a new Rose which is red in colour
     * @see Flower#Flower(int)
     */
    public Rose(int pollen) {
        super(pollen);
        this.type = Flower.ROSE;
        this.color = Color.RED;
    }

    /**
     * Roses produce 2 units of pollen a day
     * @see org.ecs.ecsbeekeeping.flower.Flower#grow()
     */
    @Override
    public boolean grow() {
        pollen+= 2;
        setChanged();
        notifyObservers(new FlowerPollenEvent(this,2));
        return true;
    }
}
