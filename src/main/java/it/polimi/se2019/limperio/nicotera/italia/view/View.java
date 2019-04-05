package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.model.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.ViewEvent;

public class View extends Observable<ViewEvent> implements Observer<ModelEvent> {

    @Override
    public void update(ModelEvent message) {

    }


    @Override
    public void register(Observer<ViewEvent> observer) {

    }

    @Override
    public void deregister(Observer<ViewEvent> observer) {

    }

    @Override
    public void notify(ViewEvent message) {

    }
}
