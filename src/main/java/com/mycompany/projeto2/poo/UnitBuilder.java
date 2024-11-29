package com.mycompany.projeto2.poo;

public class UnitBuilder extends Unit {

    private Map map;

    public UnitBuilder(int x, int y, Map map, Direction direction) {
        super("B ", 1, 0, 3, 2);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
}