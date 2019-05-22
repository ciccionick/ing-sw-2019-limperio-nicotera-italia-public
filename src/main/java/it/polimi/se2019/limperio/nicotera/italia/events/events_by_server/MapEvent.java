package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Event for notify a player about creation or update of map.
 *
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

    private int typeOfMap;

    private boolean terminatorMode;

    public MapEvent() {
        setMapEvent(true);
    }

    public ArrayList<AliasCard> getWeaponsCardsForRedSpawnSquare() {
        return weaponsCardsForRedSpawnSquare;
    }

    /**
     * Sets alias cards representing weapon cards placed in a spawn square in the correct list.
     *
     * @param square The spawn square considered.
     */
    private void setWeaponsCardsForSpawnSquare(SpawnSquare square) {
        ArrayList<AliasCard> weaponsCardsForSpawn = new ArrayList<>();
        for(WeaponCard card : square.getWeaponCards()){
            weaponsCardsForSpawn.add(new AliasCard(card.getName(),card.getDescription(),card.getColor()));
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

    public Square[][] getMap() {
        return map;
    }

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

    /**
     * Finds spawn square in the map and puts alias cards of the relative weapon cards placed in that
     * square in the correct list.
     * @param matrixOfSquares The matrix representing the map of the game.
     */
    public void setWeaponsWithTheirAlias(Square[][] matrixOfSquares) {
        for (Square[] rowSquare: matrixOfSquares) {
            for(Square square : rowSquare) {
                if (square != null) {
                    if (square.isSpawn()) {
                        setWeaponsCardsForSpawnSquare((SpawnSquare) square);
                    }
                }
            }
        }
    }

    public boolean isTerminatorMode() {
        return terminatorMode;
    }

    public void setTerminatorMode(boolean terminatorMode) {
        this.terminatorMode = terminatorMode;
    }
}
