package it.polimi.se2019.limperio.nicotera.italia.model;

 class NormalSquare extends Square {
    private AmmoTile ammoTile = null;

     NormalSquare( ColorOfFigure_Square color, boolean hasDoor){
        super(color, hasDoor);

        setSpawn(false);
    }

      AmmoTile getAmmoTile() {
         return ammoTile;
     }

      void setAmmoTile(AmmoTile ammoTile) {
         this.ammoTile = ammoTile;
     }
 }
