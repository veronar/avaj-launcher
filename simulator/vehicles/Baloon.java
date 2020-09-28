package simulator.vehicles;

import simulator.io.AvajWriter;
import simulator.WeatherTower;
import simulator.vehicles.Coordinates;
import simulator.vehicles.Aircraft;
import simulator.interfaces.Flyable;

import java.awt.*;


public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	// Constructor
	public Baloon(String name, Coordinates coordinates) {
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
				this.coordinates = new Coordinates(newLong + 2, newLat, newHeight + 4);
			}
			break;

			case "RAIN": {
				this.coordinates = new Coordinates(newLong, newLat, newHeight - 5);
				msgIndex = 1;
			}
			break;

			case "FOG": {
				this.coordinates = new Coordinates(newLong, newLat, newHeight - 3);
				msgIndex = 2;
			}
			break;

			case "SNOW": {
				this.coordinates = new Coordinates(newLong, newLat, newHeight - 15);
				msgIndex = 3;
			}
			break;

			default:
				break;
		}


		new AvajWriter("Baloon#" + (this.name) + "(" + (this.id) + ") " + msg[msgIndex]);
		// System.out.println("Baloon#" + (this.name) + "(" + (this.id) + ") " + msg[msgIndex]);

		newHeight = this.coordinates.getHeight();
		if (newHeight <= 0){
			this.weatherTower.unregister(this);
			new AvajWriter("Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " unregistered from weather tower.");
			// System.out.println("Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " unregistered from weather tower.");
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		// Register the weather tower to this flyable
		this.weatherTower = weatherTower;
		// Register (Add) this flyable to the weathertower list
		this.weatherTower.register(this);

		// Write to file: new flyable logged
		new AvajWriter("Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.");
//        System.out.println("Tower says: Baloon#" + (this.name) + "(" + (this.id) + ")" + " registered to weather tower.");
	}
}
