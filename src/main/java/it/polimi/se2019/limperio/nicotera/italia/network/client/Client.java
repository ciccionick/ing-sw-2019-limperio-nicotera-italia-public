package it.polimi.se2019.limperio.nicotera.italia.network.client;


import it.polimi.se2019.limperio.nicotera.italia.network.server.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    PrintWriter out=null;
    Scanner in = null;
    Scanner stdin = new Scanner(System.in);

    private final int PORT = 60000;
    public Socket connect() throws IOException {
        Socket serverConnection = null;
        try {
            serverConnection = new Socket(InetAddress.getLocalHost(), PORT);
            out = new PrintWriter(serverConnection.getOutputStream());
            in = new Scanner(serverConnection.getInputStream());
        }
        catch (UnknownHostException unErr){
            System.err.println("Unknown Host");
        }
        catch (IOException ioErr){
            ioErr.printStackTrace();
        }


        return serverConnection;
    }

    public static void main(String[] args) {

    }


}
