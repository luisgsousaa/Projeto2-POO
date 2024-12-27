package com.mycompany.projeto2.poo;

/**
 * Interface que define o contrato para as fábricas das unidades
 */
interface UnitFactory {
    Unit createUnit(int x, int y, GameMap gameMap, Direction direction, Civilization civilization); /// cria uma nova unidade com base nos parâmetros fornecidos
    String getUnitName(); /// nome da unidade
    int getProductionCost(); /// custo producao unidade
}
