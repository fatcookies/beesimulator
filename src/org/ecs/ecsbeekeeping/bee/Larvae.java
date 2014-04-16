package org.ecs.ecsbeekeeping.bee;

import org.ecs.ecsbeekeeping.event.bee.BeeEatEvent;
import org.ecs.ecsbeekeeping.event.bee.BeeMiscEvent;
import org.ecs.ecsbeekeeping.event.bee.NewBeeEvent;

/**
 * A class to represent a Larvae. Usually formed from an Egg and then evolves to a Pupa
 */
public class Larvae extends Bee {

    /**
     * Default constructor which creates a Larvae that's 4 days old and has 3 health
     */
    public Larvae() {
        this(3,4);
    }


    /**
     * @see org.ecs.ecsbeekeeping.bee.Bee#Bee(int, int)
     */
    public Larvae(int health, int age) {
        super(health,age);
        this.type = LARVAE;
    }

    /**
     *
     * @see org.ecs.ecsbeekeeping.bee.Bee#eat()
     */
    @Override
    public boolean eat() {
        if (hive != null) {
            if (hive.takeRoyalJelly(1) != 0) {
                setChanged();
                notifyObservers(new BeeEatEvent(this, 1, BeeEatEvent.ROYAL_JELLY));

                return true; // Bee took honey from hive
            } else {
                decreaseHealth(1);
                return false; // Bee failed to take honey from the hive
            }
        }
        return false; // Bee doesn't belong to a hive
    }

    /**
     * Larvae ages and eats. Once it reaches 6 days old if becomes a Pupa.
     * @see org.ecs.ecsbeekeeping.bee.Bee#anotherDay()
     * @return This object or a Pupa after 6 days
     */
    @Override
    public Bee anotherDay() {
        setChanged();
        notifyObservers(new BeeMiscEvent(this,BeeMiscEvent.WRIGGLE));

        age++;
        eat();
        if (getAge() > 6) {
            setChanged();
            notifyObservers(new NewBeeEvent(this, this.getType(), Bee.PUPA));
            return new Pupa();
        } else {
            return this;
        }
    }
}
