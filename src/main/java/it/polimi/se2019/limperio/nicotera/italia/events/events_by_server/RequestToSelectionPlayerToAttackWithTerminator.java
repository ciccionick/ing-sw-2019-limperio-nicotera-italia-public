package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.util.ArrayList;

/**
 * Event to request to choose a player that terminator has to attack.
 * @author Pietro L'Imperio.
 */
public class RequestToSelectionPlayerToAttackWithTerminator extends ServerEvent {

    /**
     * The list of nicknames of players among them the terminator has to attack.
     */
    private ArrayList<String> nicknamesOfPlayersAttachable = new ArrayList<>();

    /**
     * Constructor of the class that calls the method that make true the boolean field relative of this kind of class.
     */

    public RequestToSelectionPlayerToAttackWithTerminator() {
        setRequestToSelectionPlayerToAttackWithTerminator();
    }

    public ArrayList<String> getNicknamesOfPlayersAttachable() {
        return nicknamesOfPlayersAttachable;
    }

    public void setNicknamesOfPlayersAttachable(ArrayList<Player> playersAttachable) {
        for (Player player : playersAttachable) {
            nicknamesOfPlayersAttachable.add(player.getNickname());
        }
    }

}
