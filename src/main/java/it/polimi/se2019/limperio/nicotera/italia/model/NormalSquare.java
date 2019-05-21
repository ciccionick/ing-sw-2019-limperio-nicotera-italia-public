package it.polimi.se2019.limperio.nicotera.italia.model;

/**
 * This class is used to represents a normal square on the map
 * @author Francesco Nicotera
 */
 public class NormalSquare extends Square implements Cloneable {
    /**
     * the reference to the ammotile card that is on the square during the game
     */
    private AmmoTile ammoTile = null;

     NormalSquare( ColorOfFigure_Square color, boolean hasDoor, int row, int column){
        super(color, hasDoor, row, column);

        setSpawn(false);
    }

    public Object clone(){
         NormalSquare normalSquare = null;
         try{
             normalSquare = (NormalSquare) super.clone();
         }
         catch (CloneNotSupportedException ex){
             normalSquare = new NormalSquare(this.getColor(), this.isHasDoor(), this.getRow(), this.getColumn());
         }
         if(ammoTile!=null)
            normalSquare.ammoTile = (AmmoTile) this.ammoTile.clone();
         else
             normalSquare.ammoTile=null;
         return normalSquare;

    }

     public AmmoTile getAmmoTile() {
         return ammoTile;
     }

     public void setAmmoTile(AmmoTile ammoTile) {
         this.ammoTile = ammoTile;
     }
 }
