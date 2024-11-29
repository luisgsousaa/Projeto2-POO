package com.mycompany.projeto2.poo;

public class UnitProducer extends Unit {

    private Map map;

    public UnitProducer(int x, int y, Map map, Direction direction) {
        super("P ", 1, 0, 3, 2);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
}

