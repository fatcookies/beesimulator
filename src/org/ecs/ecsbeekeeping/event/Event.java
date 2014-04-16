package org.ecs.ecsbeekeeping.event;

/**
 * A class which represents an event in the Garden
 */
public class Event {

    /**
     * The Hashcode of the object which created the event. Used to as a unique identifier
     */
    private int hashCode;

    /**
     * Basic constructor to create an observable Event
     * @param o The object which created this event
     */
    public Event(Object o) {
        this.hashCode = o.hashCode();
    }

    /**
     * Gets the hash of the object which created this event
     * @return Hash of the object
     */
    public int getEventHash() {
        return hashCode;
    }
}
