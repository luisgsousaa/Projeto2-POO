package com.mycompany.projeto2.poo;

import java.util.HashMap;
import java.util.Map;


public class UnitFactoryRegistry {
    private static final Map<String, UnitFactory> factories = new HashMap<>();

    static {
        factories.put("M", new UnitMilitaryFactory());
        factories.put("E", new UnitExplorerFactory());
        factories.put("B", new UnitBuilderFactory());
        factories.put("S", new UnitSpierFactory());
    }

    public static Unit createUnit(String unitType, int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        UnitFactory factory = factories.get(unitType);
        if (factory == null) {
            System.out.println("Unidade desconhecida.");
            return null;
        }

        return factory.createUnit(x, y, gameMap, direction, civilization);
    }
}

