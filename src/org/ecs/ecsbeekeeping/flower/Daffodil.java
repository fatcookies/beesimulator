package org.ecs.ecsbeekeeping.flower;

import org.ecs.ecsbeekeeping.event.flower.FlowerPollenEvent;

import java.awt.*;

/**
 * A class that represents a Daffodil Flower
 */
public class Daffodil extends Flower{

    /**
     * Default constructor that creates a new Daffodil with 0 pollen
     */
    public Daffodil() {
        this(0);
    }

    /**
     * @see Flower#Flower(int)
     */
    public Daffodil(int pollen) {
        super(pollen);
        this.type=  Flower.DAFFODIL;
        this.color = Color.YELLOW;
    }

    /**
     * Daffodil's gain 3 pollen each day
     * @see org.ecs.ecsbeekeeping.flower.Flower#grow()
     */
    @Override
    public boolean grow() {
        pollen += 3;

        setChanged();
        notifyObservers(new FlowerPollenEvent(this,3));
        return true;
    }
}
