package simulator.interfaces;

import simulator.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weathertower);
}