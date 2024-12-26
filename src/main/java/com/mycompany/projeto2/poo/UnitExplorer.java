package com.mycompany.projeto2.poo;

public class UnitExplorer extends Unit{

    private GameMap gameMap;
    public static final String NAME = "Explorer";
    private static final String TYPE = "E";
    public static final int PRODUCTION_COST = 100;
    private static final int LIFE = 70;
    private static final int MAX_STEPS = 10;
    private static final int ATTACK_DAMAGE = 0;


    public UnitExplorer (int x, int y, GameMap gameMap, Direction direction, Civilization civilization){
        super(NAME,TYPE, civilization, LIFE, MAX_STEPS, 0, PRODUCTION_COST, 1,ATTACK_DAMAGE);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.setColonizer(true); // definida a true a possibilidade de esta unidade fundar cidades (está definida a false por default)
    }

}
