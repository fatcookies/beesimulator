package org.ecs.ecsbeekeeping.bee;

import org.ecs.ecsbeekeeping.event.bee.BeeHealthEvent;
import org.ecs.ecsbeekeeping.event.bee.NewBeeEvent;
import org.ecs.ecsbeekeeping.hive.Hive;

import java.util.Observable;

/**
 * An abstract class which a defines a basic Bee that belongs in a Hive in a Garden.
 */
public abstract class Bee extends Observable {

    // Constants for each type of Bee
    public final static int QUEEN = 1;
    public final static int WORKER = 2;
    public final static int DRONE = 3;
    public final static int EGG = 4;
    public final static int LARVAE = 5;
    public final static int PUPA = 6;


    /**
     * The type of Bee. Use Bee constants to set.
     */
    protected int type;

    /**
     * The age of this Bee in days.
     */
    protected int age;

    /**
     * The health of this Bee. 3=healthy, 0=dead
     */
    protected int health;


    /**
     * The hive this Bee belongs to.
     */
    protected Hive hive;

    /**
     * Empty constructor used when Bees transform from one type to another
     */
    public Bee() {

    }

    /**
     * Creates a new Bee
     *
     * @param health The initial health of this Bee.
     * @param age    The age of this Bee in days
     */
    public Bee(int health, int age) {
        this.health = health;
        this.age = age;
        setChanged();
        notifyObservers(new NewBeeEvent(this, this.getType()));
    }

    /**
     * Attempts to get food from hive and eat it
     *
     * @return If this Bee successfully ate
     */
    public abstract boolean eat();

    /**
     * Daily tasks of a Bee. Called every simulation "tick"
     *
     * @return The Bee after the day (potentially may be a different type)
     */
    public abstract Bee anotherDay();


    /**
     * Gets the type of the Bee
     *
     * @return The type of this Bee
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the age of this Bee
     *
     * @return The age of this Bee in days
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of this bee
     *
     * @param age The age in days that the Bee will be.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Checks if this Bee is still alive
     *
     * @return True if the Bee is alive, false if it is dead
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Increases the health of this Bee (up the maximum of 3)
     *
     * @param points The number of health points to attempt to increment by
     */
    protected void increaseHealth(int points) {
        if ((health + points) <= 3) {
            setChanged();
            notifyObservers(new BeeHealthEvent(this, points));

            health += points; // add points up to a maximum of 3 health
        } else {
            health = 3; // set to max health if health goes above 3
        }
    }

    /**
     * Decreases the health of this Bee
     *
     * @param points Number of health points to decrement by
     */
    protected void decreaseHealth(int points) {
        increaseHealth(points * -1);
    }

    /**
     * Sets the Hive that this Bee belongs to
     *
     * @param hive The hive this Bee belongs to
     */
    public void setHive(Hive hive) {
        this.hive = hive;
    }

    /**
     * Gets the Hive that this Bee is in.
     *
     * @return The Hive this Bee belongs to
     */
    public Hive getHive() {
        return hive;
    }

    /**
     * Produces a string representation of this flower for saving
     * @return The save string of this flower
     */
    public String toSaveString() {
        // Adjusts name so it conforms to the configuration file standard
        String saveName = getName().toLowerCase().replace(" ","");

        return saveName+":"+health+","+age;
    }

    public String getName() {
        switch (getType()) {
            case QUEEN:
                return "Queen";
            case WORKER:
                return "Worker";
            case DRONE:
                return "Drone";
            case EGG:
                return "Egg";
            case LARVAE:
                return "Larvae";
            case PUPA:
                return "Pupa";
            default:
                return "Bee";
        }
    }

}
