package it.polimi.se2019.limperio.nicotera.italia.model;

public class Map {

    private int typeMap;
    private static Map instanceOfMap;
    private Square[] squares;

    private Map(int typeMap)
    {
        this.typeMap=typeMap;
    }


    public static Map instanceOfMap(int typeMap)
    {
        if(instanceOfMap==null)
            instanceOfMap = new Map(typeMap);
        return instanceOfMap;
    }

    public void setAdj(){};




}
