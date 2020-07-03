package simulator.vehicles;

import simulator.vehicles.Coordinates;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long _idCounter = 0L;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        _idCounter = nextId();
        this.id = _idCounter;
    }

    private long nextId() {
        return (++_idCounter);
    }

}