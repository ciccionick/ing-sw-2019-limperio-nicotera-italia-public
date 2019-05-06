package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.FrameForInitialization;

import java.util.Scanner;

/**
 * This class handles the initial record of the players in which they choose nickname and color.
 * @author Francesco Nicotera
 */
public class InitializationView {
    /**
     * The frame utilized for the phase of initialization
     */
    private FrameForInitialization frameForInitialization;
    /**
     * reference to specific remote view of a client
     */
    private RemoteView remoteView;
    /**
     * It allows to read from command line
     */
    private Scanner stdin = new Scanner(System.in);

     InitializationView(RemoteView remoteView) {
        this.remoteView = remoteView;
    }

    /**
     * Takes the nickname of a player that is recording to game.
     * @return the nickname
     */
    private String setNickname(){
        frameForInitialization = new FrameForInitialization("Adrenaline - Setup");
        System.out.println("Scrivi nickname: ");
        return stdin.nextLine();
    }

    /**
     * Takes the color that a player wants.
     * @return the chosen color.
     */
    private String setColor(){
        System.out.println("Scrivi che colore vuoi (YELLOW, BLUE, PURPLE, GREY, GREEN): ");
        String color = stdin.nextLine();
        while (!color.equalsIgnoreCase("YELLOW")&& !color.equalsIgnoreCase("BLUE") && !color.equalsIgnoreCase("PURPLE") && !color.equalsIgnoreCase("GREY") && !color.equalsIgnoreCase("GREEN")){
            System.out.println("Riprova, scrivi che colore vuoi (YELLOW, BLUE, PURPLE, GREY, GREEN): ");
            color = stdin.nextLine();
        }
        return color;
    }


    /**
     *<p>
     *   Handles the initialization of a player in the game: it checks that there aren't two player with the same nickname or color.
     *   Generates a new event that it sends to remoteview, in order to ask to client the other information that it needs to register the player.
     *</p>
     *
     * @param event the boolean attributes in this parameter permit to recognize the phase of record in which the player is and whether to reply or ask to it.
     */
    public void handleInitialization(RequestInitializationEvent event){
        if(event.isNicknameRequest()){
            if(event.isRetake())
                System.out.println("Riprova, nickname già scelto");
            String nickname = setNickname();
            AnswerInitializationEvent ans = new AnswerInitializationEvent(nickname,null,0,false,false);
            remoteView.getNetworkHandler().replyToRequestOfInitialization(ans);
        }
        if(event.isColorRequest()){
            if(event.isRetake())
                System.out.println("Riprova, colore già scelto");
            String color = setColor();
            AnswerInitializationEvent ans = new AnswerInitializationEvent(null,color,0,false,false);
            remoteView.getNetworkHandler().replyToRequestOfInitialization(ans);
        }
        if(event.isFrenzyRequest()){
            System.out.println("Scrivi y se vuoi la frenzy, n altrimenti");
            String frenzy = stdin.nextLine();
            while(!frenzy.equalsIgnoreCase("y") && !frenzy.equalsIgnoreCase("n")) {
                System.out.println("Riprova, scrivi y se vuoi la frenzy, n altrimenti");
                frenzy = stdin.nextLine();
            }
            AnswerInitializationEvent ans = new AnswerInitializationEvent(null,null,0,frenzy.equalsIgnoreCase("y")||frenzy.equalsIgnoreCase("n"),false);
            remoteView.getNetworkHandler().replyToRequestOfInitialization(ans);
        }
        if(event.isMapRequest()){
            System.out.println("Scegli la mappa (1,2, 3 o 4): ");
            int typeOfMap= stdin.nextInt();
            while(typeOfMap!=1 && typeOfMap!=2 && typeOfMap!=3 && typeOfMap!=4){
                System.out.println("Riprova, scegli la mappa (1,2, 3 o 4): ");
                typeOfMap=stdin.nextInt();
            }
            AnswerInitializationEvent ans = new AnswerInitializationEvent(null,null,typeOfMap,false,false);
            remoteView.getNetworkHandler().replyToRequestOfInitialization(ans);
        }
        if (event.isTerminatorModeRequest()) {
            System.out.println("Scrivi y se vuoi la terminator mode nel caso in cui la partita cominci con 3 giocatori, n altrimenti");
            stdin.nextLine();
            String terminator = stdin.nextLine();
            while((!terminator.equalsIgnoreCase("y")) && !(terminator.equalsIgnoreCase("n"))) {
                System.out.println("Riprova, scrivi y se vuoi la terminator, n altrimenti");
                terminator = stdin.nextLine();
            }
            AnswerInitializationEvent ans = new AnswerInitializationEvent(null,null,0,false,terminator.equalsIgnoreCase("y")||terminator.equalsIgnoreCase("n"));
            remoteView.getNetworkHandler().replyToRequestOfInitialization(ans);
        }


    }
}
