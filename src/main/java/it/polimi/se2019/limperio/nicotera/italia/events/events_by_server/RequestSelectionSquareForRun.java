package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

public class RequestSelectionSquareForRun extends ServerEvent {

    ArrayList<Square> squaresReachableWithRunAction = new ArrayList<>();

    public RequestSelectionSquareForRun(String message){
        super(message);
        setRequestSelectionSquareForRun(true);
    }

    public void setSquaresReachableWithRunAction(ArrayList<Square> squaresReachableWithRunAction) {
        this.squaresReachableWithRunAction.addAll(squaresReachableWithRunAction);
    }

    public ArrayList<Square> getSquaresReachableWithRunAction() {
        return squaresReachableWithRunAction;
    }
}
