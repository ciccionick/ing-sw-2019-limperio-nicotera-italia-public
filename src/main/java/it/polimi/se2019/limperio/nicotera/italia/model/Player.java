package it.polimi.se2019.limperio.nicotera.italia.model;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class is used to represent the players in the game
 * @author Francesco Nicotera
 */
public class Player implements PlayerBehaviour, Comparable<Player>{
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
     * It is used at the end of the game to calculate the final ranking
     */
    private int score=0;
    /**
     * The position of the player in the turn
     */
    private int position;

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
    private boolean isDirectlyOverkilled = false;


    public Player(String nickname, boolean isFirst, int position, ColorOfFigure_Square colorOfFigure) {
        this.nickname=nickname;
        this.isFirst=isFirst;
        this.position=position;
        this.colorOfFigure = colorOfFigure;

    }

    public void createPlayerBoard(){
        playerBoard = new PlayerBoard(this.nickname, this.colorOfFigure);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public boolean isDirectlyOverkilled() {
        return isDirectlyOverkilled;
    }

    public void setDirectlyOverkilled(boolean directlyOverkilled) {
        isDirectlyOverkilled = directlyOverkilled;
    }

    public ColorOfFigure_Square getColorOfFigure() {
        return colorOfFigure;
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

    public void updateScore(int newScore){
        this.score = score+newScore;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setIsUnderThreeDamage(boolean bool){this.isUnderThreeDamage=bool;}

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
     * @param colorOfDamage the color of the player that gives the damages
     * @param numOfDamage the number of damages
     */
    public void assignDamage(ColorOfFigure_Square colorOfDamage, int numOfDamage){
        int numOfPreviousDamage = playerBoard.getDamages().size();
          while(numOfDamage>0 && playerBoard.getDamages().size()<12){
              playerBoard.getDamages().add(colorOfDamage);
              numOfDamage--;
          }
          while(playerBoard.getNumOfMarksOfOneColor(colorOfDamage)>0 && playerBoard.getDamages().size()<=12){
              playerBoard.getDamages().add(colorOfDamage);
              playerBoard.getMarks().remove(colorOfDamage);
          }

          if(playerBoard.getDamages().size()>2)
              isUnderThreeDamage=false;
          if (playerBoard.getDamages().size()>5)
              isOverSixDamage = true;
          if(playerBoard.getDamages().size()>=11)
              isDead=true;
          if(numOfPreviousDamage<11 && playerBoard.getDamages().size()==12)
              isDirectlyOverkilled = true;
    }

    /**
     * Assigns the marks to the player
     * @param colorOfDamage the color of the player that gives the marks
     * @param numOfMarks the number of marks
     */
    public void assignMarks (ColorOfFigure_Square colorOfDamage, int numOfMarks){
            int i = numOfMarks;
            while(i!=0 && playerBoard.getNumOfMarksOfOneColor(colorOfDamage)<3 ){
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


    @Override
    public void shoot(int effect, WeaponCard weaponCard, ArrayList<InvolvedPlayer> involvedPlayers, ColorOfCard_Ammo[] priceToPay, ArrayList<PowerUpCard> powerUpToDiscard) {
        if(priceToPay==null) {
            weaponCard.useWeapon(effect, involvedPlayers);
            return;
        }
        if(powerUpToDiscard==null){
            for(ColorOfCard_Ammo ammo : priceToPay){
                playerBoard.removeAmmoOfThisColor(ammo);
            }
            weaponCard.useWeapon(effect, involvedPlayers);
        }
        else{
            for (ColorOfCard_Ammo ammo : priceToPay) {
                if(areThereEnoughAmmo(ammo))
                    playerBoard.removeAmmoOfThisColor(ammo);
                else{
                    playerBoard.getPowerUpCardsOwned().remove(findPowerUpOfThisColor(powerUpToDiscard,ammo));
                }
            }
            weaponCard.useWeapon(effect, involvedPlayers);
        }


    }


    /**
     * It is called when a player decides to catch a weapon
     *
     * @param weaponCard the weapon the player want to catch
     *
     */
    @Override
    public void catchWeapon(WeaponCard weaponCard, ArrayList<PowerUpCard> powerUpCardToDiscard){
        playerBoard.getWeaponsOwned().add(weaponCard);
        if(weaponCard.getPriceToBuy()!=null) {
            for (ColorOfCard_Ammo ammo : weaponCard.getPriceToBuy()) {
                if(areThereEnoughAmmo(ammo))
                    playerBoard.removeAmmoOfThisColor(ammo);
                else{
                    playerBoard.getPowerUpCardsOwned().remove(findPowerUpOfThisColor(powerUpCardToDiscard,ammo));
                }
            }
        }
        weaponCard.setOwnerOfCard(this);

    }

    private PowerUpCard findPowerUpOfThisColor(ArrayList<PowerUpCard> powerUpCardToDiscard, ColorOfCard_Ammo ammo) {
        for(PowerUpCard powerUpCard : powerUpCardToDiscard){
            if(powerUpCard.getColor().equals(ammo))
                return powerUpCard;
        }
        throw new IllegalArgumentException();
    }

    private boolean areThereEnoughAmmo(ColorOfCard_Ammo ammo) {
        for(Ammo ammoItem : playerBoard.getAmmo()){
            if(ammoItem.getColor().equals(ammo) && ammoItem.isUsable())
                return true;
        }
        return false;
    }


    /**
     * It is called when a player decides to draw a power up card
     * @param powerUpCardsToDraw the cards that must be drawn
     */
    @Override
    public void drawPowerUpCard(PowerUpCard powerUpCardsToDraw) {
        powerUpCardsToDraw.setOwnerOfCard(this);
        playerBoard.getPowerUpCardsOwned().add(powerUpCardsToDraw);
    }

    @Override
    public void useTargetingScope(Player playerToAttack, PowerUpCard targetingScope, ColorOfCard_Ammo ammoToDiscard, PowerUpCard powerUpCardToDiscard) {
        playerToAttack.assignDamage(this.getColorOfFigure(), 1);
        targetingScope.setOwnerOfCard(null);
        targetingScope.setInTheDeckOfSomePlayer(false);
        playerBoard.getPowerUpCardsOwned().remove(targetingScope);
        if(ammoToDiscard!=null){
            for(Ammo ammoItem : playerBoard.getAmmo()){
                if(ammoItem.getColor().equals(ammoToDiscard) && ammoItem.isUsable()) {
                    ammoItem.setIsUsable(false);
                    break;
                }
            }
        }
        else{
            powerUpCardToDiscard.setOwnerOfCard(null);
            powerUpCardToDiscard.setInTheDeckOfSomePlayer(false);
            playerBoard.getPowerUpCardsOwned().remove(powerUpCardToDiscard);
        }
    }

    @Override
    public void useTagbackGranade(PowerUpCard tagback, Player playerToAttack) {
        playerToAttack.assignMarks(this.colorOfFigure, 1);
        tagback.setOwnerOfCard(null);
        tagback.setInTheDeckOfSomePlayer(false);
        playerBoard.getPowerUpCardsOwned().remove(tagback);
    }

    @Override
    public void reload(WeaponCard card, ArrayList<PowerUpCard> powerUpCardsToDiscard) {
        for (ColorOfCard_Ammo ammo : card.getPriceToReload()) {
            if (areThereEnoughAmmo(ammo))
                playerBoard.removeAmmoOfThisColor(ammo);
            else {
                playerBoard.getPowerUpCardsOwned().remove(findPowerUpOfThisColor(powerUpCardsToDiscard, ammo));
            }
        }
        card.setLoad(true);
    }



    @Override
    public int compareTo(Player o) {
        int result;
        result = nickname.compareTo(o.getNickname());
        return result;
    }

   public static class ScoreComparator implements Comparator<Player>{

       @Override
       public int compare(Player o1, Player o2) {
           return o2.getScore()-o1.getScore();
       }
   }

    public void setOverSixDamage(boolean overSixDamage) {
        isOverSixDamage = overSixDamage;
    }
}


