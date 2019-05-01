package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.model.WeaponCard;

import java.util.ArrayList;

public class MapEvent extends ModelEvent{

    private ArrayList<AliasCard> weaponsCardsForRedSpawnSquare;
    private ArrayList<AliasCard> weaponsCardsForBlueSpawnSquare;
    private ArrayList<AliasCard> weaponsCardsForYellowSpawnSquare;

    public MapEvent(String message) {
        super(message);
        setMapEvent(true);
    }

    public ArrayList<AliasCard> getWeaponsCardsForRedSpawnSquare() {
        return weaponsCardsForRedSpawnSquare;
    }

    private void setWeaponsCardsForSpawnSquare(SpawnSquare square, ColorOfFigure_Square color) {
        ArrayList<AliasCard> weaponsCardsForSpawn = new ArrayList<>();
        for(WeaponCard card : square.getWeaponCards()){
            weaponsCardsForSpawn.add(new AliasCard(card.getName(),card.getDescription(),card.getColor()));
        }
        if(color.equals(ColorOfFigure_Square.RED)) {
            this.weaponsCardsForRedSpawnSquare = weaponsCardsForSpawn;
            return;
        }
        if(color.equals(ColorOfFigure_Square.BLUE)){
            this.weaponsCardsForBlueSpawnSquare = weaponsCardsForSpawn;
            return;
        }
        if(color.equals(ColorOfFigure_Square.YELLOW))
            this.weaponsCardsForYellowSpawnSquare = weaponsCardsForSpawn;

    }

    public ArrayList<AliasCard> getWeaponsCardsForBlueSpawnSquare() {
        return weaponsCardsForBlueSpawnSquare;
    }


    public ArrayList<AliasCard> getWeaponsCardsForYellowSpawnSquare() {
        return weaponsCardsForYellowSpawnSquare;
    }

    public void setWeaponsWithTheirAlias(Square[][] matrixOfSquare) {
        for (Square[] rowSquare: matrixOfSquare) {
            for(Square square : rowSquare) {
                if (square != null) {
                    if (square.isSpawn()) {
                        setWeaponsCardsForSpawnSquare((SpawnSquare) square, square.getColor());
                    }
                }
            }
        }
    }


}
