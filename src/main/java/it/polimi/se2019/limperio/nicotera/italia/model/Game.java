package it.polimi.se2019.limperio.nicotera.italia.model;

public class Game {
    private Board board;
    private Player[] players;
    private boolean isInFrenzy = false;
    private Game instanceOfGame;
    private int numOfPlayer;
    private int playerOfTurn;
    private boolean isFirstRound = true;
    private int numOfActionOfTheTurn;
    private Square[] squareVisitedInTheTurn;
    private boolean isOver = false;
    private boolean anticipatedFrenzy = true;
    private int firstInFrenzyMode;

    public String addPlayer(String nickname){}
    public void setTimerForStart(){}
    public boolean isTurn(Player player){}
    public void calculateScore(){}

    public boolean isOver(){}
    public boolean hasWon(Player player){}
    public void updateKillshotTrack(){}
    public void changeMode(){}
    public void startGame() {}
    public void createBoard() {}
    private Game(){}
    public Game instanceOfGame(){}
    public void removePlayer(){}
    public void stopTimerForStart(){}
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

    public void setOver(boolean over) {
        isOver = over;
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
}
