package org.ecs.ecsbeekeeping.event.bee;

import org.ecs.ecsbeekeeping.bee.Bee;

/**
 * A class which represents the event when a Bee successfully takes some
 * food from the hives and eats it.
 */
public class BeeEatEvent extends BeeEvent {

    // Constants for different types of food
    public final static int HONEY = 0;
    public final static int ROYAL_JELLY = 1;
    public final static int POLLEN = 2;

    /**
     * The number of times the food was taken
     */
    private int foodChange;

    /**
     * The type of food that was eaten
     * @see #HONEY
     * @see #ROYAL_JELLY
     * @see #POLLEN
     */
    private int foodType;

    /**
     * Create a new Bee eating event
     * @param bee The bee that ate
     * @param foodChange The number of times the Bee ate
     * @param foodType The type of food the bee ate
     */
    public BeeEatEvent(Bee bee, int foodChange, int foodType) {
        super(bee);
        this.foodChange = foodChange;
        this.foodType = foodType;
    }

    /**
     * Gets the number of food that was eaten
     * @return Number of food
     */
    public int getFoodChange() {
        return foodChange;
    }

    /**
     * Gets the food type
     * @return The food type
     */
    public int getFoodType() {
        return foodType;
    }


    /**
     * Returns the food in a String form
     * @return The food the bee ate
     */
    public String getFoodName() {
        switch (getFoodType()) {
            case HONEY:
                return "Honey";
            case ROYAL_JELLY:
                return "Royal Jelly";
            case POLLEN:
                return "Pollen";
            default:
                return "Food";
        }
    }

}
