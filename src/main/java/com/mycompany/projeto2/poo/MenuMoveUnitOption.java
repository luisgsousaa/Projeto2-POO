package com.mycompany.projeto2.poo;

import java.util.Scanner;


/**
 * O jogador escolhe uma unidade controlada pela civilização e move-a para direções especificadas, consumindo os passos disponíveis e o ouro conforme o movimento.
 */
public class MenuMoveUnitOption implements MenuOption {

    @Override
    public String getDescription() {return "Mover Unidade";}

    /**
     * Executa a movimentação de uma unidade no mapa. O jogador escolhe qual unidade mover, as direções para a qual deseja deslocar e o número de passos disponíveis.
     * O movimento consome passos da unidade e ouro conforme o terreno atravessado.
     *
     */
    @Override
    public void execute(Scanner scanner, Civilization civilization, GameMap gameMap) {
        Civilization.showControlledUnits(civilization);
        System.out.println("\nIndique a unidade que pretende mover:");

        try {
            int unitIndex = Integer.parseInt(scanner.nextLine().trim());

            if (unitIndex < 1 || unitIndex > civilization.getControlledUnits().size()) {
                System.out.println("\nNumero invalido. Tente novamente.");
                return;
            }

            Unit unit = civilization.getControlledUnits().get(unitIndex - 1);

            if (unit.getStepsRemaining() <= 0) {
                System.out.println("\nEssa unidade ja usou todos os passos disponiveis neste turno.");
                return;
            }

            System.out.printf("\nPode dar ate %d passos. Indique a direcao da(s) deslocacao(oes) pretendidas:%n"+"Utilize 'c', 'b', 'e' ou 'd'.", unit.getStepsRemaining());
            String input_dirs = scanner.nextLine().toLowerCase().trim();

            if (input_dirs.length() > unit.getStepsRemaining() || input_dirs.length() <= 0) {
                System.out.println("\nNumero de direcoes invalido. Tente novamente.");
                return;
            }

            double totalCost = 0;
            int actualMoves = 0;
            int stepsMade = 0;

            for (char letter : input_dirs.toCharArray()) {
                Direction stepDir = inputToEnumDirection(String.valueOf(letter));
                if (stepDir != null) {
                        Cell currentCell = gameMap.getCell(unit.getCoordX(), unit.getCoordY());
                        int stepsToTraverse = currentCell.getTerrain().getStepsToTraverse();

                            if (unit.getStepsRemaining() >= stepsToTraverse) {
                                if (unit.move(stepDir, gameMap)) {
                                    actualMoves++;
                                    stepsMade += stepsToTraverse;
                                    totalCost += currentCell.getTerrain().getEntryCost();
                                } else {
                                    System.out.println("\nMovimento impossivel na direcao: " + letter);
                                    break;
                                }
                            } else {
                                System.out.printf("\nJa nao tem passos suficientes para atravessar o terreno na direcao: %s.%n", letter);
                                break;
                            }
                } else {
                    System.out.println("\nDirecao invalida: " + letter + ". Utilize apenas 'c', 'b', 'e' ou 'd'.");
                    return;
                }
            }

            double goldBalance = civilization.getGoldTreasure();
            if (actualMoves > 0) {
                if (goldBalance >= totalCost) {
                    civilization.addGoldTreasure(-totalCost);
                    System.out.printf("\nUnidade movida com sucesso. Deslocacoes feitas: %d Passos dados: %s Passos restantes: %d.%n", actualMoves, stepsMade, unit.getStepsRemaining());
                    System.out.println("Custo de movimento: " + totalCost + " Tesouro da civilizacao atualizado:" + civilization.getGoldTreasure());
                    gameMap.showMap();
                } else {
                    System.out.println("\nNao tem ouro suficiente para mover a unidade.");
                }
            } else {
                System.out.println("\nNenhum movimento foi realizado.");
            }

        } catch (NumberFormatException e) {
            System.out.println("\nOpcao invalida. Tente novamente.");
        }
    }

    /**
     * Converte o input do utilizador (um caractere) para uma direção enum
     *
     * @param input A direção como string
     * @return A direção correspondente (UP, DOWN, LEFT, RIGHT), ou null
     */
    private Direction inputToEnumDirection(String input) {
        switch (input) {
            case "c":
                return Direction.UP;
            case "b":
                return Direction.DOWN;
            case "e":
                return Direction.LEFT;
            case "d":
                return Direction.RIGHT;
            default:
                return null;
        }
    }

}
