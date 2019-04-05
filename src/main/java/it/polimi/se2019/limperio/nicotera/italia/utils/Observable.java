package it.polimi.se2019.limperio.nicotera.italia.utils;

import java.util.ArrayList;

public abstract class  Observable <T> {
    ArrayList<Observer <T>> observers;
    public abstract void register(Observer<T> observer);
    public abstract void deregister(Observer<T> observer);
    public abstract void notify(T message);

}
