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
    public void shoot (Player[] players, WeaponCard wheaponCard, int[] typeOfAttack){}
    @Override
    public void catchWheapon(Square square, WeaponCard wheaponCard){}
    @Override
    public void fishTwoPowerUpCard(){}
    @Override
    public void discardPowerUpCard(PowerUpCard card){}
}
