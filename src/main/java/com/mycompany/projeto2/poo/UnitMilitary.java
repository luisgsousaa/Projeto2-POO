package com.mycompany.projeto2.poo;

public class UnitMilitary extends UnitCombat {

    private GameMap gameMap;
    public static final String NAME = "Military";
    private static final String TYPE = "M";
    private static final int LIFE = 100;
    private static final int MAX_STEPS = 20; /// Número máximo de passos por turno
    public static final int PRODUCTION_COST = 6;
    private static final int ATTACK_DAMAGE = 10;



    /**
     * Construtor para criar uma unidade Military
     *
     * @param x Coordenada X inicial
     * @param y Coordenada Y inicial
     * @param gameMap O mapa do jogo onde a unidade será posicionada
     * @param direction A direção inicial da unidade
     * @param civilization A civilização à qual a unidade pertence
     */
    public UnitMilitary (int x, int y, GameMap gameMap, Direction direction, Civilization civilization){
        super(NAME,TYPE, civilization, LIFE,MAX_STEPS, 0.5, PRODUCTION_COST, 2,ATTACK_DAMAGE);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
    }

}
