package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated when the player decides to do the shoot action of the terminator action of his turn.
 * @author Pietro L'Imperio.
 */
public class TerminatorShootEvent extends ClientEvent {
    /**
     * The nickname of the player that terminator has to attack.
     */
    private String nicknamePlayerToAttack;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
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
