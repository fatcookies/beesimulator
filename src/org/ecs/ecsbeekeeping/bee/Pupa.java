package org.ecs.ecsbeekeeping.bee;

import org.ecs.ecsbeekeeping.event.bee.BeeMiscEvent;
import org.ecs.ecsbeekeeping.event.bee.NewBeeEvent;

/**
 * A class to present a Pupa Bee, evolves into either a Drone or a Worker
 */
public class Pupa extends Bee {

    /**
     * The probability that this Pupa will become a Drone after 11 days.
     * Will become a Worker otherwise.
     */
    public final static double DRONE_PROBABILITY = 0.2;

    /**
     * Default empty constructor that creates a new Pupa that's 7 days old and has 3 health
     */
    public Pupa() {
        this(3,7);
    }

    /**
     * @see org.ecs.ecsbeekeeping.bee.Bee#Bee(int, int)
     */
    public Pupa(int health, int age) {
        super(health,age);
        this.type = PUPA;
    }

    /**
     *
     * @see org.ecs.ecsbeekeeping.bee.Bee#eat()
     */
    @Override
    public boolean eat() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Pupa ages, eats and then if it's 11 days old either evolves to a Drone or Worker.
     * @see org.ecs.ecsbeekeeping.bee.Bee#anotherDay()
     * @return This Pupa if under 11 days old or a Drone/Worker after.
     */
    @Override
    public Bee anotherDay() {
        setChanged();
        notifyObservers(new BeeMiscEvent(this,BeeMiscEvent.HIDES));

        age++;
        eat();

        if (getAge() == 11) {
            double prob = Math.random();

            if (prob < DRONE_PROBABILITY) {
                setChanged();
                notifyObservers(new NewBeeEvent(this,getType(),Bee.DRONE));
                return new Drone();
            } else {
                setChanged();
                notifyObservers(new NewBeeEvent(this,getType(),Bee.WORKER));
                return new Worker();
            }
        } else {
            return this;
        }
    }
}
