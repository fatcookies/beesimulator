package org.ecs.ecsbeekeeping;

import org.ecs.ecsbeekeeping.flower.Flower;
import org.ecs.ecsbeekeeping.hive.Hive;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A class to represent a Garden which contains one Hive and a collection of Flowers.
 */
public class Garden {

    /**
     * The Hive that belongs in this Garden
     */
    private Hive hive;

    /**
     * A collection of flowers that are currently growing in this Garden
     */
    private ArrayList<Flower> flowers;


    /**
     * The Observer for all the flowers in the Garden
     */
    private Observer flowerObserver;


    /**
     * Constructor to initialise the Flowers collection
     */
    public Garden() {
        flowers = new ArrayList<Flower>();
    }

    /**
     * Processes everything in the garden once a day
     * Calls the grow method on all the flowers in the garden (as they grow once a day)
     * Calls the anotherDay method on the Hive in this garden.
     */
    public void anotherDay() {
        for(Flower flower: flowers) {
            flower.grow();
        }
        hive.anotherDay();
    }

    /**
     * Assigns a Hive to this garden. If a Hive already exists, it will be replaced
     * @param hive Hive to add to this garden.
     */
    public void addHive(Hive hive) {
        hive.setGarden(this);
        this.hive = hive;
    }

    /**
     * Adds a new flower to the collection of flowers in this Garden
     * @param flower Flower to add
     */
    public void addFlower(Flower flower) {
        if(flowerObserver !=null) {
            flower.addObserver(flowerObserver);
        }

        flowers.add(flower);
    }

    /**
     * Gets a specific flower at an index
     * @param index Index of the flower in the Flower collection
     * @return The flower at the specified index
     */
    public Flower getFlower(int index) {
        return flowers.get(index);
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }

    /**
     * Finds a random flower in the Garden
     * @return A random Flower from the Garden
     */
    public Flower findFlower() {
        int randIndex = (int) (Math.random() * size());
        return flowers.get(randIndex);
    }

    /**
     * Gets the number of flowers
     * @return The number of flowers
     */
    public int size()  {
        return flowers.size();
    }

    public Hive getHive() {
        return hive;
    }

    public void addFlowerObserver(Observer flowerObserver) {
        this.flowerObserver = flowerObserver;

        // set existing flowers
        for(Flower f: flowers) {
            f.addObserver(flowerObserver);
        }
    }

}
