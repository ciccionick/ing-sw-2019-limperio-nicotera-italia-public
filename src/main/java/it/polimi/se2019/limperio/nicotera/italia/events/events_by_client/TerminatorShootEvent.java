package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class TerminatorShootEvent extends ClientEvent {
    private String nicknamePlayerToAttack;
    public TerminatorShootEvent(String message, String nickname) {
        super(message, nickname);
        setTerminatorShootEvent();
    }

    public String getNicknamePlayerToAttack() {
        return nicknamePlayerToAttack;
    }

    public void setNicknamePlayerToAttack(String nicknamePlayerToAttack) {
        this.nicknamePlayerToAttack = nicknamePlayerToAttack;
    }
}
