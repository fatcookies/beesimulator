package org.ecs.ecsbeekeeping.hive;

import org.ecs.ecsbeekeeping.Garden;
import org.ecs.ecsbeekeeping.bee.Bee;
import org.ecs.ecsbeekeeping.event.AnotherDayEvent;
import org.ecs.ecsbeekeeping.event.bee.BeeDeathEvent;
import org.ecs.ecsbeekeeping.event.hive.HiveFoodEvent;
import org.ecs.ecsbeekeeping.event.hive.HiveNewBeeEvent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A class to represent a Hive. Hives contain a number of Bee's and a store for three types
 * of food: Honey, Royal Jelly and Pollen.
 */
public class Hive extends Observable {

    /**
     * An ArrayList to store all the Bees which are inside this Hive
     */
    public ArrayList<Bee> cells; // The Tester expects this to be public :(

    /**
     * The number of units of Honey in this Hive
     */
    private int honey;

    /**
     * The number of units of Royal Jelly in this Hive
     */
    private int royalJelly;

    /**
     * The number of units of Pollen in this Hive
     */
    private int pollen;

    /**
     * The Garden this Hive belongs to
     */
    private Garden garden;

    /**
     * The Observer which observes every Bee within this hive
     */
    private Observer beeObserver;


    /**
     * Constructor to create a new Hive with an capacity of 100 bees.
     * Initialises all the food stores to 0.
     */
    public Hive() {
        cells = new ArrayList<Bee>(100);
        honey = 0;
        royalJelly = 0;
        pollen = 0;
    }

    /**
     * Adds a new Bee to this Hive, up to a maximum of 100.
     *
     * @param bee The Bee to attempt to add to this Hive
     * @return true if the Bee was successfully added, false otherwise
     */
    public boolean addBee(Bee bee) {
        if (cells.size() < 100) {
            setChanged();
            notifyObservers(new HiveNewBeeEvent(this, bee));

            bee.setHive(this);
            if (beeObserver != null) {
                bee.addObserver(beeObserver);
            }
            return cells.add(bee);
        } else {
            return false;
        }

    }


    /**
     * Adds a new Observer to observe the Bees in this hive
     * @param observer Observer to add
     */
    public void addBeeObserver(Observer observer) {
        this.beeObserver = observer;

        // add beeObserver to existing Bees.
        for(Bee b: cells) {
            b.addObserver(observer);
        }
    }

    /**
     * Gets a Bee in a specific cell in the Hive
     *
     * @param index The index of the cell of the Bee
     * @return A Bee at the index specified
     */
    public Bee getBee(int index) {
        if (index < cells.size()) {
            return cells.get(index);
        } else {
            return null;
        }
    }

    /**
     * Adds a number of units of honey to this Hive
     *
     * @param add The number of units to add to this hive
     * @return true if the honey is successfully added
     */
    public boolean addHoney(int add) {
        if (add < 0) {
            return false;
        }

        setChanged();
        notifyObservers(new HiveFoodEvent(this, add, HiveFoodEvent.HONEY));
        honey += add;
        return true;
    }

    /**
     * Adds a number of units of Royal Jelly to this Hive
     *
     * @param add The number of units to add to this hive
     * @return true if the Royal Jelly is successfully added
     */
    public boolean addRoyalJelly(int add) {
        if (add < 0) {
            return false;
        }


        setChanged();
        notifyObservers(new HiveFoodEvent(this, add, HiveFoodEvent.ROYAL_JELLY));
        royalJelly += add;
        return true;
    }

    /**
     * Adds a number of units of Pollen to this Hive
     *
     * @param add The number of units to add to this hive
     * @return true if the Pollen is successfully added
     */
    public boolean addPollen(int add) {
        if (add < 0) {
            return false;
        }


        setChanged();
        notifyObservers(new HiveFoodEvent(this, add, HiveFoodEvent.POLLEN));
        pollen += add;
        return true;
    }

    /**
     * Attempts to take some Honey out of this Hive
     *
     * @param take The number of units of Honey to take out of this Hive
     * @return The number of units of Honey taken, 0 if unsuccessful
     */
    public int takeHoney(int take) {
        if (take > honey) {
            return 0;
        }


        setChanged();
        notifyObservers(new HiveFoodEvent(this, -take, HiveFoodEvent.HONEY));
        honey -= take;
        return take;
    }

    /**
     * Attempts to take some Royal Jelly out of this Hive
     *
     * @param take The number of units of Royal Jelly to take out of this Hive
     * @return The number of units of Royal Jelly taken, 0 if unsuccessful
     */
    public int takeRoyalJelly(int take) {
        if (take > royalJelly) {
            return 0;
        }


        setChanged();
        notifyObservers(new HiveFoodEvent(this, -take, HiveFoodEvent.ROYAL_JELLY));
        royalJelly -= take;
        return take;
    }

    /**
     * Attempts to take some Pollen out of this Hive
     *
     * @param take The number of units of Pollen to take out of this Hive
     * @return The number of units of Pollen taken, 0 if unsuccessful
     */
    public int takePollen(int take) {
        if (take > pollen) {
            return 0;
        }


        setChanged();
        notifyObservers(new HiveFoodEvent(this, -take, HiveFoodEvent.POLLEN));
        pollen -= take;
        return take;
    }

    /**
     * Sets the Garden
     *
     * @param garden The garden to set
     */
    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    /**
     * Gets the garden
     *
     * @return The garden this Hive belongs to
     */
    public Garden getGarden() {
        return garden;
    }


    /**
     * Calls the anotherDay method on all the Bees in this Hive and removes any dead Bees.
     */
    public void anotherDay() {
        for (int i = 0; i < cells.size(); i++) {
            Bee bee = cells.get(i);
            Bee newBee = bee.anotherDay();

            if (newBee.isAlive()) {
                newBee.setHive(this);
                cells.set(i, newBee); // replace the current bee with newBee
                if (beeObserver != null) {
                    newBee.addObserver(beeObserver); // must set observer in case bee evolved
                }

            } else {
                cells.remove(i); // bee has died, remove from hive :(
                setChanged();
                notifyObservers(new BeeDeathEvent(bee));
            }
        }
        setChanged();
        notifyObservers(new AnotherDayEvent(getGarden()));
    }

    /**
     * Creates a string suitable for a config file
     * @return A string representation of this Hive ready to write to a file
     */
    public String getSaveString() {
        return "hive:"+honey+","+royalJelly+","+pollen;
    }

    /**
     *
     * @return The number of Bees in this hive
     */
    public int size() {
        return cells.size();
    }


}
