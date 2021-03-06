package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

/**
 * This class is used to represents a normal square on the map
 * @author Francesco Nicotera
 */
 public class NormalSquare extends Square implements Cloneable {
    static final long serialVersionUID = 420000011;
    /**
     * the reference to the ammotile card that is on the square during the game
     */
    private AmmoTile ammoTile = null;

    /**
     * Constructor of the class where the color of the square, its row and column, the boolean that states if the square has door, are initialized.
     */
     NormalSquare( ColorOfFigure_Square color, boolean hasDoor, int row, int column){
        super(color, hasDoor, row, column);
        setSpawn(false);
    }

    /**
     * Override of the clone method to make possible the serialization avoiding the shallow copy.
     * @return The cloned object.
     */
    public Object clone(){
         NormalSquare normalSquare;
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
         normalSquare.nicknamesOfPlayersOnThisSquare = (ArrayList<String>) this.getNicknamesOfPlayersOnThisSquare().clone();
         return normalSquare;

    }

     public AmmoTile getAmmoTile() {
         return ammoTile;
     }

     public void setAmmoTile(AmmoTile ammoTile) {
         this.ammoTile = ammoTile;
     }
 }
