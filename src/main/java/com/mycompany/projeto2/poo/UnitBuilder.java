package com.mycompany.projeto2.poo;

public class UnitBuilder extends Unit {

    private GameMap gameMap;
    public static final String NAME = "Builder";
    private static final String TYPE = "B";
    public static final int PRODUCTION_COST = 150;
    private static final int LIFE = 80;
    private static final int MAX_STEPS = 15;
    private static final int ATTACK_DAMAGE = 0;

    private static final int HEAL_AMOUNT = 10;
    private static final int MAX_HEALS = 2;
    private int remainingHeals;

    public UnitBuilder(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        super(NAME,TYPE, civilization,  LIFE,MAX_STEPS, 0, PRODUCTION_COST, 2,ATTACK_DAMAGE);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.remainingHeals = MAX_HEALS;
    }

    // para a lógica de curar/reparar unidades ou cidades da sua civilizacao
    @Override
    public int getHealAmount() {return HEAL_AMOUNT;}
    @Override
    public boolean canHeal() {return remainingHeals > 0;}
    @Override
    public int getRemainingHeals() {return remainingHeals;}
    @Override
    public void resetHeals() {remainingHeals = MAX_HEALS;}
    @Override
    public void executeHeal() {
        if (remainingHeals > 0) {
            remainingHeals--;
            System.out.println("Curas restantes para este ciclo: " + remainingHeals);
        } else {
            System.out.println("Todas as curas desta unidade ja foram usadas neste ciclo.");
        }
    }
}