package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import java.io.Serializable;

/**
 * Event to reply to the server after have received some {@link it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent}.
 * <p>
 *     Has attributes for all of the information that the server can ask during the initialization phase,
 *     even some that maybe a player will not specify (ex. map, frenzy, terminator if he will not be the first).
 * </p>
 * @author Pietro L'Imperio
 */
public class AnswerInitializationEvent extends ClientEvent implements Serializable {
    static final long serialVersionUID = 420000015;
    /**
     * Nickname chosen by the client
     */
    private String nickname;
    /**
     * Color chosen by the client
     */
    private String color;
    /**
     * Type of map chosen by the client
     */
    private  int map;
    /**
     * True if the first client chose to play with frenzy mode at the end, false otherwise
     */
    private boolean frenzy;
    /**
     * True if the first client chose to play in terminator mode, false otherwise
     */
    private boolean terminator;


    /**
     * Constructor that initializes nickname, color, map, frenzy and terminator according with the request received by server.
     */
    public AnswerInitializationEvent(String nickname, String color, int map, boolean frenzy, boolean terminator) {
        this.nickname = nickname;
        this.color = color;
        this.map = map;
        this.frenzy = frenzy;
        this.terminator= terminator;
    }

    public String getNickname() {
        return nickname;
    }

    public String getColor() {
        return color;
    }

    public int getMap() {
        return map;
    }

    public boolean isFrenzy() {
        return frenzy;
    }

    public boolean isTerminator() {
        return terminator;
    }
}
