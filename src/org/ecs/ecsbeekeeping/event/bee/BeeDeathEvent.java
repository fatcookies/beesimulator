package org.ecs.ecsbeekeeping.event.bee;

import org.ecs.ecsbeekeeping.bee.Bee;

/**
 * Event when a bee has reached 0 health and therefore has died.
 */
public class BeeDeathEvent extends BeeEvent {

    /**
     * Constructor which creates a Bee death
     * @param bee
     */
    public BeeDeathEvent(Bee bee) {
        super(bee);
    }

}
