package com.mycompany.projeto2.poo;

public class UnitMilitary extends Unit{

    private GameMap gameMap;
    public static final String NAME = "Military";
    public static final int PRODUCTION_COST = 300;
    private static final int LIFE = 100;

    private static final int MAX_ATTACKS = 3;
    private int remainingAttacks;

    public UnitMilitary (int x, int y, GameMap gameMap, Direction direction, Civilization civilization){
        super(NAME,"M", civilization, LIFE,30, 0.5, PRODUCTION_COST, 2,10);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.remainingAttacks = MAX_ATTACKS;
    }

    // para a lógica de confrontar (capturar ou ferir) unidades ou cidades de civilizacoes inimigas
    @Override
    public int getRemainingAttacks() {return remainingAttacks;}
    @Override
    public void resetAttacks() {remainingAttacks = MAX_ATTACKS;}
    @Override
    public boolean canAttack() {return remainingAttacks > 0;}
    @Override
    public void executeConfrontation() {
        if (remainingAttacks > 0) {
            remainingAttacks--;
            System.out.println("Confrontos restantes para este ciclo: " + remainingAttacks);
        } else {
            System.out.println("Todos os confrontos desta unidade já foram usados neste ciclo.");
        }
    }
}
