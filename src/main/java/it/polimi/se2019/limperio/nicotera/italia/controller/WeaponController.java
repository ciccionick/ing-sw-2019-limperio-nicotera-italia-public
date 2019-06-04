package it.polimi.se2019.limperio.nicotera.italia.controller;


import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class checks the right use of a weapon. It collaborates with ShootController.
 *
 * @author  Giuseppe Italia
 */
public class WeaponController {

    private final Game game;
    private final Controller controller;


    public WeaponController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }


    boolean isThisWeaponUsable(WeaponCard weaponCard, int movementCanDoBeforeReloadAndShoot) {
        Square squareOfPlayer = weaponCard.getOwnerOfCard().getPositionOnTheMap();
        switch (weaponCard.getName()){
            case "Electroscythe":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Cyberblade":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot+1, squareOfPlayer ).isEmpty();
            case "Sledgehammer":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot, squareOfPlayer ).isEmpty();
            case "Shotgun":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty() || getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Shockwave":
                return !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Furnace":
                return !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty() || !getSquaresOfVisibleRoom(movementCanDoBeforeReloadAndShoot, squareOfPlayer, 0,true).isEmpty();
            case "Lock rifle":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(),0).isEmpty();
            case "Zx-2":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(),0).isEmpty();
            case "Machine gun":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(),0).isEmpty();
            case "Granade launcher":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(),0).isEmpty();
            case "Plasma gun":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot+2, weaponCard.getOwnerOfCard(),0).isEmpty();
            case "Railgun":
                return !playersInCardinalDirections(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Heatseeker":
                return !getPlayersNotVisible(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard()).isEmpty();
            case "Rocket launcher":
                int numOfExtraMovement = 0;
                if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()))
                    numOfExtraMovement = 2;
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot+numOfExtraMovement, weaponCard.getOwnerOfCard(), 1).isEmpty();
            case "Hellion":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 1).isEmpty();
            case "Whisper":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 2).isEmpty();
            case "Thor":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Flamethrower":
                return !squaresUsefulForFlamethrower().isEmpty();
            case "Power glove":
                return !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Tractor beam":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot+2, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Vortex cannon":
                return !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot+1, squareOfPlayer).isEmpty();


            default:
                throw  new IllegalArgumentException();
        }
    }




    private ArrayList<String> getPlayersInMySquare(int movement, Square square) {
        ArrayList<String> playersInTheSquare = new ArrayList<>();
        Player player = game.getPlayers().get(game.getPlayerOfTurn()-1);
        ArrayList<Square> squaresReachable = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(square, movement, squaresReachable );
        for(Square squareReachable : squaresReachable){
            for(Player playerInTheSquare : squareReachable.getPlayerOnThisSquare()) {
                if(!playerInTheSquare.equals(player))
                    playersInTheSquare.add(playerInTheSquare.getNickname());
            }
        }
        return playersInTheSquare;
     }

    private ArrayList<Player> getPlayersOnlyInAdjSquares(int movement, Square squareOfPlayer) {
        ArrayList<Player> playersOnlyInAdjSquares = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        ArrayList<Square> adjSquares = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(squareOfPlayer, movement, startingSquares);
        for(Square square : startingSquares){
            for(Square adjSquare : square.getAdjSquares()){
                if(!adjSquares.contains(adjSquare))
                    adjSquares.add(adjSquare);
            }
        }
        for(Square square : adjSquares){
            for(Player player : square.getPlayerOnThisSquare()){
                if(!playersOnlyInAdjSquares.contains(player))
                    playersOnlyInAdjSquares.add(player);
            }
        }

        return playersOnlyInAdjSquares;
    }

    //funziona
    private ArrayList<Square> getSquaresOfVisibleRoom(int movement, Square squareOfPlayer, int distanceNeeded, boolean differentFromMine) {
        ArrayList<Square> squaresOfVisibleRoomDifferentFromMine = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(squareOfPlayer, movement, startingSquares);
        for(Square startingSquare : startingSquares){
            for(Square square : startingSquare.getAdjSquares()) {
                if (!differentFromMine)
                    addSquaresOfThisColors(squaresOfVisibleRoomDifferentFromMine, square.getColor());
                else {
                    if (!startingSquare.getColor().equals(square.getColor()))
                        addSquaresOfThisColors(squaresOfVisibleRoomDifferentFromMine, square.getColor());
                }
            }
            if(distanceNeeded!=0)
                removeSquareCloserThan(startingSquare, squaresOfVisibleRoomDifferentFromMine,distanceNeeded);
        }

        for(Square square : squaresOfVisibleRoomDifferentFromMine){
            if(!square.getPlayerOnThisSquare().isEmpty())
                return squaresOfVisibleRoomDifferentFromMine;
        }
        squaresOfVisibleRoomDifferentFromMine = new ArrayList<>();
        return squaresOfVisibleRoomDifferentFromMine;
    }

    private void removeSquareCloserThan(Square startingSquare, ArrayList<Square> squaresOfVisibleRoomDifferentFromMine, int distanceNeeded) {
        ArrayList<Square> squareToRemove = new ArrayList<>();
        for(Square square : squaresOfVisibleRoomDifferentFromMine){
            if(distanceBetweenSquare(startingSquare,square)<distanceNeeded)
                squareToRemove.add(square);
        }

        for(Square square : squareToRemove){
            squaresOfVisibleRoomDifferentFromMine.remove(square);
        }
    }

    private int distanceBetweenSquare(Square startingSquare, Square square) {
        if(startingSquare.equals(square))
            return 0;
        ArrayList<Square> squaresReachable = new ArrayList<>();
        for(int i=1; i<8; i++){
            controller.findSquaresReachableWithThisMovements(startingSquare, i, squaresReachable);
            if(squaresReachable.contains(square))
                return i;
            squaresReachable = new ArrayList<>();
        }
        throw new IllegalArgumentException();
    }


    private void addSquaresOfThisColors(ArrayList<Square> squaresOfVisibleRoomDifferentFromMine, ColorOfFigure_Square color) {
        for (Square[] squares : game.getBoard().getMap().getMatrixOfSquares()) {
            for(Square square : squares){
                if(square!=null && square.getColor().equals(color) && !squaresOfVisibleRoomDifferentFromMine.contains(square))
                    squaresOfVisibleRoomDifferentFromMine.add(square);
            }
        }
    }

    private ArrayList<Square> squaresUsefulForFlamethrower() {
        ArrayList<Square> squaresForFlamethrower = new ArrayList<>();
        return squaresForFlamethrower;
    }

    /**
     * This method returns the players that are visible from the square of the attacking playerCanSee
     *
     * @return
     */
    ArrayList<Player> getVisiblePlayers(int movement, Player playerCanSee, int distanceNeeded)
    {
        ArrayList<Player> playersVisible = new ArrayList<>();
        ArrayList<Square> squaresVisible = getSquaresOfVisibleRoom(movement, playerCanSee.getPositionOnTheMap(), distanceNeeded,false);
        for(Square square : squaresVisible){
            for(Player playerInSquare : square.getPlayerOnThisSquare() ){
                if(!playerInSquare.equals(playerCanSee))
                    playersVisible.add(playerInSquare);
            }
        }
        playersVisible.remove(playerCanSee);
        if(playerCanSee.getNickname().equals("terminator"))
            playersVisible.remove(game.getPlayers().get(game.getPlayerOfTurn()-1));

        return playersVisible;

    }

    /**
     * returns the players positioned in cardinal directions with respect to the attacking player
     * @return List of players
     */

    private ArrayList<Player> playersInCardinalDirections(int movement, Square square)
    {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        ArrayList<Square> squaresAvailable = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(square, movement, startingSquares);
        for(Square startingSquare : startingSquares){
            addSquaresForCardinalDirections(startingSquare,squaresAvailable);
        }

        for(Square squareAvailable : squaresAvailable ){
            for(Player player : squareAvailable.getPlayerOnThisSquare()){
                if(!player.equals(game.getPlayers().get(game.getPlayerOfTurn()-1)))
                    players.add(player);
            }
        }

        return players;
    }

    private void addSquaresForCardinalDirections(Square startingSquare, ArrayList<Square> squaresAvailable) {
        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
        int row = startingSquare.getRow();
        int column = startingSquare.getColumn();
        for(Square square : matrix[row]) {
            if (square != null && !squaresAvailable.contains(square))
                squaresAvailable.add(square);
        }
        for(int i = 0; i<3 ; i++){
            if(matrix[i][column]!=null && !squaresAvailable.contains(matrix[i][column]))
                squaresAvailable.add(matrix[i][column]);
        }
    }

    /**
     * returns the players that the attacking player not can see

     * @return List of players
     */

    private ArrayList<Player> getPlayersNotVisible(int movement, Player playerCanSee)
    {
        ArrayList<Player> playersNotVisible = new ArrayList<>();
        ArrayList<Player> playersVisible = getVisiblePlayers(movement, playerCanSee, 0);
        for(Player player : game.getPlayers()){
            if(!playersVisible.contains(player) && !player.equals(playerCanSee))
                playersNotVisible.add(playerCanSee);
        }
        return playersNotVisible;
    }

    boolean canReload(WeaponCard weaponCard) {
        Player player = weaponCard.getOwnerOfCard();
        int numOfRedAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), RED);
        int numOfBlueAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), BLUE);
        int numOfYellowAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), YELLOW);
        return controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, RED) >= numOfRedAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, BLUE) >= numOfBlueAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, YELLOW)>=numOfYellowAmmoRequired;

    }

    private boolean effectAffordable(Player player, ColorOfCard_Ammo[] price){
        int numOfRedAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, RED);
        int numOfBlueAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, BLUE);
        int numOfYellowAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, YELLOW);
        return controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, RED) >= numOfRedAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, BLUE) >= numOfBlueAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, YELLOW)>=numOfYellowAmmoRequired;

    }


}
