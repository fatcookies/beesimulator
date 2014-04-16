package org.ecs.ecsbeekeeping.bee;

import org.ecs.ecsbeekeeping.event.bee.BeeMiscEvent;

/**
 * A class to represent a Bee egg laid by a queen.
 * Does nothing until it reaches 3 days old and then "evolves" to a Larvae
 */
public class Egg extends Bee {

    /**
     * Default constructor which creates an Egg that's 0 days old and with 3 health
     */
    public Egg() {
        this(3,0);
    }

    /**
     * Creates a new Egg with defined health and age
     * @see Bee#Bee(int, int)
     */
    public Egg(int health, int age) {
        super(health,age);
        this.type = EGG;
    }

    /**
     * Eggs do not eat
     * @see org.ecs.ecsbeekeeping.bee.Bee#eat()
     */
    @Override
    public boolean eat() {
        return true;
    }

    /**
     * Egg ages every day and then "evolves" to a Larvae after 3 days
     * @see org.ecs.ecsbeekeeping.bee.Bee#anotherDay()
     * @return This object or a new Larvae once Egg reaches 3 days old
     */
    @Override
    public Bee anotherDay() {
        setChanged();
        notifyObservers(new BeeMiscEvent(this,BeeMiscEvent.SITS));

        age++;
        if (getAge() > 3) {
            return new Larvae();
        } else {
            return this;
        }
    }
}
