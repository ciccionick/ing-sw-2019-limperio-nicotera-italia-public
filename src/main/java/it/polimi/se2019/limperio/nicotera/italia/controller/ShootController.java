package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseWeaponCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestActionEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAnEffect;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseWeapon;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.model.WeaponCard;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class handles the request of shoot by a player to another one.
 */
public class ShootController {

    private final Game game;
    private Controller controller;
    private ArrayList<InvolvedPlayer> involvedPlayers = new ArrayList<>();
    private ArrayList<Integer> typeOfAttack = new ArrayList<>();
    private WeaponCard weaponToUse;

    public ShootController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }


     void replyToRequestToShoot(ClientEvent message) {
        typeOfAttack = new ArrayList<>();
        involvedPlayers = new ArrayList<>();
        weaponToUse = null;
        Player player = controller.findPlayerWithThisNickname(message.getNickname());
        if(game.isInFrenzy() && player.getPosition()>=game.getFirstInFrenzyMode() || !game.isInFrenzy() && player.isOverSixDamage()){
            //seleziona quadrati a 1 distanza in cui si puo spostare e in cui puo utilizzare almeno un arma
            return;
        }
        if(game.isInFrenzy() && player.getPosition()<game.getFirstInFrenzyMode()){
            //seleziona quadrati a 2 distanza in cui si puo spostare e in cui puo utilizzare almeno un arma
        }

        else{
            boolean[] canUseWeapon = new boolean[3];
            canUseWeapon[0] = false;
            canUseWeapon[1] = false;
            canUseWeapon[2] = false;
            int i = 0;
            for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()){
                canUseWeapon[i] = controller.checkIfThisWeaponIsUsable(weaponCard, 0);
                i++;
            }

            RequestToChooseWeapon requestToChooseWeapon = new RequestToChooseWeapon();
            requestToChooseWeapon.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
            requestToChooseWeapon.setMessageForInvolved("Choose one weapon enable to shoot");
            requestToChooseWeapon.setCanUseWeapon1(canUseWeapon[0]);
            requestToChooseWeapon.setCanUseWeapon2(canUseWeapon[1]);
            requestToChooseWeapon.setCanUseWeapon3(canUseWeapon[2]);

            game.notify(requestToChooseWeapon);
        }
    }

    private WeaponCard findWeaponCardWithThisName(String name, Player player){
        for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()){
            if(weaponCard.getName().equals(name))
                return weaponCard;
        }
        throw new IllegalArgumentException();
    }

     void replyWithUsableEffectsOfThisWeapon(ClientEvent message) {
        String nameOfCard = ((RequestToUseWeaponCard)message).getWeaponWantUse().getName();
        Player player = controller.findPlayerWithThisNickname(message.getNickname());
        WeaponCard weaponCard = findWeaponCardWithThisName(nameOfCard, player);
        weaponToUse = weaponCard;
         ArrayList<Integer> usableEffectsForThisWeapon = controller.getWeaponController().getUsableEffectsForThisWeapon(weaponCard);
         RequestToChooseAnEffect requestToChooseAnEffect = new RequestToChooseAnEffect();
         if(typeOfAttack.isEmpty())
            requestToChooseAnEffect.setMessageForInvolved("Choose, to start, one of these effects:");
         else{
             for(Integer effect : typeOfAttack){
                     usableEffectsForThisWeapon.remove(effect);
             }
             requestToChooseAnEffect.setOneEffectAlreadyChoosen(true);
             requestToChooseAnEffect.setMessageForInvolved("Choose another effect or press on 'END ACTION'");
             requestToChooseAnEffect.setCanTerminateAction(true);
         }
         requestToChooseAnEffect.setNameOfCard(weaponCard.getName());
         requestToChooseAnEffect.setNicknameInvolved(player.getNickname());
         requestToChooseAnEffect.setUsableEffects(usableEffectsForThisWeapon);
         message.getMyVirtualView().update(requestToChooseAnEffect);
    }

    public ArrayList<InvolvedPlayer> getInvolvedPlayers() {
        return involvedPlayers;
    }

    public ArrayList<Integer> getTypeOfAttack() {
        return typeOfAttack;
    }

     void handleRequestToUseEffect(ClientEvent message) {
         Square squareOfPlayer = controller.findPlayerWithThisNickname(message.getNickname()).getPositionOnTheMap();
         switch (weaponToUse.getName()){
             case "Electroscythe":
             case "Cyberblade":
             case "Sledgehammer":
             case "Shotgun":
             case "Shockwave":
             case "Furnace":
             case "Lock rifle":
             case "Zx-2":
             case "Machine gun":
             case "Granade launcher":
             case "Plasma gun":
             case "Railgun":
             case "Heatseeker":
             case "Rocket launcher":
             case "Hellion":
             case "Whisper":
             case "THOR":
             case "Flamethrower":
             case "Power glove":
             case "Tractor beam":
             case "Vortex cannon":

             default:
                 throw  new IllegalArgumentException();
         }
    }
}
