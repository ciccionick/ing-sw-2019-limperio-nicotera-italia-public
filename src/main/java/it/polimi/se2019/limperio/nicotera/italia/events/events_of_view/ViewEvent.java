package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import java.io.Serializable;


public class ViewEvent implements Serializable {

    private Player player;

    private RemoteView view;

    public RemoteView getView() {
        return view;
    }

    public ViewEvent(Player player) {

        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setView(RemoteView view) {
        this.view = view;
    }
}
