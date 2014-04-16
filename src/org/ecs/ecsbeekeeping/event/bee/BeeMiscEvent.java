package org.ecs.ecsbeekeeping.event.bee;

import org.ecs.ecsbeekeeping.bee.Bee;
import org.ecs.ecsbeekeeping.event.Event;

/**
 *
 */
public class BeeMiscEvent extends BeeEvent {


    public static final int NOTHING = 0;
    public static final int SITS = 1;
    public static final int WRIGGLE = 2;
    public static final int HIDES = 3;

    /**
     * The type of miscellaneous event this Bee did
     */
    private int eventType;


    /**
     * Constructor to create a miscellaneous Bee event
     *
     * @param bee The bee which creates the event
     */
    public BeeMiscEvent(Bee bee, int eventType) {
        super(bee);
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    /**
     * Gets a string representation of the event
     * @return String representation of what this bee did
     */
    public String getDescription() {
        switch (getEventType()) {
            case NOTHING:
                return "does nothing";
            case SITS:
                return "sits there";
            case WRIGGLE:
                return "wriggles a bit";
            case HIDES:
                return "hides in cocoon";
            default:
                return "undefined behavior";
        }
    }
}
