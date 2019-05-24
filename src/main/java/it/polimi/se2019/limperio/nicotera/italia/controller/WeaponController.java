package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class checks the right use of a weapon. It collaborates with ShootController.
 */
public class WeaponController {

    private final Game game;

    public WeaponController(Game game) {
        this.game = game;
    }





    public HashMap<String, ArrayList<Integer>> controlUseWeaponCard(ArrayList<WeaponCard> weapons)
    {
        /**
         * is the map with weaponCards that the player can use
         */
        HashMap<String, ArrayList<Integer>> usableCards= new HashMap<>();

        if(weapons.size()==0) return null;
        for(WeaponCard weaponCard : weapons)
        {
            if(weaponCard.isLoad())
            {
                switch (weaponCard.getName())
                {
                    case "Electroscythe":
                        if(canUseWeaponCard(weaponCard)) usableCards.put("Electroscythe", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Cyberblade":
                        if(canUseWeaponCard(weaponCard)) usableCards.put("Cyberblade", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Sledgehammer":
                        if(canUseWeaponCard(weaponCard)) usableCards.put("Sledgehammer", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Shotgun":
                        if (canUseWeaponCard(weaponCard)) usableCards.put("Shotgun", new ArrayList<Integer>(){{add(1);}});
                        if(canUseWeaponCard1(weaponCard)) usableCards.get("Shotgun").add(2);
                        break;

                    case "Shockwave":
                        if(canUseWeaponCard1(weaponCard)) usableCards.put("Shockwave", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Furnace":
                        if(otherRoom(weaponCard)) usableCards.put("Furnace", new ArrayList<Integer>(){{add(1);}});
                        if(canUseWeaponCard1(weaponCard)) usableCards.get("Furnace").add(4);
                        break;

                    case "Lock rifle":
                        if(canSee(weaponCard)) usableCards.put("Lock rifle", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Zx-2":
                        if(canSee(weaponCard)) usableCards.put("Zx-2", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Machine gun":
                        if(canSee(weaponCard))  usableCards.put("Machine gun", new ArrayList<Integer>(){{add(1);add(2);add(3);}});
                        break;

                    case "Granade launcher":
                        if(canSee(weaponCard)) usableCards.put("Granade launcher", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Plasma gun":
                        if(canSee(weaponCard)) usableCards.put("Plasma gun", new ArrayList<Integer>(){{add(1);add(2);add(3);}});
                        break;

                    case "Railgun":
                        if(canViewIncardinalposition(weaponCard)) usableCards.put("Railgun", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Heatseeker":
                        if(notCanSee(weaponCard)) usableCards.put("Heatseeker", new ArrayList<Integer>(){{add(1);}});
                        break;

                    case "THOR":
                        if(canSee(weaponCard)) usableCards.put("THOR", new ArrayList<Integer>(){{add(1);}});
                        //mancano gli altri due effetti: bisogna ricevere il bersaglio scelto dal player e analizzare la sua visibilità
                        break;

                    case "Rocket launcher":
                        if(canSee(weaponCard) && !canUseWeaponCard(weaponCard)) usableCards.put("Rocket launcher", new ArrayList<Integer>(){{add(1);add(2);add(3);}});
                        break;

                }

            }

        }
        return usableCards;

    }


    // per le armi che attaccano player nel quadrato in qui si trova l'attaccante
    private boolean canUseWeaponCard(WeaponCard weaponCard)
    {
        int i;
        /**
         * this parameter is true when is
         */
        boolean ok=false;
        for(i=0;i<game.getPlayers().size();i++)
        {
            if(weaponCard.getOwnerOfCard().getPositionOnTheMap().equals(game.getPlayers().get(i).getPositionOnTheMap())) ok=true;
        }
        return ok;

    }



    //per le armi che attaccano player nel quadrato raggiungibile distante un movimento
    private boolean canUseWeaponCard1(WeaponCard weaponCard)
    {
        int i;
        boolean ok=false;
        for(i=0;i<4;i++)
        {
            if(weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(i).getPlayerOnThisSquare().size()!=0) ok= true;
        }
        return ok;
    }


    /**
     * Ti dice se c'e' qualcuno negli square visibili dal player
     * @param weaponCard
     * @return
     */
    private  boolean canSee(WeaponCard weaponCard)
    {
        int i,j,k;
        boolean ok=false;
        ColorOfFigure_Square color;


        for(i=0;i<=3;i++)

        {
            for(j=0;j<=3;j++)
                {
                    if(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().equals(weaponCard.getOwnerOfCard().getPositionOnTheMap().getColor()) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size()!=0) ok=true;
                }
        }
        if(weaponCard.getOwnerOfCard().getPositionOnTheMap().isHasDoor() && !ok)
        {
            //Vedo quale è il colore della stanza a cui mi affaccio con la porta
            for(k=0;k<4;k++)
            {
                if(weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor()!=weaponCard.getOwnerOfCard().getPositionOnTheMap().getColor())
                {
                    color=weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor();
                    for(i=0;i<=3;i++)

                    {
                    for(j=0;j<=3;j++)
                    {
                        if(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().equals(color) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size()!=0) ok=true;
                    }
                    }
                }

            }

        }
        return ok;

    }

    private boolean otherRoom(WeaponCard weaponCard)
    {
        boolean ok=false;
        int k,i,j;
        ColorOfFigure_Square color;
        if(!weaponCard.getOwnerOfCard().getPositionOnTheMap().isHasDoor()) return false;

        for(k=0;k<4;k++)
        {
            if(weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor()!=weaponCard.getOwnerOfCard().getPositionOnTheMap().getColor())
            {
                color=weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor();
                for(i=0;i<=3;i++)

                {
                    for(j=0;j<=3;j++)
                    {
                        if(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().equals(color) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size()!=0) ok=true;
                    }
                }
            }

        }
        return ok;

    }

    private boolean canViewIncardinalposition(WeaponCard weaponCard)
    {
        boolean ok=false;
        int i;

        int row= weaponCard.getOwnerOfCard().getPositionOnTheMap().getRow();
        int column= weaponCard.getOwnerOfCard().getPositionOnTheMap().getColumn();
        for(i=0;i<4;i++)
        {
            if(game.getBoard().getMap().getMatrixOfSquares()[row][i].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[row][i]!=weaponCard.getOwnerOfCard().getPositionOnTheMap()) ok=true;

        }
        for(i=0;i<4;i++)
        {
            if(game.getBoard().getMap().getMatrixOfSquares()[i][column].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[row][i]!=weaponCard.getOwnerOfCard().getPositionOnTheMap()) ok=true;

        }
        if(canUseWeaponCard(weaponCard)) ok=true;
        return ok;

    }

    private boolean notCanSee(WeaponCard weaponCard)
    {
        int i, j, k;
        boolean ok = false;
        ArrayList<ColorOfFigure_Square> color = new ArrayList<>();

        if (!weaponCard.getOwnerOfCard().getPositionOnTheMap().isHasDoor()) {
            for (i = 0; i <= 3; i++) {
                for (j = 0; j <= 3; j++) {
                    if (game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor() != weaponCard.getOwnerOfCard().getPositionOnTheMap().getColor() && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size() != 0)
                        ok = true;
                }
            }
        } else {
            for (k = 0; k < 4; k++) {
                if (weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor() != weaponCard.getOwnerOfCard().getPositionOnTheMap().getColor()) {
                    color.add(weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor());
                }
            }

            for (i = 0; i <= 3; i++) {
                for (j = 0; j <= 3; j++) {
                    if (!color.contains(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor()) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size() != 0)
                        ok = true;
                }
            }
        }

        return ok;
    }

    /*private int numberOfSquareDistant(Square square, Player player)
    {

        return  Math.abs(player.getPositionOnTheMap().getRow()-square.getRow()) + Math.abs(player.getPositionOnTheMap().getColumn() - square.getColumn());

    }

    /*private boolean someoneDistantAndCanSee(WeaponCard weaponCard, int i)
    {

    }*/


}
