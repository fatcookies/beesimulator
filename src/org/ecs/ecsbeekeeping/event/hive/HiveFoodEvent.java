package org.ecs.ecsbeekeeping.event.hive;

import org.ecs.ecsbeekeeping.hive.Hive;

/**
 * A class which represents the event of food being added or removed from a hive
 */
public class HiveFoodEvent extends HiveEvent {

    // Constants that represent the different Food types in the Hive
    public final static int HONEY = 0;
    public final static int ROYAL_JELLY = 1;
    public final static int POLLEN = 2;

    /**
     * The positive or negative change of food (amount)
     */
    private int change;

    /**
     * The type of food which was changed
     */
    private int foodType;


    /**
     * Constructor to create a new food change event
     *
     * @param hive     The hive that the food change happened in
     * @param change   The positive/negative amount the food changed by
     * @param foodType The type of food that was taken/gained
     */
    public HiveFoodEvent(Hive hive, int change, int foodType) {
        super(hive);
        this.change = change;
        this.foodType = foodType;
    }


    /**
     * @return The number of food which was taken/gained
     */
    public int getChange() {
        return change;
    }

    /**
     * @return The Food type code
     */
    public int getFoodType() {
        return foodType;
    }

    /**
     * Returns a string representation of the food which was altered
     *
     * @return The name of the food taken/gained
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
