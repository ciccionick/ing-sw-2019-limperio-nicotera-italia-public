package it.polimi.se2019.limperio.nicotera.italia.utils;

import java.util.ArrayList;

/**
 * This class is extended by every class wants to implement tha MVC pattern and wants to be observable from someone else on some kind of object.
 * @author Giuseppe Italia
 * @param <T> Parametric type of the object on which the class that extend this is observable on.
 */
public class  Observable <T> {
    private final ArrayList<Observer<T>> observers = new ArrayList<>();

    /**
     * Add to the list of observers an instance of a class that has to observe on the object <T>.
     * @param observer The reference of the observer to add to the list.
     */
    public void register(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * Remove from the list an instance of a class that was observing on a object <T>.
     * @param observer The reference of the observer that has to be removed.
     */
    public void deregister(Observer<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    /**
     * Calls the update method of all the classes in the list of observers.
     * @param message The message to send to the update method of a observer class.
     */
    public void notify(T message){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.update(message);
            }
        }
    }
}
