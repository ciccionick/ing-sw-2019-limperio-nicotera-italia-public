package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

public class MapView {

   private Square[][] map;

    public void update(MapEvent event){
       map = event.getMap();
       System.out.println("Mappa aggiornata con successo");
       for(int i=0;i<map.length;i++){
           for(int j=0;j<map[i].length;j++){
               if(map[i][j]!=null){
                   System.out.println(map[i][j].getColor());
               }
               else
                System.out.println("NULL");
           }
       }
    }
}
