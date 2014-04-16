package org.ecs.ecsbeekeeping.event.hive;

import org.ecs.ecsbeekeeping.event.Event;
import org.ecs.ecsbeekeeping.hive.Hive;

/**
 * A class to represent a generic event that happens in the Hive
 */
public class HiveEvent extends Event {

    /**
     * The Hive which this event happened in
     */
    private Hive hive;

    /**
     * Constructs a new generic Hive event
     * @param hive The hive the event is happening in
     */
    public HiveEvent(Hive hive) {
        super(hive);
        this.hive = hive;
    }

    /**
     *
     * @return The hive the event happened in
     */
    public Hive getHive() {
        return hive;
    }
}
