package it.polimi.se2019.limperio.nicotera.italia.model;


import java.util.ArrayList;

public class Player implements PlayerBehaviour{
    private ColorOfFigure_Square colorOfFigure;
    private boolean isFirst;
    private boolean isUnderThreeDamage = true;
    private boolean isOverSixDamage = false;
    private boolean isConnected = true;
    private int numOfDeath;
    private int score;
    private int position;
    private boolean doubleKill;
    private Square positionOnTheMap = null;
    private String nickname;
    private PlayerBoard playerBoard = null;


    public Player(String nickname, boolean isFirst, int position, ColorOfFigure_Square colorOfFigure) {
        this.nickname=nickname;
        this.isFirst=isFirst;
        this.position=position;
        this.colorOfFigure = colorOfFigure;
    }

    public void createPlayerBoard(){
        playerBoard = new PlayerBoard();
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

    public Square getPositionOnTheMap() {
        return positionOnTheMap;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPosition() {
        return position;
    }


    public void useAmmoForPay(Ammo[] ammo){}
    public void updateScore(int newscore){}





    public void setPositionOnTheMap(Square positionOnTheMap) {
       if(this.positionOnTheMap != null)
           this.positionOnTheMap.getPlayerOfThisSquare().remove(this);
        this.positionOnTheMap = positionOnTheMap;
        positionOnTheMap.setPlayerOfThisSquare(this);
    }

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

    public void assignMarks (ColorOfFigure_Square colorOfDamage, int numOfMarks){
            int i = numOfMarks;
            while(i!=0 && playerBoard.getNumOfMarksOfOneColor(colorOfDamage)<4 ){
                playerBoard.getMarks().add(colorOfDamage);
                i--;
            }
        }

    @Override
    public void run(Square square){}

    @Override
    public void catchAmmoTile (Square square){}

    @Override
    public void shoot (Player[] players, WeaponCard weaponCard, int[] typeOfAttack){}

    @Override
    public void catchWeapon(Square square, WeaponCard weaponCard){}

    @Override
    public void drawPowerUpCard(ArrayList<PowerUpCard> powerUpCardsToDraw) {
        playerBoard.getPowerUpCardsOwned().addAll(powerUpCardsToDraw);
        System.out.println("Ho aggiunto due carte al mio mazzo di power up cards: " + playerBoard.getPowerUpCardsOwned().size());

    }


    @Override
    public void discardPowerUpCard(PowerUpCard card){}

}


