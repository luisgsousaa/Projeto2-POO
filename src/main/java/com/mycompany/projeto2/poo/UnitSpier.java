package com.mycompany.projeto2.poo;

public class UnitSpier extends Unit {

    private GameMap gameMap;
    private static final String NAME = "Spier";
    private static final int MAX_LIFE = 50;


    public UnitSpier(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        super("S", civilization,  1, 0, 500, 2,0);
        //"S" + civilization.getId(),
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