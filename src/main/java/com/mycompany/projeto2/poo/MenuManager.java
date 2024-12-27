package com.mycompany.projeto2.poo;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


/**
 * Classe responsável por gerir e exibir o menu de opções do jogo.
 */
public class MenuManager {

    private GameMap gameMap;
    private ArrayList<MenuOption> menuOptions = new ArrayList<>();

    /**
     * Construtor que inicializa o menu e associa as opções de menu com o mapa do jogo.
     */
    public MenuManager(GameMap gameMap) {
        this.gameMap = gameMap;
        initializeMenuOptions();
    }

    /**
     * Inicializa as opções do menu
     */
    private void initializeMenuOptions() {
        menuOptions.add(new MenuMoveUnitOption());
        menuOptions.add(new MenuConfrontOption());
        menuOptions.add(new MenuHealOption());
        menuOptions.add(new MenuCreateUnitOption());
        menuOptions.add(new MenuCreateCityOption());
        menuOptions.add(new MenuIncreaseProductivity());
    }

    /**
     * Mostra o menu de opções e permite ao jogador escolher qual ação deseja realizar
     *
     * @param civilization A civilização do jogador atual
     * @return bolean indicando se o menu foi concluído (true) ou se o jogador deseja continuar (false)
     */
    public boolean showMenuManager(Civilization civilization) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEscolha uma opcao:");
            for (int i = 0; i < menuOptions.size(); i++) {
                System.out.printf("%d - %s%n", i + 1, menuOptions.get(i).getDescription());
            }
            System.out.println("0 - Terminar jogada.");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada invalida. Por favor, insira um numero.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= menuOptions.size()) {
                menuOptions.get(choice - 1).execute(scanner, civilization, gameMap);
            } else if (choice == 0) {
                return true;
            } else {
                System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (choice != 0);

        return false;
    }

}
