package it.polimi.se2019.limperio.nicotera.italia.model;

 public class NormalSquare extends Square {
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
