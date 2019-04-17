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

    private ServerSocket serverSocket;
    private Game game = null;
    private Controller controller = null;
    private ArrayList<Socket> listOfClient = new ArrayList<>();
    private final long delay;
    private Timer timer = null;
    private MyTask task = null;
    private ArrayList<String> listOfNickname = new ArrayList<>();

    public static void main(String argv[]) throws Exception {
        new Server();
    }

    public Server() throws Exception {
        serverSocket = new ServerSocket(4000);
        System.out.println("Il Server è in attesa sulla porta 4000.");
        game = Game.instanceOfGame();
        controller=new Controller(game);
        File file = new File("C:\\Users\\Pietro\\OneDrive\\Università\\3° Anno\\II Semestre\\Progetto ISW\\src\\main\\java\\socket\\timer.txt");
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
                Socket client = serverSocket.accept();
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
        if(listOfClient.size()==2 && timer!=null){
            timer.cancel();
            timer = null;
            System.out.println("Il timer è stato fermato perchè siamo scesi a due giocatori");
        }
    }

    public void startGame(){

        System.out.println("I players i gioco sono: ");
        for(int i=0 ;i<listOfNickname.size();i++){
            if(i==0){
                game.createPlayer(listOfNickname.get(i), true, i+1);
            }
            else{
                game.createPlayer(listOfNickname.get(i), false, i+1);
            }

        }
        game.startGame();
        while(true){}
    }

    public ArrayList<String> getListOfNickname() {
        return listOfNickname;
    }

    public void addNickname(String newNickname){
        listOfNickname.add(newNickname);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    class MyTask extends TimerTask {

        @Override
        public void run() {
            startGame();
        }
    }
}










