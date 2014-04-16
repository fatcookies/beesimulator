package org.ecs.ecsbeekeeping.event.bee;

import org.ecs.ecsbeekeeping.bee.Bee;

/**
 * An event created when a Bee's health is modified
 */
public class BeeHealthEvent extends BeeEvent {

    /**
     * The positive or negative change in the Bees health
     */
    private int healthChange;

    /**
     * Constructor to create a
     * @param bee
     * @param healthChange
     */
    public BeeHealthEvent(Bee bee, int healthChange) {
        super(bee);
        this.healthChange = healthChange;
    }

    /**
     * Gets the change in health
     * @return Health change
     */
    public int getHealthChange() {
        return healthChange;
    }

}
