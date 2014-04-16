package org.ecs.ecsbeekeeping.event.flower;

import org.ecs.ecsbeekeeping.flower.Flower;

/**
 * A class to represent the event of a new flower being created.
 */
public class NewFlowerEvent extends FlowerEvent {

    /**
     * The type of flower which was created
     */
    private int type;

    /**
     * Creates a new event
     * @param flower The flower which has just been created
     */
    public NewFlowerEvent(Flower flower) {
        super(flower);
        this.type = flower.getType();
    }

    /**
     *
     * @return The type of flower which has been created
     */
    public int getType() {
        return type;
    }
}
