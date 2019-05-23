package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;


public class RoundController {
    private Controller controller;
    private final Game game;

    public RoundController(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
    }

     void updateTurn(){
        if(game.getPlayerOfTurn()==game.getPlayers().size()){
            game.setPlayerOfTurn(1);
            game.setRound(game.getRound()+1);
        }
        else
            game.setPlayerOfTurn(game.getPlayerOfTurn()+1);
        game.setNumOfActionOfTheTurn(0);
        if(game.isTerminatorModeActive()) {
            game.setHasToDoTerminatorAction(true);

            if(game.getNumOfMaxActionForTurn()==2)
                game.setNumOfMaxActionForTurn(3);
        }
        game.getBoard().addWeaponsInSpawnSquare();
        game.getBoard().addAmmoTileInNormalSquare();
        game.sendMapEvent();
    }
}
