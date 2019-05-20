package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestActionEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;


import java.util.ArrayList;

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
    private TurnController turnController;
    private WeaponController weaponController;

    /**
     * Constructor of the class: it creates the instances of the other controller classes
     * @param game: the reference to model part of MVC pattern
     */
    public Controller(Game game) {
        this.game = game;
        catchController = new CatchController(game, this);
        powerUpController = new PowerUpController(game, this);
        roundController = new RoundController(game);
        runController = new RunController(game, this);
        shootController = new ShootController(game);
        turnController = new TurnController(game);
        weaponController = new WeaponController(game);
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
        System.out.println(message.getMessage() + " " + message.getNickname());
        if (isTheTurnOfThisPlayer(message.getNickname())) {
            if (message.isDrawTwoPowerUpCards()) {
                powerUpController.handleDrawOfTwoCards(message.getNickname());
            }
            if (message.isDiscardPowerUpCardToSpawn()) {
                powerUpController.handleDiscardOfCardToSpawn((DiscardPowerUpCardToSpawnEvent) message);
            }
            if (message.isRequestToCatchByPlayer()) {
                catchController.replyToRequestToCatch((RequestToCatchByPlayer) message);
            }

            if(message.isCatchEvent()){
                catchController.handleCatching((CatchEvent) message);
            }

            if(message.isSelectionWeaponToCatch()){
                catchController.addWeaponToPlayer((SelectionWeaponToCatch) message);
                //manca notificare l'avvenuta raccolta
            }
            if (message.isRequestToRunByPlayer()) {
               runController.handleRunActionRequest((RequestToRunByPlayer) message);
            }

            if(message.isSelectionSquareForRun()){
                runController.doRunAction((SelectionSquareForRun) message);

            }
            if (message.isRequestToShootByPlayer()) {
                //shootController.replyToRequestToShoot(message);
            }
        }
    }

    /**
     * This method finds the player who has the nickname that is passed as parameter
     * @param nickname the nickname of the player that is looked for
     * @return the player that is looked for
     * @throws IllegalArgumentException when nickname parameter isn't associated to a player
     */
    Player findPlayerWithThisNickname (String nickname){
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



    void sendRequestForAction(){
        RequestActionEvent requestActionEvent = new RequestActionEvent();
        requestActionEvent.setRequestActionEvent(true);
        requestActionEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
        requestActionEvent.setNumOfAction(game.getNumOfActionOfTheTurn()+1);
        requestActionEvent.setRound(game.getRound());
        requestActionEvent.setMessageForInvolved("Choose your action number " + requestActionEvent.getNumOfAction() +" you want to do.\n Remember you can use power up cards enabled.");
        requestActionEvent.setCanUseNewton(true);
        requestActionEvent.setCanUseTeleporter(true);
        if(!(game.getPlayers().get(game.getPlayerOfTurn()-1).getPlayerBoard().getWeaponsOwned().isEmpty()))
            requestActionEvent.setCanUseWeapon1(checkIfThisWeaponIsUsable(game.getPlayers().get(game.getPlayerOfTurn()-1).getPlayerBoard().getWeaponsOwned().get(0)));
        if(game.getPlayers().get(game.getPlayerOfTurn()-1).getPlayerBoard().getWeaponsOwned().size()>1)
            requestActionEvent.setCanUseWeapon2(checkIfThisWeaponIsUsable(game.getPlayers().get(game.getPlayerOfTurn()-1).getPlayerBoard().getWeaponsOwned().get(1)));
        if(game.getPlayers().get(game.getPlayerOfTurn()-1).getPlayerBoard().getWeaponsOwned().size()>2)
            requestActionEvent.setCanUseWeapon3(checkIfThisWeaponIsUsable(game.getPlayers().get(game.getPlayerOfTurn()-1).getPlayerBoard().getWeaponsOwned().get(2)));
        requestActionEvent.setCanShoot(requestActionEvent.isCanUseWeapon1()||requestActionEvent.isCanUseWeapon2()||requestActionEvent.isCanUseWeapon3());

        game.notify(requestActionEvent);
    }

    private boolean checkIfThisWeaponIsUsable(WeaponCard weaponCard) {
         if(game.getRound()==1 && game.getPlayerOfTurn()==1)
             return false;
         else
            return weaponCard.isLoad();
         //In realtà questo metodo deve chiamare un metodo di weapon controller (della corrispondente arma) che restituisce true se esiste un player da poter sparare
    }


    public Game getGame() {
        return game;
    }


}



