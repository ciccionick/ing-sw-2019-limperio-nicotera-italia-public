package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.NormalSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

public class MapView {

   private Square[][] map;

    public void update(ModelEvent event){
        if(event.isMapEvent()|| event.isFirstActionOfTurnEvent()) {
            map = event.getMap();
            System.out.println("Mappa aggiornata con successo");
           /* for (int i = 0; i < map.length; i++) {
                SpawnSquare spawnSquare;
                NormalSquare normalSquare;
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != null) {
                        System.out.println(map[i][j].getColor());
                        if (map[i][j].isSpawn()) {
                            spawnSquare = (SpawnSquare) map[i][j];
                            System.out.println("Con: " + spawnSquare.getWeaponCards().size() + " carte armi");
                        } else {
                            normalSquare = (NormalSquare) map[i][j];
                            System.out.println("Con ammo tile: " + normalSquare.getAmmoTile().getAmmos().size());
                        }
                    } else
                        System.out.println("NULL");
                }
            }*/
        }

    }
}
