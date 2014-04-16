package org.ecs.ecsbeekeeping.flower;

import org.ecs.ecsbeekeeping.event.flower.FlowerPollenEvent;
import org.ecs.ecsbeekeeping.event.flower.NewFlowerEvent;

import java.awt.*;
import java.util.Observable;

/**
 * An abstract class which defines a Flower which resides in a Garden
 */
public abstract class Flower extends Observable {

    // Constant values which represent the type of Flower
    public static final int ROSE = 1;
    public static final int FUCHSIA = 2;
    public static final int DAFFODIL = 3;
    public static final int BLUE_ROSE = 4;

    /**
     * The number of units of pollen this Flower contains
     */
    protected int pollen;

    /**
     * The type of Flower this is. Should be one of the constants in this class.
     */
    protected int type;

    /**
     * The colour of this Flower
     */
    protected Color color;


    /**
     * Creates a new flower with a certain amount of pollen
     * @param pollen The number of units of flower to initialise this flower with.
     */
    public Flower(int pollen) {
        this.pollen = pollen;

        setChanged();
        notifyObservers(new NewFlowerEvent(this));

    }

    /**
     * Used to add pollen to the plant. Called every day.
     * @return Whether this flower successfully grew
     */
    public abstract boolean grow();

    /**
     * Attempts to extract some pollen from this flower. A bee usually does this.
     * @param extract Number of pollen units to extract from this flower
     * @return The number of units successfully extracted. If too much is requested 0 is returned.
     */
    public int extractPollen(int extract) {
        if (extract > pollen) {
            return 0;
        } else {

            setChanged();
            notifyObservers(new FlowerPollenEvent(this,-extract));

            pollen -= extract;
            return extract;
        }
    }

    /**
     * Gets the number of units of pollen
     * @return Number of units of pollen this flower contains
     */
    public int getPollen() {
        return pollen;
    }

    /**
     * Sets the number of units of pollen this flower contains
     * @param pollen Number of units to set to
     */
    public void setPollen(int pollen) {
        this.pollen = pollen;
    }

    /**
     * Gets the type of flower this is. Can be identified by the constants in this class
     * @return This flowers type
     */
    public int getType() {
        return type;
    }

    /**
     * Produces a string representation of this flower for saving
     * @return The save string of this flower
     */
    public String toSaveString() {
        // Adjusts name so it conforms to the configuration file standard
        String saveName = getName().toLowerCase().replace(" ","");

        return saveName+":"+getPollen();
    }

    public String getName() {
        switch(getType()) {
            case ROSE:
                return "Rose";
            case FUCHSIA:
                return "Fuchsia";
            case DAFFODIL:
                return "Daffodil";
            case BLUE_ROSE:
                return "Blue Rose";
            default:
                return "Flower";
        }
    }
}
