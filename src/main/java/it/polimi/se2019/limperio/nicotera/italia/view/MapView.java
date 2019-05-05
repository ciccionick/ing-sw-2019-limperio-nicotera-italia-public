package it.polimi.se2019.limperio.nicotera.italia.view;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.NormalSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

public class MapView {

   private Square[][] map;

    public void update(ServerEvent event) {
        if (event.isMapEvent() || event.isFirstActionOfTurnEvent()) {
            map = event.getMap();
            if (event.isMapEvent()) {
                System.out.println("Mappa aggiornata con successo");
                for (int i = 0; i < map.length; i++) {
                    SpawnSquare spawnSquare;
                    NormalSquare normalSquare;
                    for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] != null) {
                            // System.out.println(map[i][j].getColor());
                            if (map[i][j].isSpawn()) {
                                spawnSquare = (SpawnSquare) map[i][j];
                                if (spawnSquare.getColor().equals(ColorOfFigure_Square.RED))
                                    spawnSquare.setWeaponsCardsForRemoteView(((MapEvent) event).getWeaponsCardsForRedSpawnSquare());
                                if (spawnSquare.getColor().equals(ColorOfFigure_Square.BLUE))
                                    spawnSquare.setWeaponsCardsForRemoteView(((MapEvent) event).getWeaponsCardsForBlueSpawnSquare());
                                if (spawnSquare.getColor().equals(ColorOfFigure_Square.YELLOW))
                                    spawnSquare.setWeaponsCardsForRemoteView(((MapEvent) event).getWeaponsCardsForYellowSpawnSquare());
                                // System.out.println("Con: " + spawnSquare.getWeaponCards().size() + " carte armi");
                                //   System.out.println("Prima carta arma " + spawnSquare.getWeaponCards().get(0).getName());
                                //  } else {
                                //      normalSquare = (NormalSquare) map[i][j];
                                //    System.out.println("Con ammo tile: " + normalSquare.getAmmoTile().getAmmos().size());
                                // }
                            } //else
                            //      System.out.println("NULL");
                        }
                    }
                }
            }

        }
    }

    public Square[][] getMap() {
        return map;
    }
}
