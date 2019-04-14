package it.polimi.se2019.limperio.nicotera.italia.network.server;


import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server  {

    private static final int PORT = 60000;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private Map<String, Client> waitingConnection = new HashMap<>();

    public static void main(String[] args) {
        Server server;
        try {
            server = new Server();
           // server.run();
        } catch (IOException e) {
            System.err.println("Impossible initialize server: " + e.getMessage() + "!");
        }
    }

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);

    }





}










