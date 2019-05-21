package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.network.server.VirtualView;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;

import java.util.ArrayList;

/**
 * Contains all of the informations about the game
 *
 * @author Pietro L'Imperio
 */
public class Game extends Observable<ServerEvent> {

    /**
     * It's true if is enable the terminator mode, false otherwise. False for default
     */
    private boolean terminatorModeActive = false;
    /**
     * The reference of the board
     */
    private Board board;
    /**
     * The list of players in the game
     */
    private ArrayList<Player> players = new ArrayList<>();
    /**
     * It's true if the game is in the frenzy phase, false otherwise. False for default
     */
    private boolean isInFrenzy = false;
    /**
     * The instance of the game in order to implement the Singleton pattern
     */
    private static Game instanceOfGame = null;
    /**
     * The number of the player in the game
     */
    private int numOfPlayer;
    /**
     * The number of the player is playing the turn
     */
    private int playerOfTurn;
    /**
     * It's true if the game is in the first round, false otherwise. True for default
     */
    private boolean isFirstRound = true;
    /**
     * The current number of the action did by the player of the turn
     */
    private int numOfActionOfTheTurn;
    /**
     * The squares visited during a turn of a player
     */
    private Square[] squareVisitedInTheTurn;
    /**
     * It's true when the game is over, false otherwise. False for default
     */
    private boolean isGameOver = false;
    /**
     * It's true if there will be the frenzy phase at the end of the game, false otherwise
     */
    private boolean anticipatedFrenzy;
    /**
     * The number of the player that is the first at the beginning of the frenzy phase
     */
    private int firstInFrenzyMode;
    /**
     * The list of the virtual view associated with the players in game
     */
    private ArrayList<VirtualView> listOfVirtualView = new ArrayList<>();
    /**
     * The list of nicknames of the players in game
     */
    private ArrayList<String> listOfNickname = new ArrayList<>();
    /**
     * The number of the maximum actions that a player could do during a turn. 2 for default.
     */
    private int numOfMaxActionForTurn = 2;
    /**
     * The number of in which round is the game
     */
    private int round=1;

    private boolean hasToDoTerminatorAction = false;

    public Game(){
        // this is the default constructor of Game class called by the main in the Server class to create
        // a reference towards to the game instance.
    }


    /**
     * Handles the beginning of the game with the initialization of its parameter and the creation of
     * player boards for each player, map and killshot track. It creates moreover the powerUp deck,
     * weapons deck and ammo tiles deck.
     * @param anticipatedFrenzy The boolean value that indicate if there will be the last phase in frenzy mode
     * @param typeMap The type of map
     * @param terminatorModeActive The boolean value that indicate if there will be the terminator mode if the number of players is 3
     */
    public void initializeGame(boolean anticipatedFrenzy, int typeMap, boolean terminatorModeActive){
        this.anticipatedFrenzy=anticipatedFrenzy;
        if(players.size()==3 || players.size()==4)
            this.terminatorModeActive = terminatorModeActive;
        if(terminatorModeActive){
            players.add(new Player("terminator", false, 4, findColorAvailable()));
        }
        setListOfNickname();
        PlayerBoardEvent pbEvent;
        for (Player player : players){
            player.createPlayerBoard();
            pbEvent = new PlayerBoardEvent();
            pbEvent.setNicknameInvolved(player.getNickname());
            pbEvent.setNicknames(listOfNickname);
            pbEvent.setPlayerBoard(player.getPlayerBoard());
            notify(pbEvent);
        }
        createBoard();
        board.createMap(typeMap);
        board.createKillShotTrack();
        KillshotTrackEvent killshotTrackEvent = new KillshotTrackEvent("", board.getKillShotTrack());
        killshotTrackEvent.setNicknames(listOfNickname);
        notify(killshotTrackEvent);
        board.createAmmoTileDeck();
        board.createPowerUpDeck();
        board.createWeaponsDeck();
        board.addAmmoTileInNormalSquare();
        board.addWeaponsInSpawnSquare();
        updateMap();
        startGame();
    }

    private ColorOfFigure_Square findColorAvailable() {
        ArrayList<ColorOfFigure_Square> colors = new ArrayList<>();
        colors.add(ColorOfFigure_Square.YELLOW);
        colors.add(ColorOfFigure_Square.BLUE);
        colors.add(ColorOfFigure_Square.GREEN);
        colors.add(ColorOfFigure_Square.GREY);
        colors.add(ColorOfFigure_Square.PURPLE);
        for (Player player : players){
            colors.remove(player.getColorOfFigure());
        }
        return colors.get(0);
    }

    /**
     * Sends the correct event towards the virtual view in accordance to the right phase of the game
     */
    private void startGame() {
        boolean requestForDrawTwoCardsDone = false;
        while(!isGameOver) {
            for (playerOfTurn = 1; playerOfTurn <= players.size(); playerOfTurn++) {
                if(round==1) {
                    requestForDrawTwoCardsDone = false;
                    if(playerOfTurn==1)
                        hasToDoTerminatorAction=true;
                }
                if (!(players.get(playerOfTurn - 1).getNickname().equals("terminator"))|| !(players.get(playerOfTurn-1).isConnected())) {
                    numOfActionOfTheTurn = 0;
                    while (numOfActionOfTheTurn < numOfMaxActionForTurn) {
                        if (numOfActionOfTheTurn == 0 && round==1 && !requestForDrawTwoCardsDone) {
                            ServerEvent requestDrawTwoPowerUpCardsEvent = new ServerEvent();
                            requestDrawTwoPowerUpCardsEvent.setMessageForInvolved("Let's start! \nIt's your first turn and you have to draw two powerUp cards to decide where you will spawn. \nPress DRAW to draw powerUp cards!");
                            requestDrawTwoPowerUpCardsEvent.setMessageForOthers("Wait! It's not your turn but the turn of "+ listOfNickname.get(playerOfTurn-1) + ". Press OK and wait for some news!");
                            requestDrawTwoPowerUpCardsEvent.setRequestForDrawTwoPowerUpCardsEvent(true);
                            requestDrawTwoPowerUpCardsEvent.setNicknames(listOfNickname);
                            requestDrawTwoPowerUpCardsEvent.setNicknameInvolved(listOfNickname.get(playerOfTurn-1));
                            notify(requestDrawTwoPowerUpCardsEvent);
                            requestForDrawTwoCardsDone=true;
                        }
                    }

                    if(terminatorModeActive) {
                        numOfMaxActionForTurn = 3;
                        hasToDoTerminatorAction = false;
                    }
                    updateMap();
                }
            }
            round++;
        }

    }

    /**
     * Update the map and send an event of type {@link MapEvent}
     */
    void updateMap(){
        MapEvent mapEvent = new MapEvent();
        mapEvent.setTerminatorMode(isTerminatorModeActive());
        mapEvent.setNicknames(listOfNickname);
        mapEvent.setMap(board.getMap().getMatrixOfSquares());
        mapEvent.setTypeOfMap(board.getMap().getTypeOfMap());
        notify(mapEvent);
    }


    private void createBoard(){
        this.board = Board.instanceOfBoard();
    }


    public static Game instanceOfGame(){
        if(instanceOfGame == null)
             instanceOfGame = new Game();
        return instanceOfGame;
    }

    public void incrementNumOfActionsOfThisTurn(){
        numOfActionOfTheTurn++;
    }

    /**
     * Creates player in accordance with the parameter
     * @param nickname The nickname of the player
     * @param isFirst True if it is the first, false otherwhise
     * @param position The position in the round
     * @param color The color of the figure of the player
     */
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
    }

    /**
     * Adds for each player his nickname
     */
    private void setListOfNickname() {
        for(Player player : players)
        {
            listOfNickname.add(player.getNickname());
        }
    }




    public int getPlayerOfTurn() {
        return playerOfTurn;
    }

    public void setGameOver(boolean over) {
        isGameOver = over;
    }

    public boolean isHasToDoTerminatorAction() {
        return hasToDoTerminatorAction;
    }

    public void setHasToDoTerminatorAction(boolean hasToDoTerminatorAction) {
        this.hasToDoTerminatorAction = hasToDoTerminatorAction;
    }

    public int getRound() {
        return round;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public int getNumOfActionOfTheTurn() {
        return numOfActionOfTheTurn;
    }

    public boolean isInFrenzy() {
        return isInFrenzy;
    }

    public int getFirstInFrenzyMode() {
        return firstInFrenzyMode;
    }

    public ArrayList<String> getListOfNickname() {
        return listOfNickname;
    }

    public int getNumOfMaxActionForTurn() {
        return numOfMaxActionForTurn;
    }

    public boolean isTerminatorModeActive() {
        return terminatorModeActive;
    }


}
