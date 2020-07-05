package simulator.vehicles;

import simulator.*;
import simulator.interfaces.Flyable;
import simulator.vehicles.Baloon;
import simulator.vehicles.Helicopter;
import simulator.vehicles.JetPlane;
import simulator.vehicles.Coordinates;

public abstract class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coords = new Coordinates(longitude, latitude, height);

        if (type.equalsIgnoreCase("Helicopter")){
            return new Helicopter(name, coords);
        }
        else if (type.equalsIgnoreCase("Baloon")){
            return new Baloon(name, coords);
        }
        else if (type.equalsIgnoreCase("JetPlane")){
            return new JetPlane(name, coords);
        }
        return (null);
    }
}