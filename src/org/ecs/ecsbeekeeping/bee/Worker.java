package org.ecs.ecsbeekeeping.bee;

import org.ecs.ecsbeekeeping.Garden;
import org.ecs.ecsbeekeeping.event.bee.BeeEatEvent;
import org.ecs.ecsbeekeeping.event.bee.BeeWorkEvent;
import org.ecs.ecsbeekeeping.flower.Flower;

/**
 * A class to represent a Worker Bee
 */
public class Worker extends Bee {

    /**
     * Number of flowers the Worker will attempt to find and collect pollen from the Garden
     */
    public static final int NUMBER_OF_FLOWERS_TO_COLLECT = 2;

    /**
     * Default constructor that creates a Worker that's 11 days old and has 3 health
     */
    public Worker() {
        this(3, 11);
    }

    /**
     * @see org.ecs.ecsbeekeeping.bee.Bee#Bee(int, int)
     */
    public Worker(int health, int age) {
        super(health, age);
        this.type = WORKER;
    }

    /**
     * Workers eat one unit of honey a day and gain a health point if successful, they
     * lose a health point if not.
     *
     * @return Whether this Worker successfully ate
     * @see Bee#eat()
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
     * The Worker attempts to collect all the pollen from two random flowers in the garden,
     * then attempts to turn it into food.
     */
    private void work() {
        if (hive != null) {
            if (hive.getGarden() != null) {
                Garden garden = hive.getGarden();
                int pollenCollected = 0;

                for (int i = 0; i < NUMBER_OF_FLOWERS_TO_COLLECT; i++) {
                    Flower flower = garden.findFlower();
                    pollenCollected += flower.extractPollen(flower.getPollen());

                }
                makeFood(pollenCollected);


                setChanged();
                notifyObservers(new BeeWorkEvent(this,pollenCollected));
            }
        }
    }

    /**
     * Turns pollen into food and adds it to the Bees Hive.
     * If there are at least two units of pollen available, the Worker creates a Royal Jelly.
     * Any remaining pollen (if any) is turned into honey.
     *
     * @param pollen The number of units of pollen this Worker can use to make with food with
     */
    private void makeFood(int pollen) {
        if (pollen >= 2) {
            hive.addRoyalJelly(1); // first attempt to make royal jelly
            pollen -= 2;
        }

        for (int i = 0; i < pollen; i++) {
            hive.addHoney(1); // turn rest of pollen to honey
        }
    }

    /**
     * @see Bee#anotherDay()
     */
    @Override
    public Bee anotherDay() {
        work();
        eat();
        age++;
        return this;
    }
}
