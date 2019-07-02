package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseNewton;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseTeleporter;
import javax.swing.*;
import java.awt.event.*;

/**
 * Listener for the power up cards stored in the deck and relative buttons to use them.
 * @author Pietro L'Imperio
 */
public class ListenerForPowerUpCard extends MouseAdapter implements ActionListener {

    /**
     * The label where is representing the icon of the card linked with an instance of the this class.
     */
    private JLabel labelOfCard;
    /**
     * The position of the card in the deck of the player.
     */
    private int numOfCard;
    /**
     * The reference of the main frame.
     */
    MainFrame mainFrame;
    /**
     * The reference of an object of the class PopupForPowerUpCard that it will be instantiate clicking on the label with the following creation of a relative dialog.
     */
    private PopupForPowerUpCard popupForPowerUpCard = null;


    /**
     * Constructor where class fields are initialized.
     */
     ListenerForPowerUpCard(JLabel labelOfCard, MainFrame mainFrame, int numOfCard) {
        this.labelOfCard = labelOfCard;
        this.mainFrame = mainFrame;
        this.numOfCard = numOfCard;
    }

    /**
     * If the reference of the popup is null creates a new instance, otherwise makes not visible the dialog creating previously and makes null the reference of the popup.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(popupForPowerUpCard!=null) {
            popupForPowerUpCard.getPopupForPC().setVisible(false);
            popupForPowerUpCard=null;
        }
        else if(labelOfCard.isEnabled()){
            popupForPowerUpCard = new PopupForPowerUpCard(mainFrame, numOfCard);
            popupForPowerUpCard.getPopupForPC().setVisible(true);
        }

    }

    /**
     * Creates an event containing the request to use a power up card. It's valid only for the Teleporter and Newton.
     * Then calls the notify of the remote view.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if((numOfCard == 1 && mainFrame.getLeftPanel().getButtonPC1().isEnabled()) || (numOfCard == 2 && mainFrame.getLeftPanel().getButtonPC2().isEnabled()) || (numOfCard == 3 && mainFrame.getLeftPanel().getButtonPC3().isEnabled())){
            if(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getName().equals("Teleporter")){
                RequestToUseTeleporter requestToUseTeleporter = new RequestToUseTeleporter("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                requestToUseTeleporter.setNumOfCard(numOfCard);
                mainFrame.getRemoteView().notify(requestToUseTeleporter);
            }
            else if(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getName().equals("Newton")){
                RequestToUseNewton requestToUseNewton = new RequestToUseNewton("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                requestToUseNewton.setNumOfCard(numOfCard);
                mainFrame.getRemoteView().notify(requestToUseNewton);
            }
        }
    }
}
