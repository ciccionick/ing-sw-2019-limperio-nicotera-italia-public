package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;

/**
 * This class is used to represent the map in the game
 * @author Pietro L'Imperio
 */
public class Map implements Serializable, Cloneable {
    static final long serialVersionUID = 420000003;
    /**
     * Contains the instance of map according to singleton pattern
     */
    private static Map instanceOfMap;
    /**
     * the map is stored in a matrix of squares
     */
    private Square[][] matrixOfSquares = new Square[3][4];

    /**
     * the builder of a spawn square
     * @deprecated @param isSpawn is always true because is a spawn square, but it needs to overload the method createSquare in order to distinguish it from the builder of a normal square
     * @param color the color of the square
     * @param hasDoor is true if the square has doors
     * @param row the x coordinate of the square
     * @param column the y coordinate of the square
     * @return the square that has built
     */
    private SpawnSquare createSquare(boolean isSpawn, ColorOfFigure_Square color , boolean hasDoor, int row, int column){
            return new SpawnSquare(color, hasDoor, row, column);
    }

    /**
     * the builder of a normal square
     * @param color the color of the square
     * @param hasDoor is true if the square has doors
     * @param row the x coordinate of the square
     * @param column the y coordinate of the square
     * @return the square that has built
     */
    private NormalSquare createSquare(ColorOfFigure_Square color , boolean hasDoor, int row, int column){
            return new NormalSquare(color, hasDoor, row, column);
    }

    /**
     * the constructor of the map, it calls the method that creates the type of map that has specified in the parameter
     * @param typeMap the type of map that musts be built
     * @throws IllegalArgumentException if the parameter is different from 1,2,3 or 4
     */
    private Map(int typeMap ) throws IllegalArgumentException
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
            case 4:
                createMapOfFourthType();
                setAdjForFourthType();
                break;
                default:
                    throw new IllegalArgumentException();
        }
    }

    /**
     * It will call the constructor if the map doesn't exist yet, according to singleton pattern
     * @param typeMap the type of map that must be created
     * @return the instance of the map
     */
    static Map instanceOfMap(int typeMap)
    {
        if(instanceOfMap==null)
            instanceOfMap = new Map(typeMap);
        return instanceOfMap;
    }
    private int typeOfMap;


    /**
     * Creates a map of first type
     */
    private void createMapOfFirstType() {

        this.typeOfMap=1;
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

    /**
     * Creates a map of second type
     */
    private void createMapOfSecondType(){

        this.typeOfMap=2;
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
        matrixOfSquares[2][3] = createSquare(true,  ColorOfFigure_Square.YELLOW, false,2,3);


    }

    /**
     * Creates a map of third type
     */
    private void createMapOfThirdType(){

        this.typeOfMap=3;
        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.RED, true,0,0);
        matrixOfSquares[0][1] = createSquare( ColorOfFigure_Square.BLUE, true,0,1);
        matrixOfSquares[0][2] = createSquare(true,  ColorOfFigure_Square.BLUE, true,0,2);
        matrixOfSquares[0][3] = createSquare(  ColorOfFigure_Square.GREEN, true,0,3);
        matrixOfSquares[1][0] = createSquare(true,  ColorOfFigure_Square.RED, true,1,0);
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.PURPLE, true,1,1);
        matrixOfSquares[1][2] = createSquare( ColorOfFigure_Square.YELLOW, true,1,2);
        matrixOfSquares[1][3] = createSquare(  ColorOfFigure_Square.YELLOW, true,1,3);
        matrixOfSquares[2][0] = createSquare(  ColorOfFigure_Square.GREY, true,2,0);
        matrixOfSquares[2][1] = createSquare(  ColorOfFigure_Square.GREY, true,2,1);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.YELLOW, true,2,2);
        matrixOfSquares[2][3] = createSquare(true,  ColorOfFigure_Square.YELLOW, false,2,3);

    }

    /**
     * Creates a map of fourth type
     */
    private void createMapOfFourthType() {

        this.typeOfMap=4;
        matrixOfSquares[0][0] = createSquare( ColorOfFigure_Square.RED, true,0,0);
        matrixOfSquares[0][1] = createSquare( ColorOfFigure_Square.BLUE, true,0,1);
        matrixOfSquares[0][2] = createSquare(true,  ColorOfFigure_Square.BLUE, true,0,2);
        matrixOfSquares[0][3] = null;
        matrixOfSquares[1][0] = createSquare(true,  ColorOfFigure_Square.RED, true,1,0);
        matrixOfSquares[1][1] = createSquare( ColorOfFigure_Square.PURPLE, true,1,1);
        matrixOfSquares[1][2] = createSquare( ColorOfFigure_Square.PURPLE, true,1,2);
        matrixOfSquares[1][3] = createSquare(  ColorOfFigure_Square.YELLOW, true,1,3);
        matrixOfSquares[2][0] = createSquare(  ColorOfFigure_Square.GREY, true,2,0);
        matrixOfSquares[2][1] = createSquare(  ColorOfFigure_Square.GREY, true,2,1);
        matrixOfSquares[2][2] = createSquare( ColorOfFigure_Square.GREY, true,2,2);
        matrixOfSquares[2][3] = createSquare(true,  ColorOfFigure_Square.YELLOW, true,2,3);
    }

    public int getTypeOfMap() {
        return typeOfMap;
    }

    /**
     * Sets the adjacency of every squares in a first type map
     */
    void setAdjForFirstType() {
        matrixOfSquares[0][0].setCardinalSquare(null, matrixOfSquares[1][0] , null, matrixOfSquares[0][1]);
        matrixOfSquares[0][1].setCardinalSquare(null, null , matrixOfSquares[0][0],   matrixOfSquares[0][2]);
        matrixOfSquares[0][2].setCardinalSquare(null,matrixOfSquares[1][2] , matrixOfSquares[0][1], null);
        matrixOfSquares[1][0].setCardinalSquare(matrixOfSquares[0][0], null, null, matrixOfSquares[1][1] );
        matrixOfSquares[1][1].setCardinalSquare(null, matrixOfSquares[2][1], matrixOfSquares[1][0], matrixOfSquares[1][2] );
        matrixOfSquares[1][2].setCardinalSquare(matrixOfSquares[0][2] , null, matrixOfSquares[1][1], matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setCardinalSquare(null, matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
        matrixOfSquares[2][1].setCardinalSquare( matrixOfSquares[1][1], null,  null, matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(null, null,  matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);
    }

    /**
     * Sets the adjacency of every squares in a second type map
     */
    void setAdjForSecondType() {
        matrixOfSquares[0][0].setCardinalSquare(null, matrixOfSquares[1][0] , null, matrixOfSquares[0][1]);
        matrixOfSquares[0][1].setCardinalSquare(null, null , matrixOfSquares[0][0],   matrixOfSquares[0][2]);
        matrixOfSquares[0][2].setCardinalSquare(null,matrixOfSquares[1][2] , matrixOfSquares[0][1], matrixOfSquares[0][3]);
        matrixOfSquares[0][3].setCardinalSquare(null,matrixOfSquares[1][3] , matrixOfSquares[0][2], null);
        matrixOfSquares[1][0].setCardinalSquare(matrixOfSquares[0][0], null, null, matrixOfSquares[1][1] );
        matrixOfSquares[1][1].setCardinalSquare(null, matrixOfSquares[2][1], matrixOfSquares[1][0], null );
        matrixOfSquares[1][2].setCardinalSquare(matrixOfSquares[0][2] , matrixOfSquares[2][2], null, matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setCardinalSquare(matrixOfSquares[0][3], matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
        matrixOfSquares[2][1].setCardinalSquare(matrixOfSquares[1][1],null, null, matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(matrixOfSquares[1][2], null, matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);

    }

    /**
     * Sets the adjacency of every squares in a third type map
     */
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
        matrixOfSquares[2][1].setCardinalSquare( matrixOfSquares[1][1],null, matrixOfSquares[2][0], matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(matrixOfSquares[1][2], null, matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);
    }

    /**
     * Sets the adjacency of every squares in a fourth type map
     */
     void setAdjForFourthType() {
        matrixOfSquares[0][0].setCardinalSquare(null, matrixOfSquares[1][0] , null, matrixOfSquares[0][1]);
        matrixOfSquares[0][1].setCardinalSquare(null, matrixOfSquares[1][1] , matrixOfSquares[0][0],   matrixOfSquares[0][2]);
        matrixOfSquares[0][2].setCardinalSquare(null,matrixOfSquares[1][2] , matrixOfSquares[0][1], null);
        matrixOfSquares[1][0].setCardinalSquare(matrixOfSquares[0][0], matrixOfSquares[2][0], null, null );
        matrixOfSquares[1][1].setCardinalSquare(matrixOfSquares[0][1], matrixOfSquares[2][1], null, matrixOfSquares[1][2] );
        matrixOfSquares[1][2].setCardinalSquare(matrixOfSquares[0][2] , null, matrixOfSquares[1][1], matrixOfSquares[1][3]);
        matrixOfSquares[1][3].setCardinalSquare(null, matrixOfSquares[2][3], matrixOfSquares[1][2],  null);
        matrixOfSquares[2][0].setCardinalSquare(matrixOfSquares[1][0],null, null, matrixOfSquares[2][1] );
        matrixOfSquares[2][1].setCardinalSquare(matrixOfSquares[1][1], null, matrixOfSquares[2][0], matrixOfSquares[2][2] );
        matrixOfSquares[2][2].setCardinalSquare(null, null, matrixOfSquares[2][1], matrixOfSquares[2][3] );
        matrixOfSquares[2][3].setCardinalSquare(matrixOfSquares[1][3], null, matrixOfSquares[2][2], null);

    }

    public Square[][] getMatrixOfSquares() {
        return matrixOfSquares;
    }

    /**
     * It deletes the map that is created in order to test the different builder of maps in test classes
     */
   public void setInstanceOfMapForTesting(){
        instanceOfMap = null;
    }


}
