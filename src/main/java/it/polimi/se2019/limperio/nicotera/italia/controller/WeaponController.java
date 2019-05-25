package it.polimi.se2019.limperio.nicotera.italia.controller;


import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class checks the right use of a weapon. It collaborates with ShootController.
 *
 * @author  Giuseppe Italia
 */
public class WeaponController {

    private final Game game;
    private final Controller controller;
    public WeaponController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }






    public HashMap<String, ArrayList<Integer>> controlUseWeaponCards(ArrayList<WeaponCard> weapons)
    {
        /**
         * is the map with weaponCards that the player can use
         */
        HashMap<String, ArrayList<Integer>> usableCards= new HashMap<>();




        if(weapons.isEmpty()) return null;
        for(WeaponCard weaponCard : weapons)
        {
            if(weaponCard.isLoad())
            {
                switch (weaponCard.getName())
                {
                    case "Electroscythe":
                        if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0)!=null) usableCards.put("Electroscythe", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Cyberblade":
                        if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0)!=null) usableCards.put("Cyberblade", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Sledgehammer":
                        if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0)!=null) usableCards.put("Sledgehammer", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Shotgun":
                        if (playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0)!=null) usableCards.put("Shotgun", new ArrayList<Integer>(){{add(1);}});
                        if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1)!=null) usableCards.get("Shotgun").add(2);
                        break;

                    case "Shockwave":
                        if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1)!=null) usableCards.put("Shockwave", new ArrayList<Integer>(){{add(1);add(2);}});
                        break;

                    case "Furnace":
                        if(playersIntheVisibleRooms(weaponCard.getOwnerOfCard())!=null) usableCards.put("Furnace", new ArrayList<Integer>(){{add(1);}});
                        //if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1)!=null) usableCards.get("Furnace").add(4);
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
                        if(playersInCardinalPosition(weaponCard.getOwnerOfCard())!=null) usableCards.put("Railgun", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Heatseeker":
                        if(playersThatNotCanSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("Heatseeker", new ArrayList<Integer>(){{add(1);}});
                        break;

                    case "Rocket launcher":
                        if(canSee(weaponCard.getOwnerOfCard())!=null && playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0)==null) usableCards.put("Rocket launcher", new ArrayList<Integer>(){{add(1);add(2);add(3);}});
                        break;

                    case "Hellion":
                        if(playersThatCanSeeAndAtLeastThisDistant(weaponCard.getOwnerOfCard(),1)!=null) usableCards.put("Hellion", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Whisper":
                        if(playersThatCanSeeAndAtLeastThisDistant(weaponCard.getOwnerOfCard(),2)!=null) usableCards.put("Whisper", new ArrayList<Integer>(){{add(1);}});
                        break;

                    case "Thor":
                        if(canSee(weaponCard.getOwnerOfCard())!=null) usableCards.put("Thor", new ArrayList<Integer>(){{add(1);}});
                        //mancano il secondo e il terzo effetto
                        break;

                    case "Flamethrower":
                        if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1)!=null) usableCards.put("Flamethrower", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Power glove":
                        if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1)!=null) usableCards.put("Power glove", new ArrayList<Integer>(){{add(1);add(4);}});
                        break;

                    case "Tractor beam":
                        break;




                }

            }

        }

        if(usableCards.isEmpty()) return null;
        return usableCards;

    }


    /**
     * returns the players that stay in the same square of attacking player(with x=0) or in position distant one square from attacking player(with x=1)
     * @param player attacking player
     * @param x distance of squares
     * @return list of players
     */
    private ArrayList<Player> playersInMySquareOrDistantOneSquare(Player player, int x)
    {

        ArrayList<Player> players= new ArrayList<>();

        if(x==0) {
            if (player.getPositionOnTheMap().getPlayerOnThisSquare().size() != 0)
                for(Player playerx: player.getPositionOnTheMap().getPlayerOnThisSquare())
                {
                    if(playerx!=player)
                        players.add(playerx);
                }
        }

        else if(x==1)
        {
            for(int i=0;i<player.getPositionOnTheMap().getAdjSquares().size();i++)
            {
                if(player.getPositionOnTheMap().getAdjSquares().get(i).getPlayerOnThisSquare().size()!=0)
                    players.addAll(player.getPositionOnTheMap().getAdjSquares().get(i).getPlayerOnThisSquare());
            }
        }

        if(players.isEmpty()) return  null;
        return players;
    }



    /**
     * This method returns the players that are visible from the square of the attacking player
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

        if(playersVisible.isEmpty()) return null;
        return playersVisible;

    }


    /**
     * returns players from other rooms visible to the attacking player
     * @param player attacking player
     * @return List of players
     */


    private ArrayList<Player> playersIntheVisibleRooms(Player player)
    {
        ArrayList<Player> players= new ArrayList<>();

        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
        ColorOfFigure_Square colorOtherRoom;
        if(!player.getPositionOnTheMap().isHasDoor()) return null;

        for(int k=0;k<player.getPositionOnTheMap().getAdjSquares().size();k++)
        {
            if(player.getPositionOnTheMap().getAdjSquares().get(k).getColor()!=player.getPositionOnTheMap().getColor())
            {
                colorOtherRoom=player.getPositionOnTheMap().getAdjSquares().get(k).getColor();
                for(int i=0;i<matrix.length;i++)

                {
                    for(int j=0;j<matrix[i].length;j++)
                    {
                        if(matrix[i][j]!=null)
                        {
                            if(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().equals(colorOtherRoom) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size()!=0)
                                players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
                        }

                    }
                }
            }

        }
        if(players.isEmpty()) return  null;
        return players;

    }

    /**
     * returns the players positioned in cardinal directions with respect to the attacking player
     * @param player attacking player
     * @return List of players
     */

    private ArrayList<Player> playersInCardinalPosition(Player player)
    {
        ArrayList<Player> players= new ArrayList<>();
        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();

        int row= player.getPositionOnTheMap().getRow();
        int column= player.getPositionOnTheMap().getColumn();
        for(int i=0;i<3;i++)
        {
            if(matrix[row][i]!=null)
            {
                if(game.getBoard().getMap().getMatrixOfSquares()[row][i].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[row][i]!=player.getPositionOnTheMap())
                    players.addAll(game.getBoard().getMap().getMatrixOfSquares()[row][i].getPlayerOnThisSquare());
            }


        }
        for(int i=0;i<4;i++)
        {
            if(matrix[i][column]!=null)
            {
                if(game.getBoard().getMap().getMatrixOfSquares()[i][column].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[i][column]!=player.getPositionOnTheMap())
                    players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][column].getPlayerOnThisSquare());
            }


        }
        if(playersInMySquareOrDistantOneSquare(player,0)!=null) players.addAll(playersInMySquareOrDistantOneSquare(player,0));

        if(players.isEmpty()) return  null;
        return players;

    }

    /**
     * returns the players that the attacking player not can see
     * @param player attacking player
     * @return List of players
     */

    private ArrayList<Player> playersThatNotCanSee(Player player)
    {
        ArrayList<Player> players= new ArrayList<>();

        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();

        ArrayList<ColorOfFigure_Square> color = new ArrayList<>();

        if (!player.getPositionOnTheMap().isHasDoor()) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if(matrix[i][j]!=null)
                    {
                        if (game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor() != player.getPositionOnTheMap().getColor() && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size() != 0)
                            players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
                    }
                    }

            }
        } else {
            for (int k = 0; k < player.getPositionOnTheMap().getAdjSquares().size(); k++) {
                if (player.getPositionOnTheMap().getAdjSquares().get(k).getColor() != player.getPositionOnTheMap().getColor()) {
                    color.add(player.getPositionOnTheMap().getAdjSquares().get(k).getColor());
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j <matrix[i].length; j++) {
                    if(matrix[i][j]!=null)
                    {
                        if (!color.contains(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor()) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size() != 0)
                            players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
                    }

                }
            }
        }

        if(players.isEmpty()) return  null;
        return players;
    }


    /**
     * returns the players that the attacking player can see and they are distant x squares
     * @param player attacking player
     * @param x distance of squares
     * @return list of players
     */

    private ArrayList<Player> playersThatCanSeeAndAtLeastThisDistant(Player player, int x)
    {
        ArrayList<Player> players= new ArrayList<>();


        players.addAll(canSee(player));
        for(Player playerx: players)
        {
            //giocatore nella mappa
            int[] primo= {playerx.getPositionOnTheMap().getRow(), playerx.getPositionOnTheMap().getColumn()};
            //giocatore attaccante
            int[] secondo={player.getPositionOnTheMap().getRow(), player.getPositionOnTheMap().getColumn()};
            if(controller.distanceOfManhattan(primo, secondo)<x)  players.remove(playerx);
        }
        if(players.isEmpty()) return  null;
        return players;
    }




}
