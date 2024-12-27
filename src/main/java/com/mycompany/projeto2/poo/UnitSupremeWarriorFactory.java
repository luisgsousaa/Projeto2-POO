package com.mycompany.projeto2.poo;



/**
 * Fábrica responsável por criar instâncias de unidades SupremeWarrior
 */
class UnitSupremeWarriorFactory implements UnitFactory {

    @Override
    public Unit createUnit(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        return new UnitSupremeWarrior(x, y, gameMap, direction, civilization);}

    @Override
    public String getUnitName() {return UnitSupremeWarrior.NAME;}

    @Override
    public int getProductionCost() {return UnitSupremeWarrior.PRODUCTION_COST;}


}
