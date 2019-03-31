package it.polimi.se2019.limperio.nicotera.italia.model;

public class Map {

    int typeMap;
    private static Map istanceOfMap = null;

    private Map(int typeMap)
    {
        this.typeMap=typeMap;
    }


    public static Map istanceOfMap(int typeMap)
    {
        if(istanceOfMap==null) istanceOfMap = new Map(typeMap);
        return istanceOfMap;
    }

    public setAdj(){};




}
