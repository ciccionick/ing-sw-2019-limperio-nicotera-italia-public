package it.polimi.se2019.limperio.nicotera.italia.network.server;

import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * View of the client in the server side. Handle the communication with the client and the exchange of event.
 * <p>
 *     Each client has her virtual view.
 * </p>
 * <p>
 *     Run in a thread.
 * </p>
 *
 * @author Pietro L'Imperio
 */

public class VirtualView extends Observable<ClientEvent> implements Observer<ServerEvent>,Runnable {
    /**
     * The reference of the client associated
     */
    private Socket client;
    /**
     * It's true until the client is online, false later.
     */
    private boolean isClientCurrentlyOnline=true;
    /**
     * The nickname of the client associated
     */
    private String nicknameOfClient;
    /**
     * The color of the client associated
     */
    private String colorOfClient;
    /**
     * The reference of the server
     */
    private Server server;
    /**
     * The object input stream
     */
    private ObjectInputStream in = null;
    /**
     * The object output stream
     */
    private ObjectOutputStream out = null;
    /**
     * The reference of the controller
     */
    private Controller controller;
    /**
     * It's true if the client associated represents the first player, false otherwise
     */
    private boolean firstPlayer;


    /**
     * Sets first player attribute, initialize the object streams
     * @param client The client associated
     * @param server The server that created it
     * @param controller The controller which he will send events by client with
     */
     VirtualView(Socket client, Server server, Controller controller) {
        this.client = client;
        this.server = server;
        this.controller = controller;
        register(controller);
        if (server.getListOfClient().size() == 1)
            this.firstPlayer = true;
        else
            this.firstPlayer = false;
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            try {
                client.close();
            } catch (Exception er) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Manages the first phase of initialization of the client and then will remain in listening for
     * the handle of the event by client
     */
    @Override
    public void run() {
        try {
            boolean invalidInitialization = true;
            RequestInitializationEvent req;
            while (invalidInitialization) {
                out.writeObject(new RequestInitializationEvent("Digit your nickname", true, false, false, false, false));
                AnswerInitializationEvent ans = (AnswerInitializationEvent) in.readObject();
                while(server.getListOfNickname().contains(ans.getNickname())){
                    req = new RequestInitializationEvent("Digit your nickname: ", true, false, false, false, false);
                    req.setRetake(true);
                    out.writeObject(req);
                    ans = (AnswerInitializationEvent) in.readObject();
                }
                server.addNickname(ans.getNickname());
                nicknameOfClient = ans.getNickname();
                out.writeObject(new RequestInitializationEvent("Digit your color:", false, true, false, false, false));
                ans = (AnswerInitializationEvent) in.readObject();
                while(server.getListOfColor().contains(ans.getColor().toUpperCase())){
                    req = new RequestInitializationEvent("Digit your color: ", false, true, false, false, false);
                    req.setRetake(true);
                    out.writeObject(req);
                    ans = (AnswerInitializationEvent) in.readObject();
                }
                server.getListOfColor().add(ans.getColor().toUpperCase());
                colorOfClient = ans.getColor().toUpperCase();
                if(firstPlayer){
                    out.writeObject(new RequestInitializationEvent("Choose if u want frenzy:", false, false, true, false, false));
                    ans = (AnswerInitializationEvent) in.readObject();
                    server.setAnticipatedFrenzy(ans.isFrenzy());
                    out.writeObject(new RequestInitializationEvent("Choose which map do you want to play with:", false, false, false, false, true));
                    ans = (AnswerInitializationEvent) in.readObject();
                    server.setTypeMap(ans.getMap(), false);
                    out.writeObject(new RequestInitializationEvent("Choose if u want terminator mode:", false, false, false, true, false));
                    ans = (AnswerInitializationEvent) in.readObject();
                    server.setTerminatorMode(ans.isTerminator());
                }
                req = new RequestInitializationEvent("ack: ", false, false, false, false, false);
                req.setAck(true);
                out.writeObject(req);
                invalidInitialization=false;
            }
        } catch (SocketException se) {
            handleDisconnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (isClientCurrentlyOnline) {
            ClientEvent newEvent;
            try {
                System.out.println("La virtual view di " + nicknameOfClient + " e in attesa di messaggi provenienti dalla remote view..");
                newEvent = (ClientEvent) in.readObject();
                newEvent.setMyVirtualView(this);
                notify(newEvent);
            } catch (SocketException se) {
                handleDisconnection();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Sends events received by Model and Controller to the client through socket stream only if its client is involved
     * @param event The event received
     */
    @Override
    public void update(ServerEvent event) {
        if(event.getNicknames().contains(nicknameOfClient)) {
            try {
                out.writeObject(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Handles the disconnection of the client sending information to the server and the controller
     */
    private void handleDisconnection(){
        System.out.println("Il client " + nicknameOfClient + " si è disonnesso!");
        server.getListOfNickname().remove(nicknameOfClient);
        server.getListOfColor().remove(colorOfClient);
        server.deregister(this, client);
        isClientCurrentlyOnline=false;
        //QUI BISOGNA INVIARE UN MESSAGGIO AL CONTROLLER DICENDO CHE IL PLAYER CON IL NICKNAME nicknameOfClient SI E' DISCONNESSO IN MODO CHE IL MODEL LO SAPPIA E POSSA EVENTUALMENTE SALTARE IL SUO TURNO
        try {
            in.close();
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}