package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestActionEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for checking the correctness of the action of the players, according to MVC pattern
 * @author Pietro L'Imperio
 *
 */
public class Controller implements Observer<ClientEvent> {


    private final Game game;
    private CatchController catchController;
    private PowerUpController powerUpController;
    private RoundController roundController;
    private RunController runController;
    private ShootController shootController;
    private WeaponController weaponController;
    private TerminatorController terminatorController;
    private DeathController deathController;
    private Timer timer = null;
    private TurnTask turnTask;

    /**
     * Constructor of the class: it creates the instances of the other controller classes
     * @param game: the reference to model part of MVC pattern
     */
    public Controller(Game game) {
        this.game = game;
        catchController = new CatchController(game, this);
        powerUpController = new PowerUpController(game, this);
        roundController = new RoundController(this,game);
        runController = new RunController(game, this);
        shootController = new ShootController(game, this);
        weaponController = new WeaponController(game, this);
        deathController = new DeathController(game, this);

    }

    /**
     * <p>
     *     This method handles the messages that VirtualView sends to Controller.
     * </p>
     * <p>
     *     It recognize which specific controller have to be called to handle the message through the if case.
     * </p>
     *
     * @param message it contains the type of action that the player has done.
     */
    public void update(ClientEvent message) {
        if (isTheTurnOfThisPlayer(message.getNickname())&&game.getPlayerHasToRespawn()==null || findPlayerWithThisNickname(message.getNickname()).isDead()) {
            if (message.isDrawPowerUpCard() && findPlayerWithThisNickname(message.getNickname()).isHasToBeGenerated()) {
                if(game.getRound()==1 && game.getPlayerOfTurn()==1 && game.isTerminatorModeActive()){
                    terminatorController = new TerminatorController(this, game);
                }
                powerUpController.handleDrawOfPowerUpCards((DrawPowerUpCards) message);
            }
            if (message.isDiscardPowerUpCardToSpawn()&&findPlayerWithThisNickname(message.getNickname()).isHasToBeGenerated()) {
                powerUpController.handleDiscardOfCardToSpawn((DiscardPowerUpCardToSpawnEvent) message);
            }
            if (message.isRequestToCatchByPlayer()) {
                catchController.replyToRequestToCatch((RequestToCatchByPlayer) message);
            }


            if(message.isCatchEvent()){
                if(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname().equals(message.getNickname()))
                    catchController.handleCatching((CatchEvent) message);
            }

            if(message.isGenerationTerminatorEvent()){
                terminatorController.handleSpawnOfTerminator(message);
            }

            if(message.isRequestTerminatorActionByPlayer()){
                terminatorController.handleFirstRequestAction(message);
            }

            if(message.isRequestToShootWithTerminator()){
                terminatorController.sendRequestToChoosePlayerToAttack(message);
            }

            if (message.isRequestToMoveTerminator()) {
                    terminatorController.handleRequestMove(message);
            }

            if(message.isMoveTerminatorEvent())
                terminatorController.handleMove(message);

            if(message.isRequestToGoOn()){
                game.setHasToDoTerminatorAction(false);
                handleTheEndOfAnAction();
            }

            if(message.isSelectionWeaponToCatch()){
                catchController.handleSelectionWeaponToCatch((SelectionWeaponToCatch) message);
            }
            if (message.isRequestToRunByPlayer()) {
               runController.handleRunActionRequest((RequestToRunByPlayer) message);
            }

            if(message.isSelectionSquareForRun()){
                runController.doRunAction((RunEvent) message);
            }

            if(message.isTerminatorShootEvent()){
                terminatorController.handleTerminatorShootAction(message);
            }

            if(message.isSelectionWeaponToDiscard()){
                catchController.handleSelectionWeaponToCatchAfterDiscard((SelectionWeaponToDiscard) message);
            }
            if (message.isRequestToShootByPlayer()) {
                shootController.replyToRequestToShoot(message);
            }
            if(message.isRequestToUseWeaponCard()){
                shootController.replyWithUsableEffectsOfThisWeapon(message);
            }
            if(message.isRequestToUseEffect())
                shootController.handleRequestToUseEffect((RequestToUseEffect) message);
            if(message.isDiscardPowerUpCardAsAmmo()) {
                if(((DiscardPowerUpCardAsAmmo)message).isToCatch())
                    catchController.handleRequestToDiscardPowerUpCardAsAmmo((DiscardPowerUpCardAsAmmo) message);
                if(((DiscardPowerUpCardAsAmmo)message).isToPayAnEffect())
                    shootController.handleDiscardPowerUpToPayAnEffect(message);
                if(((DiscardPowerUpCardAsAmmo)message).isToTargeting())
                    shootController.handleDiscardPowerUpToUseTargeting((DiscardPowerUpCardAsAmmo) message);
            }
            if(message.isDiscardAmmoOrPowerUpToPayTargeting()){
                shootController.handlePaymentForTargeting((DiscardAmmoOrPowerUpToPayTargeting) message);
            }
            if(message.isChoosePlayer()){
                if(((ChoosePlayer)message).isToTargeting())
                    shootController.handleUseOfTargeting((ChoosePlayer) message);
            }
        }
    }

    /**
     * This method finds the player who has the nickname that is passed as parameter
     * @param nickname the nickname of the player that is looked for
     * @return the player that is looked for
     * @throws IllegalArgumentException when nickname parameter isn't associated to a player
     */
    public Player findPlayerWithThisNickname (String nickname){
        for(Player player : game.getPlayers()){
            if(player.getNickname().equals(nickname)){
                return player;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Finds the squares that can be reached with the number of movements that are passed as parameter
     * @param currentPosition start position
     * @param numOfMovements number of the movements usable to reach the squares
     * @param listOfSquareReachable It is filled with the reachable squares
     */
    void findSquaresReachableWithThisMovements(Square currentPosition, int numOfMovements, ArrayList<Square> listOfSquareReachable){
        if(numOfMovements == 0 && !listOfSquareReachable.contains(currentPosition)){
            listOfSquareReachable.add(0,currentPosition);
            return;
        }
        for(Square square : currentPosition.getAdjSquares()) {
            if(numOfMovements-1 >= 0)
            {
                findSquaresReachableWithThisMovements(square, numOfMovements -1, listOfSquareReachable);

            }
        }
        if(!listOfSquareReachable.contains(currentPosition)){
            listOfSquareReachable.add(0,currentPosition);
        }

    }


    /**
     *This method calculates the distance between two squares on the map
     * @param startCoordinates the coordinates of the start position
     * @param targetCoordinates the coordinates of the end position
     * @return the distance
     */
    int distanceOfManhattan(int[] startCoordinates, int[] targetCoordinates) {
        return Math.abs(startCoordinates[0] - targetCoordinates[0]) + Math.abs(startCoordinates[1]- targetCoordinates[1]);
    }

    /**
     * This method checks if is the turn of the player who has the nickname that is passed
     * @param nickname the nickname of the player
     * @return boolean that is true if it is the turn of the player with the nickname parameter
     */
     private boolean isTheTurnOfThisPlayer(String nickname){
        return nickname.equals(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
    }

    ArrayList<ServerEvent.AliasCard> substituteWeaponsCardWithTheirAlias(ArrayList<WeaponCard> weapons){
        ArrayList<ServerEvent.AliasCard> weaponsAsAlias = new ArrayList<>();
        for(WeaponCard weapon : weapons){
            weaponsAsAlias.add(new ServerEvent.AliasCard(weapon.getName(), weapon.getDescription(), weapon.getColor()));
        }
        return weaponsAsAlias;
    }



    public RoundController getRoundController() {
        return roundController;
    }

    void handleTheEndOfAnAction(){
        game.incrementNumOfActionsOfThisTurn();

        if(game.getNumOfActionOfTheTurn()<game.getNumOfMaxActionForTurn()){
            sendRequestForAction();
        }
        else {
            //qui va un if che controlla se ci sono armi da caricare e c'è la possibilità di caricarle, in tal caso si interrompe qui e si manda un evento di richiesta di ricarica
            if(timer!=null)
                timer.cancel();
            timer = null;
            turnTask = null;
            roundController.updateTurn();
            if (!isSomeoneDied() && !game.isGameOver()) {
                    if (game.getRound() > 1)
                        sendRequestForAction();
                    else {
                        sendRequestToDrawPowerUpCard(game.getPlayers().get(game.getPlayerOfTurn() - 1), 2);
                    }
            }
        }
    }

    private boolean isSomeoneDied() {
         for(Player player : game.getPlayers()){
             if(player.isDead())
                 return true;
         }
         return false;
    }

     ShootController getShootController() {
        return shootController;
    }

    void sendRequestForAction() {
         if(game.getRound()>1 && game.getNumOfActionOfTheTurn()==0 && timer!=null) {

             timer.cancel();
             timer = null;
             turnTask = null;

         }
        RequestActionEvent requestActionEvent = new RequestActionEvent();
        requestActionEvent.setRequestActionEvent(true);
        requestActionEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn() - 1).getNickname());
        requestActionEvent.setNumOfAction(game.getNumOfActionOfTheTurn() + 1);
        requestActionEvent.setRound(game.getRound());
        if (game.getRound() > 1 && game.getNumOfActionOfTheTurn() == 0) {
            requestActionEvent.setMessageForInvolved("It's your turn! Choose you first action!");
            requestActionEvent.setMessageForOthers("Change turn! Now it's the turn of " + game.getPlayers().get(game.getPlayerOfTurn() - 1).getNickname() + "\nWait for some news!");
            requestActionEvent.setNicknames(game.getListOfNickname());
        } else
            requestActionEvent.setMessageForInvolved("Choose your action number " + requestActionEvent.getNumOfAction() + " you want to do.\nRemember you can use power up cards enabled.");
        if (game.getRound() == 1 && game.getPlayerOfTurn() == 1)
            requestActionEvent.setCanUseNewton(false);
        else
            requestActionEvent.setCanUseNewton(true);
        requestActionEvent.setCanUseTeleporter(true);
        requestActionEvent.setCanShoot(checkIfPlayerCanShoot(findPlayerWithThisNickname(requestActionEvent.getNicknameInvolved()).getPlayerBoard().getWeaponsOwned()));
        requestActionEvent.setHasToDoTerminatorAction(game.isHasToDoTerminatorAction());
        if (game.isHasToDoTerminatorAction() && game.getNumOfActionOfTheTurn() + 1 == game.getNumOfMaxActionForTurn()) {
            requestActionEvent.setCanCatch(false);
            requestActionEvent.setCanRun(false);
            requestActionEvent.setCanShoot(false);
        } else {
            requestActionEvent.setCanCatch(true);
            if(!game.isInFrenzy())
                requestActionEvent.setCanRun(true);
            else {
                if (game.getFirstInFrenzyMode()!=1 && game.getPlayers().get(game.getPlayerOfTurn() - 1).getPosition() >= game.getFirstInFrenzyMode())
                    requestActionEvent.setCanRun(true);
                else
                    requestActionEvent.setCanRun(false);
            }
        }
        game.notify(requestActionEvent);
        if (game.getNumOfActionOfTheTurn() == 0 && game.getRound() > 1) {
            setTimerForTurn(false,false);
        }
    }

    public void sendRequestToDrawPowerUpCard(Player playerHasToDraw, int numOfPowerUpCardToDraw) {
        playerHasToDraw.setHasToBeGenerated(true);
        if(timer!=null){
            timer.cancel();
            timer=null;
            turnTask = null;
        }
        String messageForInvolved;
        String messageForOthers;
        ServerEvent event = new ServerEvent();

        if(numOfPowerUpCardToDraw==1){
            messageForInvolved = "Draw a power up card and then discard one of yours to decide where you will be spawn";
            messageForOthers = playerHasToDraw.getNickname() + " has to draw power up card to decide where he'll be spawn";
            event.setRequestForDrawOnePowerUpCardEvent(true);
        }
        else{
            messageForInvolved = "Let's start! \nIt's your first turn and you have to draw two powerUp cards to decide where you will spawn. \nPress DRAW to draw powerUp cards!";
            messageForOthers = "Wait! It's not your turn but the turn of " + game.getListOfNickname().get(game.getPlayerOfTurn() - 1) + ". Press OK and wait for some news!";
            event.setRequestForDrawTwoPowerUpCardsEvent(true);
        }

        event.setMessageForInvolved(messageForInvolved);
        event.setMessageForOthers(messageForOthers);
        event.setNicknames(game.getListOfNickname());
        event.setNicknameInvolved(playerHasToDraw.getNickname());
        game.notify(event);
        setTimerForTurn(numOfPowerUpCardToDraw==2,numOfPowerUpCardToDraw==1);


    }


     boolean checkIfThisWeaponIsUsable(WeaponCard weaponCard, int movementCanDoBeforeReloadAndShoot) {
         if(game.getRound()==1 && game.getPlayerOfTurn()==1)
            return false;
         if(!weaponCard.isLoad() && !game.isInFrenzy() || !weaponCard.isLoad() && game.isInFrenzy() && !weaponController.canReload(weaponCard))
             return false;
         return (weaponController.isThisWeaponUsable(weaponCard, movementCanDoBeforeReloadAndShoot));

    }

     CatchController getCatchController() {
        return catchController;
    }

    WeaponController getWeaponController() {
        return weaponController;
    }

    private boolean checkIfPlayerCanShoot(ArrayList<WeaponCard> weaponDeck){
         int movementCanDoBeforeReloadAndShoot = 0;
         if(game.isInFrenzy()){
             if(game.getPlayers().get(game.getPlayerOfTurn()-1).getPosition()>game.getFirstInFrenzyMode())
                 movementCanDoBeforeReloadAndShoot=1;
             else
                 movementCanDoBeforeReloadAndShoot=2;
         }
         for(WeaponCard weaponCard : weaponDeck){
             if(checkIfThisWeaponIsUsable(weaponCard, movementCanDoBeforeReloadAndShoot))
                 return true;
         }
         return false;
    }

    PowerUpController getPowerUpController() {
        return powerUpController;
    }


    private void setTimerForTurn(boolean isForFirstRound, boolean isForRegeneration) {
        timer = new Timer();
        turnTask = new TurnTask();
        try {
            if(isForFirstRound)
                timer.schedule(turnTask, game.getDelay()+6000);
            else {
                if(!isForRegeneration)
                    timer.schedule(turnTask, game.getDelay());
                else
                    timer.schedule(turnTask, game.getDelay()/3);
            }
        } catch (IllegalStateException er) {
            er.printStackTrace();
        }

    }


    public void handleDisconnection(String nicknameOfPlayerDisconnected){
         Player player = findPlayerWithThisNickname(nicknameOfPlayerDisconnected);
         player.setConnected(false);
         game.getListOfNickname().remove(player.getNickname());
         if(findPlayerWithThisNickname(nicknameOfPlayerDisconnected).getPosition()==game.getPlayerOfTurn())
             roundController.updateTurn();

    }

     DeathController getDeathController() {
        return deathController;
    }

    private class TurnTask extends TimerTask {

        @Override
        public void run() {
            Player previousPlayer = game.getPlayers().get(game.getPlayerOfTurn()-1);
            roundController.updateTurn();
            PowerUpCard powerUpCard;
            if(previousPlayer.isHasToBeGenerated()){
                ColorOfCard_Ammo color;
                Square square = null;
                if(previousPlayer.getPlayerBoard().getPowerUpCardsOwned().size()==2){
                    powerUpCard = previousPlayer.getPlayerBoard().getPowerUpCardsOwned().remove(1);
                    color = powerUpCard.getColor();
                    Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
                    for(int i=0; i<matrix.length;i++){
                        for(int j=0 ; j<matrix[i].length;j++){
                            if(matrix[i][j]!=null && matrix[i][j].isSpawn() && color.toString().equals(matrix[i][j].getColor().toString()))
                                square = matrix[i][j];
                        }
                    }

                }
                else {
                    powerUpCard = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0); //add to local array the first powerUp card of the deck
                    game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0)); //add to used deck the first of normal deck and remove from this one
                    game.getBoard().getPowerUpDeck().getUsedPowerUpCards().get(game.getBoard().getPowerUpDeck().getUsedPowerUpCards().size() - 1).setInTheDeckOfSomePlayer(true); //set boolean attribute to the card
                    powerUpCard.setOwnerOfCard(previousPlayer);
                    previousPlayer.drawPowerUpCard(powerUpCard);
                    square = game.getBoard().getMap().getMatrixOfSquares()[1][0];
                }

                powerUpController.spawnPlayer(previousPlayer, square);
            }
            ServerEvent timerOverEvent = new ServerEvent();
            timerOverEvent.setNicknameInvolved(previousPlayer.getNickname());
            timerOverEvent.setMessageForInvolved("The time for your turn is over. \nWait for the next turn!");
            timerOverEvent.setTimerOverEvent(true);
            game.notify(timerOverEvent);

            if(game.getRound()!=1){
                sendRequestForAction();
            }
            else
                sendRequestToDrawPowerUpCard(game.getPlayers().get(game.getPlayerOfTurn()-1),2);
        }
    }
}



