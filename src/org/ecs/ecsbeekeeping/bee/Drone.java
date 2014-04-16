package org.ecs.ecsbeekeeping.bee;

import org.ecs.ecsbeekeeping.event.bee.BeeEatEvent;
import org.ecs.ecsbeekeeping.event.bee.BeeMiscEvent;

/**
 * A class to represent a Drone Bee.
 * In the current model this type of Bee does not serve any purpose and only eats.
 */
public class Drone extends Bee {

    /**
     * Default empty constructor creates a new Drone that's 11 days old with 3 health
     */
    public Drone() {
        this(3,11);
    }

    /**
     * @see Bee#Bee(int, int)
     * @param health
     * @param age
     */
    public Drone(int health, int age) {
        super(health, age);
        this.type = DRONE;
    }

    /**
     * Drones eat one unit of honey a day, lose a health point if unsuccessful.
     * @see org.ecs.ecsbeekeeping.bee.Bee#eat()
     */
    @Override
    public boolean eat() {
        if (hive != null) {
            if (hive.takeHoney(1) != 0) {
                setChanged();
                notifyObservers(new BeeEatEvent(this, 1, BeeEatEvent.HONEY));
                return true; // Bee took honey from hive

            } else {
                decreaseHealth(1);
                return false; // Bee failed to take honey from the hive
            }
        }
        return false; // Bee doesn't belong to a hive
    }

    /**
     * Currently Drones only eat every day.
     * @see org.ecs.ecsbeekeeping.bee.Bee#anotherDay()
     */
    @Override
    public Bee anotherDay() {
        age++;
        eat();
        setChanged();
        notifyObservers(new BeeMiscEvent(this,BeeMiscEvent.NOTHING));
        return this;
    }
}
