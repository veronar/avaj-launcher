package simulator;

import simulator.Tower;
import simulator.vehicles.Coordinates;
import simulator.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    };

    public void changeWeather() {
        this.conditionsChanged();
    };
}