package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.*;
import java.util.Map;


public class RoundController {
    private Controller controller;
    private final Game game;
    private ArrayList<Integer> scoreForPlayers = new ArrayList<>();

    public RoundController(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
    }

     void updateTurn() {
         ArrayList<Player> deadPlayers = getPlayersDeadInThisTurn();

         if (isFinishedTheLastFrenzyTurn()) {
             if(!deadPlayers.isEmpty())
                 countScoreForDeaths(deadPlayers);
             handleEndOfGame(false);
             return;
         }
         updatePlayerOfTurn();
         while (!game.getPlayers().get((game.getPlayerOfTurn() - 1)).isConnected()) {
             if(game.getPlayers().get(game.getPlayerOfTurn()-1).getPositionOnTheMap()==null)
                 randomSpawn(game.getPlayers().get(game.getPlayerOfTurn()-1));
             updatePlayerOfTurn();
         }

         game.setNumOfActionOfTheTurn(0);

         if (game.isTerminatorModeActive()) {
             game.setHasToDoTerminatorAction(true);

             if (game.getNumOfMaxActionForTurn() == 2)
                 game.setNumOfMaxActionForTurn(3);
         }
         if(game.isInFrenzy() && game.getPlayers().get(game.getPlayerOfTurn()-1).getPosition()<game.getFirstInFrenzyMode()) {
             game.setNumOfMaxActionForTurn(game.getNumOfMaxActionForTurn() - 1);
         }
         updateDecksAndSquares();


         if (!deadPlayers.isEmpty()) {
             countScoreForDeaths(deadPlayers);
             handleRespawnOfDeadPlayers(deadPlayers);
         }
         game.setPlayerHasToRespawn(null);


     }

      void randomSpawn(Player player){
        Square square;
        if(player.getPlayerBoard().getPowerUpCardsOwned().isEmpty()&&game.getRound()==1) {
            PowerUpCard powerUpCardToSpawn1 = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
            PowerUpCard powerUpCardToSpawn2 = game.getBoard().getPowerUpDeck().getPowerUpCards().get(1);
            game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(1));
             square = controller.getPowerUpController().findSpawnSquareWithThisColor(powerUpCardToSpawn2.getColor());
            player.drawPowerUpCard(powerUpCardToSpawn1);
            powerUpCardToSpawn1.setOwnerOfCard(player);
            powerUpCardToSpawn1.setInTheDeckOfSomePlayer(true);
        }
        else if(game.getRound()==1 && player.getPlayerBoard().getPowerUpCardsOwned().size()==2){
             square = controller.getPowerUpController().findSpawnSquareWithThisColor(player.getPlayerBoard().getPowerUpCardsOwned().get(1).getColor());
             player.getPlayerBoard().getPowerUpCardsOwned().get(1).setInTheDeckOfSomePlayer(false);
            player.getPlayerBoard().getPowerUpCardsOwned().get(1).setOwnerOfCard(null);
            player.getPlayerBoard().getPowerUpCardsOwned().remove(1);
        }
        else
            square = game.getBoard().getMap().getMatrixOfSquares()[1][0];
         player.setPositionOnTheMap(square);
         player.setDead(false);
         player.setHasToBeGenerated(false);
         PlayerBoardEvent pbEvent = new PlayerBoardEvent();
         pbEvent.setNicknameInvolved(player.getNickname());
         pbEvent.setNicknames(game.getListOfNickname());
         pbEvent.setPlayerBoard(player.getPlayerBoard());
         pbEvent.setMessageForOthers(player.getNickname() + " has been generated in " + square.getColor().toString() + " spawn square");
         game.notify(pbEvent);
         MapEvent mapEvent = new MapEvent();
         mapEvent.setNicknames(game.getListOfNickname());
         mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
         game.notify(mapEvent);
     }

    private void handleRespawnOfDeadPlayers(ArrayList<Player> deadPlayers) {
        for(Player deadPlayer : deadPlayers){
            game.setPlayerHasToRespawn(deadPlayer);
            if(deadPlayer.getNickname().equals("terminator"))
                controller.getPowerUpController().sendRequestToChooseSquareForSpawnOfTerminator();
            else if (!deadPlayer.isConnected())
                randomSpawn(deadPlayer);
            else
                controller.sendRequestToDrawPowerUpCard(deadPlayer, 1);
        }
    }

    private void updateDecksAndSquares() {
        if(game.getBoard().getAmmoTiledeck().getAmmoTile().size()<3){
            game.getBoard().getAmmoTiledeck().shuffleDeck();
        }
        if(game.getBoard().getPowerUpDeck().getUsedPowerUpCards().size()<3){
            game.getBoard().getPowerUpDeck().shuffleDeck();
        }
        game.getBoard().addWeaponsInSpawnSquare();
        game.getBoard().addAmmoTileInNormalSquare();
        game.sendMapEvent();
    }

    private void initializeNumOfKillDoneInTheTurn() {
        for(Player player : game.getPlayers()){
            player.setNumOfKillDoneInTheTurn(0);
        }
    }

    private void updatePlayerOfTurn() {
        if (game.getPlayerOfTurn() == game.getPlayers().size() || game.isTerminatorModeActive()&&game.getPlayerOfTurn()+1==game.getPlayers().size()) {
            game.setPlayerOfTurn(1);
            game.setRound(game.getRound() + 1);
        } else
            game.setPlayerOfTurn(game.getPlayerOfTurn() + 1);
    }

    private boolean isFinishedTheLastFrenzyTurn() {
        return game.isInFrenzy()&&game.getPlayerOfTurn()==game.getPlayers().size()&&game.getFirstInFrenzyMode()==1 || game.isInFrenzy()&&game.getPlayerOfTurn()+1==game.getFirstInFrenzyMode();
    }




     void countScoreForDeaths(ArrayList<Player> deadPlayers) {
        initializeScoreForPlayers();
        assignScoreForDoubleKill();
        initializeNumOfKillDoneInTheTurn();

        for (Player player : deadPlayers) {
            countScoreForPlayerDeath(player);
            player.getPlayerBoard().setDamages(new ArrayList<>());
            if(game.isInFrenzy())
                player.getPlayerBoard().setFrenzyBoardPlayer(true);
            PlayerBoardEvent pbEvent = new PlayerBoardEvent();
            pbEvent.setNicknameInvolved(player.getNickname());
            pbEvent.setNicknames(game.getListOfNickname());
            pbEvent.setPlayerBoard(player.getPlayerBoard());
            game.notify(pbEvent);
        }
            sendUpdateOfScore(false, false);

        if(isPassingFromNormalToFrenzyMode() && game.isAnticipatedFrenzy()) {
            game.setInFrenzy(true);
            game.setFirstInFrenzyMode(game.getPlayerOfTurn());
            if(game.getPlayerOfTurn()==1)
                game.setNumOfMaxActionForTurn(game.getNumOfMaxActionForTurn()-1);
            updatePlayerBoardForFrenzyMode();
        }
    }

    private void updatePlayerBoardForFrenzyMode() {
        for (Player player : game.getPlayers()) {
            player.getPlayerBoard().setFrenzyActionBar(true);
            PlayerBoardEvent pbEvent = new PlayerBoardEvent();
            pbEvent.setNicknameInvolved(player.getNickname());
            pbEvent.setNicknames(game.getListOfNickname());
            if (player.getPlayerBoard().getDamages().isEmpty()) {
                player.getPlayerBoard().setFrenzyBoardPlayer(true);
                pbEvent.setFirstFrenzyPlayerBoard(true);
                pbEvent.setMessageForOthers("The player board of " + player.getNickname() + " has passed to frenzy mode");
                pbEvent.setMessageForInvolved("Your player board has passed to frenzy mode");

            }
            pbEvent.setPlayerBoard(player.getPlayerBoard());
            game.notify(pbEvent);
        }
    }



    private void sendUpdateOfScore(boolean finalUpdate, boolean isForDisconnectionOfThirdPlayer) {
        String message;
        if (!finalUpdate)
            message = "Updating score: \n";
        else
            message = "Final ranking: \n";
        if (!finalUpdate) {
            for (int i = 0; i < scoreForPlayers.size(); i++) {
                game.getPlayers().get(i).updateScore(scoreForPlayers.get(i));
                if (scoreForPlayers.get(i) != 0)
                    message = message.concat(game.getPlayers().get(i).getNickname() + ": " + scoreForPlayers.get(i) + " points added\n");
            }
        } else {
            game.getPlayers().sort(new Player.ScoreComparator());
            checkAnyParity();
            for (int i = 0; i < game.getPlayers().size(); i++) {
                message = message.concat(game.getPlayers().get(i).getNickname() + ": " + game.getPlayers().get(i).getScore() + " points\n");
            }

        }

        ServerEvent updateScoreEvent = new ServerEvent();
        updateScoreEvent.setUpdateScoreEvent(true);
        updateScoreEvent.setNicknames(game.getListOfNickname());
        String messageForInvolved = "";
        String messageForOthers =  "";
        if(isForDisconnectionOfThirdPlayer){
            messageForInvolved = "You have remained in two playing, so the match finish here. \n\n";
            messageForOthers = "You have remained in two playing, so the match finish here. \n\n";
        }
        if (finalUpdate) {
            updateScoreEvent.setFinalUpdate(true);
            updateScoreEvent.setNicknameInvolved(game.getPlayers().get(0).getNickname());
            messageForInvolved = messageForInvolved.concat("Congratulations, You have won!\n");
            messageForInvolved = messageForInvolved.concat(message);
            updateScoreEvent.setMessageForInvolved(messageForInvolved);
             messageForOthers = messageForOthers.concat("It's not your lucky day, you have lost!\n").concat(message);
            updateScoreEvent.setMessageForOthers(messageForOthers);

        } else {
            updateScoreEvent.setMessageForOthers(message);
        }
        game.notify(updateScoreEvent);

    }

    private void checkAnyParity() {
        int maxScore = game.getPlayers().get(0).getScore();
        ArrayList<Player> playersWithMaxScore = new ArrayList<>();
        ArrayList<ColorOfFigure_Square> colorOfPlayersWithMaxScore = new ArrayList<>();
        for(Player player : game.getPlayers()){
            if(player.getScore()==maxScore) {
                playersWithMaxScore.add(player);
                colorOfPlayersWithMaxScore.add(player.getColorOfFigure());
            }
        }

        if(playersWithMaxScore.size()!=1){
            for(ColorOfFigure_Square colorOfDamage : convertDamageFromKillshotTrack(game.getBoard().getKillShotTrack().getTokensOfDeath())){
                if(colorOfPlayersWithMaxScore.contains(colorOfDamage)){
                    for (Player player : playersWithMaxScore){
                        if(player.getColorOfFigure().equals(colorOfDamage) && !game.getPlayers().get(0).equals(player)){
                            game.getPlayers().set(game.getPlayers().indexOf(player),game.getPlayers().get(0));
                            game.getPlayers().set(0, player);
                            return;
                        }
                    }

                }
            }
        }
    }


    private boolean isPassingFromNormalToFrenzyMode() {
        return !game.getBoard().getKillShotTrack().getTokensOfDeath().get(game.getNumOfSkullToRemoveToPassToFrenzy()-1).get(0).toString().equals("SKULL")&&!game.isInFrenzy();
    }

    public void handleEndOfGame(boolean isForDisconnectionOfThirdPlayer) {
        game.setGameOver(true);
        initializeScoreForPlayers();
        ArrayList<Integer> scoreForDamageInKillShotTrack = new ArrayList<>();
        scoreForDamageInKillShotTrack.add(8);
        scoreForDamageInKillShotTrack.add(6);
        scoreForDamageInKillShotTrack.add(4);
        scoreForDamageInKillShotTrack.add(2);
        scoreForDamageInKillShotTrack.add(1);
        scoreForDamageInKillShotTrack.add(1);

        countScoreForKillshoTrack(scoreForDamageInKillShotTrack);
        for(Player player : game.getPlayers())
            if(!player.getPlayerBoard().getDamages().isEmpty())
                countScoreForPlayerDeath(player);
        int i=0;
        for(Player player : game.getPlayers()) {
            player.updateScore(scoreForPlayers.get(i));
            i++;
        }

        sendUpdateOfScore(true, isForDisconnectionOfThirdPlayer);
    }

    private void countScoreForKillshoTrack(ArrayList<Integer> scoreForDamageInKillShotTrack) {
        HashMap<ColorOfFigure_Square, Integer> hashMapForDamage = countFrequencyOfDamageInKillShotTrack();
        int indexOfScoreToAssign = 0;
        int scoreToAssign;
        while(!hashMapForDamage.isEmpty()){
            scoreToAssign = scoreForDamageInKillShotTrack.get(indexOfScoreToAssign);
            int max = findMaxFrequency(hashMapForDamage);
            ColorOfFigure_Square colorOfPlayerToAddScore;
            colorOfPlayerToAddScore = findColorWithMaxFrequency(hashMapForDamage,max);
            if(!maxIsUnique(hashMapForDamage,max)){
                ArrayList<ColorOfFigure_Square> colorsWithMaxFrequency = getColorsWithMaximumFrequency(hashMapForDamage,max);
                colorOfPlayerToAddScore = getFirstOfMaximumColor(colorsWithMaxFrequency, convertDamageFromKillshotTrack(game.getBoard().getKillShotTrack().getTokensOfDeath()));
            }
            scoreForPlayers.set(findIndexOfPlayerOfThisColor(colorOfPlayerToAddScore), scoreForPlayers.get(findIndexOfPlayerOfThisColor(colorOfPlayerToAddScore))+scoreToAssign);
            hashMapForDamage.remove(colorOfPlayerToAddScore);
            indexOfScoreToAssign++;
        }
    }

    private HashMap<ColorOfFigure_Square, Integer> countFrequencyOfDamageInKillShotTrack() {
        ArrayList<ArrayList<ColorOfDeathToken>> damageForNormalMode = game.getBoard().getKillShotTrack().getTokensOfDeath();
        ArrayList<ColorOfDeathToken> damageForFrenzyMode = game.getBoard().getKillShotTrack().getTokenOfFrenzyMode();

        ArrayList<ColorOfFigure_Square> damage = convertDamageFromKillshotTrack(damageForNormalMode);

        if(!damageForFrenzyMode.isEmpty()){
            for(ColorOfDeathToken token : damageForFrenzyMode)
                damage.add(convertColorOfTokenInColorOfFigure(token));
        }
        HashMap<ColorOfFigure_Square, Integer> hashMapToReturn = new HashMap<>();
        for(ColorOfFigure_Square color : ColorOfFigure_Square.values()){
            hashMapToReturn.put(color, Collections.frequency(damage, color));
        }
        for(ColorOfFigure_Square color : ColorOfFigure_Square.values()){
            if(hashMapToReturn.get(color)==0)
                hashMapToReturn.remove(color);
        }
        return hashMapToReturn;
    }

    private ArrayList<ColorOfFigure_Square> convertDamageFromKillshotTrack(ArrayList<ArrayList<ColorOfDeathToken>> damageForNormalMode) {
        ArrayList<ColorOfFigure_Square> damageToReturn = new ArrayList<>();
        for(ArrayList<ColorOfDeathToken> listOfToken : damageForNormalMode){
            for(ColorOfDeathToken token : listOfToken) {
                    damageToReturn.add(convertColorOfTokenInColorOfFigure(token));
                }
            }

        return damageToReturn;
    }

    private ColorOfFigure_Square convertColorOfTokenInColorOfFigure(ColorOfDeathToken token){
        switch (token.toString()){
            case "GREEN":
                return ColorOfFigure_Square.GREEN;
            case "BLUE":
                return ColorOfFigure_Square.BLUE;
            case "PURPLE":
                return ColorOfFigure_Square.PURPLE;
            case "YELLOW":
                return ColorOfFigure_Square.YELLOW;
            case "GREY":
                return ColorOfFigure_Square.GREY;
            case "SKULL":
                return null;
                default:
                    throw new IllegalArgumentException();
        }
    }

    private void initializeScoreForPlayers() {
        scoreForPlayers = new ArrayList<>();
        for(int i=0 ; i<game.getPlayers().size();i++)
            scoreForPlayers.add(0);
    }

    private void assignScoreForDoubleKill() {
        for(int i=0 ; i<game.getPlayers().size(); i++){
            if(game.getPlayers().get(i).getNumOfKillDoneInTheTurn()==2)
                scoreForPlayers.set(i, scoreForPlayers.get(i));
        }
    }

    private void countScoreForPlayerDeath(Player deadPlayer) {
        if(!deadPlayer.getPlayerBoard().isFrenzyBoardPlayer()) {
            ColorOfFigure_Square colorOfFirstDamage = deadPlayer.getPlayerBoard().getDamages().get(0);
            scoreForPlayers.set(findIndexOfPlayerOfThisColor(colorOfFirstDamage), scoreForPlayers.get(findIndexOfPlayerOfThisColor(colorOfFirstDamage)) + 1);
        }
        HashMap<ColorOfFigure_Square, Integer> hashMapForDamage = countFrequencyOfDamageInPlayerBoard(deadPlayer);
        int indexOfScoreToAssign = 0;
        int scoreToAssign;

        while(!hashMapForDamage.isEmpty()){
            if(deadPlayer.getPlayerBoard().isFrenzyBoardPlayer())
                scoreToAssign = deadPlayer.getPlayerBoard().getScoreBarForFrenzyMode().get(indexOfScoreToAssign);
            else
                scoreToAssign = deadPlayer.getPlayerBoard().getScoreBarForNormalMode().get(indexOfScoreToAssign);

            int max = findMaxFrequency(hashMapForDamage);
            ColorOfFigure_Square colorOfPlayerToAddScore;
            colorOfPlayerToAddScore = findColorWithMaxFrequency(hashMapForDamage,max);
            if(!maxIsUnique(hashMapForDamage,max)){
                ArrayList<ColorOfFigure_Square> colorsWithMaxFrequency = getColorsWithMaximumFrequency(hashMapForDamage,max);
                colorOfPlayerToAddScore = getFirstOfMaximumColor(colorsWithMaxFrequency, deadPlayer.getPlayerBoard().getDamages());
            }
            scoreForPlayers.set(findIndexOfPlayerOfThisColor(colorOfPlayerToAddScore), scoreForPlayers.get(findIndexOfPlayerOfThisColor(colorOfPlayerToAddScore))+scoreToAssign);
            hashMapForDamage.remove(colorOfPlayerToAddScore);
            indexOfScoreToAssign++;
        }

        if(deadPlayer.getPlayerBoard().isFrenzyBoardPlayer())
            deadPlayer.getPlayerBoard().getScoreBarForFrenzyMode().remove(0);
        else
            deadPlayer.getPlayerBoard().getScoreBarForNormalMode().remove(0);

    }

    private ColorOfFigure_Square getFirstOfMaximumColor(ArrayList<ColorOfFigure_Square> colorsWithMaxFrequency, ArrayList<ColorOfFigure_Square> damages) {
        for(ColorOfFigure_Square damage : damages ){
            if(colorsWithMaxFrequency.contains(damage)){
                for(ColorOfFigure_Square color : colorsWithMaxFrequency){
                    if(color.toString().equals(damage.toString()))
                        return color;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private ArrayList<ColorOfFigure_Square> getColorsWithMaximumFrequency(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage, int max) {
        ArrayList<ColorOfFigure_Square> colorsToReturn = new ArrayList<>();
        for(Map.Entry<ColorOfFigure_Square,Integer> entry : hashMapForDamage.entrySet()){
            if(entry.getValue()==max)
                colorsToReturn.add(entry.getKey());
        }
        return colorsToReturn;
    }

    private boolean maxIsUnique(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage, int max) {
        return Collections.frequency(hashMapForDamage.values(), max)==1;
    }

    private int findMaxFrequency(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage) {
        int max=1;
        for(int num : hashMapForDamage.values()){
            if(num>max)
                max=num;
        }
        return max;
    }

    private ColorOfFigure_Square findColorWithMaxFrequency(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage, int max) {
        for(Map.Entry<ColorOfFigure_Square,Integer> entry : hashMapForDamage.entrySet()){
            if(entry.getValue()==max)
                return entry.getKey();
        }
        throw new IllegalArgumentException();
    }

    private HashMap<ColorOfFigure_Square, Integer> countFrequencyOfDamageInPlayerBoard(Player deadPlayer) {
        ArrayList<ColorOfFigure_Square> damage = deadPlayer.getPlayerBoard().getDamages();
        HashMap<ColorOfFigure_Square, Integer> hashMapToReturn = new HashMap<>();
        for(ColorOfFigure_Square color : ColorOfFigure_Square.values()){
            hashMapToReturn.put(color, Collections.frequency(damage, color));
        }
        for(ColorOfFigure_Square color : ColorOfFigure_Square.values()){
            if(hashMapToReturn.get(color)==0)
                hashMapToReturn.remove(color);
        }
        return hashMapToReturn;
    }


    private int findIndexOfPlayerOfThisColor(ColorOfFigure_Square color) {
        for(int i=0; i<game.getPlayers().size();i++){
            if(color.toString().equals(game.getPlayers().get(i).getColorOfFigure().toString()))
                return i;
        }
        throw new IllegalArgumentException();
    }

     ArrayList<Player> getPlayersDeadInThisTurn() {
        ArrayList<Player> deadPlayers = new ArrayList<>();
        for(Player player : game.getPlayers()){
            if(player.isDead())
                deadPlayers.add(player);
        }
        return deadPlayers;
    }


}
