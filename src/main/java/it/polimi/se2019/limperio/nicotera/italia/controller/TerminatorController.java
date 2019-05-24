package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.GenerationTerminatorEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

class TerminatorController {
    private Controller controller;
    private Game game;

     TerminatorController(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
    }


     void handleSpawnOfTerminator(ClientEvent message) {
         int row = ((GenerationTerminatorEvent)message).getRow();
         int column = ((GenerationTerminatorEvent)message).getColumn();
         Square square = game.getBoard().getMap().getMatrixOfSquares()[row][column];
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         terminator.setPositionOnTheMap(square);
         MapEvent mapEvent = new MapEvent();
         mapEvent.setNicknames(game.getListOfNickname());
         mapEvent.setNicknameInvolved("terminator");
         mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
         mapEvent.setMessageForOthers("Terminator has been generated in the spawn square of the " + square.getColor().toString() + " color!");
         game.notify(mapEvent);

         if(game.getRound()==1){
             controller.getPowerUpController().sendRequestToDiscardPowerUpCardToBeGenerate(message.getNickname());
         }
    }
}
