package it.polimi.se2019.limperio.nicotera.italia.network.client;

import java.io.IOException;

public class ClientMain {

    /**
     * Starts when the application run in the client side
     * @param argv Parameter of main
     * @throws IOException if there will be some problem with initialization of the streams
     * @throws ClassNotFoundException if there will be problems with the object received by the object input stream
     */
    public static void main(String[] argv) throws IOException, ClassNotFoundException {
        Client client;
        client = new Client();
        client.handleConnectionWithServer();
        //client.setFrameForRequestIP( new FrameForRequestIP(client));
    }
}
