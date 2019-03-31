package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

public abstract class WheaponCard extends Card {

    private Boolean isLoad;
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


    public abstract void useWheapon(ArrayList<Integer> typeOfAttack);

    public WheaponCard(ColorOfCard_Ammo color, String name, String description) {
        super(color, name, description);

    }

    public WheaponCard(int typeOfWheaponCard) {
        switch(typeOfWheaponCard) {
            case 1:
                new LockRifle();
            case 2:
                new ElectroScythe();
            case 3:
                new MachineGun();
            case 4:
                new TractorBeam();
            case 5:
                new Thor();
            case 6:
                new VortexCannon();
            case 7:
                new PlasmaGun();
            case 8:
                new Furnace();
            case 9:
                new Whisper();
            case 10:
                new HeatSeeker();
            case 11:
                new Hellion();
            case 12:
                new Flamethower();
            case 13:
                new Zx2();
            case 14:
                new GranadeLauncher();
            case 15:
                new Shotgun();
            case 16:
                new RocketLauncher();
            case 17:
                new PowerGlove();
            case 18:
                new Railgun();
            case 19:
                new Shockwave();
            case 20:
                new Cyberblade();
            case 21:
               new Sledgehammer();
        }

    }
}
