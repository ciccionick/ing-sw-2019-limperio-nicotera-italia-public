package it.polimi.se2019.limperio.nicotera.italia.model;

public class Map {

    private int typeMap;
    private static Map instanceOfMap;
    private Square[][] matrixOfSquares;



    private void createMapOfFirstType() {
        Direction[] directions;
        directions = new Direction[]{Direction.DOWN, null};
        matrixOfSquares[0][0] = createSquare(false, directions, ColorOfFigure_Square.BLUE);
        matrixOfSquares[0][1] = createSquare(false, null, ColorOfFigure_Square.BLUE);
        matrixOfSquares[0][2] = createSquare(true, directions, ColorOfFigure_Square.BLUE);
        matrixOfSquares[0][4] = null;
        matrixOfSquares[1][1] = createSquare(false, directions, ColorOfFigure_Square.RED);
        directions[0] = Direction.UP;
        matrixOfSquares[1][0] = createSquare(true, directions, ColorOfFigure_Square.RED);
        directions[1] = Direction.RIGHT;
        matrixOfSquares[1][2] = createSquare(false, directions, ColorOfFigure_Square.RED);
        directions[0] = Direction.LEFT;
        directions[1] = null;
        matrixOfSquares[1][3] = createSquare(false, directions, ColorOfFigure_Square.YELLOW);
        matrixOfSquares[2][0] = null;
        directions[0] = Direction.UP;
        directions[1] = null;
        matrixOfSquares[2][1] = createSquare(false, directions, ColorOfFigure_Square.GREY);
        directions[0] = Direction.RIGHT;
        matrixOfSquares[2][2] = createSquare(false, directions, ColorOfFigure_Square.GREY);
        directions[0] = Direction.LEFT;
        matrixOfSquares[2][3] = createSquare(true, directions, ColorOfFigure_Square.YELLOW);

    }

    private void createMapOfSecondType(){

    }

    private void createMapOfThirdType(){

    }



    private Map(int typeMap)
    {
        this.typeMap=typeMap;
        switch (typeMap){
            case 1:
                createMapOfFirstType();
            case 2:
                createMapOfSecondType();
            case 3:
                createMapOfThirdType();
        }

    }


    public static Map instanceOfMap(int typeMap)
    {
        if(instanceOfMap==null)
            instanceOfMap = new Map(typeMap);
        return instanceOfMap;
    }

    public void setAdj(){}

    private Square createSquare(boolean isSpawn, Direction[] directions, ColorOfFigure_Square color){
        return null;
    }



}
