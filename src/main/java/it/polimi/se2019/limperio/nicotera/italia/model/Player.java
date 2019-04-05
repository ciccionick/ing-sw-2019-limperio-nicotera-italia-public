package it.polimi.se2019.limperio.nicotera.italia.model;

public class Player implements PlayerBehaviour{
    private final ColorOfFigure_Square colorOfFigure;
    private boolean isFirst;
    private boolean isUnderThreeDamage = true;
    private boolean isOverSixDamage = false;
    private int numOfDeath;
    private int score;
    private boolean doubleKill;
    private Square positionOnTheMap;
    private String nickname;
    private PlayerBoard playerBoard;


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

    public Player(String nickname, ColorOfFigure_Square colorOfFigure) {
        this.colorOfFigure = colorOfFigure;
        this.nickname = nickname;
    }
    public void useAmmoForPay(Ammo[] ammo){}
    public void updateScore(int newscore){}
    public void createPlayerBoard(){}

    @Override
    public void run(Square square){}
    @Override
    public void catchAmmoTile (Square square){}
    @Override
    public void shoot (Player[] players, WeaponCard weaponCard, int[] typeOfAttack){}
    @Override
    public void catchWheapon(Square square, WeaponCard weaponCard){}

    @Override
    public void drawTwoPowerUpCard() {

    }

    @Override
    public void discardPowerUpCard(PowerUpCard card){}

    public void setPositionOnTheMap(Square positionOnTheMap) {
        this.positionOnTheMap = positionOnTheMap;
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
            while(numOfMarks!=0 && playerBoard.getNumOfMarksOfOneColor(colorOfDamage)<4 ){
                playerBoard.getMarks().add(colorOfDamage);
            }
        }

}


