package it.polimi.se2019.limperio.nicotera.italia.model;

public class Map {

    private static Map instanceOfMap;
    private Square[][] matrixOfSquares;



    private void createMapOfFirstType() {
        Direction[] directions;
        directions = new Direction[]{Direction.DOWN, null};
        matrixOfSquares[0][0] = createSquare(false, directions, ColorOfFigure_Square.BLUE);
        matrixOfSquares[0][1] = createSquare(false, null, ColorOfFigure_Square.BLUE);
        directions[1] = Direction.DOWN;
        matrixOfSquares[0][2] = createSquare(true, directions, ColorOfFigure_Square.BLUE);
        matrixOfSquares[0][3] = null;
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

        Direction[] directions;
        directions = new Direction[]{Direction.DOWN, null};
        matrixOfSquares[0][0] = createSquare(false, directions, ColorOfFigure_Square.BLUE);
        matrixOfSquares[0][1] = createSquare(false, null, ColorOfFigure_Square.BLUE);
        directions[0] = Direction.DOWN;
        directions[1] = Direction.RIGHT;
        matrixOfSquares[0][2] = createSquare(true, directions, ColorOfFigure_Square.BLUE);
        directions[1] = Direction.LEFT;
        matrixOfSquares[0][3] = createSquare(false, directions, ColorOfFigure_Square.GREEN);
        directions[0] = Direction.UP;
        directions[1] = null;
        matrixOfSquares[1][0] = createSquare(true, directions, ColorOfFigure_Square.RED);
        directions[0] = Direction.DOWN;
        matrixOfSquares[1][1] = createSquare(false, directions, ColorOfFigure_Square.RED);
        directions[0] = Direction.UP;
        matrixOfSquares[1][2] = createSquare(false, directions, ColorOfFigure_Square.YELLOW);
        matrixOfSquares[1][3] = createSquare(false, directions, ColorOfFigure_Square.YELLOW);
        matrixOfSquares[2][0] = null;
        directions[0] = Direction.UP;
        directions[1] = Direction.RIGHT;
        matrixOfSquares[2][1] = createSquare(false, directions, ColorOfFigure_Square.GREY);
        directions[0] = Direction.LEFT;
        matrixOfSquares[2][2] = createSquare(false, directions, ColorOfFigure_Square.YELLOW);
        directions[0] = null;
        matrixOfSquares[2][3] = createSquare(true, directions, ColorOfFigure_Square.YELLOW);


    }

    private void createMapOfThirdType(){

        Direction[] directions;
        directions = new Direction[]{Direction.RIGHT, null};
        matrixOfSquares[0][0] = createSquare(false, directions, ColorOfFigure_Square.RED);
        directions[0] = Direction.LEFT;
        directions[1] = Direction.DOWN;
        matrixOfSquares[0][1] = createSquare(false, null, ColorOfFigure_Square.BLUE);
        directions[0] = Direction.RIGHT;
        matrixOfSquares[0][2] = createSquare(true, directions, ColorOfFigure_Square.BLUE);
        directions[0] = Direction.LEFT;
        matrixOfSquares[0][3] = createSquare(false, directions, ColorOfFigure_Square.GREEN);
        directions[0] = Direction.DOWN;
        directions[1] = null;
        matrixOfSquares[1][0] = createSquare(true, directions, ColorOfFigure_Square.RED);
        directions[1] = Direction.UP;
        matrixOfSquares[1][1] = createSquare(false, directions, ColorOfFigure_Square.PURPLE);
        directions[0] = Direction.UP;
        directions[1] = null;
        matrixOfSquares[1][2] = createSquare(false, directions, ColorOfFigure_Square.YELLOW);
        matrixOfSquares[1][3] = createSquare(false, directions, ColorOfFigure_Square.YELLOW);
        matrixOfSquares[2][0] = createSquare(false, directions, ColorOfFigure_Square.GREY);
        directions[1] = Direction.RIGHT;
        matrixOfSquares[2][1] = createSquare(false, directions, ColorOfFigure_Square.GREY);
        directions[0] = Direction.LEFT;
        directions[1] = null;
        matrixOfSquares[2][2] = createSquare(false, directions, ColorOfFigure_Square.YELLOW);
        directions[0] = null;
        matrixOfSquares[2][3] = createSquare(true, directions, ColorOfFigure_Square.YELLOW);

    }



    private Map(int typeMap)
    {
        int typeMap1 = typeMap;
        switch (typeMap){
            case 1:
                createMapOfFirstType();
                setAdjForFirstType();
            case 2:
                createMapOfSecondType();
                setAdjForSecondType();
            case 3:
                createMapOfThirdType();
                setAdjForThirdType();
        }

    }


    public static Map instanceOfMap(int typeMap)
    {
        if(instanceOfMap==null)
            instanceOfMap = new Map(typeMap);
        return instanceOfMap;
    }


    public void setAdjForFirstType() {
        matrixOfSquares[0][0].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][1]);
        matrixOfSquares[0][0].setAdjSquares(Direction.DOWN, matrixOfSquares[1][0]);
        matrixOfSquares[0][1].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][2]);
        matrixOfSquares[0][1].setAdjSquares(Direction.LEFT, matrixOfSquares[0][0]);
        matrixOfSquares[0][2].setAdjSquares(Direction.LEFT, matrixOfSquares[0][1]);
        matrixOfSquares[0][2].setAdjSquares(Direction.DOWN, matrixOfSquares[1][2]);
        matrixOfSquares[1][0].setAdjSquares(Direction.UP, matrixOfSquares[0][0]);
        matrixOfSquares[1][0].setAdjSquares(Direction.RIGHT, matrixOfSquares[1][1]);
        matrixOfSquares[1][1].setAdjSquares(Direction.DOWN, matrixOfSquares[2][1]);
        matrixOfSquares[1][1].setAdjSquares(Direction.RIGHT, matrixOfSquares[1][2]);
        matrixOfSquares[1][1].setAdjSquares(Direction.LEFT, matrixOfSquares[1][0]);
        matrixOfSquares[1][2].setAdjSquares(Direction.LEFT, matrixOfSquares[1][1]);
        matrixOfSquares[1][2].setAdjSquares(Direction.UP, matrixOfSquares[0][2]);
        matrixOfSquares[1][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][3]);
        matrixOfSquares[1][3].setAdjSquares(Direction.LEFT, matrixOfSquares[1][2]);
        matrixOfSquares[2][1].setAdjSquares(Direction.DOWN, matrixOfSquares[1][1]);
        matrixOfSquares[2][1].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][2]);
        matrixOfSquares[2][2].setAdjSquares(Direction.LEFT, matrixOfSquares[2][1]);
        matrixOfSquares[2][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][3]);
        matrixOfSquares[2][3].setAdjSquares(Direction.UP, matrixOfSquares[1][3]);
        matrixOfSquares[2][3].setAdjSquares(Direction.LEFT, matrixOfSquares[2][2]);

    }

    public void setAdjForSecondType() {
        matrixOfSquares[0][0].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][1]);
        matrixOfSquares[0][0].setAdjSquares(Direction.DOWN, matrixOfSquares[1][0]);
        matrixOfSquares[0][1].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][2]);
        matrixOfSquares[0][1].setAdjSquares(Direction.LEFT, matrixOfSquares[0][0]);
        matrixOfSquares[0][2].setAdjSquares(Direction.LEFT, matrixOfSquares[0][1]);
        matrixOfSquares[0][2].setAdjSquares(Direction.DOWN, matrixOfSquares[1][2]);
        matrixOfSquares[0][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][3]);
        matrixOfSquares[0][3].setAdjSquares(Direction.DOWN, matrixOfSquares[1][3]);
        matrixOfSquares[0][3].setAdjSquares(Direction.LEFT, matrixOfSquares[0][2]);
        matrixOfSquares[1][0].setAdjSquares(Direction.UP, matrixOfSquares[0][0]);
        matrixOfSquares[1][0].setAdjSquares(Direction.RIGHT, matrixOfSquares[1][1]);
        matrixOfSquares[1][1].setAdjSquares(Direction.DOWN, matrixOfSquares[2][1]);
        matrixOfSquares[1][1].setAdjSquares(Direction.LEFT, matrixOfSquares[1][0]);
        matrixOfSquares[1][2].setAdjSquares(Direction.UP, matrixOfSquares[0][2]);
        matrixOfSquares[1][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][3]);
        matrixOfSquares[1][3].setAdjSquares(Direction.LEFT, matrixOfSquares[1][2]);
        matrixOfSquares[1][3].setAdjSquares(Direction.UP, matrixOfSquares[0][3]);
        matrixOfSquares[2][1].setAdjSquares(Direction.UP, matrixOfSquares[1][1]);
        matrixOfSquares[2][1].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][2]);
        matrixOfSquares[2][2].setAdjSquares(Direction.LEFT, matrixOfSquares[2][1]);
        matrixOfSquares[2][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][3]);
        matrixOfSquares[2][2].setAdjSquares(Direction.UP, matrixOfSquares[1][2]);
        matrixOfSquares[2][3].setAdjSquares(Direction.UP, matrixOfSquares[1][3]);
        matrixOfSquares[2][3].setAdjSquares(Direction.LEFT, matrixOfSquares[2][2]);

    }


    public void setAdjForThirdType() {
        matrixOfSquares[0][0].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][1]);
        matrixOfSquares[0][0].setAdjSquares(Direction.DOWN, matrixOfSquares[1][0]);
        matrixOfSquares[0][1].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][2]);
        matrixOfSquares[0][1].setAdjSquares(Direction.LEFT, matrixOfSquares[0][0]);
        matrixOfSquares[0][1].setAdjSquares(Direction.DOWN, matrixOfSquares[1][1]);
        matrixOfSquares[0][2].setAdjSquares(Direction.LEFT, matrixOfSquares[0][1]);
        matrixOfSquares[0][2].setAdjSquares(Direction.DOWN, matrixOfSquares[1][2]);
        matrixOfSquares[0][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[0][3]);
        matrixOfSquares[0][3].setAdjSquares(Direction.DOWN, matrixOfSquares[1][3]);
        matrixOfSquares[0][3].setAdjSquares(Direction.LEFT, matrixOfSquares[0][2]);
        matrixOfSquares[1][0].setAdjSquares(Direction.UP, matrixOfSquares[0][0]);
        matrixOfSquares[1][0].setAdjSquares(Direction.DOWN, matrixOfSquares[2][0]);
        matrixOfSquares[1][1].setAdjSquares(Direction.DOWN, matrixOfSquares[2][1]);
        matrixOfSquares[1][1].setAdjSquares(Direction.UP, matrixOfSquares[0][1]);
        matrixOfSquares[1][2].setAdjSquares(Direction.UP, matrixOfSquares[0][2]);
        matrixOfSquares[1][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][3]);
        matrixOfSquares[1][3].setAdjSquares(Direction.LEFT, matrixOfSquares[1][2]);
        matrixOfSquares[1][3].setAdjSquares(Direction.UP, matrixOfSquares[0][3]);
        matrixOfSquares[2][0].setAdjSquares(Direction.UP, matrixOfSquares[1][0]);
        matrixOfSquares[2][0].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][1]);
        matrixOfSquares[2][1].setAdjSquares(Direction.UP, matrixOfSquares[1][1]);
        matrixOfSquares[2][1].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][2]);
        matrixOfSquares[2][1].setAdjSquares(Direction.LEFT, matrixOfSquares[2][0]);
        matrixOfSquares[2][2].setAdjSquares(Direction.LEFT, matrixOfSquares[2][1]);
        matrixOfSquares[2][2].setAdjSquares(Direction.RIGHT, matrixOfSquares[2][3]);
        matrixOfSquares[2][2].setAdjSquares(Direction.UP, matrixOfSquares[1][2]);
        matrixOfSquares[2][3].setAdjSquares(Direction.UP, matrixOfSquares[1][3]);
        matrixOfSquares[2][3].setAdjSquares(Direction.LEFT, matrixOfSquares[2][2]);

    }


    private Square createSquare(boolean isSpawn, Direction[] directions, ColorOfFigure_Square color){
        if(isSpawn)
            return new SpawnSquare(directions, color);
        else
            return new NormalSquare(directions, color);
    }



}
