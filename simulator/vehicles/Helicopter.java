package simulator.vehicles;

import simulator.*;
import simulator.vehicles.Coordinates;


public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    // Constructor
    public Helicopter(String name, Coordinates coordinates) {

    }

    public void updateConditions() {

    }

    public void registerTower(WeatherTower weatherTower) {
        // Register the weather tower to this flyable
        this.weatherTower = weatherTower;
        // Register (Add) this flyable to the weathertower list
        this.weatherTower.register(this);

        // Write to file: new flyable logged
        // "Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.\n";
        Sytem.out.println("Tower says: Helicopter#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.\n");
    }
}
