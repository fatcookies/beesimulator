package org.ecs.ecsbeekeeping.flower;

import org.ecs.ecsbeekeeping.event.flower.FlowerPollenEvent;

import java.awt.*;

/**
 * A class that represents a Blue Rose in a Garden
 */
public class BlueRose extends Rose {

    /**
     * Default constructor that creates a new Blue Rose with 0 pollen
     */
    public BlueRose() {
        this(0);
    }

    /**
     * @see Flower#Flower(int)
     * @param pollen
     */
    public BlueRose(int pollen) {
        super(pollen);
        this.type = Flower.BLUE_ROSE;
        this.color = Color.BLUE;
    }

    /**
     * Blue Roses increase their pollen by 1 every day.
     * @see org.ecs.ecsbeekeeping.flower.Flower#grow()
     * @return If this Blue Rose successfully grew
     */
    @Override
    public boolean grow() {
        pollen++;
        setChanged();
        notifyObservers(new FlowerPollenEvent(this,1));
        return true;
    }
}
