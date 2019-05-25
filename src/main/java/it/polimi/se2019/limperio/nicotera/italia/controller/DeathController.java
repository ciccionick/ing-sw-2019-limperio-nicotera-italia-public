package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

 class DeathController {

    private Game game;
    private Controller controller;

     DeathController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }

     void handleDeath(Player playerToAttack) {

    }
}
