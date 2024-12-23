package com.mycompany.projeto2.poo;

public class UnitMilitary extends Unit{

    private Map map;
    private static final String NAME = "Military";
    private static final int MAX_LIFE = 100;


    public UnitMilitary (int x, int y, Map map, Direction direction, Civilization civilization){
        super("M", civilization, 30, 0.5, 3, 2,10);
        this.setCoordX(x);
        this.setCoordY(y);
        this.map = map;
        this.setDirection(direction);
    }
    @Override
    public String getUnitName() {return NAME;}

    @Override
    public int getUnitMaxLife() {
        return MAX_LIFE; // Retorna o valor máximo de vida para esta unidade
    }


}
