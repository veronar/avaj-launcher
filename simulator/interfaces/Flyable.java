package simulator.interfaces.flyable;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weathertower);
}