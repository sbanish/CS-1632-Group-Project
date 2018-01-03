package edu.pitt.battleshipgame.common.board;

import java.util.HashMap;
import java.io.Serializable;

public class Coordinate implements Serializable {
    public static final int MAX_DIM = 10;
    private int row, col;
    private static final String formattingRules = "Coordinate format must be <A-J>:<1-10>";
    // Support for mapping from letters to numbers...
    private static final HashMap<Character,Integer> columnMap = new HashMap<Character, Integer>(){{
            put('A',0);
            put('B',1);
            put('C',2);
            put('D',3);
            put('E',4);
            put('F',5);
            put('G',6);
            put('H',7);
            put('I',8);
            put('J',9);
        }
    };

    // Support for mapping from numbers to letters
    private static final char [] reverseColumnMap =
        { 'A','B','C','D','E','F','G','H','I','J'};
    
    /**
     * A simple constructor that calls setCoordinates below.
     */
    public Coordinate (String coord) throws IllegalArgumentException {
        setCoordinates(coord);
    }
    
    public Coordinate (int _row, int _col) {
        setRow(_row);
        setCol(_col);
    }

    
    public boolean isCoordEqual(Coordinate coord){
        if((this.getCol()==coord.getCol())&&(this.getRow()==coord.getRow())){
            return true;
        }
        else{
        return false;
        }
    }
    
    /**
     * This function will parse and set the coordinates for the board.
     * This allows any parsing errors to be detected on the client side
     * before it gets to the server.
     */
    public void setCoordinates(String coord) throws IllegalArgumentException {
        if(coord.equals("exit")){
            //System.out.println("Your opponent has quit");
            System.exit(0);
        }
        if (coord.length() > 4) {
            throw new IllegalArgumentException(formattingRules);
        }
        if (coord.toCharArray()[1] != ':') {
            throw new IllegalArgumentException(formattingRules);
        }
        
        int _col = 0;
        String [] coordinates = coord.split(":");
        
        if(coordinates.length!=2){
            throw new IllegalArgumentException(formattingRules);
        }
        
        
        
        
        if (columnMap.keySet().contains(coordinates[0].toUpperCase().charAt(0))) {
            _col = columnMap.get(coordinates[0].toUpperCase().charAt(0));
        } else {
            throw new IllegalArgumentException(formattingRules);
        }

        int _row = 0;
        
        try {
            
            
            if((Integer.parseInt(coordinates[1])>0)&&(Integer.parseInt(coordinates[1])<11)){
                _row = Integer.parseInt(coordinates[1]) - 1;
            }
            else{
               throw new IllegalArgumentException(formattingRules);
            }
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(formattingRules);
        }

        // At this point we have passed all tests and we can initialize the coordinate.
        setCol(_col);
        setRow(_row);
    }

    public void setCol(int _col) {
        col = _col;
    }

    public void setRow(int _row) {
        row = _row;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public static int columnLookup(char colName) {
        return columnMap.get(colName);
    }
    
    public static char reverseColumnLookup(int col) {
        return reverseColumnMap[col];
    }
    
    public String toString() {
        return reverseColumnLookup(col) + ":" + (row+1);
    }
}