package com.mycompany.projeto2.poo;

public class UnitProducer extends Unit {

    private GameMap gameMap;
    private static final String NAME = "Producer";
    private static final int MAX_LIFE = 60;


    public UnitProducer(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        super("P", civilization, 1, 0, 50, 2,0);
        //"P" + civilization.getId(),
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
    }

    @Override
    public String getUnitName() {return NAME;}



    @Override
    public int getUnitMaxLife() {
        return MAX_LIFE;
    }
}

