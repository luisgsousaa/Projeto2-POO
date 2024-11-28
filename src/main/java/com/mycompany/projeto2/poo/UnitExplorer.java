package com.mycompany.projeto2.poo;

public class UnitExplorer extends Unit{
    
    private Cell[][] map;

    public UnitExplorer (int x, int y, Cell[][] map, Direction direction){
        super("E ", 1, 0, 2, 1);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
}
