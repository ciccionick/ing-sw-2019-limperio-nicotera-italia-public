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


     ArrayList<Integer> getUsableEffectsForThisWeapon(WeaponCard weaponCard) {
         ArrayList<Integer> usableEffects = new ArrayList<>();
         Square squareOfPlayer = weaponCard.getOwnerOfCard().getPositionOnTheMap();
         switch (weaponCard.getName()){
            case "Electroscythe":
                if(!getPlayersInMySquare(0, squareOfPlayer).isEmpty() && !controller.getShootController().getTypeOfAttack().contains(1) && !controller.getShootController().getTypeOfAttack().contains(4) ) {
                    usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Cyberblade":
                if(!controller.getShootController().getTypeOfAttack().contains(2))
                    usableEffects.add(2);
                if(!getPlayersInMySquare(0, squareOfPlayer).isEmpty() && !controller.getShootController().getTypeOfAttack().contains(1))
                    usableEffects.add(1);
                //questo if bisogna controllarlo perchè non è detto che la size deve essere maggiore di 1, bisogna piuttosto controllare che nei player nel mio quadrato ci sia qualcun altro rispetto a chi ho gia colpito
                if(!controller.getShootController().getTypeOfAttack().contains(1) && getPlayersInMySquare(0, squareOfPlayer).size()>1 && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect2()) )
                    usableEffects.add(3);
                break;

            case "Sledgehammer":
                if(!getPlayersInMySquare(0,squareOfPlayer).isEmpty()){
                    usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Shotgun":
                if(!getPlayersInMySquare(0, squareOfPlayer).isEmpty())
                    usableEffects.add(1);
                if(!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty())
                    usableEffects.add(4);
                break;

            case "Shockwave":
                if(!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty()) {
                    usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Furnace":
                if(!getSquaresOfVisibleRoom(0, squareOfPlayer, 0, true).isEmpty())
                    usableEffects.add(1);
                if(!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty())
                    usableEffects.add(4);
                break;

            case "Lock rifle":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && !effectAlreadyChoosen(1))
                    usableEffects.add(1);
                if(getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).size()>1 && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && effectAlreadyChoosen(1) && !effectAlreadyChoosen(2))
                    usableEffects.add(2);
                break;

            case "Zx-2":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty()) {
                    usableEffects.add(1);
                    usableEffects.add(4);
                }
                break;

            case "Machine gun":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty()) {
                    if(!effectAlreadyChoosen(1))
                        usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && effectAlreadyChoosen(1) && !effectAlreadyChoosen(2) && (!effectAlreadyChoosen(3)||controller.getShootController().getPlayersAttacked().size()>1))
                        usableEffects.add(2);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect2()) && effectAlreadyChoosen(1) && !effectAlreadyChoosen(3) && (!effectAlreadyChoosen(2)||controller.getShootController().getPlayersAttacked().size()>1)){
                        usableEffects.add(3);
                    }
                }
                break;

            case "Granade launcher": //ricordati che quando andrai a gestire quest'arma nello shoot controller devi spostare momentaneamente il player spostato nel primo effetto nel suo square originale e chiaamre lo use weapon per il secondo effetto e poi ripiazzarlo
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty())
                    usableEffects.add(1);
                if(controller.getShootController().getTypeOfAttack().contains(1) && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()))
                    usableEffects.add(2);
                break;

            case "Plasma gun":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && !effectAlreadyChoosen(1))
                    usableEffects.add(1);
                if(!effectAlreadyChoosen(2))
                    usableEffects.add(2);
                if(!effectAlreadyChoosen(3) && effectAlreadyChoosen(1) && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()))
                    usableEffects.add(3);
                break;

            case "Railgun":
                if(!getPlayersInCardinalDirections(0, squareOfPlayer).isEmpty()){
                    usableEffects.add(1);
                    usableEffects.add(4);
                }
                break;

            case "Heatseeker":
                if(!getPlayersNotVisible(0, weaponCard.getOwnerOfCard()).isEmpty() && !effectAlreadyChoosen(1))
                    usableEffects.add(1);
                break;

            case "Rocket launcher":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 1).isEmpty())
                    usableEffects.add(1);
                if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && !controller.getShootController().getTypeOfAttack().contains(2) && !controller.getShootController().getTypeOfAttack().contains(3))
                    usableEffects.add(2);
                if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect2()) && controller.getShootController().getTypeOfAttack().contains(1))
                    usableEffects.add(3); //ricordati che nel shoot controller questo va gestito alla stregue del granade launcher
                break;

            case "Hellion":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 1).isEmpty()){
                    usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Whisper":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 2).isEmpty())
                    usableEffects.add(1);
                break;

            case "THOR":
                Player referencePlayer;
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && !effectAlreadyChoosen(1))
                    usableEffects.add(1);
                if(effectAlreadyChoosen(1) && !effectAlreadyChoosen(2) && effectAffordable(weaponCard.getOwnerOfCard(),weaponCard.getPriceToPayForEffect1())) {
                    referencePlayer = controller.getShootController().getInvolvedPlayers().get(0).getPlayer();
                    if(!getVisiblePlayers(0, referencePlayer, 0).isEmpty())
                        usableEffects.add(2);
                }
                if(!effectAlreadyChoosen(3) && effectAlreadyChoosen(2) && effectAffordable(weaponCard.getOwnerOfCard(),weaponCard.getPriceToPayForEffect1())) {
                    referencePlayer = controller.getShootController().getInvolvedPlayers().get(1).getPlayer();
                    if(!getVisiblePlayers(0, referencePlayer, 0).isEmpty())
                        usableEffects.add(3);
                }
                break;

            case "Flamethrower":
                if(!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty()){
                    usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;
            case "Power glove":
                if(!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty()) {
                    usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Tractor beam":
                if(!getVisiblePlayers(2, weaponCard.getOwnerOfCard(), 0).isEmpty())
                    usableEffects.add(1);
                if(!getPlayersInMySquare(2, squareOfPlayer).isEmpty() && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                    usableEffects.add(4);
                break;

            case "Vortex cannon":
                ArrayList<Square> listOfVisibleSquares = getSquaresOfVisibleRoom(0, squareOfPlayer, 0, false);
                listOfVisibleSquares.remove(squareOfPlayer);
                for(Square square : listOfVisibleSquares){
                    if(!usableEffects.contains(1) && (!getPlayersInMySquare(0, square).isEmpty() || !getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty())){
                        usableEffects.add(1);
                    }
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && getPlayersInMySquare(0, square).size()+getPlayersOnlyInAdjSquares(0,square).size()>1 && controller.getShootController().getTypeOfAttack().contains(1) ) {
                        usableEffects.add(2);
                        break;
                    }
                }
                break;
            default: throw new IllegalArgumentException();

        }
        return usableEffects;
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
                return !getPlayersInCardinalDirections(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
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
            case "THOR":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Flamethrower":
                return !squaresUsefulForFlamethrower(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Power glove":
                return !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Tractor beam":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot+2, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Vortex cannon":
                ArrayList<Square> listOfVisibleSquares = getSquaresOfVisibleRoom(movementCanDoBeforeReloadAndShoot, squareOfPlayer, 0, false);
                listOfVisibleSquares.remove(squareOfPlayer);
                for(Square square : listOfVisibleSquares){
                    if(!getPlayersInMySquare(0, square).isEmpty() || !getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty())
                        return true;
                }
                return false;


            default:
                throw  new IllegalArgumentException();
        }
    }

    private boolean effectAlreadyChoosen(int numOfEffect){
        return controller.getShootController().getTypeOfAttack().contains(numOfEffect);
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




    private ArrayList<Square> getSquaresOfVisibleRoom(int movement, Square squareOfPlayer, int distanceNeeded, boolean differentFromMine) {
        ArrayList<Square> squaresOfVisibleRoom = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(squareOfPlayer, movement, startingSquares);
        for(Square startingSquare : startingSquares){
            for(Square square : startingSquare.getAdjSquares()) {
                if (!differentFromMine)
                    addSquaresOfThisColors(squaresOfVisibleRoom, square.getColor());
                else {
                    if (!startingSquare.getColor().equals(square.getColor()))
                        addSquaresOfThisColors(squaresOfVisibleRoom, square.getColor());
                }
            }
            if(distanceNeeded!=0)
                removeSquareCloserThan(startingSquare, squaresOfVisibleRoom,distanceNeeded);
        }
        removeSquareWithoutPlayers(squaresOfVisibleRoom);
        return squaresOfVisibleRoom;
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

    private void removeSquareWithoutPlayers(ArrayList<Square> listOfSquares){
        ArrayList<Square> squaresToRemove = new ArrayList<>();

        for(Square square : listOfSquares){
            if(square.getPlayerOnThisSquare().isEmpty())
                squaresToRemove.add(square);
        }

        for(Square squareToRemove : squaresToRemove){
            listOfSquares.remove(squareToRemove);
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

    private ArrayList<Square> squaresUsefulForFlamethrower(int movementCanDoBeforeReloadAndShoot, Square squareOfPlayer) {
        ArrayList<Square> squaresForFlamethrower = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(squareOfPlayer, movementCanDoBeforeReloadAndShoot, startingSquares);
        for(Square startingSquare : startingSquares){
            addSquaresForCardinalDirections(startingSquare, squaresForFlamethrower,2);
        }
        removeSquareWithoutPlayers(squaresForFlamethrower);
        return squaresForFlamethrower;
    }


    ArrayList<Player> getVisiblePlayers(int movement, Player playerCanSee, int distanceNeeded)
    {
        ArrayList<Player> playersVisible = new ArrayList<>();
        ArrayList<Square> squaresVisible = getSquaresOfVisibleRoom(movement, playerCanSee.getPositionOnTheMap(), distanceNeeded,false);
        for(Square square : squaresVisible){
            for(Player playerInSquare : square.getPlayerOnThisSquare() ){
                if(!playerInSquare.equals(playerCanSee) && !playersVisible.contains(playerInSquare))
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

    private ArrayList<Player> getPlayersInCardinalDirections(int movement, Square square)
    {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        ArrayList<Square> squaresAvailable = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(square, movement, startingSquares);
        for(Square startingSquare : startingSquares){
            addSquaresForCardinalDirections(startingSquare,squaresAvailable,0);
        }

        for(Square squareAvailable : squaresAvailable ){
            for(Player player : squareAvailable.getPlayerOnThisSquare()){
                if(!player.equals(game.getPlayers().get(game.getPlayerOfTurn()-1)))
                    players.add(player);
            }
        }

        return players;
    }

     void addSquaresForCardinalDirections(Square startingSquare, ArrayList<Square> squaresAvailable, int limitOfDistance) {
        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
        int row = startingSquare.getRow();
        int column = startingSquare.getColumn();
        for (Square square : matrix[row]) {
            if (square != null && !squaresAvailable.contains(square))
                squaresAvailable.add(square);
        }
        for (int i = 0; i < 3; i++) {
            if (matrix[i][column] != null && !squaresAvailable.contains(matrix[i][column]))
                squaresAvailable.add(matrix[i][column]);
        }

        ArrayList<Square> squaresCloserThanDistance = new ArrayList<>();
        ArrayList<Square> squaresToRemove = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(startingSquare, limitOfDistance, squaresCloserThanDistance);
        for (Square squareAvailable : squaresAvailable) {
            if (!squaresCloserThanDistance.contains(squareAvailable))
                squaresToRemove.add(squareAvailable);
        }

        for (Square squareToRemove : squaresToRemove) {
            squaresAvailable.remove(squareToRemove);
        }

    }

    /**
     * returns the players that the attacking player not can see

     * @return List of players
     */


    ArrayList<Player> getPlayersNotVisible(int movement, Player playerCanSee)
    {
        ArrayList<Player> playersNotVisible = new ArrayList<>();
        while(movement>=0) {
            ArrayList<Player> playersVisible = getVisiblePlayers(movement, playerCanSee, 0);
            for (Player player : game.getPlayers()) {
                if (!playersVisible.contains(player) && !playersNotVisible.contains(player) && !player.equals(playerCanSee))
                    playersNotVisible.add(player);
            }
            movement--;
        }
        return playersNotVisible;
    }


    private boolean effectAffordable(Player player, ColorOfCard_Ammo[] price){
        int numOfRedAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, RED);
        int numOfBlueAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, BLUE);
        int numOfYellowAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, YELLOW);
        return controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, RED, true) >= numOfRedAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, BLUE, true) >= numOfBlueAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, YELLOW, true)>=numOfYellowAmmoRequired;

    }


}
