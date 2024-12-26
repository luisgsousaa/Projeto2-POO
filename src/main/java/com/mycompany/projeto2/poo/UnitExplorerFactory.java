package com.mycompany.projeto2.poo;

class UnitExplorerFactory implements UnitFactory {

    @Override
    public Unit createUnit(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        return new UnitExplorer(x, y, gameMap, direction, civilization);}

    @Override
    public String getUnitName() {return UnitExplorer.NAME;}

    @Override
    public int getProductionCost() {return UnitExplorer.PRODUCTION_COST;}
}
