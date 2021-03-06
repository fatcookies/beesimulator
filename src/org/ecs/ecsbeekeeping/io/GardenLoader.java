package org.ecs.ecsbeekeeping.io;

import org.ecs.ecsbeekeeping.Garden;
import org.ecs.ecsbeekeeping.bee.*;
import org.ecs.ecsbeekeeping.flower.*;
import org.ecs.ecsbeekeeping.hive.Hive;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observer;

/**
 * A class used to load a Garden object from a static text configuration file
 */
public class GardenLoader {

    /**
     * The garden object to be loaded
     */
    private Garden garden;

    /**
     * The hive object to be loaded
     */
    private Hive hive;

    /**
     * The Observer (if any) which listens to events that are generated from Bees
     */
    private Observer beeObserver;

    /**
     * The Observer (if any) which listens to events that are generated by the Hive
     */
    private Observer hiveObserver;

    /**
     * The Observer (if any) which listens to events from the flowers in the Garden
     */
    private Observer flowerObserver;

    /**
     * Loads a Garden from a configuration file with the following format:
     * Hive:Honey,Royal Jelly,Pollen
     * Beename:health,days old
     * Flowername:pollen
     *
     * @param filename The relative path of the file to attempt to load
     * @return The Garden based on the configuration file
     * @throws Exception If the file cannot be read parsing fails
     */
    public Garden loadGardenfromFile(String filename) throws Exception {
        FileReader fileReader = new FileReader(new File(filename));
        BufferedReader reader = new BufferedReader(fileReader);
        ArrayList<String> lines = new ArrayList<String>();

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            lines.add(currentLine);
        }


        return loadGarden(lines);

    }


    /**
     * Loads a Garden using configuration statements in an ArrayList
     *
     * @see #loadGardenfromFile(String)
     */
    public Garden loadGarden(ArrayList<String> lines) throws Exception {
        garden = new Garden();
        if(flowerObserver !=null) {
            garden.addFlowerObserver(flowerObserver);
        }

        for (String currentLine : lines) {
            try {
                String[] split = currentLine.split(":");
                String objectName = split[0]; // get substring before colon.
                int[] parameters = getParameters(split[1]);
                parseLine(objectName, parameters);
            } catch (Exception e) {
                throw new Exception("Error parsing line:\n" + currentLine);
            }
        }

        garden.addHive(hive);

        return garden;
    }

    /**
     * Creates appropriate objects based on their name.
     * Passes the appropriate parameters
     * Adds the object to any other appropriate objects.
     *
     * @param name       The name of the object to be created
     * @param parameters The parameters to initialise the object
     * @throws Exception If parsing fails
     */
    private void parseLine(String name, int[] parameters) throws Exception {

        //todo: make less ugly

        if (name.equals("hive")) {
            hive = new Hive();

            if(beeObserver !=null) {
                hive.addBeeObserver(beeObserver);
            }
            if(hiveObserver != null) {
                hive.addObserver(hiveObserver);
            }

            hive.addHoney(parameters[0]);
            hive.addRoyalJelly(parameters[1]);
            hive.addPollen(parameters[2]);

        } else if (name.equals("drone")) {
            hive.addBee(new Drone(parameters[0], parameters[1]));
        } else if (name.equals("egg")) {
            hive.addBee(new Egg(parameters[0], parameters[1]));
        } else if (name.equals("larvae")) {
            hive.addBee(new Larvae(parameters[0], parameters[1]));
        } else if (name.equals("pupa")) {
            hive.addBee(new Pupa(parameters[0], parameters[1]));
        } else if (name.equals("queen")) {
            hive.addBee(new Queen(hive, parameters[0], parameters[1]));
        } else if (name.equals("worker")) {
            hive.addBee(new Worker(parameters[0], parameters[1]));

        } else if (name.equals("bluerose")) {
            garden.addFlower(new BlueRose(parameters[0]));
        } else if (name.equals("daffodil")) {
            garden.addFlower(new Daffodil(parameters[0]));
        } else if (name.equals("fuchsia")) {
            garden.addFlower(new Fuchsia(parameters[0]));
        } else if (name.equals("rose")) {
            garden.addFlower(new Rose(parameters[0]));
        } else {
            throw new Exception("Unknown object " + name);
        }


    }

    /**
     * Splits a list of comma separated numbers into an array
     *
     * @param list String representation of numbers to be converted
     * @return An array of parameters
     * @throws Exception Any integer conversion fails or invalid syntax
     */
    private static int[] getParameters(String list) throws Exception {
        String[] params = list.split(",");
        int[] result = new int[params.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(params[i]);
        }

        return result;
    }


    public void addBeeObserver(Observer observer) {
        this.beeObserver = observer;
    }
    public void addFlowerObserver(Observer observer) {
        this.flowerObserver = observer;
    }
    public void addHiveObserver(Observer observer) {
        this.hiveObserver = observer;
    }

}
