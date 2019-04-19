package it.polimi.se2019.limperio.nicotera.italia.network.server;


import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;

import java.io.*;
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
    private ArrayList<String> listOfColor = new ArrayList<>();
    private boolean anticipatedFrenzy=true;
    private int typeMap=0;
    private boolean gameIsStarted = false;

    public static void main(String[] argv) throws Exception {
        new Server();
    }

    private Server() throws  IOException {
        serverSocket = new ServerSocket(4000);
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
                    task = new MyTask();
                    try{
                        timer.schedule(task,delay);
                    }
                    catch (IllegalStateException er){
                        er.printStackTrace();
                    }
                }
                if (listOfClient.size()==5){
                    timer.cancel();
                    timer=null;
                    System.out.println("Il timer e' stato fermato perchè sta per cominciare il gioco!");
                    TimeUnit.SECONDS.sleep(10);
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
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

     void deregister(VirtualView view, Socket client) {
        synchronized (this) {
            game.deregister(view);
            listOfClient.remove(client);
        }
        if(listOfNickname.isEmpty()) {
            System.out.println("Sono rimasti in gioco: ");
            for (String name : listOfNickname) {
                System.out.println(name);
            }
        }
        if(listOfClient.size()==2 && timer!=null && !gameIsStarted){
            timer.cancel();
            timer = null;
            System.out.println("Il timer è stato fermato perchè siamo scesi a due giocatori");
        }
        if(listOfClient.size()==2 && gameIsStarted){
            System.out.println("Il controller conta i punti");
        }
    }

    private void startGame(){

        System.out.println("I players i gioco sono: ");
        for(int i=0 ;i<listOfNickname.size();i++){
            game.createPlayer(listOfNickname.get(i), i==0, i+1, listOfColor.get(i).toUpperCase());
        }
        if(typeMap==0){
            setTypeMap(0, true);
        }
        game.startGame(anticipatedFrenzy,typeMap);
        gameIsStarted=true;
        while(serverSocket.isBound()){
            //server is running..
        }
    }



     synchronized void addNickname(String newNickname, String color){
        listOfNickname.add(newNickname);
        listOfColor.add(color);
    }

     ArrayList<Socket> getListOfClient() {
        return listOfClient;
    }

     ArrayList<String> getListOfNickname() {
        return listOfNickname;
    }

     ArrayList<String> getListOfColor() {
        return listOfColor;
    }

     void setAnticipatedFrenzy(boolean anticipatedFrenzy) {
        this.anticipatedFrenzy = anticipatedFrenzy;
    }

     boolean isAnticipatedFrenzy() {
        return anticipatedFrenzy;
    }

    void setTypeMap(int typeMap, boolean defaultValue) {
        if(!defaultValue) {
            this.typeMap = typeMap;
        }
        else{
            switch (listOfClient.size()){
                case 4:
                    setTypeMap(2, false);
                    break;
                case 5:
                    setTypeMap(3, false);
                    break;
                default:
                    setTypeMap(1, false);
            }
        }
    }

     int getTypeMap() {
        return typeMap;
    }



    class MyTask extends TimerTask{

        @Override
        public void run() {
            startGame();
        }
    }

}










