package simulator;

import simulator.interfaces.Flyable;
import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observer = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        if (observer.contains(flyable)) {
            return;
        }
        this.observer.add(flyable);
    }

    public void unregister(Flyable flyable) {
        this.observer.remove(flyable);
    }

    protected void conditionsChanged() {
//        int i = 0;
//        int len = observer.size();
//
//        while (i < len) {
//            Flyable fly = observer.get(i);
//            fly.updateConditions();
//            i++;
//        }

        for(Flyable flyable : observer) {
            flyable.updateConditions();
        }
    }
}