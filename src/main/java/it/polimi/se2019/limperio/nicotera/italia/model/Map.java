package it.polimi.se2019.limperio.nicotera.italia.model;



import java.io.Serializable;


public class Map implements Serializable {

    private static Map instanceOfMap;
    private Square[][] matrixOfSquares = new Square[3][4];


    private SpawnSquare createSquare(boolean isSpawn, ColorOfFigure_Square color , boolean hasDoor, int row, int column){
            return new SpawnSquare(color, hasDoor, row, column);
    }

    private NormalSquare createSquare(ColorOfFigure_Square color , boolean hasDoor, int row, int column){
            return new NormalSquare(color, hasDoor, row, column);
    }

    private Map(int typeMap) throws IllegalArgumentException
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
        for(int i=0;i<matrixOfSquares.length;i++){
            for(int j=0;j<matrixOfSquares[i].length;j++){
                if(matrixOfSquares[i][j]!=null)
                    matrixOfSquares[i][j].setAdjForSquare();
            }
        }

    }




     static Map instanceOfMap(int typeMap)
    {
        if(instanceOfMap==null)
            instanceOfMap = new Map(typeMap);
        return instanceOfMap;
    }



    private void createMapOfFirstType() {


        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.BLUE, true, 0, 0);
        matrixOfSquares[0][1] = createSquare(  ColorOfFigure_Square.BLUE, false, 0, 1);
        matrixOfSquares[0][2] = createSquare(true, ColorOfFigure_Square.BLUE, true,0,2);
        matrixOfSquares[0][3] = null;
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.RED, true,1,1);
        matrixOfSquares[1][0] = createSquare(true, ColorOfFigure_Square.RED, true,1,0);
        matrixOfSquares[1][2] = createSquare( ColorOfFigure_Square.RED, true,1,2);
        matrixOfSquares[1][3] = createSquare( ColorOfFigure_Square.YELLOW, true,1,3);
        matrixOfSquares[2][0] = null;
        matrixOfSquares[2][1] = createSquare( ColorOfFigure_Square.GREY, true,2,1);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.GREY, true,2,2);
        matrixOfSquares[2][3] = createSquare(true, ColorOfFigure_Square.YELLOW, true,2,3);

    }

    private void createMapOfSecondType(){


        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.BLUE, true,0,0);
        matrixOfSquares[0][1] = createSquare( ColorOfFigure_Square.BLUE, false,0,1);
        matrixOfSquares[0][2] = createSquare(true, ColorOfFigure_Square.BLUE, true,0,2);
        matrixOfSquares[0][3] = createSquare(  ColorOfFigure_Square.GREEN, true,0,3);
        matrixOfSquares[1][0] = createSquare(true, ColorOfFigure_Square.RED, true,1,0);
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.RED, true,1,1);
        matrixOfSquares[1][2] = createSquare(  ColorOfFigure_Square.YELLOW, true,1,2);
        matrixOfSquares[1][3] = createSquare(  ColorOfFigure_Square.YELLOW, true,1,3);
        matrixOfSquares[2][0] = null;
        matrixOfSquares[2][1] = createSquare( ColorOfFigure_Square.GREY, true,2,1);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.YELLOW, true,2,2);
        matrixOfSquares[2][3] = createSquare(true,  ColorOfFigure_Square.YELLOW, true,2,3);


    }

    private void createMapOfThirdType(){

        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.RED, true,0,0);
        matrixOfSquares[0][1] = createSquare( ColorOfFigure_Square.BLUE, false,0,1);
        matrixOfSquares[0][2] = createSquare(true,  ColorOfFigure_Square.BLUE, true,0,2);
        matrixOfSquares[0][3] = createSquare(  ColorOfFigure_Square.GREEN, true,0,3);
        matrixOfSquares[1][0] = createSquare(true,  ColorOfFigure_Square.RED, true,1,0);
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.PURPLE, true,1,1);
        matrixOfSquares[1][2] = createSquare( ColorOfFigure_Square.YELLOW, true,1,2);
        matrixOfSquares[1][3] = createSquare(  ColorOfFigure_Square.YELLOW, true,1,3);
        matrixOfSquares[2][0] = createSquare(  ColorOfFigure_Square.GREY, true,2,0);
        matrixOfSquares[2][1] = createSquare(  ColorOfFigure_Square.GREY, true,2,1);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.YELLOW, true,2,2);
        matrixOfSquares[2][3] = createSquare(true,  ColorOfFigure_Square.YELLOW, true,2,3);

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
        matrixOfSquares[1][3].setCardinalSquare(matrixOfSquares[0][3], matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
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
        matrixOfSquares[1][3].setCardinalSquare(matrixOfSquares[0][3], matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
        matrixOfSquares[2][0].setCardinalSquare(matrixOfSquares[1][0],null, null, matrixOfSquares[2][1] );
        matrixOfSquares[2][1].setCardinalSquare(null, matrixOfSquares[1][1], matrixOfSquares[2][0], matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(matrixOfSquares[1][2], null, matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);
    }

    public Square[][] getMatrixOfSquares() {
        return matrixOfSquares;
    }

    /* this method is used in order to test the creation of different map in the same testing class*/
    void setInstanceOfMapForTesting(){
        instanceOfMap = null;
    }

}
