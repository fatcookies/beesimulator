package org.ecs.ecsbeekeeping.event.bee;

import org.ecs.ecsbeekeeping.bee.Bee;
import org.ecs.ecsbeekeeping.event.bee.BeeEvent;

/**
 * A class to represent the event of a new Bee being made. Usually thrown
 * when a Queen lays a new egg. Also thrown when one type of Bee turns into
 * another
 */
public class NewBeeEvent extends BeeEvent {

    /**
     * The type the Bee used to be before evolving
     */
    private int oldType;

    /**
     * The type of the Bee after it has evolved
     */
    private int newType;

    /**
     * Constructor for a new bee which has not evolved
     * @param bee The new Bee
     * @param newType The type of the new Bee
     */
    public NewBeeEvent(Bee bee, int newType) {
        this(bee, -1, newType);
    }

    /**
     * Constructor for a new bee which has evolved
     * @param bee The bee
     * @param oldType The type of the bee before evolving
     * @param newType The type of the bee after evolving
     */
    public NewBeeEvent(Bee bee, int oldType, int newType) {
        super(bee);
        this.oldType = oldType;
        this.newType = newType;
    }

    /**
     * The old type of Bee
     * @return The type of the bee before evolving
     */
    public int getOldBeeType() {
        return oldType;
    }

    /**
     * Gets the new type of Bee
     * @return The type of the bee after evolving
     */
    public int getNewBeeType() {
        return newType;
    }
}
