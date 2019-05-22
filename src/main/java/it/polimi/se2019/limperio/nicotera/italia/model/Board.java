package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.AmmoTileDeck.instanceOfAmmoTileDeck;
import static it.polimi.se2019.limperio.nicotera.italia.model.Map.instanceOfMap;
import static it.polimi.se2019.limperio.nicotera.italia.model.PowerUpDeck.instanceOfPowerUpDeck;

/**
 * Contains all of the informations about the board
 *
 * @author Pietro L'Imperio
 */
public class Board {
    /**
     * The instance for the implementation of Singleton pattern
     */
    private static Board instanceOfBoard = null;
    /**
     * The reference of the map
     */
    private Map map;
    /**
     * The reference of the ammo tiles deck
     */
    private AmmoTileDeck ammoTileDeck;
    /**
     * The reference of the weapons deck
     */
    private WeaponsDeck weaponsDeck;
    /**
     * The reference of the powerUp deck
     */
    private PowerUpDeck powerUpDeck;
    /**
     * The reference of the killshot track
     */
    private KillshotTrack killShotTrack;

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
        this.ammoTileDeck = instanceOfAmmoTileDeck();
    }

     void createKillShotTrack()
    {
        killShotTrack = KillshotTrack.instanceOfKillShotTrack();
    }

    public Map getMap() {
        return map;
    }

    public AmmoTileDeck getAmmoTiledeck() {
        return ammoTileDeck;
    }

    public WeaponsDeck getWeaponsDeck() {
        return weaponsDeck;
    }

    public PowerUpDeck getPowerUpDeck() {
        return powerUpDeck;
    }

     KillshotTrack getKillShotTrack() {
        return killShotTrack;
    }

    /**
     * Adds for each spawn square weapons until the number of weapons in that square is equal to 3
     */
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
                square.getWeaponCards().add(weaponsDeck.removeWeaponCard());
            }
        }

    }

    /**
     * Adds for each normal square an ammo tile
     */
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
                ammoTileDeck.getAmmoTilesOnTheMap().add(ammoTileDeck.getAmmoTile().get(0));
                ammoTileDeck.getAmmoTile().remove(0);
                square.setAmmoTile(ammoTileDeck.getAmmoTilesOnTheMap().get(ammoTileDeck.getAmmoTilesOnTheMap().size()-1));
            }
        }

    }


    public void reshuffleAmmoTileDeck(){

    }

    public void reshufflePowerUpDeck(){

    }
}
