package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

/**
 * Event generated to request to a player to choose a square to complete an action.
 * @author Pietro L'Imperio.
 */
public class RequestSelectionSquareForAction extends ServerEvent {

    /**
     * List of square reachable or selectable.
     */
    private ArrayList<Square> squaresReachable = new ArrayList<>();
    /**
     * It's true if the request to choose a square is to complete the run action, false otherwise.
     */
    private boolean isSelectionForRun = false;
    /**
     * It's true if the request to choose a square is to know where the player wants to catch, false otherwise.
     */
    private boolean isSelectionForCatch = false;
    /**
     * It's true if the request to choose a square is to decide where the terminator has to be spawn, false otherwise.
     */
    private boolean isSelectionForSpawnTerminator = false;
    /**
     * It's true if the request to choose a square is to move the terminator during his action, false otherwise.
     */
    private boolean isSelectionForMoveTerminator = false;
    /**
     * It's true if the request to choose a square is to decide where move using teleporter, false otherwise.
     */
    private boolean isSelectionForTeleporter = false;
    /**
     * It's true if the request to choose a square is to decide where move a player using Newton, false otherwise.
     */
    private boolean isSelectionForNewton = false;
    /**
     * It's true if the request to choose a square is to decide where move before shoot during frenzy turn or when player has more than six damage, false otherwise.
     */
    private boolean isBeforeToShoot = false;
    /**
     * It's true if the request to choose a square is relative an effect during a shoot action by player, false otherwise.
     */
    private boolean isForActionShoot = false;

    /**
     * Constructor of the class that initialize the message for the player involved and the boolean field relative to this kind of event.
     * @param message Message for the involved player
     */
    public RequestSelectionSquareForAction(String message){
        super(message);
        setRequestSelectionSquareForAction();
    }

    public void setSquaresReachable(ArrayList<Square> squaresReachable) {
        this.squaresReachable.addAll(squaresReachable);
    }

    public ArrayList<Square> getSquaresReachable() {
        return squaresReachable;
    }

    public boolean isForActionShoot() {
        return isForActionShoot;
    }

    public void setForActionShoot() {
        isForActionShoot = true;
    }

    public boolean isBeforeToShoot() {
        return isBeforeToShoot;
    }

    public void setBeforeToShoot( ) {
        isBeforeToShoot = true;
    }

    public boolean isSelectionForMoveTerminator() {
        return isSelectionForMoveTerminator;
    }

    public void setSelectionForMoveTerminator() {
        isSelectionForMoveTerminator = true;
    }

    public boolean isSelectionForSpawnTerminator() {
        return isSelectionForSpawnTerminator;
    }

    public void setSelectionForSpawnTerminator() {
        isSelectionForSpawnTerminator = true;
    }

    public boolean isSelectionForRun() {
        return isSelectionForRun;
    }

    public void setSelectionForRun() {
        isSelectionForRun = true;
    }

    public boolean isSelectionForCatch() {
        return isSelectionForCatch;
    }

    public void setSelectionForCatch() {
        isSelectionForCatch = true;
    }

    public boolean isSelectionForTeleporter() {
        return isSelectionForTeleporter;
    }

    public void setSelectionForTeleporter() {
        isSelectionForTeleporter = true;
    }

    public boolean isSelectionForNewton() {
        return isSelectionForNewton;
    }

    public void setSelectionForNewton() {
        isSelectionForNewton = true;
    }
}
