//package simulator;

import simulator.Tower;
import simulator.WeatherTower;
import simulator.interfaces.Flyable;
import simulator.vehicles.*;
import simulator.weather.WeatherProvider;
import simulator.vehicles.AircraftFactory;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    private static WeatherTower weatherTower;
//    private static ArrayList<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Error: Atleast one argument is required, eg. scenario.txt");
            return;
        }

        // AircraftFactory is an abstract class - we can't instantiate it
        final AircraftFactory factory = new AircraftFactory() {};
        // Create the singleton weathertower
//        WeatherTower weatherTower = new WeatherTower();
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
//            String line = buffer.readLine();
            String line = "";
            // Check variables serves as checking for the first line of scenario.txt
            int check = 0;
//            weatherTower = new WeatherTower();

            while((line = buffer.readLine()) != null) {
                // Check for empty lines
                if (line.trim().length() == 0)
                    continue;
                // Checks if we have parsed the first line yet
                if (check == 0) {

                    cycles = Integer.parseInt(line);
                    if (cycles < 0) {
                        buffer.close();
                        throw new Exception ("Number must be positive");
                    }
                    System.out.println("Simulation will run " + cycles + " times.");
                    check++;
                    continue;
                }

                // Split line in 5 parts
                String[] aircraft = line.split(" ");
//                System.out.println(line);
//                System.out.println("check = " + check);

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
                    flyable.registerTower(weatherTower);

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
