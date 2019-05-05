package it.polimi.se2019.limperio.nicotera.italia.model;

/**
 * This class is used to represents a normal square on the map
 * @author Francesco Nicotera
 */
 public class NormalSquare extends Square {
    /**
     * the reference to the ammotile card that is on the square during the game
     */
    private AmmoTile ammoTile = null;

     NormalSquare( ColorOfFigure_Square color, boolean hasDoor, int row, int column){
        super(color, hasDoor, row, column);

        setSpawn(false);
    }

     public AmmoTile getAmmoTile() {
         return ammoTile;
     }

      void setAmmoTile(AmmoTile ammoTile) {
         this.ammoTile = ammoTile;
     }
 }
