package com.mycompany.projeto2.poo;

import java.util.Scanner;

/**
 * Interface que define uma opção de menu.
 * Cada opção de menu precisa implementar a execução de uma ação quando selecionada, além de fornecer uma descrição da opção.
 */
public interface MenuOption {
    void execute(Scanner scanner, Civilization civilization, GameMap gameMap);
    String getDescription();
}
