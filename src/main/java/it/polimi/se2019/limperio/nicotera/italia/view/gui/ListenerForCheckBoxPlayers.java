package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the check box buttons concerning the choice of multiple players.
 * @author Pietro L'Imperio.
 */
public class ListenerForCheckBoxPlayers implements ActionListener {
    /**
     * The number of buttons already selected.
     */
    private int numOfButtonsSelected = 0;
    /**
     * The maximum number of buttons selectable.
     */
    private int namOfMaxCheckBoxSelectable;
    /**
     * The reference of the class that creates the dialog where there are the group of check box that use this listener.
     */
    private PopupToChooseMultiplePlayers popupToChooseMultiplePlayers;

    /**
     * Constructor of the class that initialize the class fields.
     */
    ListenerForCheckBoxPlayers(int numOfMaxSelectableCheckBox, PopupToChooseMultiplePlayers popupToChooseMultiplePlayers) {
        this.namOfMaxCheckBoxSelectable = numOfMaxSelectableCheckBox;
        this.popupToChooseMultiplePlayers = popupToChooseMultiplePlayers;
    }

    /**
     * Adds to a list (selectedNames) the nickname of the players selected with check box avoiding that the number of selected players is lower than the maximum.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (numOfButtonsSelected == namOfMaxCheckBoxSelectable) {
            for (JCheckBox checkBox : popupToChooseMultiplePlayers.getListOfCheckBox()) {
                if (checkBox.isSelected() && !checkBox.getActionCommand().equals(e.getActionCommand())) {
                    checkBox.setSelected(false);
                    popupToChooseMultiplePlayers.getSelectedNames().add(e.getActionCommand());
                    popupToChooseMultiplePlayers.getSelectedNames().remove(checkBox.getActionCommand());
                    break;
                }
            }
        } else {
            popupToChooseMultiplePlayers.getSelectedNames().add(e.getActionCommand());
            numOfButtonsSelected++;
        }
    }

}
