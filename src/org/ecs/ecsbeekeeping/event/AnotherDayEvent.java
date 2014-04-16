package org.ecs.ecsbeekeeping.event;

import org.ecs.ecsbeekeeping.Garden;

/**
 * Event which signifies there has been another day
 */
public class AnotherDayEvent extends Event {

    /**
     * Default constructor
     * @param g The garden which a day has started in.
     */
    public AnotherDayEvent(Garden g) {
        super(g);
    }
}
