package com.mycompany.projeto2.poo;

public class UnitSupremeWarrior extends UnitCombat {

    private GameMap gameMap;
    public static final String NAME = "SupremeWarrior";
    private static final String TYPE = "S";
    private static final int LIFE = 50;
    private static final int MAX_STEPS = 35; /// Número máximo de passos por turno
    public static final int PRODUCTION_COST = 1000;
    private static final int ATTACK_DAMAGE = 30;

    /**
     * Construtor para criar uma unidade SupremeWarrior
     *
     * @param x Coordenada X inicial
     * @param y Coordenada Y inicial
     * @param gameMap O mapa do jogo onde a unidade será posicionada
     * @param direction A direção inicial da unidade
     * @param civilization A civilização à qual a unidade pertence
     */
    public UnitSupremeWarrior(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        super(NAME,TYPE, civilization, LIFE, MAX_STEPS, 0, PRODUCTION_COST, 2,ATTACK_DAMAGE);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.setColonizer(true);  /// permite a possibilidade desta unidade fundar cidades (está definida a false por default)
    }
}