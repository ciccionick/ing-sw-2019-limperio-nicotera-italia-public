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

    @Override
    public boolean isSelectionSquareToUseTeleporter() {
        return isSelectionSquareToUseTeleporter;
    }

    @Override
    public void setSelectionSquareToUseTeleporter(boolean selectionSquareToUseTeleporter) {
        isSelectionSquareToUseTeleporter = selectionSquareToUseTeleporter;
    }

    @Override
    public boolean isSelectionSquareToUseNewton() {
        return isSelectionSquareToUseNewton;
    }

    @Override
    public void setSelectionSquareToUseNewton(boolean selectionSquareToUseNewton) {
        isSelectionSquareToUseNewton = selectionSquareToUseNewton;
    }

    @Override
    public boolean isMoveTerminatorEvent() {
        return isMoveTerminatorEvent;
    }

    @Override
    public void setMoveTerminatorEvent(boolean moveTerminatorEvent) {
        isMoveTerminatorEvent = moveTerminatorEvent;
    }

    @Override
    public boolean isCatchEvent() {
        return isCatchEvent;
    }

    @Override
    public void setCatchEvent(boolean catchEvent) {
        isCatchEvent = catchEvent;
    }

    @Override
    public boolean isSelectionSquareForShootAction() {
        return isSelectionSquareForShootAction;
    }

    @Override
    public void setSelectionSquareForShootAction(boolean selectionSquareForShootAction) {
        isSelectionSquareForShootAction = selectionSquareForShootAction;
    }

    @Override
    public boolean isGenerationTerminatorEvent() {
        return isGenerationTerminatorEvent;
    }

    @Override
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
