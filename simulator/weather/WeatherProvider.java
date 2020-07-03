package simulator.weather;

import simulator.vehicles.Coordinates;
import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = { "SUN", "FOG", "SNOW", "RAIN" };

    // Constructor
    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return (this.weatherProvider);
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int rand = new Random().nextInt(4);
        return (weather[rand]);
    }
}