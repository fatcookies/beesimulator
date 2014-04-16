package org.ecs.ecsbeekeeping.event.bee;

import org.ecs.ecsbeekeeping.bee.Bee;

/**
 * A class to represent when a Bee attempts to take some pollen from flowers
 * in the Garden.
 */
public class BeeWorkEvent extends BeeEvent {

    /**
     * The number of pollen collected from the flowers
     */
    private int pollenCollected;

    /**
     * Constructor to create a new work event
     * @param bee The bee that did the work
     * @param pollenColleted The number of pollen collected
     */
    public BeeWorkEvent(Bee bee, int pollenColleted) {
        super(bee);
        this.pollenCollected = pollenColleted;
    }


    /**
     * Gets the number of pollen collected from the garden
     * @return Number of pollen
     */
    public int getPollenCollected() {
        return pollenCollected;
    }
}
