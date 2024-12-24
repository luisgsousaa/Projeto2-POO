package com.mycompany.projeto2.poo;

public class UnitExplorer extends Unit{

    private Map map;
    private static final String NAME = "Explorer";
    private static final int MAX_LIFE = 70;

    public UnitExplorer (int x, int y, Map map, Direction direction, Civilization civilization){
        super("E", civilization,  1, 0, 2, 1,0);
        //"E" + civilization.getId(),
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }

    @Override
    public String getUnitName() {return NAME;}



    @Override
    public int getUnitMaxLife() {
        return MAX_LIFE;
    }

}
