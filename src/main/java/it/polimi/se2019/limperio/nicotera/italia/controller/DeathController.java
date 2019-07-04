package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.KillshotTrackEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

/**
 * Controller that handles the deaths of the players.
 * @author Pietro L'Imperio
 */
class DeathController {

    /**
     * Reference of the game.
     */
    private Game game;
    /**
     * Reference of the controller.
     */
    private Controller controller;

    /**
     * Constructor that initialize game and controller references.
     */
     DeathController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }

    /**
     * Handles the death of a player (deadPlayer) by the killerPlayer. Adds tokens of death to the frenzy or normal killshot track according with the phase of the game.
     * Increments the number of killing made by the killerPlayer during the turn and calls the end of the game if is not anticipated frenzy and the last skull has been removed.
     * @param killerPlayer Player that kills deadPlayer.
     * @param deadPlayer Player killed by killerPlayer.
     */
     void handleDeath(Player killerPlayer, Player deadPlayer) {
         String messageForInvolved;
         String messageForOthers;
         if(deadPlayer.getPlayerBoard().getDamages().size()==11){
             messageForInvolved = "You have been killed by " + killerPlayer.getNickname();
             messageForOthers = deadPlayer.getNickname() + " has been killed by " + killerPlayer.getNickname();
         }
         else{
             messageForInvolved = "You have been overkilled by " + killerPlayer.getNickname();
             messageForOthers = deadPlayer.getNickname() + " has overkilled by " + killerPlayer.getNickname();
         }
         PlayerBoardEvent pbEvent = new PlayerBoardEvent();
         pbEvent.setNicknameInvolved(deadPlayer.getNickname());
         pbEvent.setNicknames(game.getListOfNickname());
         pbEvent.setPlayerBoard(deadPlayer.getPlayerBoard());
         pbEvent.setMessageForInvolved(messageForInvolved);
         pbEvent.setMessageForOthers(messageForOthers);
         pbEvent.setNotifyAboutActionDone();
         game.notify(pbEvent);

         Player playerOfTheTurn = game.getPlayers().get(game.getPlayerOfTurn()-1);
         playerOfTheTurn.setNumOfKillDoneInTheTurn(playerOfTheTurn.getNumOfKillDoneInTheTurn()+1);

         ColorOfDeathToken colorOfKiller = convertColorOfFigureInColorOfToken(killerPlayer.getColorOfFigure());
         deadPlayer.setDead(true);

         if(game.isInFrenzy()) {
             game.getBoard().getKillShotTrack().getTokenOfFrenzyMode().add(colorOfKiller);
             if(deadPlayer.getPlayerBoard().getDamages().size()==12)
                 game.getBoard().getKillShotTrack().getTokenOfFrenzyMode().add(colorOfKiller);
         }
         else{
             if(deadPlayer.getPlayerBoard().getDamages().size()==11){
                     game.getBoard().getKillShotTrack().getTokensOfDeath().get(getFirstSkullPosition()).set(0, colorOfKiller);
             }
             else {
                 killerPlayer.assignMarks(deadPlayer.getColorOfFigure(), 1);
                 if(deadPlayer.isDirectlyOverkilled())
                     game.getBoard().getKillShotTrack().getTokensOfDeath().get(getFirstSkullPosition()).set(0, colorOfKiller);
                 game.getBoard().getKillShotTrack().getTokensOfDeath().get(getFirstSkullPosition()-1).add(colorOfKiller);
                 deadPlayer.setDirectlyOverkilled(false);
             }
         }
         KillshotTrackEvent killshotTrackEvent = new KillshotTrackEvent("", game.getBoard().getKillShotTrack());
         killshotTrackEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
         killshotTrackEvent.setNicknames(game.getListOfNickname());
         game.notify(killshotTrackEvent);

         if(!game.isAnticipatedFrenzy() && !game.getBoard().getKillShotTrack().getTokensOfDeath().get(game.getNumOfSkullToRemoveToPassToFrenzy()-1).get(0).equals(ColorOfDeathToken.SKULL)) {
             ArrayList<Player> deadPlayers = new ArrayList<>();
             for(Player player : game.getPlayers()){
                 if (player.isDead())
                     deadPlayers.add(player);
             }
             controller.getRoundController().countScoreForDeaths(deadPlayers);
             controller.getRoundController().handleEndOfGame(false);
         }
    }

    /**
     * Gets the position of the first skull in the killshot track.
     * @return The index of the first position on the killshot track occupied by a skull.
     */
    private int getFirstSkullPosition() {
         for(int i=0;i<game.getBoard().getKillShotTrack().getTokensOfDeath().size();i++){
             if(game.getBoard().getKillShotTrack().getTokensOfDeath().get(i).get(0).toString().equals("SKULL"))
                 return i;
         }
         throw new IllegalArgumentException();
    }

    /**
     * Convert the color of figure to color of death token to put the correct token on the killshot track according with the color of figure of the killerPlayer
     * @param colorOfFigure The color of figure of the killer player.
     * @return The color of death token obtained by the color of the figure of the killer.
     */
    private ColorOfDeathToken convertColorOfFigureInColorOfToken(ColorOfFigure_Square colorOfFigure) {
         for(ColorOfDeathToken colorOfToken : ColorOfDeathToken.values()){
             if(colorOfToken.toString().equals(colorOfFigure.toString()))
                 return colorOfToken;
         }
         throw new IllegalArgumentException();
    }


 }
