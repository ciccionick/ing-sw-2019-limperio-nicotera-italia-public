package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated when the player has selected a square.
 * @author Pietro L'Imperio.
 */
public class SelectionSquare extends ClientEvent {

    /**
     * Its' true if the selection is made to use teleporter, false otherwise.
     */
    private boolean isSelectionSquareToUseTeleporter;
    /**
     * It's true if the selection is made to use Newton, false otherwise.
     */
    private boolean isSelectionSquareToUseNewton;
    /**
     * It's true if the selection is concerning the movement of the terminator, false otherwise.
     */
    private boolean isMoveTerminatorEvent;
    /**
     * It's true if the event is the movement made before to do a catch action, false otherwise.
     */
    private boolean isCatchEvent;
    /**
     * It's true if the selection is concerning one of the effect used by the player during the shoot action.
     */
    private boolean isSelectionSquareForShootAction;
    /**
     * It's true if the selection is made by to spawn terminator.
     */
    private boolean isGenerationTerminatorEvent;
    /**
     * It's true if the selection is made to complete the run action, false otherwise.
     */
    private boolean isRunEvent;
    /**
     * It's true if the selection is made to do a movement before to shoot when this is allowed.
     */
    private boolean isBeforeToShoot;

    /**
     * The row of the square selected.
     */
    private int row;
    /**
     * The column of the square selected.
     */
    private int column;

    /**
     * Constructor of the class where the message for the server, nickname of the player involved and row and column of a selected square are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public SelectionSquare(String message, String nickname, int row, int column) {
        super(message, nickname);
        setSelectionSquare();
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isSelectionSquareToUseTeleporter() {
        return isSelectionSquareToUseTeleporter;
    }

    public void setSelectionSquareToUseTeleporter(boolean selectionSquareToUseTeleporter) {
        isSelectionSquareToUseTeleporter = selectionSquareToUseTeleporter;
    }

    public boolean isSelectionSquareToUseNewton() {
        return isSelectionSquareToUseNewton;
    }

    public void setSelectionSquareToUseNewton(boolean selectionSquareToUseNewton) {
        isSelectionSquareToUseNewton = selectionSquareToUseNewton;
    }

    public boolean isMoveTerminatorEvent() {
        return isMoveTerminatorEvent;
    }

    public void setMoveTerminatorEvent(boolean moveTerminatorEvent) {
        isMoveTerminatorEvent = moveTerminatorEvent;
    }

    public boolean isCatchEvent() {
        return isCatchEvent;
    }

    public void setCatchEvent(boolean catchEvent) {
        isCatchEvent = catchEvent;
    }

    public boolean isSelectionSquareForShootAction() {
        return isSelectionSquareForShootAction;
    }

    public void setSelectionSquareForShootAction(boolean selectionSquareForShootAction) {
        isSelectionSquareForShootAction = selectionSquareForShootAction;
    }

    public boolean isGenerationTerminatorEvent() {
        return isGenerationTerminatorEvent;
    }

    public void setGenerationTerminatorEvent(boolean generationTerminatorEvent) {
        isGenerationTerminatorEvent = generationTerminatorEvent;
    }

    public boolean isRunEvent() {
        return isRunEvent;
    }

    public void setRunEvent(boolean runEvent) {
        isRunEvent = runEvent;
    }

    public boolean isBeforeToShoot() {
        return isBeforeToShoot;
    }

    public void setBeforeToShoot(boolean beforeToShoot) {
        isBeforeToShoot = beforeToShoot;
    }
}
