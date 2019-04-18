package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

public class UpdateOfPlayerStatus extends ModelEvent {


    public UpdateOfPlayerStatus(String message, String nickname) {
        super(message, nickname);
    }
}