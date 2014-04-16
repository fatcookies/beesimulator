package org.ecs.ecsbeekeeping.event.flower;

import org.ecs.ecsbeekeeping.event.Event;
import org.ecs.ecsbeekeeping.flower.Flower;

/**
 * A class to represent a generic Flower event
 */
public class FlowerEvent extends Event {

    /**
     * The flower which created this event
     */
    private Flower flower;

    /**
     * Default constructor to create a generic Flower event
     * @param flower
     */
    public FlowerEvent(Flower flower) {
        super(flower);
        this.flower = flower;
    }

    /**
     *
     * @return The flower which created this event
     */
    public Flower getFlower() {
        return flower;
    }

    /**
     * Returns a unique string representation of the flower which created the event
     * @return The name of the flower
     */
    public String getFlowerName() {
        return flower.getName() + "-" + getEventHash();
    }
}
