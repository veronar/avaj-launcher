package simulator.vehicles;

import simulator.*;
import simulator.vehicles.Coordinates;
import simulator.vehicles.Aircraft;
import simulator.interfaces.Flyable;



public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    // Constructor
    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        String msg[] = {
                "Its SUNNY",
                "Its RAIN",
                "Its FOG",
                "Its SNOW"
        };
        int msgIndex = 0;
        // Get the current coordinates & store for changing according to weather
        int newLong = this.coordinates.getLongitude();
        int newLat = this.coordinates.getLatitude();
        int newHeight = this.coordinates.getHeight();

        switch (weather) {
            case "SUN": {
                this.coordinates = new Coordinates(newLong + 10, newLat, newHeight + 2);
            }
            break;

            case "RAIN": {
                this.coordinates = new Coordinates(newLong + 5, newLat, newHeight);
                msgIndex = 1;
            }
            break;

            case "FOG": {
                this.coordinates = new Coordinates(newLong + 1, newLat, newHeight);
                msgIndex = 2;
            }
            break;

            case "SNOW": {
                this.coordinates = new Coordinates(newLong, newLat, newHeight - 12);
                msgIndex = 3;
            }
            break;

            default:
                break;
        }

        System.out.println("Helicopter#" + (this.name) + "(" + (this.id) + ") " + msg[msgIndex]);

        newHeight = this.coordinates.getHeight();
        if (newHeight <= 0){
            this.weatherTower.unregister(this);
            System.out.println("Tower says: Helicopter#" + (this.name) + "(" + (this.id) + ")" + " unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        // Register the weather tower to this flyable
        this.weatherTower = weatherTower;
        // Register (Add) this flyable to the weathertower list
        this.weatherTower.register(this);

        // Write to file: new flyable logged
        // "Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.\n";
        System.out.println("Tower says: Helicopter#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.");
    }
}
