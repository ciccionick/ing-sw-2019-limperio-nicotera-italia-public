package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

public class RequestToUseWeaponCard extends ClientEvent {
    private ServerEvent.AliasCard weaponWantUse;
    public RequestToUseWeaponCard(String message, String nickname) {
        super(message, nickname);
        setRequestToUseWeaponCard(true);
    }

    public ServerEvent.AliasCard getWeaponWantUse() {
        return weaponWantUse;
    }

    public void setWeaponWantUse(ServerEvent.AliasCard weaponWantUse) {
        this.weaponWantUse = weaponWantUse;
    }
}
