package com.mycompany.projeto2.poo;



/**
 * Fábrica responsável por criar instâncias de unidades SupremeWarrior
 */
class UnitMilitaryFactory implements UnitFactory {

    @Override
    public Unit createUnit(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        return new UnitMilitary(x, y, gameMap, direction, civilization);}

    @Override
    public String getUnitName() {return UnitMilitary.NAME;}

    @Override
    public int getProductionCost() {return UnitMilitary.PRODUCTION_COST;}
}