package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.AmmoTileDeck.instanceOfAmmoTileDeck;
import static it.polimi.se2019.limperio.nicotera.italia.model.Map.instanceOfMap;
import static it.polimi.se2019.limperio.nicotera.italia.model.PowerUpDeck.instanceOfPowerUpDeck;

public class Board {
    private static Board instanceOfBoard = null;

    private Map map;
    private AmmoTileDeck ammotiledeck;
    private WeaponsDeck weaponsDeck;
    private PowerUpDeck powerUpDeck;
    private KillShotTrack killShotTrack;

    private Board()
    { }

     static Board instanceOfBoard() {
        if(instanceOfBoard==null) instanceOfBoard= new Board();
        return instanceOfBoard;
    }

     void createMap(int typeMap)
    {
       this.map = instanceOfMap(typeMap);
    }

     void createPowerUpDeck(){
        this.powerUpDeck= instanceOfPowerUpDeck();
    }

     void createWeaponsDeck(){
        this.weaponsDeck = WeaponsDeck.instanceOfWeaponsDeck();
     }

     void createAmmoTileDeck(){
        this.ammotiledeck = instanceOfAmmoTileDeck();
    }

     void createKillShotTrack()
    {
        killShotTrack = KillShotTrack.instanceOfKillShotTrack();
    }

    public Map getMap() {
        return map;
    }

    public AmmoTileDeck getAmmotiledeck() {
        return ammotiledeck;
    }

    public WeaponsDeck getWeaponsDeck() {
        return weaponsDeck;
    }

    public PowerUpDeck getPowerUpDeck() {
        return powerUpDeck;
    }

     KillShotTrack getKillShotTrack() {
        return killShotTrack;
    }

    void addWeaponsInSpawnSquare(){
        ArrayList<SpawnSquare> spawnSquares = new ArrayList<>();
        for(int i = 0; i<getMap().getMatrixOfSquares().length;i++){
            for(int j=0 ; j< getMap().getMatrixOfSquares()[i].length; j++){
                if(getMap().getMatrixOfSquares()[i][j]!=null && getMap().getMatrixOfSquares()[i][j].isSpawn()){
                    spawnSquares.add((SpawnSquare) getMap().getMatrixOfSquares()[i][j]);
                }
            }
        }
        for (SpawnSquare square : spawnSquares){
            while(square.getWeaponCards().size()<3){
                square.getWeaponCards().add(weaponsDeck.getWeaponCard());
            }
        }

    }

    void addAmmoTileInNormalSquare(){
        ArrayList<NormalSquare> normalSquare = new ArrayList<>();
        for(int i = 0; i<getMap().getMatrixOfSquares().length;i++){
            for(int j=0 ; j< getMap().getMatrixOfSquares()[i].length; j++){
                if(getMap().getMatrixOfSquares()[i][j]!=null && !getMap().getMatrixOfSquares()[i][j].isSpawn()){
                    normalSquare.add((NormalSquare) getMap().getMatrixOfSquares()[i][j]);
                }
            }
        }
        for (NormalSquare square : normalSquare){
            if(square.getAmmoTile()==null){
                square.setAmmoTile(ammotiledeck.getAmmoTile().get(0));
                ammotiledeck.getAmmoTilesOnTheMap().remove(ammotiledeck.getAmmoTile().get(0));
            }
        }

    }
}
