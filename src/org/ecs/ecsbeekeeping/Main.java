package org.ecs.ecsbeekeeping;

import org.ecs.ecsbeekeeping.event.BeehiveEventHandler;
import org.ecs.ecsbeekeeping.io.GardenLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * A Main class which loads a config file, creates a new garden and starts a new simulation
 */
public class Main {


    public static void main(String[] args) {
        if (args.length >= 1) {
            GardenLoader config = new GardenLoader();

            // Creates the observer and assigns it to handle Bee, Hive and Flower events.
            BeehiveEventHandler beeHandler = new BeehiveEventHandler();
            config.addBeeObserver(beeHandler);
            config.addHiveObserver(beeHandler);
            config.addFlowerObserver(beeHandler);

            try {

                // sets Log writer if there are two parameters
                if (args.length == 2) {
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(args[1], true)));
                    beeHandler.setLogWriter(writer);
                }

                // Loads the Garden with the observers and starts the simulation
                Garden g = config.loadGardenfromFile(args[0]);
                final Simulator sim = new Simulator(g);
                sim.start(500);


            } catch (Exception e) {

                System.err.println(e.getLocalizedMessage() +
                        "\nError loading config from file: " + args[0]);
            }
        } else {
            System.err.println("Invalid arguments:\n" +
                    "Syntax: java Main configfile\n" +
                    "Syntax: java Main configfile logfile");
        }
    }
}
