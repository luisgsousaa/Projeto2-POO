package com.mycompany.projeto2.poo;

public class UnitSpier extends Unit {

    private Map map;
    private static final String NAME = "Spier";

    public UnitSpier(int x, int y, Map map, Direction direction, Civilization civilization) {
        super("S", civilization,  1, 0, 3, 2,100,0);
        //"S" + civilization.getId(),
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }

    @Override
    public String getUnitName() {return NAME;}
}