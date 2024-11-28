package com.mycompany.projeto2.poo;

public class UnitMilitary extends Unit{
    
    private Cell[][] map;

    public UnitMilitary (int x, int y, Cell[][] map, Direction direction){
        super("M ", 2, 0.5, 3, 2);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
}
