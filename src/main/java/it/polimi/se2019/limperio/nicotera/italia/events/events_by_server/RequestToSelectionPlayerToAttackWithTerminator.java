package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.util.ArrayList;

public class RequestToSelectionPlayerToAttackWithTerminator extends ServerEvent {

    private ArrayList<String> nicknamesOfPlayersAttachable = new ArrayList<>();

    public RequestToSelectionPlayerToAttackWithTerminator() {
        setRequestToSelectionPlayerToAttackWithTerminator(true);
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
