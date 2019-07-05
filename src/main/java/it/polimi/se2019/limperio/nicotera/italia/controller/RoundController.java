package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.*;
import java.util.Map;

/**
 * Controller that handles all of the operations to complete the turn and pass to successive one.
 * @author Pietro L'Imperio.
 */
public class RoundController {
    /**
     * Reference of the controller.
     */
    private Controller controller;
    /**
     * Reference of the game.
     */
    private final Game game;
    /**
     * List of score to attribute to each player in case of one or multiple deaths during the finished turn.
     */
    private ArrayList<Integer> scoreForPlayers = new ArrayList<>();

    /**
     * Contructor that initializes the game and controller references.
     */
    public RoundController(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
    }

    /**
     * Changes the player of the turn. Checks if is the last turn of the frenzy mode to call the end of the game.
     * Sets correctly the number of maximum actions that a player can do in the next turn. Updates the decks of cards and ammo tile and restore the situation on the squares.
     * Calls the methods to handle the deaths that have happened during the last turn, if there has been at least one.
     */
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

    /**
     * Handles a random spawn to generate players that jump turn, or has been not connected during the moment when they have to decide power up to discard to be spawn.
     * At the end send a map event and a player board event to notify to all the players the update.
     * @param player Player that has to randomly spawn.
     */
      void randomSpawn(Player player){
        Square square;
        if(player.getPlayerBoard().getPowerUpCardsOwned().isEmpty()&&game.getRound()==1) {
            PowerUpCard powerUpCardToSpawn1 = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
            PowerUpCard powerUpCardToSpawn2 = game.getBoard().getPowerUpDeck().getPowerUpCards().get(1);
            game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(1));
            square = controller.getPowerUpController().findSpawnSquareWithThisColor(powerUpCardToSpawn2.getColor());
            player.drawPowerUpCard(powerUpCardToSpawn1);
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
         player.setOverSixDamage(false);
         player.setIsUnderThreeDamage(true);
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

    /**
     * Handles the respawn of dead players at the end of the turn.
     * @param deadPlayers List of player died during the last turn.
     */
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

    /**
     * Updates the decks of ammo tile and power up cards if their size is lower than 3 and refill the square where the player of the last turn went to catch.
     */
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

    /**
     * Initializes the number of killings done during the turn.
     */
    private void initializeNumOfKillDoneInTheTurn() {
        for(Player player : game.getPlayers()){
            player.setNumOfKillDoneInTheTurn(0);
        }
    }

    /**
     * Updates the player of the turn, changing the value of the integer field in the game.
     */
    private void updatePlayerOfTurn() {
        if (game.getPlayerOfTurn() == game.getPlayers().size() || game.isTerminatorModeActive()&&game.getPlayerOfTurn()+1==game.getPlayers().size()) {
            game.setPlayerOfTurn(1);
            game.setRound(game.getRound() + 1);
        } else
            game.setPlayerOfTurn(game.getPlayerOfTurn() + 1);
    }

    /**
     * Checks if the last turn of frenzy mode is finished or not.
     * @return True if the finished turn was the last turn of frenzy mode, false otherwise.
     */
    private boolean isFinishedTheLastFrenzyTurn() {
        return game.isInFrenzy()&&game.getPlayerOfTurn()==game.getPlayers().size()&&game.getFirstInFrenzyMode()==1 || game.isInFrenzy()&&game.getPlayerOfTurn()+1==game.getFirstInFrenzyMode();
    }


    /**
     * Counts the score for the deaths happens during the turn, sending the update of the score
     * @param deadPlayers The list of the player died during the last turn.
     */
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

    /**
     * Changes the player board of the players to pass in frenzy mode, or only tha action bar in the case the player has still some damage.
     */
    private void updatePlayerBoardForFrenzyMode() {
        for (Player player : game.getPlayers()) {
            player.getPlayerBoard().setFrenzyActionBar(true);
            PlayerBoardEvent pbEvent = new PlayerBoardEvent();
            pbEvent.setNicknameInvolved(player.getNickname());
            pbEvent.setNicknames(game.getListOfNickname());
            if (player.getPlayerBoard().getDamages().isEmpty()) {
                player.getPlayerBoard().setFrenzyBoardPlayer(true);
                pbEvent.setMessageForInvolved("Your player board has passed to frenzy mode");
            }
            else{
                pbEvent.setMessageForInvolved("The action bar of your player board has passed to frenzy mode");
            }
            pbEvent.setPlayerBoard(player.getPlayerBoard());
            game.notify(pbEvent);
        }
    }


    /**
     * Sends a message with the update of the score due to the death, or the final ranking at the end of the game.
     * @param finalUpdate It's true if the update is to notify the final ranking at the end of the game.
     * @param isForDisconnectionOfThirdPlayer It's true if the final update is generated because the game is finished for the disconnection of the third player.
     */
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
        updateScoreEvent.setUpdateScoreEvent();
        updateScoreEvent.setNicknames(game.getListOfNickname());
        updateScoreEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
        String messageForInvolved = "";
        String messageForOthers =  "";
        if(isForDisconnectionOfThirdPlayer){
            messageForInvolved = "You have remained in two playing, so the match finish here. \n\n";
            messageForOthers = "You have remained in two playing, so the match finish here. \n\n";
        }
        if (finalUpdate) {
            updateScoreEvent.setFinalUpdate();
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

    /**
     * Checks if counting the damage on the player board of a dead player there are more than one player with the same number of damage on the board.
     */
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

    /**
     * Checks if the removing skull is the last of the normal mode.
     * @return True if the removing skull is the skull in the position read by file and stored in the relative field of the game (numOfSkullToRemoveToPassToFrenzy), false otherwise.
     */
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

    /**
     * At the end of the game counts the score for each player according to the number of damage on the killshot track.
     * @param scoreForDamageInKillShotTrack The list of the score to give in order to player for the counts of damage on killsho track.
     */
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

    /**
     * Gets the frequency of damage for each player on killshot track
     * @return Hash map where the key is the color of figure of the players and the value is the number of the damage of that color on the killshot track
     */
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

    /**
     * Converts the tokens of death in color of figure to attribute the correct score.
     * @param damageForNormalMode It's true if the damage to convert have been token from the killshot track of the normal mode, false otherwise.
     * @return The array list of all the damage converted from tokens of death.
     */
    private ArrayList<ColorOfFigure_Square> convertDamageFromKillshotTrack(ArrayList<ArrayList<ColorOfDeathToken>> damageForNormalMode) {
        ArrayList<ColorOfFigure_Square> damageToReturn = new ArrayList<>();
        for(ArrayList<ColorOfDeathToken> listOfToken : damageForNormalMode){
            for(ColorOfDeathToken token : listOfToken) {
                    damageToReturn.add(convertColorOfTokenInColorOfFigure(token));
                }
            }
        return damageToReturn;
    }

    /**
     * Convert token of death in color of figure.
     * @param token Color of the token to convert.
     * @return Color of figure relative to the token of death passed by parameter.
     */
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

    /**
     * Initializes the array list that store the score for each player for the count of each dead player.
     */
    private void initializeScoreForPlayers() {
        scoreForPlayers = new ArrayList<>();
        for(int i=0 ; i<game.getPlayers().size();i++)
            scoreForPlayers.add(0);
    }

    /**
     * Assigns score for double killings, one point more.
     */
    private void assignScoreForDoubleKill() {
        for(int i=0 ; i<game.getPlayers().size(); i++){
            if(game.getPlayers().get(i).getNumOfKillDoneInTheTurn()==2)
                scoreForPlayers.set(i, scoreForPlayers.get(i));
        }
    }

    /**
     * Handles the counting of the score due to the death of a player.
     * @param deadPlayer Player died.
     */
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

    /**
     * Gets the color of the first player that gave the first damage to the dead player.
     * @param colorsWithMaxFrequency List of colors that has the same number of damage on the player board of the player.
     * @param damages List of damage of the player board of the dead player.
     * @return The color of the figure of the player that gave the first damage to the dead player.
     */
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

    /**
     * Get the color with the maximum frequency in the hash map passed by parameter.
     * @param hashMapForDamage Hash map the contains color of figure as a key and the number of damage of that color on the player board as a value.
     * @param max Number of the damage of the color that has more damage on the player board.
     * @return
     */
    private ArrayList<ColorOfFigure_Square> getColorsWithMaximumFrequency(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage, int max) {
        ArrayList<ColorOfFigure_Square> colorsToReturn = new ArrayList<>();
        for(Map.Entry<ColorOfFigure_Square,Integer> entry : hashMapForDamage.entrySet()){
            if(entry.getValue()==max)
                colorsToReturn.add(entry.getKey());
        }
        return colorsToReturn;
    }

    /**
     * Checks if there is only one player with a determinate number of damage given to the dead player.
     * @param hashMapForDamage Hash map the contains color of figure as a key and the number of damage of that color on the player board as a value.
     * @param max Number of the damage of the color that has more damage on the player board.
     * @return True if there is only one player that gave "max" damage to the dead player, false otherwise.
     */
    private boolean maxIsUnique(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage, int max) {
        return Collections.frequency(hashMapForDamage.values(), max)==1;
    }

    /**
     * Gets the maximum frequency of a color of figure among the damage of the dead player.
     * @param hashMapForDamage Hash map the contains color of figure as a key and the number of damage of that color on the player board as a value.
     * @return The number of maximum frequency.
     */
    private int findMaxFrequency(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage) {
        int max=1;
        for(int num : hashMapForDamage.values()){
            if(num>max)
                max=num;
        }
        return max;
    }

    /**
     * Gets the color of figure that has the maximum frequency of damage given on the player board of the dead player.
     * @param hashMapForDamage Hash map the contains color of figure as a key and the number of damage of that color on the player board as a value.
     * @param max  Number of the damage of the color that has more damage on the player board.
     * @return The color of figure with the maximum frequency.
     */
    private ColorOfFigure_Square findColorWithMaxFrequency(HashMap<ColorOfFigure_Square, Integer> hashMapForDamage, int max) {
        for(Map.Entry<ColorOfFigure_Square,Integer> entry : hashMapForDamage.entrySet()){
            if(entry.getValue()==max)
                return entry.getKey();
        }
        throw new IllegalArgumentException();
    }

    /**
     * Counts the frequency of damage on the player board of dead player and store this information in a hash map.
     * @param deadPlayer The player died.
     * @return Hash map the contains color of figure as a key and the number of damage of that color on the player board as a value.
     */
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


    /**
     * Find the position of a player on the list of players of the game according with his color.
     * @param color The color of figure of the player on whose is interested on the position in the list of player of the game.
     * @return The position of the player with the color passed by parameter in the list of players of the game.
     */
    private int findIndexOfPlayerOfThisColor(ColorOfFigure_Square color) {
        for(int i=0; i<game.getPlayers().size();i++){
            if(color.toString().equals(game.getPlayers().get(i).getColorOfFigure().toString()))
                return i;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Gets the list of the player died during the turn.
     * @return The list of players died in the turn.
     */
     ArrayList<Player> getPlayersDeadInThisTurn() {
        ArrayList<Player> deadPlayers = new ArrayList<>();
        for(Player player : game.getPlayers()){
            if(player.isDead())
                deadPlayers.add(player);
        }
        return deadPlayers;
    }


}
