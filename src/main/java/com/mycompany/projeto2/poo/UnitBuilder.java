package com.mycompany.projeto2.poo;

public class UnitBuilder extends Unit {

    private Map map;
    private static final String NAME = "Builder";
    private static final int HEAL_AMOUNT = 10;

    private static final int MAX_LIFE = 80;
    private static final int MAX_HEALS = 2;
    private int remainingHeals;

    public UnitBuilder(int x, int y, Map map, Direction direction, Civilization civilization) {
        super("B", civilization,  30, 0, 3, 2,0);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
        this.remainingHeals = MAX_HEALS;
    }

    @Override
    public String getUnitName() {return NAME;}

    @Override
    public int getHealAmount() {
        return HEAL_AMOUNT;
    }

    @Override
    public int getUnitMaxLife() {
        return MAX_LIFE;
    }



    @Override
    public int getRemainingHeals() {
        return remainingHeals;
    }
    @Override
    public void resetHeals() {
        remainingHeals = MAX_HEALS;
    }
    @Override
    public void executeHeal() {
        if (remainingHeals > 0) {
            remainingHeals--;
            System.out.println("Curas restantes para este ciclo: " + remainingHeals);
        } else {
            System.out.println("Todas as curas desta unidade ja foram usadas neste ciclo.");
        }
    }
    @Override
    public boolean canHeal() {
        return remainingHeals > 0;
    }



























}