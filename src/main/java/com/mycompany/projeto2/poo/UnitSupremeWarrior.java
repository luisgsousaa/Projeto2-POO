package com.mycompany.projeto2.poo;

public class UnitSupremeWarrior extends UnitCombat {

    private GameMap gameMap;
    public static final String NAME = "Spier";
    private static final String TYPE = "S";
    private static final int LIFE = 50;
    private static final int MAX_STEPS = 35;
    public static final int PRODUCTION_COST = 1000;
    private static final int ATTACK_DAMAGE = 30;
    //private static final int MAX_ATTACKS = 3;
    //private int remainingAttacks;

    public UnitSupremeWarrior(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        super(NAME,TYPE, civilization, LIFE, MAX_STEPS, 0, PRODUCTION_COST, 2,ATTACK_DAMAGE);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.setColonizer(true); // definida a true a possibilidade de esta unidade fundar cidades (está definida a false por default)
        //this.remainingAttacks = MAX_ATTACKS;
    }
}