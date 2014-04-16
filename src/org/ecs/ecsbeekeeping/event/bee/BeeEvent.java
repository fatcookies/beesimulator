package org.ecs.ecsbeekeeping.event.bee;

import org.ecs.ecsbeekeeping.bee.Bee;
import org.ecs.ecsbeekeeping.event.Event;

/**
 * A generic event which involves a Bee
 */
public class BeeEvent extends Event {

    /**
     * The bee which created this event
     */
    private Bee bee;

    /**
     * Constructor to create a generic Bee event
     * @param bee The bee which creates the event
     */
    public BeeEvent(Bee bee) {
        super(bee);
        this.bee = bee;
    }

    /**
     * Returns The bee which created this event
     * @return The bee
     */
    public Bee getBee() {
        return bee;
    }

    /**
     * Gets the name of the type of Bee and its unique number
     * @return The bees unique name
     */
    public String getBeeName() {
        return bee.getName()+"-"+getEventHash();
    }
}
