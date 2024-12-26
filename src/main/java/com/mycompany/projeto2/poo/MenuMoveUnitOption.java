package com.mycompany.projeto2.poo;

import java.util.Scanner;

public class MenuMoveUnitOption implements MenuOption {

    @Override
    public String getDescription() {return "Mover Unidade";}


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

            System.out.printf("\nPode dar ate %d passos. Indique a direcao da(s) deslocacao(oes) pretendidas:%n", unit.getStepsRemaining());
            String input_dirs = scanner.nextLine().toLowerCase().trim();

            if (input_dirs.length() > unit.getStepsRemaining() || input_dirs.length() <= 0) {
                System.out.println("\nNumero de direcoes invalido. Tente novamente.");
                return;
            }

            double totalCost = 0;
            int actualSteps = 0;

            for (char letter : input_dirs.toCharArray()) {
                Direction step_dir = inputToEnumDirection(String.valueOf(letter));
                if (step_dir != null) {
                    if (unit.move(step_dir, gameMap)) {
                        Cell currentCell = gameMap.getCell(unit.getCoordX(), unit.getCoordY());
                        if (currentCell.getTerrain() != null) {
                            totalCost += currentCell.getTerrain().getEntryCost();

                            int stepsToTraverse = currentCell.getTerrain().getStepsToTraverse();

                            if (unit.getStepsRemaining() >= stepsToTraverse) {
                                actualSteps++;
                            }
                            else {
                                System.out.println("\nJa nao tem passos suficientes para fazer a deslocacao deste terreno.");
                                return;
                            }
                        }

                    } else {
                        continue;
                    }
                } else {
                    System.out.println("\nDirecao invalida: " + letter + ". Utilize apenas 'c', 'b', 'e' ou 'd'.");
                    return;
                }
            }

            double goldBalance = civilization.getGoldTreasure();
            if (actualSteps > 0) {
                if (goldBalance >= totalCost) {
                    civilization.addGoldTreasure(-totalCost);
                    System.out.printf("\nUnidade movida com sucesso. Deslocacoes feitas: %d Passos restantes: %d.%n", actualSteps, unit.getStepsRemaining());
                    System.out.println("customovimento testar " + totalCost + " /////APAGAR/////" + civilization.getGoldTreasure());
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
