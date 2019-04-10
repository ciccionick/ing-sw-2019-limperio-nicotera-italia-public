package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.ViewEvent;

public class ControllerForUseWeapon extends Controller {

    public ControllerForUseWeapon(Game game) {
        super(game);
    }

    @Override
    public void update(ViewEvent message) {
        check(message);
    }

    @Override
    public void check(ViewEvent message) throws IllegalArgumentException {
        switch (message.getClass().getName()) {
            case "ShootAction":
                if(checkForShoot(message)){
                    /*chiamata a metodi del model*/
                }
                else
                    throw new IllegalArgumentException();
            case "ReloadWeapons":
                if(checkForReload(message)) {
                    /*chiamata a metodi del model*/
                }
                else
                    throw new IllegalArgumentException();
        }
    }

    private boolean checkForShoot(ViewEvent message){
        return true;
    }

    private boolean checkForReload(ViewEvent message){
        return true;
    }
}
