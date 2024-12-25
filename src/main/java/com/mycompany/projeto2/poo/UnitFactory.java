package com.mycompany.projeto2.poo;

interface UnitFactory {
    Unit createUnit(int x, int y, GameMap gameMap, Direction direction, Civilization civilization);
}
