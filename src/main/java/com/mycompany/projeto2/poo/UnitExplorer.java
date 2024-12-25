package com.mycompany.projeto2.poo;

public class UnitExplorer extends Unit{

    private GameMap gameMap;
    private static final String NAME = "Explorer";
    private static final int MAX_LIFE = 70;

    public UnitExplorer (int x, int y, GameMap gameMap, Direction direction, Civilization civilization){
        super("E", civilization,  100, 0, 100, 1,0);
        //"E" + civilization.getId(),
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.setColonizer(true);
    }

    @Override
    public String getUnitName() {return NAME;}



    @Override
    public int getUnitMaxLife() {
        return MAX_LIFE;
    }

}
