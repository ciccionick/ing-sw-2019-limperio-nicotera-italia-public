package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;

/**
 * This class handles the run action by a player and checks where it can move to.
 */
public class RunController {

    private final Game game;

    public RunController(Game game) {
        this.game = game;
    }
}
