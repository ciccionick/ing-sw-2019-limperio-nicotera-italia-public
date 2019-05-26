package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.KillshotTrackEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

class DeathController {

    private Game game;
    private Controller controller;

     DeathController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }

     void handleDeath(Player killerPlayer, Player deadPlayer) {
         Player playerOfTheTurn = game.getPlayers().get(game.getPlayerOfTurn()-1);
         ColorOfDeathToken colorOfKiller = convertColorOfFigureInColorOfToken(killerPlayer.getColorOfFigure());
         playerOfTheTurn.setNumOfKillDoneInTheTurn(playerOfTheTurn.getNumOfKillDoneInTheTurn()+1);
         deadPlayer.setDeath(true);
         if(game.isInFrenzy()) {
             game.getBoard().getKillShotTrack().getTokenOfFrenzyMode().add(colorOfKiller);
         }
         else{
             if(deadPlayer.getPlayerBoard().getDamages().size()==11){
                 game.getBoard().getKillShotTrack().getTokensOfDeath().get(firstSkullPosition()).set(0, colorOfKiller);
             }
             else {
                 killerPlayer.assignMarks(deadPlayer.getColorOfFigure(), 1);
                 game.getBoard().getKillShotTrack().getTokensOfDeath().get(firstSkullPosition()-1).add(colorOfKiller);
             }
         }
         System.out.println("Prima dell'aggiornamento della kill");
         KillshotTrackEvent killshotTrackEvent = new KillshotTrackEvent("", game.getBoard().getKillShotTrack());
         killshotTrackEvent.setNicknames(game.getListOfNickname());
         game.notify(killshotTrackEvent);

    }

    private int firstSkullPosition() {
         for(int i=0;i<game.getBoard().getKillShotTrack().getTokensOfDeath().size();i++){
             if(game.getBoard().getKillShotTrack().getTokensOfDeath().get(i).get(0).toString().equals("SKULL"))
                 return i;
         }
         throw new IllegalArgumentException();
    }

    private ColorOfDeathToken convertColorOfFigureInColorOfToken(ColorOfFigure_Square colorOfFigure) {
         for(ColorOfDeathToken colorOfToken : ColorOfDeathToken.values()){
             if(colorOfToken.toString().equals(colorOfFigure.toString()))
                 return colorOfToken;
         }
         throw new IllegalArgumentException();
    }


 }
