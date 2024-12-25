package com.mycompany.projeto2.poo;

class UnitBuilderFactory implements UnitFactory {
    @Override
    public Unit createUnit(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        return new UnitBuilder(x, y, gameMap, direction, civilization);
    }
}
