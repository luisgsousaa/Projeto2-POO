package com.mycompany.projeto2.poo;

public class UnitBuilder extends Unit {

    private Map map;
    private static final String NAME = "Builder";
    private static final int HEAL_AMOUNT = 10;

    private static final int MAX_LIFE = 80;

    public UnitBuilder(int x, int y, Map map, Direction direction, Civilization civilization) {
        super("B", civilization,  30, 0, 3, 2,0);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }

    @Override
    public String getUnitName() {return NAME;}

    @Override
    public int getHealAmount() {
        return HEAL_AMOUNT;
    }

    @Override
    public int getUnitMaxLife() {
        return MAX_LIFE; // Retorna o valor máximo de vida para esta unidade
    }
}