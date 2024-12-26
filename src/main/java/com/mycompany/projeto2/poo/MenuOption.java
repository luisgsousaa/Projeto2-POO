package com.mycompany.projeto2.poo;

import java.util.Scanner;

public interface MenuOption {
    void execute(Scanner scanner, Civilization civilization, GameMap gameMap);
    String getDescription();
}
