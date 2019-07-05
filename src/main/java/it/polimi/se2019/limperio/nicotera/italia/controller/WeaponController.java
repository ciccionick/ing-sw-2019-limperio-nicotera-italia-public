package it.polimi.se2019.limperio.nicotera.italia.controller;


import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * Controller used to check the usability of weapons. Collaborates with Shoot controller to guide the player in his shoot action.
 * @author  Giuseppe Italia
 */
public class WeaponController {

    /**
     * The reference of the game.
     */
    private final Game game;
    /**
     * The reference of the controller.
     */
    private final Controller controller;

    /**
     * Constructor that initializes game and controller references.
     */
    public WeaponController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }


    /**
     * Gets, for each weapon passed by parameter, an array list containing the numbers of the effect usable by a weapon.
     * The control happens checking if the effect is affordable, usable for the position of the owner in the moment that is called and for the previous choices of the player.
     * 1 is for the basic effect or basic mode.
     * 2 is for the first extra effect.
     * 3 is for the second extra effect.
     * 4 is for the alternative mode
     * @param weaponCard Weapon card to check which effects has usable.
     * @return Array list with the number relative to the effects usable by the weapon passed by parameter.
     */
    ArrayList<Integer> getUsableEffectsForThisWeapon(WeaponCard weaponCard) {
        ArrayList<Integer> usableEffects = new ArrayList<>();
        Square squareOfPlayer = weaponCard.getOwnerOfCard().getPositionOnTheMap();
        switch (weaponCard.getName()) {
            case "Electroscythe":
                if (!getPlayersInMySquare(0, squareOfPlayer).isEmpty() && !controller.getShootController().getTypeOfAttack().contains(1) && !controller.getShootController().getTypeOfAttack().contains(4)) {
                    usableEffects.add(1);
                    if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Cyberblade":
                if (!getPlayersInMySquare(0, squareOfPlayer).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                if (!effectAlreadyChosen(2))
                    usableEffects.add(2);
                if(!effectAlreadyChosen(3) && effectAlreadyChosen(1) && !(getPlayersInMySquare(0, squareOfPlayer).size()==1 && getPlayersInMySquare(0, squareOfPlayer).contains(controller.getShootController().getPlayersAttacked().get(0).getNickname())))
                    usableEffects.add(3);
                break;

            case "Sledgehammer":
                if (!getPlayersInMySquare(0, squareOfPlayer).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty()) {
                    usableEffects.add(1);
                    if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Shotgun":
                if(!getPlayersInMySquare(0, squareOfPlayer).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty())
                    usableEffects.add(1);
                if(!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty())
                    usableEffects.add(4);
                break;

            case "Shockwave":
                if(!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty()) {
                    usableEffects.add(1);
                    if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()) && controller.getShootController().getTypeOfAttack().isEmpty())
                        usableEffects.add(4);
                }
                break;

            case "Furnace":
                ArrayList<Square> squares = new ArrayList<>(getSquaresOfVisibleRoom(0, squareOfPlayer, 0, true));
                removeSquareWithoutPlayers(squares);
                if (!squares.isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty())
                    usableEffects.add(1);
                if (!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty())
                    usableEffects.add(4);
                break;

            case "Lock rifle":
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                if (getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).size() > 1 && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && effectAlreadyChosen(1) && !effectAlreadyChosen(2))
                    usableEffects.add(2);
                break;

            case "Zx-2":
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty()) {
                    usableEffects.add(1);
                    usableEffects.add(4);
                }
                break;

            case "Machine gun":
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty()) {
                    if (!effectAlreadyChosen(1))
                        usableEffects.add(1);
                    if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && effectAlreadyChosen(1) && !effectAlreadyChosen(2) && (!effectAlreadyChosen(3) || controller.getShootController().getPlayersAttacked().size() > 1))
                        usableEffects.add(2);
                    if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect2()) && effectAlreadyChosen(1) && !effectAlreadyChosen(3) && (!effectAlreadyChosen(2) || controller.getShootController().getPlayersAttacked().size() > 1)) {
                        usableEffects.add(3);
                    }
                }
                break;

            case "Granade launcher":
                if(!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                if(effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && !effectAlreadyChosen(2) && effectAlreadyChosen(1))
                    usableEffects.add(2);
                break;

            case "Plasma gun":
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                if (!effectAlreadyChosen(2) && !effectAlreadyChosen(3))
                    usableEffects.add(2);
                if (!effectAlreadyChosen(3) && effectAlreadyChosen(1) && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()))
                    usableEffects.add(3);
                break;

            case "Railgun":
                if (!getPlayersInCardinalDirections(0, squareOfPlayer, true).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty()) {
                    usableEffects.add(1);
                    usableEffects.add(4);
                }
                break;

            case "Heatseeker":
                if (!getPlayersNotVisible(0, weaponCard.getOwnerOfCard()).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                break;

            case "Rocket launcher":
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 1).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()) && !effectAlreadyChosen(2) && !effectAlreadyChosen(3))
                    usableEffects.add(2);
                if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect2()) && !effectAlreadyChosen(3) && effectAlreadyChosen(1))
                    usableEffects.add(3);
                break;

            case "Hellion":
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 1).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty()) {
                    usableEffects.add(1);
                    if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Whisper":
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 2).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                break;

            case "THOR":
                Player referencePlayer;
                if (!getVisiblePlayers(0, weaponCard.getOwnerOfCard(), 0).isEmpty() && !effectAlreadyChosen(1))
                    usableEffects.add(1);
                if (effectAlreadyChosen(1) && !effectAlreadyChosen(2) && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1())) {
                    referencePlayer = controller.getShootController().getInvolvedPlayers().get(0).getPlayer();
                    if (!getVisiblePlayers(0, referencePlayer, 0).isEmpty())
                        usableEffects.add(2);
                }
                if (!effectAlreadyChosen(3) && effectAlreadyChosen(2) && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1())) {
                    referencePlayer = controller.getShootController().getInvolvedPlayers().get(1).getPlayer();
                    if (!getVisiblePlayers(0, referencePlayer, 0).isEmpty())
                        usableEffects.add(3);
                }
                break;

            case "Flamethrower":
                if (!squaresUsefulForFlamethrower(0, squareOfPlayer).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty()) {
                    usableEffects.add(1);
                    if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;
            case "Power glove":
                if (!getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty()) {
                    usableEffects.add(1);
                    if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()))
                        usableEffects.add(4);
                }
                break;

            case "Tractor beam":
                if(!getPlayersCouldBeAttackedFromBasicEffectOfTractorBeam(weaponCard).isEmpty() && controller.getShootController().getTypeOfAttack().isEmpty())
                    usableEffects.add(1);
                if(!getPlayersInMySquare(2, squareOfPlayer).isEmpty() && effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForAlternativeMode()) && controller.getShootController().getTypeOfAttack().isEmpty())
                    usableEffects.add(4);
                break;

            case "Vortex cannon":
                ArrayList<Square> listOfVisibleSquares = getSquaresOfVisibleRoom(0, squareOfPlayer, 0, false);
                listOfVisibleSquares.remove(squareOfPlayer);
                for (Square square : listOfVisibleSquares) {
                    if (!effectAlreadyChosen(1) && (!getPlayersInMySquare(0, square).isEmpty() || !getPlayersOnlyInAdjSquares(0, squareOfPlayer).isEmpty())) {
                        usableEffects.add(1);
                        break;
                    }
                }
                if(effectAlreadyChosen(1) && !effectAlreadyChosen(2)){
                    if(controller.getShootController().getInvolvedPlayers().get(0).getSquare().getPlayerOnThisSquare().size()>1 || !getPlayersOnlyInAdjSquares(0, controller.getShootController().getInvolvedPlayers().get(0).getSquare()).isEmpty())
                        usableEffects.add(2);
                }
                break;
            default:
                throw new IllegalArgumentException();

        }
        return usableEffects;
    }

    /**
     * Gets the list of players that could be attacked from the basic effect of tractor beam.
     * @param weaponCard The weapon card that is involved in the choice of the effect.
     * @return Array list of players could be attacked from basic effect of tractor beam
     */
     ArrayList<Player> getPlayersCouldBeAttackedFromBasicEffectOfTractorBeam(WeaponCard weaponCard) {
        ArrayList<Player> playersToReturn = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            if(!getSquareCouldBeSelectedForTractorBeam(weaponCard, player).isEmpty() && player.getPositionOnTheMap()!=null)
                playersToReturn.add(player);
        }
        playersToReturn.remove(weaponCard.getOwnerOfCard());
        return playersToReturn;
    }

    /**
     * Gets an array list with the squares that could be selected to use correctly the effects of tractor beam.
     * @param weaponCard Weapon card involved in the check.
     * @param player Owner of the weapon card.
     * @return Array list with the squares of the map selectable to use the effect of tractor beam.
     */
    ArrayList<Square> getSquareCouldBeSelectedForTractorBeam(WeaponCard weaponCard, Player player){
        ArrayList<Square> squares = new ArrayList<>();
        ArrayList<Square> squareReachableFromPlayer = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(player.getPositionOnTheMap(), 2, squareReachableFromPlayer);
        for (Square square : squareReachableFromPlayer) {
            if (getSquaresOfVisibleRoom(0, weaponCard.getOwnerOfCard().getPositionOnTheMap(), 0, false).contains(square)) {
                squares.add(square);
            }
        }
        return squares;
    }

    /**
     * Checks if a weapon is usable. To do this checks if there is at least one effect usable. This control is made by to enable the button shoot on the GUI in the right moment, when the player could really shoot.
     * @param weaponCard Weapon card to check if has at least one effect usable.
     * @param movementCanDoBeforeReloadAndShoot Movement that player could do before to shoot because has more than six damage or becuase the game is in frenzy mode.
     * @return True if the weapon passed by parameter is usable, false otherwise.
     */
    boolean isThisWeaponUsable(WeaponCard weaponCard, int movementCanDoBeforeReloadAndShoot) {
        Square squareOfPlayer = weaponCard.getOwnerOfCard().getPositionOnTheMap();
        switch (weaponCard.getName()) {
            case "Electroscythe":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Cyberblade":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot + 1, squareOfPlayer).isEmpty();
            case "Sledgehammer":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Shotgun":
                return !getPlayersInMySquare(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty() || !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Shockwave":
                return !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty();
            case "Furnace":
                ArrayList<Player> players = new ArrayList<>();
                for(Square square : getSquaresOfVisibleRoom(0, squareOfPlayer, 0, true)){
                    players.addAll(square.getPlayerOnThisSquare());
                }
                return !getPlayersOnlyInAdjSquares(movementCanDoBeforeReloadAndShoot, squareOfPlayer).isEmpty() || !players.isEmpty();
            case "Lock rifle":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Zx-2":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Machine gun":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Granade launcher":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Plasma gun":
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot + 2, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Railgun":
                return !getPlayersInCardinalDirections(movementCanDoBeforeReloadAndShoot, squareOfPlayer, true).isEmpty();
            case "Heatseeker":
                return !getPlayersNotVisible(movementCanDoBeforeReloadAndShoot, weaponCard.getOwnerOfCard()).isEmpty();
            case "Rocket launcher":
                int numOfExtraMovement = 0;
                if (effectAffordable(weaponCard.getOwnerOfCard(), weaponCard.getPriceToPayForEffect1()))
                    numOfExtraMovement = 2;
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot + numOfExtraMovement, weaponCard.getOwnerOfCard(), 1).isEmpty();
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
                return !getVisiblePlayers(movementCanDoBeforeReloadAndShoot + 2, weaponCard.getOwnerOfCard(), 0).isEmpty();
            case "Vortex cannon":
                ArrayList<Square> listOfVisibleSquares = getSquaresOfVisibleRoom(movementCanDoBeforeReloadAndShoot, squareOfPlayer, 0, false);
                listOfVisibleSquares.remove(squareOfPlayer);
                for (Square square : listOfVisibleSquares) {
                    if (!getPlayersInMySquare(0, square).isEmpty() || !getPlayersOnlyInAdjSquares(0, square).isEmpty())
                        return true;
                }
                return false;


            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if an effect has been already chosen during the shoot action.
     * @param numOfEffect Number of the effect that the methods has to check if it has been already chosen.
     * @return True if the effect passed by parameter has been already chosen, false otherwise.
     */
    private boolean effectAlreadyChosen(int numOfEffect) {
        return controller.getShootController().getTypeOfAttack().contains(numOfEffect);
    }


    /**
     * Gets the list of nicknames of players in the square passed by parameter.
     * @param movement Movement that the player owner of the weapon card could do before to choose a player or more than a player among them contained in the list.
     * @param square Square where check the players contained on it.
     * @return  Array list of nicknames of the player in the square passed by parameter.
     */
    ArrayList<String> getPlayersInMySquare(int movement, Square square) {
        ArrayList<String> playersInTheSquare = new ArrayList<>();
        Player player = game.getPlayers().get(game.getPlayerOfTurn() - 1);
        ArrayList<Square> squaresReachable = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(square, movement, squaresReachable);
        for (Square squareReachable : squaresReachable) {
            for (Player playerInTheSquare : squareReachable.getPlayerOnThisSquare()) {
                if (!playerInTheSquare.equals(player))
                    playersInTheSquare.add(playerInTheSquare.getNickname());
            }
        }
        return playersInTheSquare;
    }


    /**
     * Gets the list of players on the squares of the adjacency of the squareOfPlayer passed by parameter.
     * @param movement Possibly movements to do before to check the player in the adjacency.
     * @param squareOfPlayer Square of the player that is interested to know who there are on the adjacency
     * @return Array list of players on the adjacent squares of the squareOfPlayer.
     */
    ArrayList<Player> getPlayersOnlyInAdjSquares(int movement, Square squareOfPlayer) {
        ArrayList<Player> playersOnlyInAdjSquares = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        ArrayList<Square> adjSquares = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(squareOfPlayer, movement, startingSquares);
        for (Square square : startingSquares) {
            for (Square adjSquare : square.getAdjSquares()) {
                if (!adjSquares.contains(adjSquare))
                    adjSquares.add(adjSquare);
            }
        }
        for (Square square : adjSquares) {
            for (Player player : square.getPlayerOnThisSquare()) {
                if (!playersOnlyInAdjSquares.contains(player))
                    playersOnlyInAdjSquares.add(player);
            }
        }

        return playersOnlyInAdjSquares;
    }


    /**
     * Gets the squares of visible room for a player that is on squareOfPlayer.
     * @param movement Possibly movements that a player could do before to check which squares are visible.
     * @param squareOfPlayer Reference square.
     * @param distanceNeeded distance within which consider the squares.
     * @param differentFromMine It's true when the squares of the room of squareOfPlayer has to be not considered, false otherwise.
     * @return Array list of the square of visible rooms.
     */
    ArrayList<Square> getSquaresOfVisibleRoom(int movement, Square squareOfPlayer, int distanceNeeded, boolean differentFromMine) {
        ArrayList<Square> squaresOfVisibleRoom = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(squareOfPlayer, movement, startingSquares);
        for (Square startingSquare : startingSquares) {
            for (Square square : startingSquare.getAdjSquares()) {
                if (!differentFromMine)
                    addSquaresOfThisColors(squaresOfVisibleRoom, square.getColor());
                else {
                    if (!startingSquare.getColor().equals(square.getColor()))
                        addSquaresOfThisColors(squaresOfVisibleRoom, square.getColor());
                }
            }
            if (distanceNeeded != 0)
                removeSquareCloserThan(startingSquare, squaresOfVisibleRoom, distanceNeeded);
        }
        return squaresOfVisibleRoom;
    }

    /**
     * Removes from a list of squares the squares closer thant distanceNeeded with the reference of startingSquare.
     * @param startingSquare The square of reference from that calculate the distance.
     * @param listOfSquareFromWhereRemoveSquares List of square from where remove squares.
     * @param distanceNeeded Distance within which to consider the squares
     */
    private void removeSquareCloserThan(Square startingSquare, ArrayList<Square> listOfSquareFromWhereRemoveSquares, int distanceNeeded) {
        ArrayList<Square> squareToRemove = new ArrayList<>();
        for (Square square : listOfSquareFromWhereRemoveSquares) {
            if (distanceBetweenSquare(startingSquare, square) < distanceNeeded)
                squareToRemove.add(square);
        }

        for (Square square : squareToRemove) {
            listOfSquareFromWhereRemoveSquares.remove(square);
        }
    }

    /**
     * Removes from a list of squares the squares without players.
     * @param listOfSquares List of squares from where remove squares without players.
     */
     void removeSquareWithoutPlayers(ArrayList<Square> listOfSquares) {
        ArrayList<Square> squaresToRemove = new ArrayList<>();

        for (Square square : listOfSquares) {
            if (square.getPlayerOnThisSquare().isEmpty())
                squaresToRemove.add(square);
        }

        for (Square squareToRemove : squaresToRemove) {
            listOfSquares.remove(squareToRemove);
        }
    }

    /**
     * Gets the distance between two squares.
     * @param startingSquare Square of reference.
     * @param square Square from which calculate the distance.
     * @return The distance between the two squares passed by parameter.
     */
    private int distanceBetweenSquare(Square startingSquare, Square square) {
        if (startingSquare.equals(square))
            return 0;
        ArrayList<Square> squaresReachable = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            controller.findSquaresReachableWithThisMovements(startingSquare, i, squaresReachable);
            if (squaresReachable.contains(square))
                return i;
            squaresReachable = new ArrayList<>();
        }
        throw new IllegalArgumentException();
    }


    /**
     * Adds to the list of squares all of the squares of the color passed by parameter.
     * @param squaresOfVisibleRoomDifferentFromMine List of square where add squares.
     * @param color Color of the squares to add.
     */
    private void addSquaresOfThisColors(ArrayList<Square> squaresOfVisibleRoomDifferentFromMine, ColorOfFigure_Square color) {
        for (Square[] squares : game.getBoard().getMap().getMatrixOfSquares()) {
            for (Square square : squares) {
                if (square != null && square.getColor().equals(color) && !squaresOfVisibleRoomDifferentFromMine.contains(square))
                    squaresOfVisibleRoomDifferentFromMine.add(square);
            }
        }
    }

    /**
     * Gets the squares useful to use the effects of flamethrower.
     * @param movementCanDoBeforeReloadAndShoot Possibly movements that the player has to use flamethrower could do before to use the weapon.
     * @param squareOfPlayer Square of reference from where checks with squares are available to be added to the list.
     * @return Array list of squares useful to use effect of flamethrower.
     */
    private ArrayList<Square> squaresUsefulForFlamethrower(int movementCanDoBeforeReloadAndShoot, Square squareOfPlayer) {
        ArrayList<Square> squaresForFlamethrower = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(squareOfPlayer, movementCanDoBeforeReloadAndShoot, startingSquares);
        for (Square startingSquare : startingSquares) {
            addSquaresForCardinalDirections(startingSquare, squaresForFlamethrower, 2, false);
        }
        removeSquareWithoutPlayers(squaresForFlamethrower);
        return squaresForFlamethrower;
    }


    /**
     * Gets the list of players visible by the player passed by parameter.
     * @param movement Possibly movement that the player passed by parameter could do before to check which player can see.
     * @param playerCanSee Player interested to know which players is able to see.
     * @param distanceNeeded Distance within which to consider the squares.
     * @return Array list of players visible by the player passed by parameter.
     */
    ArrayList<Player> getVisiblePlayers(int movement, Player playerCanSee, int distanceNeeded) {
        ArrayList<Player> playersVisible = new ArrayList<>();
        ArrayList<Square> squaresVisible = getSquaresOfVisibleRoom(movement, playerCanSee.getPositionOnTheMap(), distanceNeeded, false);
        for (Square square : squaresVisible) {
            for (Player playerInSquare : square.getPlayerOnThisSquare()) {
                if (!playerInSquare.equals(playerCanSee) && !playersVisible.contains(playerInSquare))
                    playersVisible.add(playerInSquare);
            }
        }
        playersVisible.remove(playerCanSee);
        if (playerCanSee.getNickname().equals("terminator"))
            playersVisible.remove(game.getPlayers().get(game.getPlayerOfTurn() - 1));
        return playersVisible;
    }

    /**
     * Gets the list of players positioned in squares on cardinal directions respect to the square passed by parameter.
     * @param movement Possibly movements could be done before to check the players available to be added to the list.
     * @param square Square of reference from where cehck the players available to be added to the list.
     * @return List of players positioned in squares on cardinal directions respect to the square passed by parameter.
     */

     ArrayList<Player> getPlayersInCardinalDirections(int movement, Square square, boolean ignoreWalls) {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Square> startingSquares = new ArrayList<>();
        ArrayList<Square> squaresAvailable = new ArrayList<>();
        controller.findSquaresReachableWithThisMovements(square, movement, startingSquares);
        for (Square startingSquare : startingSquares) {
            addSquaresForCardinalDirections(startingSquare, squaresAvailable, 0, ignoreWalls);
        }

        for (Square squareAvailable : squaresAvailable) {
            for (Player player : squareAvailable.getPlayerOnThisSquare()) {
                if (!player.equals(game.getPlayers().get(game.getPlayerOfTurn() - 1)))
                    players.add(player);
            }
        }

        return players;
    }


    /**
     * Adds to a list of squares all of the squares on the cardinal directions respect the startinSquare passed by parameter.
     * @param startingSquare Square from where checks squares on its cardinal directions.
     * @param squaresAvailable List of squares where add the square on cardinal directions respect squaresAvailable.
     * @param limitOfDistance Distance within which to consider the squares.
     * @param ignoreWalls It's true if in the research of square is possible ignore walls, false otherwise.
     */
    void addSquaresForCardinalDirections(Square startingSquare, ArrayList<Square> squaresAvailable, int limitOfDistance, boolean ignoreWalls) {
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
        if(!ignoreWalls) {
            controller.findSquaresReachableWithThisMovements(startingSquare, limitOfDistance, squaresCloserThanDistance);
            for (Square squareAvailable : squaresAvailable) {
                if (!squaresCloserThanDistance.contains(squareAvailable))
                    squaresToRemove.add(squareAvailable);
            }

            for (Square squareToRemove : squaresToRemove) {
                squaresAvailable.remove(squareToRemove);
            }
            squaresAvailable.remove(startingSquare);
        }

    }

    /**
     * Gets players not visible by the player passed by parameter.
     * @param movement Movement that playerCanSee could do before the research of players not visible.
     * @param playerCanSee Player interested to know the players for him not visible.
     * @return Array list of players not visible by playerCanSee.
     */
    ArrayList<Player> getPlayersNotVisible(int movement, Player playerCanSee) {
        ArrayList<Player> playersNotVisible = new ArrayList<>();
        while (movement >= 0) {
            ArrayList<Player> playersVisible = getVisiblePlayers(movement, playerCanSee, 0);
            for (Player player : game.getPlayers()) {
                if (!playersVisible.contains(player) && !playersNotVisible.contains(player) && !player.equals(playerCanSee))
                    playersNotVisible.add(player);
            }
            movement--;
        }
        ArrayList<Player> playersNotAlreadySpawn = new ArrayList<>();
        for(Player player : game.getPlayers()){
            if(player.getPositionOnTheMap()==null)
                playersNotAlreadySpawn.add(player);
        }
        for(Player player : playersNotAlreadySpawn){
           playersNotVisible.remove(player);
        }
        return playersNotVisible;
    }


    /**
     * Checks if an effect is affordable by  a player passed by parameter.
     * @param player Player that wants to use the effect involved.
     * @param price The price to pay for the effect.
     * @return True if the effect is affordable, false otherwise.
     */
    private boolean effectAffordable(Player player, ColorOfCard_Ammo[] price) {
        int numOfRedAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, RED);
        int numOfBlueAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, BLUE);
        int numOfYellowAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(price, YELLOW);
        return controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, RED, true) >= numOfRedAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, BLUE, true) >= numOfBlueAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, YELLOW, true) >= numOfYellowAmmoRequired;
    }

    /**
     * Gets the square useful to use alternative mode for Power glove and Flamethrower.
     * @param squareOfAttacker Square where there is the attacker.
     * @param squareOfFirstAttacked Square where there is the first player attacked during the shoot action.
     * @return Square usable for the alternative mode of power glove and flamethrower.
     */
    Square getSquareForAlternativeModeOfPowerGloveAndFlamethrower(Square squareOfAttacker, Square squareOfFirstAttacked) {
        Square[][] matrixOfSquare = game.getBoard().getMap().getMatrixOfSquares();
        if (squareOfAttacker.getRow() == squareOfFirstAttacked.getRow()) {
            if (squareOfAttacker.getColumn() < squareOfFirstAttacked.getColumn()) {
                if (squareOfFirstAttacked.getColumn()+1<4 && matrixOfSquare[squareOfAttacker.getRow()][squareOfFirstAttacked.getColumn() + 1] != null)
                    return matrixOfSquare[squareOfAttacker.getRow()][squareOfFirstAttacked.getColumn() + 1];
                else
                    return null;
            } else {
                if (squareOfFirstAttacked.getColumn()-1>=0 && matrixOfSquare[squareOfAttacker.getRow()][squareOfFirstAttacked.getColumn() - 1] != null)
                    return matrixOfSquare[squareOfAttacker.getRow()][squareOfFirstAttacked.getColumn() - 1];
                else
                    return null;
            }
        } else {
            if (squareOfAttacker.getRow() < squareOfFirstAttacked.getRow()) {
                if (squareOfFirstAttacked.getRow()+1<3 && matrixOfSquare[squareOfFirstAttacked.getRow()+1][squareOfAttacker.getColumn()] != null)
                    return matrixOfSquare[squareOfFirstAttacked.getRow()+1][squareOfAttacker.getColumn()];
                else
                    return null;
            } else {
                if (squareOfFirstAttacked.getRow()-1>=0 && matrixOfSquare[squareOfFirstAttacked.getRow()-1][squareOfAttacker.getColumn()] != null)
                    return matrixOfSquare[squareOfFirstAttacked.getRow()-1][squareOfAttacker.getColumn()];
                else
                    return null;
            }
        }
    }

    /**
     * Gets the list of players selectable to use the alternative mode of railgun.
     * @param squareOfAttacker Square of the attacker player.
     * @param squareOfFirstAttacked Square of the first player attacked during the shoot action.
     * @return The list of the players that could be attacked by the alternative mode of railgun.
     */
    ArrayList<Player> getPlayersForAlternativeModeOfRailgun(Square squareOfAttacker, Square squareOfFirstAttacked){
        ArrayList<Player> playersToReturn = new ArrayList<>();
        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
        int i;
        if(squareOfAttacker.getRow() == squareOfFirstAttacked.getRow()){
            i=squareOfAttacker.getColumn();
            if(squareOfAttacker.getColumn()<squareOfFirstAttacked.getColumn()) {
                while (i <= 3) {
                    if (matrix[squareOfAttacker.getRow()][i] != null) {
                        playersToReturn.addAll(matrix[squareOfAttacker.getRow()][i].getPlayerOnThisSquare());
                    }
                        i++;
                }
            }
            else{
                while(i>=0){
                    if(matrix[squareOfAttacker.getRow()][i]!=null) {
                        playersToReturn.addAll(matrix[squareOfAttacker.getRow()][i].getPlayerOnThisSquare());
                    }
                        i--;
                }
            }
        }
        else{
            i = squareOfAttacker.getRow();
            if(squareOfAttacker.getRow()<squareOfFirstAttacked.getRow()){
                while(i<=2){
                    if(matrix[i][squareOfAttacker.getColumn()]!=null)
                        playersToReturn.addAll(matrix[i][squareOfAttacker.getColumn()].getPlayerOnThisSquare());
                i++;
                }
            }
            else{
                while(i>=0){
                    if(matrix[i][squareOfAttacker.getColumn()]!=null)
                        playersToReturn.addAll(matrix[i][squareOfAttacker.getColumn()].getPlayerOnThisSquare());
                    i--;
                }
            }
        }
        playersToReturn.remove(game.getPlayers().get(game.getPlayerOfTurn()-1));
        return playersToReturn;
    }

    /**
     * Gets the square useful for the effect of vortex cannon.
     * @param squareOfPlayer Square of the player that wants to open the vortex cannon.
     * @return Array list of the squares available to open a vortex on it.
     */
    ArrayList<Square> getSquaresForVortexCannon(Square squareOfPlayer){
        ArrayList<Square> squaresAvailableForVortex = getSquaresOfVisibleRoom(0, squareOfPlayer, 0, false);
        squaresAvailableForVortex.remove(squareOfPlayer);
        Player ownerOfCard = game.getPlayers().get(game.getPlayerOfTurn()-1);
        ArrayList<Square> squaresToRemove = new ArrayList<>();
        boolean isToRemove = true;
        for(Square square : squaresAvailableForVortex){
            if(!square.getPlayerOnThisSquare().isEmpty())
                isToRemove = false;
            else {
                for (Square adjSquare : square.getAdjSquares()) {
                    if ((adjSquare.getPlayerOnThisSquare().contains(ownerOfCard)&&adjSquare.getPlayerOnThisSquare().size()>1)||(!adjSquare.getPlayerOnThisSquare().contains(ownerOfCard)&&!adjSquare.getPlayerOnThisSquare().isEmpty())) {
                        isToRemove = false;
                        break;
                    }
                }
            }
            if(isToRemove && !squaresToRemove.contains(square))
                squaresToRemove.add(square);
            isToRemove = true;
        }
        for(Square squareToRemove : squaresToRemove){
            squaresAvailableForVortex.remove(squareToRemove);
        }
        return squaresAvailableForVortex;
    }

}
