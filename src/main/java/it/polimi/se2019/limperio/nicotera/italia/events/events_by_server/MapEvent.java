package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

/**
 * Event for update the state of map view in the remote view of a player.
 * @author Pietro L'Imperio
 */
public class MapEvent extends ServerEvent {

    /**
     * List of alias cards representing weapon cards in the red spawn square
     */
    private ArrayList<AliasCard> weaponsCardsForRedSpawnSquare;
    /**
     * List of alias cards representing weapon cards in the blue spawn square
     */
    private ArrayList<AliasCard> weaponsCardsForBlueSpawnSquare;
    /**
     * List of alias cards representing weapon cards in the yellow spawn square
     */
    private ArrayList<AliasCard> weaponsCardsForYellowSpawnSquare;
    /**
     * The map (only the matrix) updated in {@link MapEvent}
     */
    private Square[][] map = new Square[3][4];

    /**
     * The number that represents the type of map that players are playing with.
     */
    private int typeOfMap;

    /**
     * It's true if the game started with the terminator mode active, otherwise false.
     */
    private boolean terminatorMode;
    /**
     * It's true if the update of the map is caused by the use of teleporter by some players, otherwise false.
     */
    private boolean isForTeleporter = false;
    /**
     * It's true if the update of the map is caused by the use of Newton by some players, otherwise false.
     */
    private boolean isForNewton = false;
    /**
     * It's true if the update of the map is caused for the movement of the player before to shoot.
     */
    private boolean isBeforeToShoot = false;
    /**
     * Constructor that sets true the boolean field relative of this event.
     */

    public MapEvent() {
        setMapEvent();
    }

    /**
     * Sets alias cards representing weapon cards placed in a spawn square in the correct list.
     * @param square The spawn square considered.
     */
    private void setWeaponsCardsForSpawnSquare(SpawnSquare square) {
        ArrayList<AliasCard> weaponsCardsForSpawn = new ArrayList<>();
        for(WeaponCard card : square.getWeaponCards()){
            AliasCard aliasCard = new AliasCard(card.getName(),card.getDescription(),card.getColor());
            aliasCard.setNameOfEffects(card.getNamesOfAttack());
            aliasCard.setDescriptionOfEffects(card.getDescriptionsOfAttack());
            weaponsCardsForSpawn.add(aliasCard);
        }
        if(square.getColor().equals(ColorOfFigure_Square.RED)) {
            this.weaponsCardsForRedSpawnSquare = weaponsCardsForSpawn;
            return;
        }
        if(square.getColor().equals(ColorOfFigure_Square.BLUE)){
            this.weaponsCardsForBlueSpawnSquare = weaponsCardsForSpawn;
            return;
        }
        if(square.getColor().equals(ColorOfFigure_Square.YELLOW))
            this.weaponsCardsForYellowSpawnSquare = weaponsCardsForSpawn;

    }

    /**
     * Finds spawn square in the map and puts alias cards of the relative weapon cards placed in that
     * square in the correct list.
     * @param matrixOfSquares The matrix representing the map of the game.
     */
    private void setWeaponsWithTheirAlias(Square[][] matrixOfSquares) {
        for (Square[] rowSquare: matrixOfSquares) {
            for(Square square : rowSquare) {
                if (square != null && square.isSpawn()) {
                    setWeaponsCardsForSpawnSquare((SpawnSquare) square);
                }
            }
        }
    }


    /**
     * Sets the matrix of square cloning all of the squares in the right position.
     * @param map Matrix of the squares the make the map.
     */
    public void setMap(Square[][] map) {
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]!=null) {
                    if (map[i][j].isSpawn())
                        this.map[i][j] = (Square) ((SpawnSquare) map[i][j]).clone();
                    else
                        this.map[i][j] = (Square) ((NormalSquare) map[i][j]).clone();
                }
            }
        }
        setWeaponsWithTheirAlias(map);
    }

    public Square[][] getMap() {
        return map;
    }

    public ArrayList<AliasCard> getWeaponsCardsForRedSpawnSquare() {
        return weaponsCardsForRedSpawnSquare;
    }

    public int getTypeOfMap() {
        return typeOfMap;
    }

    public void setTypeOfMap(int typeOfMap) {
        this.typeOfMap = typeOfMap;
    }

    public ArrayList<AliasCard> getWeaponsCardsForBlueSpawnSquare() {
        return weaponsCardsForBlueSpawnSquare;
    }


    public ArrayList<AliasCard> getWeaponsCardsForYellowSpawnSquare() {
        return weaponsCardsForYellowSpawnSquare;
    }

    public boolean isTerminatorMode() {
        return terminatorMode;
    }

    public void setTerminatorMode(boolean terminatorMode) {
        this.terminatorMode = terminatorMode;
    }

    public boolean isForTeleporter() {
        return isForTeleporter;
    }

    public void setForTeleporter(boolean forTeleporter) {
        isForTeleporter = forTeleporter;
    }

    public boolean isForNewton() {
        return isForNewton;
    }

    public void setForNewton(boolean forNewton) {
        isForNewton = forNewton;
    }

    public boolean isBeforeToShoot() {
        return isBeforeToShoot;
    }

    public void setBeforeToShoot(boolean beforeToShoot) {
        isBeforeToShoot = beforeToShoot;
    }
}
