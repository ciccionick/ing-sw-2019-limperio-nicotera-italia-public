package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerForEnter implements KeyListener {

    private InitializationView initializationView;
    private FrameForInitialization frameForInitialization;

     KeyListenerForEnter(FrameForInitialization frameForInitialization, InitializationView initializationView) {
        this.initializationView = initializationView;
        this.frameForInitialization = frameForInitialization;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //not implemented
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            String nickname;
            JTextField textField = (JTextField) this.frameForInitialization.getMapOfComponents().get("TextField");
            nickname = textField.getText();
            if(nickname.length()!=0){
                AnswerInitializationEvent ans = new AnswerInitializationEvent(nickname,null,0,false,false);
                initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //not implemented
    }
}
