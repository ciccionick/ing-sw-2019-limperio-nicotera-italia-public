package it.polimi.se2019.limperio.nicotera.italia.controller;


import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class checks the right use of a weapon. It collaborates with ShootController.
 */
public class WeaponController {

    private final Game game;
    private final Controller controller;
    public WeaponController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
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
                        if(canUseWeaponCard(weaponCard,0)!=null) usableCards.put("Electroscythe", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Cyberblade":
                        if(canUseWeaponCard(weaponCard,0)!=null) usableCards.put("Cyberblade", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Sledgehammer":
                        if(canUseWeaponCard(weaponCard,0)!=null) usableCards.put("Sledgehammer", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Shotgun":
                        if (canUseWeaponCard(weaponCard,0)!=null) usableCards.put("Shotgun", new ArrayList<Integer>(){{add(1);}});
                        if(canUseWeaponCard(weaponCard,1)!=null) usableCards.get("Shotgun").add(2);
                        break;

                    case "Shockwave":
                        if(canUseWeaponCard(weaponCard,1)!=null) usableCards.put("Shockwave", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Furnace":
                        if(otherRoom(weaponCard)!=null) usableCards.put("Furnace", new ArrayList<Integer>(){{add(1);}});
                        if(canUseWeaponCard(weaponCard,1)!=null) usableCards.get("Furnace").add(4);
                        break;

                    case "Lock rifle":
                        if(canSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("Lock rifle", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Zx-2":
                        if(canSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("Zx-2", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Machine gun":
                        if(canSee(weaponCard.getOwnerOfCard())!=null)  usableCards.put("Machine gun", new ArrayList<Integer>(){{add(1);add(2);add(3);}});
                        break;

                    case "Granade launcher":
                        if(canSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("Granade launcher", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Plasma gun":
                        if(canSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("Plasma gun", new ArrayList<Integer>(){{add(1);add(2);add(3);}});
                        break;

                    case "Railgun":
                        if(canViewInCardinalPosition(weaponCard)!=null) usableCards.put("Railgun", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Heatseeker":
                        if(notCanSee(weaponCard)!=null) usableCards.put("Heatseeker", new ArrayList<Integer>(){{add(1);}});
                        break;

                    case "THOR":
                        if(canSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("THOR", new ArrayList<Integer>(){{add(1);}});
                        //mancano gli altri due effetti: bisogna ricevere il bersaglio scelto dal player e analizzare la sua visibilità
                        break;

                    case "Rocket launcher":
                        if(canSee(weaponCard.getOwnerOfCard())!=null && canUseWeaponCard(weaponCard,0)==null) usableCards.put("Rocket launcher", new ArrayList<Integer>(){{add(1);add(2);add(3);}});
                        break;

                    case "Hellion":
                        if(canSeeAndAtLeastxDistant(weaponCard,1)!=null) usableCards.put("Hellion", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Whisper":
                        if(canSeeAndAtLeastxDistant(weaponCard,2)!=null) usableCards.put("Whisper", new ArrayList<Integer>(){{add(1);}});
                        break;

                    case "Thor":
                        if(canSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("Thor", new ArrayList<Integer>(){{add(1);}});
                        //mancano il secondo e il terzo effetto
                        break;

                    case "Flamethrower":
                        if(canUseWeaponCard(weaponCard,1)!=null) usableCards.put("Flamethrower", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Power glove":
                        if(canUseWeaponCard(weaponCard,1)!=null) usableCards.put("Power glove", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;



                }

            }

        }
        return usableCards;

    }



    //Va a vedere con x=0 se ho player nel mio stesso square e con x=1 player in uno square raggiungibile distante un uno square
    private ArrayList<Player> canUseWeaponCard(WeaponCard weaponCard, int x)
    {
        /**
         * x=2 mi controlla che tra tutte le persone visibili ce ne stiano alucune ad un distanza di almeno 1 square
         */
        int i,j;
        ArrayList<Player> players= new ArrayList<>();

        switch (x)
        {
        case 0:
            if(weaponCard.getOwnerOfCard().getPositionOnTheMap().getPlayerOnThisSquare().size()!=0) players.addAll(weaponCard.getOwnerOfCard().getPositionOnTheMap().getPlayerOnThisSquare());
            break;

        case 1:

            for(i=0;i<4;i++)
            {
                if(weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(i).getPlayerOnThisSquare().size()!=0) players.addAll(weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(i).getPlayerOnThisSquare());
            }
            break;


        }

         return players;
    }



    /**
     * Ti dice se c'e' qualcuno negli square visibili dal player
     *
     * @return
     */
     ArrayList<Player> canSee(Player player)
    {
        ArrayList<Player> playersVisible = new ArrayList<>();
        ColorOfFigure_Square colorOfSquareOfPlayer = player.getPositionOnTheMap().getColor();
        HashMap<ColorOfFigure_Square,ArrayList<Square>> hashMapOfSquares = new HashMap<>();
        hashMapOfSquares.put(ColorOfFigure_Square.GREEN, new ArrayList<>());
        hashMapOfSquares.put(ColorOfFigure_Square.RED, new ArrayList<>());
        hashMapOfSquares.put(ColorOfFigure_Square.BLUE, new ArrayList<>());
        hashMapOfSquares.put(ColorOfFigure_Square.GREY, new ArrayList<>());
        hashMapOfSquares.put(ColorOfFigure_Square.YELLOW, new ArrayList<>());
        hashMapOfSquares.put(ColorOfFigure_Square.PURPLE, new ArrayList<>());
        ArrayList<Square> squaresVisible = new ArrayList<>();

        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
        for(int i = 0; i< matrix.length; i++){
            for (int j=0;j< matrix[i].length;j++){
                if(matrix[i][j] != null)
                    hashMapOfSquares.get(matrix[i][j].getColor()).add(matrix[i][j]);
            }
        }

        for(Square adjSquare : player.getPositionOnTheMap().getAdjSquares()){
            if(!adjSquare.getColor().equals(colorOfSquareOfPlayer))
                squaresVisible.addAll(hashMapOfSquares.get(adjSquare.getColor()));
        }
        squaresVisible.addAll(hashMapOfSquares.get(colorOfSquareOfPlayer));

        for(Square square : squaresVisible){
            if(square.getPlayerOnThisSquare()!=null)
                playersVisible.addAll(square.getPlayerOnThisSquare());
        }
        playersVisible.remove(player);
        if(player.getNickname().equals("terminator"))
            playersVisible.remove(game.getPlayers().get(game.getPlayerOfTurn()-1));
        return playersVisible;

    }




    private ArrayList<Player> otherRoom(WeaponCard weaponCard)
    {
        ArrayList<Player> players= new ArrayList<>();
        int k,i,j;
        ColorOfFigure_Square color;
        if(!weaponCard.getOwnerOfCard().getPositionOnTheMap().isHasDoor()) return null;

        for(k=0;k<4;k++)
        {
            if(weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor()!=weaponCard.getOwnerOfCard().getPositionOnTheMap().getColor())
            {
                color=weaponCard.getOwnerOfCard().getPositionOnTheMap().getAdjSquares().get(k).getColor();
                for(i=0;i<=3;i++)

                {
                    for(j=0;j<=3;j++)
                    {
                        if(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().equals(color) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size()!=0) players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
                    }
                }
            }

        }
        return players;

    }

    private ArrayList<Player> canViewInCardinalPosition(WeaponCard weaponCard)
    {
        ArrayList<Player> players= new ArrayList<>();
        int i;

        int row= weaponCard.getOwnerOfCard().getPositionOnTheMap().getRow();
        int column= weaponCard.getOwnerOfCard().getPositionOnTheMap().getColumn();
        for(i=0;i<4;i++)
        {
            if(game.getBoard().getMap().getMatrixOfSquares()[row][i].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[row][i]!=weaponCard.getOwnerOfCard().getPositionOnTheMap()) players.addAll(game.getBoard().getMap().getMatrixOfSquares()[row][i].getPlayerOnThisSquare());

        }
        for(i=0;i<4;i++)
        {
            if(game.getBoard().getMap().getMatrixOfSquares()[i][column].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[row][i]!=weaponCard.getOwnerOfCard().getPositionOnTheMap()) players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][column].getPlayerOnThisSquare());

        }
        if(canUseWeaponCard(weaponCard,0)!=null) players.addAll(canUseWeaponCard(weaponCard,0));
        return players;

    }

    private ArrayList<Player> notCanSee(WeaponCard weaponCard)
    {
        ArrayList<Player> players= new ArrayList<>();
        int i, j, k;

        ArrayList<ColorOfFigure_Square> color = new ArrayList<>();

        if (!weaponCard.getOwnerOfCard().getPositionOnTheMap().isHasDoor()) {
            for (i = 0; i <= 3; i++) {
                for (j = 0; j <= 3; j++) {
                    if (game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor() != weaponCard.getOwnerOfCard().getPositionOnTheMap().getColor() && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size() != 0)
                        players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
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
                        players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
                }
            }
        }

        return players;
    }



    private ArrayList<Player> canSeeAndAtLeastxDistant(WeaponCard weaponCard, int x)
    {
        ArrayList<Player> players= new ArrayList<>();


        players.addAll(canSee(weaponCard.getOwnerOfCard()));
        for(Player playerx: players)
        {
            //giocatore nella mappa
            int[] primo= {playerx.getPositionOnTheMap().getRow(), playerx.getPositionOnTheMap().getColumn()};
            //giocatore attaccante
            int[] secondo={weaponCard.getOwnerOfCard().getPositionOnTheMap().getRow(), weaponCard.getOwnerOfCard().getPositionOnTheMap().getColumn()};
            if(controller.distanceOfManhattan(primo, secondo)<x)  players.remove(playerx);
        }
        return players;
    }




}
