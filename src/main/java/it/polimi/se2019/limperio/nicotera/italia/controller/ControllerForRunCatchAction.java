package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.ViewEvent;

public class ControllerForRunCatchAction extends Controller {

    public ControllerForRunCatchAction(Game game) {
        super(game);
    }

    @Override
    public void check(ViewEvent message) /*throws IllegalChooseByUser */ {
        if(checkForRunCatchAction(message)){
            /*chiamata a metodi del model*/
        }
        else
            /*throw new IllegalChooseByUser()*/;

    }

    @Override
    public void update(ViewEvent message) {
        check(message);
    }


    private boolean checkForRunCatchAction(ViewEvent message){
        return true;
    }
}
