package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeathController {
    Game game = Game.instanceOfGame();

    Controller controller = new Controller(game);
    DeathController deathController= new DeathController(game, controller);



    @Before
    public void setUp(){
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "PURPLE");
        game.setGameOver(false);
        game.initializeGame(true, 1, false);
    }


    @Test
    public void handleDeathTestInFrenzyMood()
    {


        //Test in frenzy mood
        game.setInFrenzy(true);
        deathController.handleDeath(game.getPlayers().get(0), game.getPlayers().get(1));
        assertEquals(game.getBoard().getKillShotTrack().getTokenOfFrenzyMode().get(0), ColorOfDeathToken.BLUE);


    }

    @Test
    public void handleDeathTest()
    {
        //Test not in Frenzy mood


        game.setInFrenzy(false);
        deathController.handleDeath(game.getPlayers().get(0), game.getPlayers().get(1));
        int x=0;
        for(int i=0;i<game.getBoard().getKillShotTrack().getTokensOfDeath().size();i++){
            if(game.getBoard().getKillShotTrack().getTokensOfDeath().get(i).get(0).toString().equals("SKULL"))
                x= i;
        }

        assertEquals(game.getBoard().getKillShotTrack().getTokensOfDeath().get(x-1).get(1), ColorOfDeathToken.BLUE);




    }

    /*@Test
    public void a()
    {
        //Test with death player with 11 damage
        boolean stop=false;

        while(stop==false)
        {
            game.getPlayers().get(1).getPlayerBoard().getDamages().add(ColorOfFigure_Square.BLUE);
            if(game.getPlayers().get(1).getPlayerBoard().getDamages().size()==11)
                stop=true;

        }


        deathController.handleDeath(game.getPlayers().get(0), game.getPlayers().get(1));
        int x=0;
        for(int i=0;i<game.getBoard().getKillShotTrack().getTokensOfDeath().size();i++){
            if(game.getBoard().getKillShotTrack().getTokensOfDeath().get(i).get(0).toString().equals("SKULL"))
                x= i;
        }
        System.out.println(x);
        assertEquals(game.getBoard().getKillShotTrack().getTokensOfDeath().get(x-2).get(0), ColorOfDeathToken.BLUE);
    }*/




}
