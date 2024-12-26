package com.mycompany.projeto2.poo;

public class UnitSpier extends Unit {

    private GameMap gameMap;
    public static final String NAME = "Spier";
    private static final String TYPE = "S";
    public static final int PRODUCTION_COST = 500;
    private static final int LIFE = 50;

    public UnitSpier(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        super(NAME,TYPE, civilization, LIFE, 1, 0, PRODUCTION_COST, 2,0);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
    }
}