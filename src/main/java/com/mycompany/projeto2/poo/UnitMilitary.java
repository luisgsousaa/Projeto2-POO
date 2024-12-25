package com.mycompany.projeto2.poo;

public class UnitMilitary extends Unit{

    private GameMap gameMap;
    private static final String NAME = "Military";
    private static final int MAX_LIFE = 100;
    private static final int MAX_ATTACKS = 3;
    private int remainingAttacks;

    public UnitMilitary (int x, int y, GameMap gameMap, Direction direction, Civilization civilization){
        super("M", civilization, 30, 0.5, 300, 2,10);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.remainingAttacks = MAX_ATTACKS;
    }
    @Override
    public String getUnitName() {return NAME;}

    @Override
    public int getUnitMaxLife() {
        return MAX_LIFE;
    }
    @Override
    public int getRemainingAttacks() {
        return remainingAttacks;
    }
    @Override
    public void resetAttacks() {
        remainingAttacks = MAX_ATTACKS;
    }
    @Override
    public void executeConfrontation() {
        if (remainingAttacks > 0) {
            remainingAttacks--;
            System.out.println("Confrontos restantes para este ciclo: " + remainingAttacks);
        } else {
            System.out.println("Todos os confrontos desta unidade já foram usados neste ciclo.");
        }
    }
    @Override
    public boolean canAttack() {
        return remainingAttacks > 0;
    }



}
