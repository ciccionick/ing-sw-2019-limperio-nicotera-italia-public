package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.ViewEvent;

public class ControllerForEndTurn extends Controller{

    public ControllerForEndTurn(Game game) {
        super(game);
    }

    @Override
    public void update(ViewEvent message) {
        this.check(message);
    }

    @Override
    public void check(ViewEvent message) /*throws IllegalChooseByUser */ {
        if(checkForEndTurn(message)) {
            /*chiamata a metodi del model*/
        }
        else
            /*throw new IllegalChooseByUser()*/;
    }

    private boolean checkForEndTurn(ViewEvent message){
        return true;
    }
}
