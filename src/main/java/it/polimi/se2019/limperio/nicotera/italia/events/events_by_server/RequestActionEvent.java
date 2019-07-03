package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event to request to a player to choose an action to do showing what he can really do.
 * @author Pietro L'Imperio
 */
public class RequestActionEvent extends ServerEvent {

    /**
     * It's true if the player can run, false otherwise.
     */
    private boolean canRun = false;
    /**
     * It's true if the player can catch, false otherwise.
     */
    private boolean canCatch = false;
    /**
     * It's true if the player can shoot, false otherwise.
     */
    private boolean canShoot = false;
    /**
     * It's true if the player can use Newton, false otherwise.
     */
    private boolean canUseNewton = false;
    /**
     * It's true if the player can use teleporter, false otherwise.
     */
    private boolean canUseTeleporter = false;
    /**
     * It's true if the player has to do a terminator action before to end his turn, false otherwise.
     */
    private boolean hasToDoTerminatorAction = false;
    /**
     * The number of the current action the player has to do.
     */
    private int numOfAction;

    /**
     * Constructor that sets true the boolean field relative of this event.
     */
    public RequestActionEvent() {
        setRequestActionEvent();
    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public boolean isCanUseNewton() {
        return canUseNewton;
    }

    public void setCanUseNewton(boolean canUseNewton) {
        this.canUseNewton = canUseNewton;
    }

    public boolean isCanUseTeleporter() {
        return canUseTeleporter;
    }

    public void setCanUseTeleporter(boolean canUseTeleporter) {
        this.canUseTeleporter = canUseTeleporter;
    }

    public boolean isHasToDoTerminatorAction() {
        return hasToDoTerminatorAction;
    }

    public void setHasToDoTerminatorAction(boolean hasToDoTerminatorAction) {
        this.hasToDoTerminatorAction = hasToDoTerminatorAction;
    }

    public boolean isCanRun() {
        return canRun;
    }

    public void setCanRun(boolean canRun) {
        this.canRun = canRun;
    }

    public boolean isCanCatch() {
        return canCatch;
    }

    public void setCanCatch(boolean canCatch) {
        this.canCatch = canCatch;
    }

    @Override
    public int getNumOfAction() {
        return numOfAction;
    }

    @Override
    public void setNumOfAction(int numOfAction) {
        this.numOfAction = numOfAction;
    }


}
