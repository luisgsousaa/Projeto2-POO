package com.mycompany.projeto2.poo;

public class UnitProducer extends Unit {

    private Map map;
    private static final String NAME = "Producer";

    public UnitProducer(int x, int y, Map map, Direction direction, Civilization civilization) {
        super("P", civilization, 1, 0, 3, 2,100,0);
        //"P" + civilization.getId(),
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }

    @Override
    public String getUnitName() {return NAME;}
}

