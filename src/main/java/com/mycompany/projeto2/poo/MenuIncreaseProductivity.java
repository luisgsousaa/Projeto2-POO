package com.mycompany.projeto2.poo;

import java.util.Scanner;



/**
 * Permite ao jogador escolher um aumento de produtividade de um terreno que será permanente
 */
public class MenuIncreaseProductivity implements MenuOption {

    @Override
    public String getDescription() {
        return "Aumentar Produtividade de forma permanente";
    }


    /**
     * O jogador escolhe uma % de aumento da produtividade e o custo em ouro correspondente
     * Após escolher as coordenadas do terreno, o aumento é aplicado, se o jogador tiver ouro suficiente e o terreno for adequado
     *
     * @param scanner Objeto Scanner utilizado para capturar a entrada do jogador.
     * @param civilization A civilização do jogador, que é usada para verificar o ouro disponível.
     * @param gameMap O mapa do jogo, que contém os terrenos e suas respectivas células.
     */
    @Override
    public void execute(Scanner scanner, Civilization civilization, GameMap gameMap) {
        System.out.println("Tesouro da sua civilizacao: " + civilization.getGoldTreasure());

        System.out.println("Escolha um aumento de produtividade:");
        System.out.println("1- 50% - 10 ouros");
        System.out.println("2- 100% - 15 ouros");
        System.out.println("3- 150% - 20 ouros");
        System.out.println("4- 300% - 50 ouros");

        int option = 0;
        int cost = 0;
        double multiplier = 1.0;

        try {
            option = scanner.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Por favor, insira um numero.");
            scanner.nextLine();
            return;
        }

        switch (option) {
            case 1 -> {
                cost = 10;
                multiplier = 1.5;
            }
            case 2 -> {
                cost = 15;
                multiplier = 2.0;
            }
            case 3 -> {
                cost = 20;
                multiplier = 2.5;
            }
            case 4 -> {
                cost = 50;
                multiplier = 5.0;
            }
            default -> {
                System.out.println("Opcao invalida. Tente novamente.");
                return;
            }
        }

        if (civilization.getGoldTreasure() < cost) {
            System.out.println("Nao tem ouro suficiente para realizar esta operacao.");
            return;
        }

        int x = 0, y = 0;
        try {
            System.out.print("Insira a coordenada X do terreno: ");
            x = scanner.nextInt();
            System.out.print("Insira a coordenada Y do terreno: ");
            y = scanner.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Por favor, insira numeros inteiros para as coordenadas.");
            scanner.nextLine();
            return;
        }

        if (x < 0 || x >= gameMap.getWidth() || y < 0 || y >= gameMap.getHeight()) {
            System.out.println("As coordenadas estao fora dos limites do mapa.");
            return;
        }

        Cell cell = gameMap.getCell(x, y);
        if (cell == null) {
            System.out.println("Essas coordenadas sao invalidas.");
            return;
        }

        if (cell.getProductivity() <= 0) {
            System.out.println("Esse terreno nao é produtivo, portanto não suporta um aumento da produtividade.");
            return;
        }

        cell.increaseProductivityByMultiplier(multiplier);
        civilization.addGoldTreasure(-cost);

        System.out.println("A produtividade do terreno escolhido foi aumentada de forma permanente.");
        System.out.println("Ouro restante: " + civilization.getGoldTreasure());
    }
}
