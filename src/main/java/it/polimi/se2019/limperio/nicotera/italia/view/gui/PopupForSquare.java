package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;


 class PopupForSquare {

    JLabel labelPlayer1;
    JLabel labelPlayer2;
    JLabel labelPlayer3;
    JLabel labelPlayer4;
    JLabel labelPlayer5;


    JDialog popup = new JDialog();
    final JPanel contentPane = new JPanel();


    JDialog getPopup() {
        return popup;
    }
}
