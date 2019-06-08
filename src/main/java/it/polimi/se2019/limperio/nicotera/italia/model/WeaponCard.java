package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

/**
 * This class implements the game's weaponCards
 *
 * @author Giuseppe Italia
 */
public abstract class WeaponCard extends Card  {

    private Boolean isLoad = true;
    private ColorOfCard_Ammo[] priceToBuy = null;
    private ColorOfCard_Ammo[] priceToReload;
    private Boolean[] hasThisKindOfAttack;
    private ArrayList<String> namesOfAttack = new ArrayList<>();
    private ArrayList<String> descriptionsOfAttack = new ArrayList<>();
    private ColorOfCard_Ammo[] priceToPayForEffect1 = null;
    private ColorOfCard_Ammo[] priceToPayForEffect2 = null;
    private ColorOfCard_Ammo[] priceToPayForAlternativeMode = null;




    public Boolean getLoad() {
        return isLoad;
    }

    public void setLoad(Boolean load) {
        isLoad = load;
    }

    public ArrayList<String> getNamesOfAttack() {
        return namesOfAttack;
    }

    public void setNamesOfAttack(ArrayList<String> namesOfAttack) {
        this.namesOfAttack = namesOfAttack;
    }

    public ArrayList<String> getDescriptionsOfAttack() {
        return descriptionsOfAttack;
    }

    public void setDescriptionsOfAttack(ArrayList<String> descriptionsOfAttack) {
        this.descriptionsOfAttack = descriptionsOfAttack;
    }

    public ColorOfCard_Ammo[] getPriceToPayForEffect1() {
        return priceToPayForEffect1;
    }

    public void setPriceToPayForEffect1(ColorOfCard_Ammo[] priceToPayForEffect1) {
        this.priceToPayForEffect1 = priceToPayForEffect1;
    }

    public ColorOfCard_Ammo[] getPriceToPayForEffect2() {
        return priceToPayForEffect2;
    }

    public void setPriceToPayForEffect2(ColorOfCard_Ammo[] priceToPayForEffect2) {
        this.priceToPayForEffect2 = priceToPayForEffect2;
    }

    public ColorOfCard_Ammo[] getPriceToPayForAlternativeMode() {
        return priceToPayForAlternativeMode;
    }

    public void setPriceToPayForAlternativeMode(ColorOfCard_Ammo[] priceToPayForAlternativeMode) {
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

    /**
     * generates the attack through a weapon card
     * @param typeOfAttack list of attack types, so if only first effect or with the second etc.
     * @param involvedPlayers the players affected by this attack
     * @throws IllegalArgumentException is generated if one element of typeOfAttack is not between 1 and 4
     */

    public abstract void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) throws IllegalArgumentException;

    public WeaponCard(ColorOfCard_Ammo color, String name) {
        super(color, name);
    }


    /**
     * Generates WeaponCard
     * @param typeOfWeaponCard number with which we differentiate the typology of WeaponCard
     * @return The WeaponCard generated
     * @throws IllegalArgumentException is generated if typeOfWeaponCard is not between 0 and 20
     */


     public static WeaponCard createWeaponCard(int typeOfWeaponCard) throws IllegalArgumentException{
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
}