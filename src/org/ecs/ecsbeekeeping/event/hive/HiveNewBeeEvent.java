package org.ecs.ecsbeekeeping.event.hive;

import org.ecs.ecsbeekeeping.bee.Bee;
import org.ecs.ecsbeekeeping.event.hive.HiveEvent;
import org.ecs.ecsbeekeeping.hive.Hive;

/**
 * A class to represent the event of a new bee being added to a hive
 */
public class HiveNewBeeEvent extends HiveEvent {

    /**
     * The bee which was added to the hive
     */
    private Bee bee;

    /**
     * Constructor to create a new bee being added to a hive event
     * @param hive The hive the bee was added to
     * @param bee The bee which was added
     */
    public HiveNewBeeEvent(Hive hive, Bee bee) {
        super(hive);
        this.bee = bee;
    }

    /**
     *
     * @return The bee which was added to the hive
     */
    public Bee getNewBee() {
        return bee;
    }
}
