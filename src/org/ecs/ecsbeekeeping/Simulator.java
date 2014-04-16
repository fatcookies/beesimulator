package org.ecs.ecsbeekeeping;

import org.ecs.ecsbeekeeping.bee.Bee;
import org.ecs.ecsbeekeeping.flower.Flower;
import org.ecs.ecsbeekeeping.hive.Hive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simulator class which handles the simulation of a Garden and it's contents
 */
public class Simulator {

    /**
     * The Garden to simulate
     */
    private Garden garden;

    /**
     * Timer to periodically process each day
     */
    private Timer timer;

    /**
     * Number of days this simulator has been running for
     */
    private int days;


    /**
     * Creates a new Simulator with a specified Garden
     *
     * @param garden The Garden to simulate
     */
    public Simulator(Garden garden) {
        this.garden = garden;
        timer = new Timer();
        days = 0;
    }

    /**
     * Starts a new simulation
     *
     * @param tickInterval How long a day lasts in milliseconds.
     */
    public void start(int tickInterval) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                garden.anotherDay();
                days++;
                // Saves garden every 50 days
                if (days % 50 == 0) {
                    saveGarden();
                }
            }
        }, 0, tickInterval);
    }

    /**
     * Stops the current simulation if it has already been started
     */
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Saves the garden which was loaded to a new file
     *
     * @return Whether the save was successful
     * @throws java.io.IOException Thrown if the saving failed
     */
    public boolean saveGarden() {

        File file = new File("savedGarden");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            Hive hive = garden.getHive();

            // Save the Hives state
            writer.write(hive.getSaveString());
            writer.newLine();

            // Save all the flowers
            for (int i = 0; i < garden.size(); i++) {
                Flower flower = garden.getFlower(i);
                writer.write(flower.toSaveString());
                writer.newLine();
            }

            // Save the Bees
            for (int i = 0; i < hive.size(); i++) {
                Bee bee = hive.getBee(i);
                writer.write(bee.toSaveString());
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException ioe) {
            System.err.println("Error saving config");
            return false;
        }


        return true;

    }


}
