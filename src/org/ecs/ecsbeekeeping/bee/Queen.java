package org.ecs.ecsbeekeeping.bee;

import org.ecs.ecsbeekeeping.event.bee.BeeEatEvent;
import org.ecs.ecsbeekeeping.hive.Hive;

/**
 * A class to represent a Queen Bee of a hive.
 */
public class Queen extends Bee {

    /**
     * The hive which this Queen belongs to
     */
    private Hive hive;

    /**
     * Number of days this Bee has been Queen for
     */
    private int ageAsQueen;

    public Queen() {
        super(3,11);
        this.type = QUEEN;
        ageAsQueen = 0;
    }

    /**
     * Creates a new Bee
     * @param hive The hive this Queen belongs to
     * @param health The health of this Queen
     * @param age The age of this queen in days (total age)
     */
    public Queen(Hive hive, int health, int age) {
        super(health, age);
        this.hive = hive;
        this.type = QUEEN;
        ageAsQueen = 0;
    }

    /**
     * Default constructor which creates a new Bee that's 11 days old and has 3 health.
     * @param hive The hive this Queen belongs to
     */
    public Queen(Hive hive) {
        this(hive, 3, 11);
    }


    /**
     * Attempts to take 2 units of honey from this Queen's hive, increases health if successful,
     * decreases health if unsuccessful/
     * @see org.ecs.ecsbeekeeping.bee.Queen#eat()
     * @return Whether this Queen successfully ate
     */
    @Override
    public boolean eat() {
        if (hive.takeHoney(2) != 0) {
            setChanged();
            notifyObservers(new BeeEatEvent(this,2,BeeEatEvent.HONEY));
            increaseHealth(1);
            return true;
        } else {
            decreaseHealth(1);
            return false;
        }
    }


    /**
     * Ages this queen, eats and every three days lays a new egg in the Queens hive
     * @return This queen
     */
    @Override
    public Bee anotherDay() {
        age++;
        ageAsQueen++;

        eat();

        if ((ageAsQueen % 3) == 0) {
            hive.addBee(new Egg()); // every three days the queen lays an egg.
        }

        return this;
    }
}
