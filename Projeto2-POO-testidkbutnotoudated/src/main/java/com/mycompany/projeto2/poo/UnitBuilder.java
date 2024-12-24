package com.mycompany.projeto2.poo;

public class UnitBuilder extends Unit {

    private Map map;
    private static final String NAME = "Builder";

    public UnitBuilder(int x, int y, Map map, Direction direction, Civilization civilization) {
        super("B", civilization,  1, 0, 3, 2,100,0);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }

    @Override
    public String getUnitName() {return NAME;}
    public Map getMap() {return this.map;}
}