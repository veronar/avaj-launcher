
import simulator.interfaces.Flyable;
import simulator.WeatherTower;
import simulator.weather.WeatherProvider;
import simulator.vehicles.AircraftFactory;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {

    public static PrintWriter printWriter = null;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Error: At least one argument is required, eg. scenario.txt");
            return;
        }


        // AircraftFactory is an abstract class - we can't instantiate it
//        final AircraftFactory factory = new AircraftFactory() {};
        // Create the singleton weathertower
        WeatherTower weatherTower = new WeatherTower();
        // Cycles - stores how many times the simulation will run ie. first line of the scenario.txt
        int cycles = 0;
        // Check if Simulation file already exists
        File simulation = new File("simulation.txt");
        if (simulation.exists()) {
            simulation.delete();
        }



        // Try because we are opening files & parsing ints - these could fail / throw exceptions
        try {
            // Opens file given in argument
            File file = new File(args[0]);
            // BufferedReader reads text from an input stream, ie the file
            BufferedReader buffer = new BufferedReader(new FileReader(file));

            // Creates simulation.txt which will be written to
            printWriter = new PrintWriter(new File("./simulation.txt"));

            String line = "";
            // Check variables serves as checking for the first line of scenario.txt
            int check = 0;

            // Readline iterates through the file line by line
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
//                    System.out.println("Simulation will run " + cycles + " times.");
                    check++;
                    continue;
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

                    Flyable flyable = AircraftFactory.newAircraft(aircraft[0], aircraft[1], lon, lat, height);
                    flyable.registerTower(weatherTower);

                } catch (NumberFormatException nfe) {
                    System.err.println("Error: Coordinates must be numbers");
                    break;
                } catch (Exception e) {
                    System.err.println("Error: " + e);
                }
            }
            buffer.close();

        } catch (NumberFormatException e) {
            System.err.println("Error: First line of scenario must be an Integer");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        WeatherProvider.getProvider();
        while (cycles > 0) {
            weatherTower.changeWeather();
            cycles--;
        }

    }
}
