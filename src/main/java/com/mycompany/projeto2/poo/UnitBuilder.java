package com.mycompany.projeto2.poo;

public class UnitBuilder extends Unit {

    private Map map;

    public UnitBuilder(int x, int y, Map map, Direction direction, Civilization civilization) {
        super("B ", civilization,  1, 0, 3, 2);
        //"B" + civilization.getId(),
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
}