package com.mycompany.projeto2.poo;

public class UnitSpier extends Unit {

    private Map map;

    public UnitSpier(int x, int y, Map map, Direction direction) {
        super("S ", 1, 0, 3, 2);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
}