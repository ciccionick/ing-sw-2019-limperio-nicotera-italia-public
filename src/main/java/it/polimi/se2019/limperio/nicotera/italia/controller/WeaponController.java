package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;

/**
 * This class checks the right use of a weapon. It collaborates with ShootController.
 */
public class WeaponController {

    private final Game game;

    public WeaponController(Game game) {
        this.game = game;
    }
}
