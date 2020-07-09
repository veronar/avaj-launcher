package simulator;

import simulator.interfaces.Flyable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Tower {
    private List<Flyable> observer = new CopyOnWriteArrayList<Flyable>();

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
//        while (i < len - 2) {
//            Flyable fly = observer.get(i);
//            fly.updateConditions();
//            i++;
//        }

        for(Flyable flyable : observer) {
            flyable.updateConditions();
        }
    }
}