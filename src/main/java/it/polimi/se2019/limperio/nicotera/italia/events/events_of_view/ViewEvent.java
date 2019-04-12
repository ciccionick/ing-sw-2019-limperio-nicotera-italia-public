package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.view.View;


public class ViewEvent {

    private Player player;

    private View view;

    public View getView() {
        return view;
    }

    public ViewEvent(Player player) {

        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setView(View view) {
        this.view = view;
    }
}
