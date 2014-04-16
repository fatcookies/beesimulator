package org.ecs.ecsbeekeeping.flower;

import org.ecs.ecsbeekeeping.event.flower.FlowerPollenEvent;

import java.awt.*;

/**
 * A class to represent a Fuchsia Flower
 */
public class Fuchsia extends Flower {

    /**
     * Default constructor which creates a new Fuchsia with no pollen
     */
    public Fuchsia() {
        this(0);
    }

    /**
     * Fuchsias are purple
     * @see Flower#Flower(int)
     */
    public Fuchsia(int pollen) {
        super(pollen);
        this.type = Flower.FUCHSIA;
        this.color = Color.getColor("purple");
    }

    /**
     * Fuchsia produce one pollen unit a day
     * @see org.ecs.ecsbeekeeping.flower.Flower#grow()
     */
    @Override
    public boolean grow() {
        pollen++;
        setChanged();
        notifyObservers(new FlowerPollenEvent(this,1));
        return true;
    }
}
