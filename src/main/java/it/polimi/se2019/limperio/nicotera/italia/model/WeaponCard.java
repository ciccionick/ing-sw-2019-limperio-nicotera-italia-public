package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

/**
 * This class implements the weapon cards of the game.
 * @author Giuseppe Italia
 */
public abstract class WeaponCard extends Card  {

    /**
     * It's true if the weapon is load, false otherwise.
     */
    private boolean isLoad = true;
    /**
     * It's the price to pay to buy the weapon.
     */
    private ColorOfCard_Ammo[] priceToBuy = null;
    /**
     * It's the price to pay to reload the weapon.
     */
    private ColorOfCard_Ammo[] priceToReload;
    /**
     * It's the array of boolean that shows which kind of effect the weapon has.
     */
    private Boolean[] hasThisKindOfAttack;
    /**
     * The list of the names of the effects that the weapon has.
     */
    private ArrayList<String> namesOfAttack = new ArrayList<>();
    /**
     * The list of the descriptions of the different effects the weapon has.
     */
    private ArrayList<String> descriptionsOfAttack = new ArrayList<>();
    /**
     * The price to pay to use the first extra effect.
     */
    private ColorOfCard_Ammo[] priceToPayForEffect1 = null;
    /**
     * The price to pay to use the second extra effect.
     */
    private ColorOfCard_Ammo[] priceToPayForEffect2 = null;
    /**
     * The price to pay to use the alternative mode.
     */
    private ColorOfCard_Ammo[] priceToPayForAlternativeMode = null;



    /**
     * Handles the attack of a weapon card. Through a switch of if-else statement and according with the typeOfAttack calls the relative method of a weapon to complete the attack chosen by the player. Sometimes the switch statement is not required, for example when the weapon has only one effect.
     * @param typeOfAttack Number of the effect of the weapon to use.
     * @param involvedPlayers List of object that contains player or/and square involved in the attack.
     * @throws IllegalArgumentException is generated if one element of typeOfAttack is not between 1 and 4
     */
    public abstract void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) ;

    /**
     * Constructor of the class that initialize name and color of the card.
     */
    public WeaponCard(ColorOfCard_Ammo color, String name) {
        super(color, name);
    }


    /**
     * Creates weapon cards according with the number passed by parameter.
     * @param typeOfWeaponCard Number with which differentiate the typology of weapon card to create
     * @return The weapon card generated
     * @throws IllegalArgumentException is generated if typeOfWeaponCard is not between 0 and 20
     */
     public static WeaponCard createWeaponCard(int typeOfWeaponCard){
        switch(typeOfWeaponCard) {
            case 0:
                return new Cyberblade();
            case 1:
                return new LockRifle();
            case 2:
                return new ElectroScythe();
            case 3:
                return new MachineGun();
            case 4:
                return new TractorBeam();
            case 5:
                return new Thor();
            case 6:
                return new VortexCannon();
            case 7:
                return new PlasmaGun();
            case 8:
                return new Furnace();
            case 9:
                return new Whisper();
            case 10:
                return new Heatseeker();
            case 11:
                return new Hellion();
            case 12:
                return new Flamethrower();
            case 13:
                return new Zx2();
            case 14:
                return new GranadeLauncher();
            case 15:
                return new Shotgun();
            case 16:
                return new RocketLauncher();
            case 17:
                return new PowerGlove();
            case 18:
                return new Railgun();
            case 19:
                return new Shockwave();
            case 20:
                return new Sledgehammer();

            default:
                throw new IllegalArgumentException();
        }

    }

    public ArrayList<String> getNamesOfAttack() {
        return namesOfAttack;
    }


    public ArrayList<String> getDescriptionsOfAttack() {
        return descriptionsOfAttack;
    }


    public ColorOfCard_Ammo[] getPriceToPayForEffect1() {
        return priceToPayForEffect1;
    }

    void setPriceToPayForEffect1(ColorOfCard_Ammo[] priceToPayForEffect1) {
        this.priceToPayForEffect1 = priceToPayForEffect1;
    }

    public ColorOfCard_Ammo[] getPriceToPayForEffect2() {
        return priceToPayForEffect2;
    }

    void setPriceToPayForEffect2(ColorOfCard_Ammo[] priceToPayForEffect2) {
        this.priceToPayForEffect2 = priceToPayForEffect2;
    }

    public ColorOfCard_Ammo[] getPriceToPayForAlternativeMode() {
        return priceToPayForAlternativeMode;
    }

    void setPriceToPayForAlternativeMode(ColorOfCard_Ammo[] priceToPayForAlternativeMode) {
        this.priceToPayForAlternativeMode = priceToPayForAlternativeMode;
    }

    public void setPriceToBuy(ColorOfCard_Ammo[] priceToBuy) {
        this.priceToBuy = priceToBuy;
    }


    public void setPriceToReload(ColorOfCard_Ammo[] priceToReload) {
        this.priceToReload = priceToReload;
    }


    public void setHasThisKindOfAttack(Boolean[] hasThisKindOfAttack) {
        this.hasThisKindOfAttack = hasThisKindOfAttack;

    }

    public void setLoad(boolean load) {
        isLoad = load;
    }


    public Boolean[] getHasThisKindOfAttack() {
        return hasThisKindOfAttack;
    }

    public ColorOfCard_Ammo[] getPriceToReload() {
        return priceToReload;
    }

    public ColorOfCard_Ammo[] getPriceToBuy() {
        return priceToBuy;
    }


    public boolean isLoad() {
        return isLoad;
    }
}