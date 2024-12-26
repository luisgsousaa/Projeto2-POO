package com.mycompany.projeto2.poo;

class UnitSpierFactory implements UnitFactory {

    @Override
    public Unit createUnit(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        return new UnitSpier(x, y, gameMap, direction, civilization);}

    @Override
    public String getUnitName() {return UnitSpier.NAME;}

    @Override
    public int getProductionCost() {return UnitSpier.PRODUCTION_COST;}


}
