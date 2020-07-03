package simulator.tower;

import simulator.interfaces.*;

public abstract class Tower {
    private ArrayList<Flyable> observer = new ArrayList<Flyable>();

    public void register(Flyable flyable) {

    }

    public void unregister(Flyable flyable) {

    }

    protected void conditionsChanged() {

    }
}