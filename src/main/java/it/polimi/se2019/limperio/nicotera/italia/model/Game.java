package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

import java.util.ArrayList;


public class Game extends Observable<ModelEvent> {


    private Board board;
    private ArrayList<Player> players = new ArrayList<>();
    private boolean isInFrenzy = false;
    private static Game instanceOfGame;
    private int numOfPlayer;
    private int playerOfTurn;
    private boolean isFirstRound = true;
    private int numOfActionOfTheTurn;
    private Square[] squareVisitedInTheTurn;
    private boolean isGameOver = false;
    private boolean anticipatedFrenzy = true;
    private int firstInFrenzyMode;

    public void addPlayer(String nickname, ColorOfFigure_Square color){
        players.add(new Player(nickname, color));

    }

    public void removePlayer(Player player){
        players.remove(player);

    }


    public boolean isTurn(Player player){
        return true;
    }

    public void calculateScore(){}

    public boolean getisGameOver(){
        return isGameOver;
    }
    public boolean hasWon(Player player){
        return true;
    }
    public void updateKillshotTrack(){}
    public void changeMode(){}
    public void startGame(){}
    public void createBoard(){
        this.board = Board.instanceOfBoard();
    }
    private Game(){
        createBoard();
    }
    public static Game instanceOfGame(){
        if(instanceOfGame == null)
             instanceOfGame = new Game();
        return instanceOfGame;
    }

    public void updateTurn(){}
    public void updateScore(Player player){}
    public void updateScoreForKillshot(){}
    public void showWinner(int scoreOfWinner){}
    public void updateScoreFinal(){}

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

    }

    @Override
    public void deregister(Observer<ModelEvent> observer) {

    }

    @Override
    public void notify(ModelEvent message) {

    }
}
