package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.server.VirtualView;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

import java.util.ArrayList;


public class Game extends Observable<ModelEvent> {


    private Board board;
    private ArrayList<Player> players = new ArrayList<>();
    private boolean isInFrenzy = false;
    private static Game instanceOfGame = null;
    private int numOfPlayer;
    private int playerOfTurn;
    private boolean isFirstRound = true;
    private int numOfActionOfTheTurn;
    private Square[] squareVisitedInTheTurn;
    private boolean isGameOver = false;
    private boolean anticipatedFrenzy;
    private int firstInFrenzyMode;
    private ArrayList<VirtualView> listOfVirtualView = new ArrayList<>();
    private ArrayList<String> listOfNickname = new ArrayList<>();

    public Game(){
        // this is the default constructor of Game class called by the main in the Server class to create
        // a reference towards to the game instance.
    }



    public void startGame(boolean anticipatedFrenzy, int typeMap){
        this.anticipatedFrenzy=anticipatedFrenzy;
        PlayerBoardEvent pbEvent;
        for (Player player : players){
            player.createPlayerBoard();
            pbEvent = new PlayerBoardEvent("Successfull creation of player board of " + player.getNickname());
            pbEvent.getNickname().add(player.getNickname());
            pbEvent.setPlayerBoard(player.getPlayerBoard());
            notify(pbEvent);
        }
        createBoard();
        board.createMap(typeMap);
        MapEvent mapEvent = new MapEvent("Successfull creation of map");
        setListOfNickname();
        mapEvent.setNickname(listOfNickname);
        mapEvent.setMap(Map.getMatrixOfSquares());
        notify(mapEvent);
        board.createKillShotTrack();
        KillshotTrackEvent killshotTrackEvent = new KillshotTrackEvent("Successfull creation of killshot track");
        killshotTrackEvent.setKillShotTrack(board.getKillShotTrack());
        killshotTrackEvent.setNickname(listOfNickname);
        notify(killshotTrackEvent);
        board.createAmmoTileDeck();
        board.createPowerUpDeck();
        board.createWeaponsDeck();
        startWithTheFirstTurn();
    }

    private void startWithTheFirstTurn() {
        playerOfTurn = 1;
        numOfActionOfTheTurn=0;
        StartTurnEvent startTurnEvent = new StartTurnEvent("E' il tuo primo turno e devi pescare due carte potenziamento e scartarne una per decidere il tuo punto di generazione ");
        startTurnEvent.getNickname().add(listOfNickname.get(playerOfTurn-1));
        notify(startTurnEvent);
    }


    private void createBoard(){

        this.board = Board.instanceOfBoard();
    }


    public static Game instanceOfGame(){
        if(instanceOfGame == null)
             instanceOfGame = new Game();
        return instanceOfGame;
    }


    public void createPlayer(String nickname, boolean isFirst, int position, String color){
        ColorOfFigure_Square colorOfThisPlayer=null;
        switch (color){
            case "YELLOW":
                colorOfThisPlayer=ColorOfFigure_Square.YELLOW;
                break;
            case "BLUE":
                colorOfThisPlayer=ColorOfFigure_Square.BLUE;
                break;
            case "GREEN":
                colorOfThisPlayer=ColorOfFigure_Square.GREEN;
                break;
            case "PURPLE":
                colorOfThisPlayer=ColorOfFigure_Square.PURPLE;
                break;
            case "GREY":
                colorOfThisPlayer=ColorOfFigure_Square.GREY;
                break;
            default: throw new IllegalArgumentException();
        }

        players.add(new Player(nickname, isFirst, position, colorOfThisPlayer));
        System.out.println(players.get((players.size()-1)).getNickname() + " in " + players.get((players.size()-1)).getPosition() + "^ posizione nel turno");
        if(players.get(players.size()-1).isFirst()){
            System.out.println("Ed è il primo del turno con il colore " + players.get(players.size()-1).getColorOfFigure());

        }
        else
            System.out.println("E non è il primo del turno con il colore " + players.get(players.size()-1).getColorOfFigure());
    }

    public ArrayList<String> getListOfNickname() {
        return listOfNickname;
    }

    private void setListOfNickname() {
        for(Player player : players)
        {
            listOfNickname.add(player.getNickname());
        }
    }

    public void updateTurn(){
        if(playerOfTurn==players.size()){
            playerOfTurn=1;
            if(isFirstRound)
                isFirstRound=false;
        }
        if(isFirstRound){


        }
        else{

        }

    }


    public void updateScore(Player player){}
    public void updateScoreForKillshot(){}
    public void showWinner(int scoreOfWinner){}
    public void updateScoreFinal(){}

    public boolean isTurn(Player player){
        return true;
    }

    public void calculateScore(){

    }

    public boolean getisGameOver(){
        return isGameOver;
    }

    public boolean hasWon(Player player){
        return true;
    }
    public void updateKillshotTrack(){}
    public void changeMode(){}

    public boolean isInFrenzy() {
        return isInFrenzy;
    }

    public void setInFrenzy(boolean inFrenzy) {
        isInFrenzy = inFrenzy;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public int getPlayerOfTurn() {
        return playerOfTurn;
    }

    public void setPlayerOfTurn(int playerOfTurn) {
        this.playerOfTurn = playerOfTurn;
    }

    public boolean isFirstRound() {
        return isFirstRound;
    }

    public void setFirstRound(boolean firstRound) {
        isFirstRound = firstRound;
    }

    public int getNumOfActionOfTheTurn() {
        return numOfActionOfTheTurn;
    }

    public void setNumOfActionOfTheTurn(int numOfActionOfTheTurn) {
        this.numOfActionOfTheTurn = numOfActionOfTheTurn;
    }

    public Square[] getSquareVisitedInTheTurn() {
        return squareVisitedInTheTurn;
    }

    public void setSquareVisitedInTheTurn(Square[] squareVisitedInTheTurn) {
        this.squareVisitedInTheTurn = squareVisitedInTheTurn;
    }

    public void setGameOver(boolean over) {
        isGameOver = over;
    }

    public boolean isAnticipatedFrenzy() {
        return anticipatedFrenzy;
    }

    public void setAnticipatedFrenzy(boolean anticipatedFrenzy) {
        this.anticipatedFrenzy = anticipatedFrenzy;
    }

    public int getFirstInFrenzyMode() {
        return firstInFrenzyMode;
    }

    public void setFirstInFrenzyMode(int firstInFrenzyMode) {
        this.firstInFrenzyMode = firstInFrenzyMode;
    }


    public boolean checkIfIsDead(Player player){
    return true;
    }

    public void handleDeath(Player player){}



    @Override
    public void register(Observer<ModelEvent> observer) {
        listOfVirtualView.add((VirtualView) observer);
    }

    @Override
    public void deregister(Observer<ModelEvent> observer) {
        listOfVirtualView.remove(observer);
    }

    @Override
    public void notify(ModelEvent message) {
        for (VirtualView view : listOfVirtualView){
            view.update(message);
        }
    }
}
