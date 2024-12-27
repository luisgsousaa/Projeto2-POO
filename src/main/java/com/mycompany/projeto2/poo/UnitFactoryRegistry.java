package com.mycompany.projeto2.poo;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que regista e gerencia diferentes tipos de fábricas de unidades
 * Permite criar instâncias de unidades com base no tipo especificado
 */
public class UnitFactoryRegistry {
    private static final Map<String, UnitFactory> factories = new HashMap<>();

    /// Bloco estático para inicializar as fábricas disponíveis
    static {
        factories.put("M", new UnitMilitaryFactory());
        factories.put("E", new UnitExplorerFactory());
        factories.put("B", new UnitBuilderFactory());
        factories.put("S", new UnitSupremeWarriorFactory());
    }

    /**
     * Método para criar uma unidade com base no tipo fornecido
     * @param unitType O tipo da unidade
     * @param x coordenada x da unidade
     * @param y coordenada y da unidade
     * @param gameMap O mapa do jogo onde a unidade será criada
     * @param direction A direção inicial da unidade
     * @param civilization A civilização à qual a unidade pertence
     * @return Uma nova instância de unidade, ou null se o tipo for desconhecido
     */
    public static Unit createUnit(String unitType, int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        UnitFactory factory = factories.get(unitType);
        if (factory == null) {
            System.out.println("Unidade desconhecida.");
            return null;
        }
        return factory.createUnit(x, y, gameMap, direction, civilization);
    }

    /**
     * Retorna o mapa de fábricas registadas
     */
    public static Map<String, UnitFactory> getFactories() {return factories;}

}

