package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.RequestNicknameEvent;

import java.io.IOException;

public interface RemoteViewInterface {
    public void showMessage(String message);
    public void sendMessage() throws IOException;
    public void handleEvent(RequestNicknameEvent event) throws IOException;
}
