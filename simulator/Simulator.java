package simulator;

import simulator.Tower;
import simulator.WeatherTower;
import simulator.interfaces.*;
import simulator.vehicles.*;
import simulator.weather.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Simulator {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Error: Atleast one argument is required, eg. scenario.txt");
            return;
        }

        // AircraftFactory is an abstract class - we can't instantiate it
        // but we can use it like this (in this case)
        final AircraftFactory factory = new AircraftFactory() {};
        // Create the singleton weathertower
        WeatherTower wt = new WeatherTower();
        // Cycles - stores how many times the simulation will run ie. first line of the scenario.txt
        int cycles = 0;

        // Try because we are opening files & parsing ints - these could fail / throw exceptions
        try {
            // Opens file given in argument
            File file = new File(args[0]);
            // Creates simulation.txt which will be written to
            File simulation = new File("simulation.txt");
            // BufferedReader reads text from an input stream, ie the file
            BufferedReader buffer = new BufferedReader(new FileReader(file));

            // Readline iterates through the file line by line
            String line = buffer.readLine();
            // Check variables serves as checking for the first line of scenario.txt
            int check = 0;

            while(line != null) {
                // Check for empty lines
                if (line.trim().length() == 0)
                    continue;
                // Checks if we have parsed the first line yet
                if (check == 0) {
                    try {
                        cycles = Integer.parseInt(line);
                        if (cycles < 0) {
                            buffer.close();
                            throw new Exception ("Number must be positive");
                        }
                        System.out.println("Simulation will run " + cycles + " times.");
                        check = 1;
                    } catch (Exception e) {
                        System.err.println("Error: A error occured while parsing the first line of the scenario.");
                        System.err.println("Error: " + e);
                    }
                }

                // Split line in 5 parts
                String[] aircraft = line.split(" ");

                // Validate the 5 parts
                if (aircraft.length != 5) {
                    buffer.close();
                    throw new Exception ("Incorrect aircraft format");
                }
                try {
                    int lon = Integer.parseInt(aircraft[2]);
                    int lat = Integer.parseInt(aircraft[3]);
                    int height = Integer.parseInt(aircraft[4]);
                    Flyable flyable = factory.newAircraft(aircraft[0], aircraft[1], lon, lat, height);
                    flyable.registerTower(wt);

                } catch (Exception e) {
                    System.err.println("Error: A error occured while parsing the aircraft details.");
                    System.err.println("Error: " + e);
                }
            }


        } catch (NumberFormatException e) {
            System.err.println("Error: First line of scenario must be an Integer");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}