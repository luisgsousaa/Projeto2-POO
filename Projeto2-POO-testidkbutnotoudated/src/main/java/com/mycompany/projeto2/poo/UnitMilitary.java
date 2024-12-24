package com.mycompany.projeto2.poo;

public class UnitMilitary extends Unit{

    private Map map;
    private static final String NAME = "Military";

    public UnitMilitary (int x, int y, Map map, Direction direction, Civilization civilization){
        super("M", civilization, 10, 0.5, 3, 2,100,10);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
    @Override
    public String getUnitName() {return NAME;}
}
