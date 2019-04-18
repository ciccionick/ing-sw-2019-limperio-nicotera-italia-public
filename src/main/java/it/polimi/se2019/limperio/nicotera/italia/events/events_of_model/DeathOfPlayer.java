package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.util.ArrayList;

public class DeathOfPlayer extends ModelEvent {


    public DeathOfPlayer(String message, String nickname) {
        super(message, nickname);
    }
}
