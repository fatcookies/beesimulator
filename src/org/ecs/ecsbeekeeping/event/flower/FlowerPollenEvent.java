package org.ecs.ecsbeekeeping.event.flower;

import org.ecs.ecsbeekeeping.flower.Flower;

/**
 * Class to represent the event when a flower gains pollen.
 * A flower usually gains pollen when it grows
 */
public class FlowerPollenEvent extends FlowerEvent {

    /**
     * The change of pollen from the flower
     */
    private int amount;

    /**
     * Creates a new pollen event
     * @param flower The flower which had pollen gained/lost
     * @param amount The amount of pollen which was changed
     */
    public FlowerPollenEvent(Flower flower,int amount) {
        super(flower);
        this.amount = amount;
    }

    /**
     *
     * @return The change in pollen in the flower
     */
    public int getPollenChange() {
        return amount;
    }
}
