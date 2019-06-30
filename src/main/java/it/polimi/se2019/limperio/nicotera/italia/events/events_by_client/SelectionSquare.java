package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class SelectionSquare extends ClientEvent {

    private boolean isSelectionSquareToUseTeleporter;
    private boolean isSelectionSquareToUseNewton;
    private boolean isMoveTerminatorEvent;
    private boolean isCatchEvent;
    private boolean isSelectionSquareForShootAction;
    private boolean isGenerationTerminatorEvent;
    private boolean isRunEvent;
    private boolean isBeforeToShoot;

    private int row;
    private int column;

    public SelectionSquare(String message, String nickname, int row, int column) {
        super(message, nickname);
        setSelectionSquare(true);
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
