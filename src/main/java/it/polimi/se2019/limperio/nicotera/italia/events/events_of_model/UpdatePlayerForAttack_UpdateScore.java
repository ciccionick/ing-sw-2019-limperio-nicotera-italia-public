package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.util.ArrayList;

public class UpdatePlayerForAttack_UpdateScore extends ModelEvent{


    public UpdatePlayerForAttack_UpdateScore(String message, String nickname) {
        super(message, nickname);
    }
}
