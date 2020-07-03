package simulator.vehicles;

import simulator.*;
import simulator.vehicles.Coordinates;
import simulator.vehicles.Aircraft;


public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    // Constructor
    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String msg[] = {
                "\uD83C\uDF1E  Sunshine. Daisy. Bottom. Mellow. Turn this stupid fat rat YELLOW",
                "\uD83C\uDF27️",
                "\uD83C\uDF2B️",
                "\uD83C\uDF2B ⛄"
        };
        int msgIndex = 0;
        switch (weather) {
            case "SUN": {
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        0, 0)
                );
            msgIndex = 0;
            }
            break;
            // case 2
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        // Register the weather tower to this flyable
        this.weatherTower = weatherTower;
        // Register (Add) this flyable to the weathertower list
        this.weatherTower.register(this);

        // Write to file: new flyable logged
        // "Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.\n";
        Sytem.out.println("Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.\n");
    }
}
