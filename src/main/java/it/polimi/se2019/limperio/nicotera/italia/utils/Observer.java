package it.polimi.se2019.limperio.nicotera.italia.utils;

/**
 * Interface that permits to implement the update method called by the notify method of the class that extend Observable.
 * @param <T> The parametric type of the class of the object interested in the observation.
 */
public interface Observer <T> {
    /**
     * The method that is called by the notify method of the Observable classes.
     * @param message The message sent by the notify method of the observable classes.
     */
    void update(T message);
}
