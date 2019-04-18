package it.polimi.se2019.limperio.nicotera.italia.network.server;


import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Server  {

    private ServerSocket Server;
    private Game game = null;
    private Controller controller = null;
    private ArrayList<Socket> listOfClient = new ArrayList<>();
    private final long delay;
    private Timer timer = null;
    private MyTask task = null;
    private ArrayList<String> listOfNickname = new ArrayList<>();
    private ArrayList<String> listOfColor = new ArrayList<>();
    private boolean anticipatedFrenzy=false;
    private int typeMap;
    private boolean gameIsStarted = false;

    public static void main(String argv[]) throws Exception {
        new Server();
    }

    public Server() throws Exception {
        Server = new ServerSocket(4000);
        System.out.println("Il Server è in attesa sulla porta 4000.");
        game=Game.instanceOfGame();
        controller=new Controller(game);
        File file = new File("C:\\Users\\Pietro\\OneDrive\\Università\\3° Anno\\II Semestre\\Progetto ISW\\adrenalina\\src\\main\\java\\it\\polimi\\se2019\\limperio\\nicotera\\italia\\utils\\timer.txt");
        FileReader inFile = new FileReader(file);
        BufferedReader bin = new BufferedReader(inFile);
        delay = Long.parseLong(bin.readLine());
        System.out.println("Il timer è a: " + delay/1000 + " secondi");
        task = new MyTask();
        run();
    }

    public void run() {

        while (true) {
            try {
                if(listOfClient.size()==3 && timer==null){
                    System.out.println("Il timer è partito!");
                    timer = new Timer();
                    timer.schedule(task,delay);
                }
                if (listOfClient.size()==5){
                    timer.cancel();
                    timer=null;
                    System.out.println("Il timer e' stato fermato perchè sta per cominciare il gioco!");
                    TimeUnit.SECONDS.sleep(5);
                    startGame();
                }

                System.out.println("In attesa di Connessione.");
                Socket client = Server.accept();
                listOfClient.add(client);
                System.out.println("Connessione accettata da: " + client.getInetAddress());
                VirtualView virtualView = new VirtualView(client, this, controller);
                game.register(virtualView);
                Thread thread = new Thread(virtualView);
                thread.start();
            } catch (Exception e) {
            }

        }
    }

    public void deregister(VirtualView view, Socket client) {
        game.deregister(view);
        listOfClient.remove(client);
        if(listOfClient.size()==2 && timer!=null && !gameIsStarted){
            timer.cancel();
            timer = null;
            System.out.println("Il timer è stato fermato perchè siamo scesi a due giocatori");
        }
        if(listOfClient.size()==1){
            System.out.println("Il controller conta i punti");
        }
    }

    public void startGame(){

        System.out.println("I players i gioco sono: ");
        for(int i=0 ;i<listOfNickname.size();i++){
            if(i==0){
                game.createPlayer(listOfNickname.get(i), true, i+1, listOfColor.get(i).toUpperCase());
            }
            else{
                game.createPlayer(listOfNickname.get(i), false, i+1, listOfColor.get(i).toUpperCase());
            }

        }
        game.startGame(anticipatedFrenzy,typeMap);
        while(true){}
    }

    class MyTask extends TimerTask{

        @Override
        public void run() {
            startGame();
        }
    }


    public synchronized void addNickname(String newNickname, String color){
        listOfNickname.add(newNickname);
        listOfColor.add(color);
    }

    public ArrayList<Socket> getListOfClient() {
        return listOfClient;
    }

    public ArrayList<String> getListOfNickname() {
        return listOfNickname;
    }

    public ArrayList<String> getListOfColor() {
        return listOfColor;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setAnticipatedFrenzy(boolean anticipatedFrenzy) {
        this.anticipatedFrenzy = anticipatedFrenzy;
    }

    public boolean isAnticipatedFrenzy() {
        return anticipatedFrenzy;
    }

    public void setTypeMap(int typeMap) {
        this.typeMap = typeMap;
    }

    public int getTypeMap() {
        return typeMap;
    }
}










