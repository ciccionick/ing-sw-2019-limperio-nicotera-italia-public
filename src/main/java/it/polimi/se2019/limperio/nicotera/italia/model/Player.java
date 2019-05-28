package it.polimi.se2019.limperio.nicotera.italia.model;


/**
 * This class is used to represent the players in the game
 * @author Francesco Nicotera
 */
public class Player implements PlayerBehaviour{
    /**
     * Records the colour of the player
     */
    private ColorOfFigure_Square colorOfFigure;
    /**
     * It is true if the player is the first of the turn
     */
    private boolean isFirst;
    /**
     * It is used to distinguish if the player can move two position before catching
     */
    private boolean isUnderThreeDamage = true;
    /**
     * It is used to distinguish if the player can move one position before shooting
     */
    private boolean isOverSixDamage = false;
    private boolean isConnected = true;
    /**
     * @deprecated records the number of death of the player
     */
    private int numOfDeath;
    /**
     * It is used at the end of the game to calculate the final ranking
     */
    private int score=0;
    /**
     * The position of the player in the turn
     */
    private int position;
    /**
     * @deprecated
     */
    private boolean doubleKill;
    /**
     * The square in which the player is during the game
     */
    private Square positionOnTheMap = null;
    /**
     * It's unique for every player
     */
    private String nickname;
    /**
     * The reference to player's board
     */
    private PlayerBoard playerBoard = null;

    private boolean isDead = false;

    private int numOfKillDoneInTheTurn = 0;

    private boolean hasToBeGenerated = true;


    public Player(String nickname, boolean isFirst, int position, ColorOfFigure_Square colorOfFigure) {
        this.nickname=nickname;
        this.isFirst=isFirst;
        this.position=position;
        this.colorOfFigure = colorOfFigure;
    }

    public void createPlayerBoard(){
        playerBoard = new PlayerBoard(this.nickname, this.colorOfFigure);
    }




    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public ColorOfFigure_Square getColorOfFigure() {
        return colorOfFigure;
    }

    public int getNumOfDeath() {
        return numOfDeath;
    }

    public int getScore() {
        return score;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isUnderThreeDamage() {
        return isUnderThreeDamage;
    }

    public boolean isOverSixDamage() {
        return isOverSixDamage;
    }

    public boolean hasDoubleKill() {
        return doubleKill;
    }

    public Square getPositionOnTheMap() { return positionOnTheMap; }

    public String getNickname() {
        return nickname;
    }

    public int getPosition() {
        return position;
    }

    public boolean isHasToBeGenerated() {
        return hasToBeGenerated;
    }

    public void setHasToBeGenerated(boolean hasToBeGenerated) {
        this.hasToBeGenerated = hasToBeGenerated;
    }

    public void useAmmoForPay(Ammo[] ammo){}


    public void updateScore(int newScore){
        this.score = score+newScore;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    /**
     * Moves the player on the square that is passed as parameter
     * @param positionOnTheMap the square in which the player has to be moved
     */
    public void setPositionOnTheMap(Square positionOnTheMap) {
       if(this.positionOnTheMap != null) {
           this.positionOnTheMap.getPlayerOnThisSquare().remove(this);
           this.positionOnTheMap.getNicknamesOfPlayersOnThisSquare().remove(getNickname());
       }
        this.positionOnTheMap = positionOnTheMap;
        positionOnTheMap.setPlayerOnThisSquare(this);
    }

    /**
     * Assigns the damages to the player and also converts marks to damages according to the game's rules
     * @param colOfDamage the color of the player that gives the damages
     * @param numOfDamage the number of damages
     */
    public void assignDamage(ColorOfFigure_Square colOfDamage, int numOfDamage){
           int numOfPreviousDamage;
           numOfPreviousDamage=playerBoard.getDamages().size();
           if(numOfDamage+numOfPreviousDamage+playerBoard.getNumOfMarksOfOneColor(colOfDamage)<=12){
               for(int i=0;i<numOfDamage;i++){
                   playerBoard.getDamages().add(colOfDamage);
               }
               if(playerBoard.getNumOfMarksOfOneColor(colOfDamage)>0) {
                   for (int i = 0; i < playerBoard.getNumOfMarksOfOneColor(colOfDamage); i++) {
                       playerBoard.getDamages().add(colOfDamage);
                   }
                   playerBoard.removeMarkOfOneColor(colOfDamage);
               }
           }
           else{
               while(playerBoard.getDamages().size()<12){
                   if(playerBoard.getNumOfMarksOfOneColor(colOfDamage)!=0){
                       playerBoard.getDamages().add(colOfDamage);
                       playerBoard.getMarks().remove(colOfDamage);
                   }
                   playerBoard.getDamages().add(colOfDamage);
               }
           }
    }

    /**
     * Assigns the marks to the player
     * @param colorOfDamage the color of the player that gives the marks
     * @param numOfMarks the number of marks
     */
    public void assignMarks (ColorOfFigure_Square colorOfDamage, int numOfMarks){
            int i = numOfMarks;
            while(i!=0 && playerBoard.getNumOfMarksOfOneColor(colorOfDamage)<4 ){
                playerBoard.getMarks().add(colorOfDamage);
                i--;
            }
        }



    public boolean isConnected() {
        return isConnected;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getNumOfKillDoneInTheTurn() {
        return numOfKillDoneInTheTurn;
    }

    public void setNumOfKillDoneInTheTurn(int numOfKillDoneInTheTurn) {
        this.numOfKillDoneInTheTurn = numOfKillDoneInTheTurn;
    }

    /**
     * It is called when a player decides to do a run action
     * @param square the final position the player wants to reach
     */
    @Override
    public void run(Square square){}

    /**
     * It is called when a player decide to catch an ammotile
     * @param square the position in which the player wants to catch
     */
    @Override
    public void catchAmmoTile (Square square){}

    /**
     * It is called when a player decide to shoot another ones
     * @param players the players that have shot
     * @param weaponCard the weapon used to shoot
     * @param typeOfAttack the type of attack of weapon used
     */
    @Override
    public void shoot (Player[] players, WeaponCard weaponCard, int[] typeOfAttack){}

    /**
     * It is called when a player decides to catch a weapon
     *
     * @param weaponCard the weapon the player want to catch
     */
    @Override
    public void catchWeapon(WeaponCard weaponCard){
        playerBoard.getWeaponsOwned().add(weaponCard);
        if(weaponCard.getPriceToBuy()!=null) {
            for (ColorOfCard_Ammo ammo : weaponCard.getPriceToBuy()) {
                playerBoard.removeAmmoOfThisColor(ammo);
            }
        }

    }

    /**
     * It is called when a player decides to draw a power up card
     * @param powerUpCardsToDraw the cards that must be drawn
     */
    @Override
    public void drawPowerUpCard(PowerUpCard powerUpCardsToDraw) {
        playerBoard.getPowerUpCardsOwned().add(powerUpCardsToDraw);
    }

    /**
     * It is called when a player discards power up card in order to be spawn
     * @param card the discarded card
     */
    @Override
    public void discardPowerUpCard(PowerUpCard card){}

}


