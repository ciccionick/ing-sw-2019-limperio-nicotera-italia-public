package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.model.WeaponCard;

import java.util.ArrayList;

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
    private Square[][] map = null;

    public MapEvent(String message) {
        super(message);
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
        this.map = map;
        setWeaponsWithTheirAlias(map);
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


}
