package it.polimi.se2019.limperio.nicotera.italia.utils;

import java.util.ArrayList;

public class  Observable <T> {
    private final ArrayList<Observer<T>> observers = new ArrayList<>();

    public void register(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void deregister(Observer<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    public void notify(T message){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.update(message);
            }
        }
    }
}
