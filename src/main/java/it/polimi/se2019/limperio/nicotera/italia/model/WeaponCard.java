package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

public abstract class WeaponCard extends Card {

    private Boolean isLoad = true;
    private ColorOfCard_Ammo[] priceToBuy;
    private ColorOfCard_Ammo[] priceToReload;
    private Boolean[] hasThisKindOfAttack;




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


    public abstract void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) throws IllegalArgumentException;

    public WeaponCard(ColorOfCard_Ammo color, String name) {
        super(color, name);
    }

     static WeaponCard createWeaponCard(int typeOfWeaponCard) throws IllegalArgumentException{
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
                return new HeatSeeker();
            case 11:
                return new Hellion();
            case 12:
                return new Flamethower();
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