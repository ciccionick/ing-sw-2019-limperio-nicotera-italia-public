package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.SelectionViewForSquareWhereCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.RequestToCatchByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

class CatchController {
    private final Game game;
    private Controller controller;
    CatchController(Game game, Controller controller){
        this.game=game;
        this.controller=controller;
    }

    void replyToRequestToCatch(RequestToCatchByPlayer event){
        ArrayList<Square> squareAvailableToCatch = findSquareWherePlayerCanCatch(controller.findPlayerWithThisNickname(event.getNickname()));
        SelectionViewForSquareWhereCatch newSelectionEvent = new SelectionViewForSquareWhereCatch("Scegli in quale fra questi quadrati vuoi raccogliere");
        newSelectionEvent.getNickname().add(event.getNickname());
        newSelectionEvent.setSquaresReachableForCatch(squareAvailableToCatch);
        event.getMyVirtualView().update(newSelectionEvent);

    }



    private ArrayList<Square> findSquareWherePlayerCanCatch(Player player){
        ArrayList<Square> listOfSquareReachable = new ArrayList<>();
        SpawnSquare spawnSquare;
        NormalSquare normalSquare;
        if(player.getPositionOnTheMap().isSpawn()) {
            spawnSquare = (SpawnSquare) player.getPositionOnTheMap();
            if(!spawnSquare.getWeaponCards().isEmpty())
                listOfSquareReachable.add(player.getPositionOnTheMap());
        }
        else{
            normalSquare= (NormalSquare) player.getPositionOnTheMap();
            if(normalSquare.getAmmoTile()!=null)
                listOfSquareReachable.add(player.getPositionOnTheMap());
        }
        for(Square square : player.getPositionOnTheMap().getAdjSquares()){
            if(square!=null) {
                if (square.isSpawn()) {
                    spawnSquare = (SpawnSquare) square;
                    if (!spawnSquare.getWeaponCards().isEmpty())
                        listOfSquareReachable.add(square);
                } else {
                    normalSquare = (NormalSquare) square;
                    if (normalSquare.getAmmoTile() != null)
                        listOfSquareReachable.add(normalSquare);
                }
            }
        }
        return listOfSquareReachable;

    }
}
