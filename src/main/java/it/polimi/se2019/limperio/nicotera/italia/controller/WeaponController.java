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



    public Boolean checkIfThereIsAtLeastOneEffectUsable(WeaponCard weaponCard)
    {

        return false;
    }



    /*public ArrayList<Integer> controlUseWeaponCards(WeaponCard weaponCard)
    {

        ArrayList<Integer> usableEffects= new ArrayList<>();


        //return controlUseWeaponCardForAmmo(weaponCard, controlUseWeaponCardForEffect(weaponCard));

    }*/



    private ArrayList<Integer> controlUseWeaponCardForEffect(WeaponCard weaponCard)
    {

        ArrayList<Integer> usableEffect= new ArrayList<>();

        switch (weaponCard.getName())
        {
            case "Electroscythe":
                if(!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                }

                break;

            case "Cyberblade":
                if(!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                }
                //manca il terzo effetto
                break;

            case "Sledgehammer":
                if(!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                }
                break;

            case "Shotgun":
                if (!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0).isEmpty())
                    usableEffect.add(1);
                if(!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1).isEmpty())
                    usableEffect.add(2);
                break;

            case "Shockwave":
                if(!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                }
                break;

            case "Furnace":
                if((!playersIntheVisibleRooms(weaponCard.getOwnerOfCard()).isEmpty()) && !playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(4);
                }
                else if(playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1)!=null)
                    usableEffect.add(4);
                else usableEffect.add(1);
                break;

            case "Lock rifle":
                if(!canSee(weaponCard.getOwnerOfCard()).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                }
                break;

            case "Zx-2":
                if(!canSee(weaponCard.getOwnerOfCard()).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(4);
                }
                break;

            case "Machine gun":
                if(!canSee(weaponCard.getOwnerOfCard()).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                    usableEffect.add(3);
                }
                break;

            case "Granade launcher":
                if(!canSee(weaponCard.getOwnerOfCard()).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                }
                break;

            case "Plasma gun":
                if(!canSee(weaponCard.getOwnerOfCard()).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                    usableEffect.add(3);
                }
                break;

            case "Railgun":
                if(!playersInCardinalPosition(weaponCard.getOwnerOfCard()).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(4);
                }
                break;

            case "Heatseeker":
                if(!playersThatNotCanSee(weaponCard.getOwnerOfCard()).isEmpty())
                    usableEffect.add(1);
                break;

            case "Rocket launcher":
                if(!canSee(weaponCard.getOwnerOfCard()).isEmpty() && playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),0).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(2);
                    usableEffect.add(3);
                }
                break;

            case "Hellion":
                if(!playersThatCanSeeAndAtLeastThisDistant(weaponCard.getOwnerOfCard(),1).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(4);
                }
                break;

            case "Whisper":
                if(!playersThatCanSeeAndAtLeastThisDistant(weaponCard.getOwnerOfCard(),2).isEmpty())
                    usableEffect.add(1);
                break;

            case "Thor":
                if(!canSee(weaponCard.getOwnerOfCard()).isEmpty())
                    usableEffect.add(1);
                //mancano il secondo e il terzo effetto
                break;

            case "Flamethrower":
                if(!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(4);
                }
                break;

            case "Power glove":
                if(!playersInMySquareOrDistantOneSquare(weaponCard.getOwnerOfCard(),1).isEmpty())
                {
                    usableEffect.add(1);
                    usableEffect.add(4);
                }
                break;

            case "Tractor beam":
                break;

            case "Vortex cannon":
                break;







        }

        return usableEffect;
    }



    /*public ArrayList<Integer>  controlUseWeaponCardForAmmo(WeaponCard weaponCard, ArrayList<Integer> effects)
    {
        ArrayList<Integer> effectsUsable= new ArrayList<>();

        Player cardOwner= weaponCard.getOwnerOfCard();
        ColorOfCard_Ammo[] priceEffect= new ColorOfCard_Ammo[];
        Boolean trovato;


        if(effects.isEmpty()) return effectsUsable;

        for(Integer x: effects)
        {
            switch (x)
            {

                case 2:
                    if(weaponCard.getPriceForEffect2[0]!=null)
                        priceEffect[0]= weaponCard.getPriceForEffect2[0];

                    break;

                case 3:
                    if(weaponCard.getPriceForEffect3[0]!=null)
                        priceEffect[0]= weaponCard.getPriceForEffect3[0];

                case 4:
                    priceEffect[0]= weaponCard.getPriceForEffect4[0];
                    if(weaponCard.getPriceForEffect4[1]!=null)
                        priceEffect[1]= weaponCard.getPriceForEffect4[1];

                    break;

            }

            if(priceEffect[0]==null)
                effectsUsable.add(x);
            else
            {
                trovato=false;

                    for(Ammo ammo: cardOwner.getPlayerBoard().getAmmo())
                    {
                        if(ammo.getColor().equals(priceEffect[0]) && ammo.isUsable()
                        {
                            if(priceEffect[1]!=null)
                            {
                                for(Ammo ammox: cardOwner.getPlayerBoard().getAmmo())
                                {
                                    if(ammox.getColor().equals(priceEffect[1]) && ammo.isUsable() && ammo!=ammox) trovato= true;
                                }

                            }
                            else trovato=true;

                        }
                    }
                    for(PowerUpCard powerUpCard: cardOwner.getPlayerBoard().getPowerUpCardsOwned()) {
                        if (powerUpCard.getColor().equals(priceEffect[0])) {
                            if (priceEffect[1] != null) {
                                for (PowerUpCard powerUpCardx : cardOwner.getPlayerBoard().getPowerUpCardsOwned()) {
                                    if (powerUpCardx.getColor().equals(priceEffect[1]) && powerUpCard != powerUpCardx)
                                        trovato = true;
                                }
                            } else trovato = true;
                        }
                    }


                    if(trovato) effectsUsable.add(x);
                }

            priceEffect[0]=null;
            priceEffect[1]=null;

            }




            return effectsUsable;

        }*/



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
            if (!player.getPositionOnTheMap().getPlayerOnThisSquare().isEmpty())
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
                if(!player.getPositionOnTheMap().getAdjSquares().get(i).getPlayerOnThisSquare().isEmpty())
                    players.addAll(player.getPositionOnTheMap().getAdjSquares().get(i).getPlayerOnThisSquare());
            }
        }


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
        for(int i=0;i<4;i++)
        {
            if(matrix[row][i]!=null)
            {
                if(game.getBoard().getMap().getMatrixOfSquares()[row][i].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[row][i]!=player.getPositionOnTheMap())
                    players.addAll(game.getBoard().getMap().getMatrixOfSquares()[row][i].getPlayerOnThisSquare());
            }


        }
        for(int i=0;i<3;i++)
        {
            if(matrix[i][column]!=null)
            {
                if(game.getBoard().getMap().getMatrixOfSquares()[i][column].getPlayerOnThisSquare().size()!=0 && game.getBoard().getMap().getMatrixOfSquares()[i][column]!=player.getPositionOnTheMap())
                    players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][column].getPlayerOnThisSquare());
            }


        }
        if(playersInMySquareOrDistantOneSquare(player,0)!=null) players.addAll(playersInMySquareOrDistantOneSquare(player,0));


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

        /**
         * Colors of rooms visible from attacking player
         */
        ArrayList<ColorOfFigure_Square> colorsVisible = new ArrayList<>();
        /**
         * color of the attacking player's square
         */
        ColorOfFigure_Square colorOfSquare= player.getPositionOnTheMap().getColor();

        if (!player.getPositionOnTheMap().isHasDoor()) {

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if(matrix[i][j]!=null)
                    {
                        if (game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor() != colorOfSquare && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size() != 0)
                            players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
                    }
                    }


            }
        } else {

            colorsVisible.add(colorOfSquare);
            for (int k = 0; k < player.getPositionOnTheMap().getAdjSquares().size(); k++) {
                if (player.getPositionOnTheMap().getAdjSquares().get(k).getColor() != colorOfSquare) {
                    colorsVisible.add(player.getPositionOnTheMap().getAdjSquares().get(k).getColor());

                }
            }


            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j <matrix[i].length; j++) {
                    if(matrix[i][j]!=null)
                    {
                        if ((!colorsVisible.contains(game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor())) && game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().size() != 0)
                            players.addAll(game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare());
                    }

                }
            }

        }


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
        if(!players.isEmpty())
        {
        for(Player playerx: players)
        {
            //giocatore nella mappa
            int[] primo= {playerx.getPositionOnTheMap().getRow(), playerx.getPositionOnTheMap().getColumn()};
            //giocatore attaccante
            int[] secondo={player.getPositionOnTheMap().getRow(), player.getPositionOnTheMap().getColumn()};
            if(controller.distanceOfManhattan(primo, secondo)<x)  players.remove(playerx);
        }
        }


        return players;
    }




}
