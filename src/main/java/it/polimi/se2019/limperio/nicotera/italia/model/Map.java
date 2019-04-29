package it.polimi.se2019.limperio.nicotera.italia.model;



import java.io.Serializable;


class Map implements Serializable {

    private static Map instanceOfMap;
    private Square[][] matrixOfSquares = new Square[3][4];


    private SpawnSquare createSquare(boolean isSpawn, ColorOfFigure_Square color , boolean hasDoor){
            return new SpawnSquare(color, hasDoor);
    }

    private NormalSquare createSquare(ColorOfFigure_Square color , boolean hasDoor){
            return new NormalSquare(color, hasDoor);
    }

    private Map(int typeMap)
    {
        switch (typeMap){
            case 1:
                createMapOfFirstType();
                setAdjForFirstType();
                break;
            case 2:
                createMapOfSecondType();
                setAdjForSecondType();
                break;
            case 3:
                createMapOfThirdType();
                setAdjForThirdType();
                break;
                default:
                    throw new IllegalArgumentException();
        }

    }




     static Map instanceOfMap(int typeMap)
    {
        if(instanceOfMap==null)
            instanceOfMap = new Map(typeMap);
        return instanceOfMap;
    }



    private void createMapOfFirstType() {


        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.BLUE, true);
        matrixOfSquares[0][1] = createSquare(  ColorOfFigure_Square.BLUE, false);
        matrixOfSquares[0][2] = createSquare(true, ColorOfFigure_Square.BLUE, true);
        matrixOfSquares[0][3] = null;
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.RED, true);
        matrixOfSquares[1][0] = createSquare(true, ColorOfFigure_Square.RED, true);
        matrixOfSquares[1][2] = createSquare( ColorOfFigure_Square.RED, true);
        matrixOfSquares[1][3] = createSquare( ColorOfFigure_Square.YELLOW, true);
        matrixOfSquares[2][0] = null;
        matrixOfSquares[2][1] = createSquare( ColorOfFigure_Square.GREY, true);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.GREY, true);
        matrixOfSquares[2][3] = createSquare(true, ColorOfFigure_Square.YELLOW, true);

    }

    private void createMapOfSecondType(){


        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.BLUE, true);
        matrixOfSquares[0][1] = createSquare( ColorOfFigure_Square.BLUE, false);
        matrixOfSquares[0][2] = createSquare(true, ColorOfFigure_Square.BLUE, true);
        matrixOfSquares[0][3] = createSquare(  ColorOfFigure_Square.GREEN, true);
        matrixOfSquares[1][0] = createSquare(true, ColorOfFigure_Square.RED, true);
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.RED, true);
        matrixOfSquares[1][2] = createSquare(  ColorOfFigure_Square.YELLOW, true);
        matrixOfSquares[1][3] = createSquare(  ColorOfFigure_Square.YELLOW, true);
        matrixOfSquares[2][0] = null;
        matrixOfSquares[2][1] = createSquare( ColorOfFigure_Square.GREY, true);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.YELLOW, true);
        matrixOfSquares[2][3] = createSquare(true,  ColorOfFigure_Square.YELLOW, true);


    }

    private void createMapOfThirdType(){

        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.RED, true);
        matrixOfSquares[0][1] = createSquare( ColorOfFigure_Square.BLUE, false);
        matrixOfSquares[0][2] = createSquare(true,  ColorOfFigure_Square.BLUE, true);
        matrixOfSquares[0][3] = createSquare(  ColorOfFigure_Square.GREEN, true);
        matrixOfSquares[1][0] = createSquare(true,  ColorOfFigure_Square.RED, true);
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.PURPLE, true);
        matrixOfSquares[1][2] = createSquare( ColorOfFigure_Square.YELLOW, true);
        matrixOfSquares[1][3] = createSquare(  ColorOfFigure_Square.YELLOW, true);
        matrixOfSquares[2][0] = createSquare(  ColorOfFigure_Square.GREY, true);
        matrixOfSquares[2][1] = createSquare(  ColorOfFigure_Square.GREY, true);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.YELLOW, true);
        matrixOfSquares[2][3] = createSquare(true,  ColorOfFigure_Square.YELLOW, true);

    }




     void setAdjForFirstType() {
        matrixOfSquares[0][0].setCardinalSquare(null, matrixOfSquares[1][0] , null, matrixOfSquares[0][1]);
        matrixOfSquares[0][1].setCardinalSquare(null, null , matrixOfSquares[0][0],   matrixOfSquares[0][2]);
        matrixOfSquares[0][2].setCardinalSquare(null,matrixOfSquares[1][2] , matrixOfSquares[0][1], null);
        matrixOfSquares[1][0].setCardinalSquare(matrixOfSquares[0][0], null, null, matrixOfSquares[1][1] );
        matrixOfSquares[1][1].setCardinalSquare(null, matrixOfSquares[2][1], matrixOfSquares[1][0], matrixOfSquares[1][2] );
        matrixOfSquares[1][2].setCardinalSquare(matrixOfSquares[0][2] , null, matrixOfSquares[1][1], matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setCardinalSquare(null, matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
        matrixOfSquares[2][1].setCardinalSquare(null, matrixOfSquares[1][1], null, matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(null, null, matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);
    }

     void setAdjForSecondType() {
        matrixOfSquares[0][0].setCardinalSquare(null, matrixOfSquares[1][0] , null, matrixOfSquares[0][1]);
        matrixOfSquares[0][1].setCardinalSquare(null, null , matrixOfSquares[0][0],   matrixOfSquares[0][2]);
        matrixOfSquares[0][2].setCardinalSquare(null,matrixOfSquares[1][2] , matrixOfSquares[0][1], matrixOfSquares[0][3]);
        matrixOfSquares[0][3].setCardinalSquare(null,matrixOfSquares[1][3] , matrixOfSquares[0][2], null);
        matrixOfSquares[1][0].setCardinalSquare(matrixOfSquares[0][0], null, null, matrixOfSquares[1][1] );
        matrixOfSquares[1][1].setCardinalSquare(null, matrixOfSquares[2][1], matrixOfSquares[1][0], null );
        matrixOfSquares[1][2].setCardinalSquare(matrixOfSquares[0][2] , matrixOfSquares[2][2], null, matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setCardinalSquare(matrixOfSquares[1][3], matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
        matrixOfSquares[2][1].setCardinalSquare(null, matrixOfSquares[1][1], null, matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(matrixOfSquares[1][2], null, matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);

    }


     void setAdjForThirdType() {
        matrixOfSquares[0][0].setCardinalSquare(null, matrixOfSquares[1][0] , null, matrixOfSquares[0][1]);
        matrixOfSquares[0][1].setCardinalSquare(null, matrixOfSquares[1][1] , matrixOfSquares[0][0],   matrixOfSquares[0][2]);
        matrixOfSquares[0][2].setCardinalSquare(null,matrixOfSquares[1][2] , matrixOfSquares[0][1], matrixOfSquares[0][3]);
        matrixOfSquares[0][3].setCardinalSquare(null,matrixOfSquares[1][3] , matrixOfSquares[0][2], null);
        matrixOfSquares[1][0].setCardinalSquare(matrixOfSquares[0][0], matrixOfSquares[2][0], null, null );
        matrixOfSquares[1][1].setCardinalSquare(matrixOfSquares[0][1], matrixOfSquares[2][1], null, null );
        matrixOfSquares[1][2].setCardinalSquare(matrixOfSquares[0][2] , matrixOfSquares[2][2], null, matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setCardinalSquare(matrixOfSquares[1][3], matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
        matrixOfSquares[2][0].setCardinalSquare(matrixOfSquares[1][0],null, null, matrixOfSquares[2][1] );
        matrixOfSquares[2][1].setCardinalSquare(null, matrixOfSquares[1][1], matrixOfSquares[2][0], matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(matrixOfSquares[1][2], null, matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);

    }

    Square[][] getMatrixOfSquares() {
        return matrixOfSquares;
    }


}
