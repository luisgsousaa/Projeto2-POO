package com.mycompany.projeto2.poo;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * A classe permite que o jogador utilize unidades curadoras para recuperar a vida de unidades ou cidades da mesma civilizacao.
 */
public class MenuHealOption implements MenuOption {

    @Override
    public String getDescription() {return "Curar unidade ou reparar cidade";}


    /**
     * Executa a opção de curar uma unidade ou reparar uma cidade.
     * O jogador pode selecionar uma unidade curadora próxima das unidades ou cidades da mesma civ feridas e realizar a cura ou reparo.
     */
    @Override
    public void execute(Scanner scanner, Civilization civilization, GameMap gameMap) {
        ArrayList<Unit> healingUnits = new ArrayList<>();

        /// unidades que podem curar
        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.getHealAmount() > 0) {
                healingUnits.add(unit);
            }
        }

        if (healingUnits.isEmpty()) {
            System.out.println("\nNao tem unidades curadoras na sua civilizacao.");
            return;
        }


        ArrayList<Unit> availableHealers = getAvailableHealers(healingUnits, gameMap);

        if (availableHealers.isEmpty()) {
            System.out.println("Nao tem unidades curadoras adjacentes a aliados feridos. Aproxime-se de uma unidade que precisa de cura e tente novamente.");
            return;
        }

        if (availableHealers.size() > 1) {
            System.out.println("\nTem mais do que uma unidade curadora próxima de aliados feridos. Escolha a que pretende utilizar:");
            for (int i = 0; i < availableHealers.size(); i++) {
                Unit healer = availableHealers.get(i);
                System.out.printf("%d - %s (%d, %d)\n", i + 1, healer.getType() + healer.getUnitCivNum(),
                        healer.getCoordX(), healer.getCoordY());
            }

            int choice = -1;
            while (choice < 1 || choice > availableHealers.size()) {
                choice = scanner.nextInt();
            }

            Unit selectedHealer = availableHealers.get(choice - 1);
            performHealing(scanner, selectedHealer, gameMap);
        } else {
            performHealing(scanner, availableHealers.get(0), gameMap); //cura logo se so tiver uma curadora
        }
    }


    /**
     * Obtém as unidades curadoras que estão próximas a aliados feridos
     *
     * @param healingUnits Lista de unidades que podem curar
     * @return lista de unidades curadoras próximas a aliados feridos
     */
    private ArrayList<Unit> getAvailableHealers(ArrayList<Unit> healingUnits, GameMap gameMap) {
        ArrayList<Unit> availableHealers = new ArrayList<>();
        boolean foundAlly = false;

        for (Unit healer : healingUnits) {
            int x = healer.getCoordX();
            int y = healer.getCoordY();

            ArrayList<Object> adjacentAllies = getAdjacentAllies(x, y, healer, gameMap);

            if (!adjacentAllies.isEmpty()) {
                availableHealers.add(healer);
                foundAlly = true;
            }
        }

        return availableHealers;
    }


    /**
     * Obtém as entidades da mesma civ adjacentes a uma unidade curadora
     * @return lista de entidades da mesma civ  adjacentes à unidade curadora que podem ser curados (feridos)
     */
    private ArrayList<Object> getAdjacentAllies(int x, int y, Unit healer, GameMap gameMap) {
        ArrayList<Object> adjacentAllies = new ArrayList<>();

        int[][] directions = {
                {0, -1}, // cima
                {0, 1},  // baixo
                {-1, 0}, // esquerda
                {1, 0}   // direita
        };

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            Cell adjacentCell = gameMap.getCell(newX, newY);
            if (adjacentCell.isSomethingOnTop()) {
                if (adjacentCell.getUnit() != null) {
                    Unit potentialAlly = adjacentCell.getUnit();
                    if (potentialAlly.getUnitCiv().equals(healer.getUnitCiv()) && potentialAlly.getLife() < potentialAlly.getUnitMaxLife()) {
                        adjacentAllies.add(potentialAlly);
                    }
                }

                if (adjacentCell.getBelongsToCity() && adjacentCell.getCity() != null) {
                    City potentialAllyCity = adjacentCell.getCity();
                    if (potentialAllyCity.getCityCiv().equals(healer.getUnitCiv()) && potentialAllyCity.getLife() < potentialAllyCity.getCityMaxLife()) {
                        adjacentAllies.add(potentialAllyCity);
                    }
                }
            }
        }

        return adjacentAllies;
    }


    /**
     * Realiza a cura ou reparo de um entidade da mesma civ
     *
     * @param healer A unidade curadora
     */
    private void performHealing(Scanner scanner, Unit healer, GameMap gameMap) {
        if (!healer.canHeal()) {
            System.out.println("A sua unidade não pode curar mais neste ciclo.");
            return;
        }


        int x = healer.getCoordX();
        int y = healer.getCoordY();

        ArrayList<Object> adjacentAllies = getAdjacentAllies(x, y, healer, gameMap);

        if (adjacentAllies.size() == 1) {
            healTarget(scanner, adjacentAllies.get(0), healer);
        } else {
            System.out.println("\nIndique o aliado que pretende curar:");
            for (int i = 0; i < adjacentAllies.size(); i++) {
                Object ally = adjacentAllies.get(i);
                if (ally instanceof Unit) {
                    Unit allyUnit = (Unit) ally;
                    System.out.printf("%d - %s (%d, %d) : %dHP%n",
                            i + 1, allyUnit.getType() + allyUnit.getUnitCivNum(),
                            allyUnit.getCoordX(), allyUnit.getCoordY(), allyUnit.getLife());
                } else if (ally instanceof City) {
                    City allyCity = (City) ally;
                    System.out.printf("%d - %s (%d, %d) : %dHP%n",
                            i + 1, allyCity.getType() + allyCity.getCityCivNum(),
                            allyCity.getCoordX(), allyCity.getCoordY(), allyCity.getLife());
                }
            }

            int choice = -1;
            while (choice < 1 || choice > adjacentAllies.size()) {
                choice = scanner.nextInt();
            }
            healTarget(scanner, adjacentAllies.get(choice - 1), healer);
        }
        healer.executeHeal();
        gameMap.showMap();
    }

    /**
     * Realiza a cura de um alvo específico (unidade ou cidade)
     * @param targetToHeal O alvo que será curado, que pode ser uma unidade ou cidade
     * @param healer A unidade curadora que realizará a cura
     */
    private void healTarget(Scanner scanner, Object targetToHeal, Unit healer) {
        if (targetToHeal instanceof Unit) {
            Unit allyUnit = (Unit) targetToHeal;
            allyUnit.heal(healer.getHealAmount());

            System.out.printf("\n%s (%d,%d) agora tem %dHP.%n",
                    allyUnit.getType() + allyUnit.getUnitCivNum(),
                    allyUnit.getCoordX(), allyUnit.getCoordY(),
                    allyUnit.getLife());
        } else if (targetToHeal instanceof City) {
            City allyCity = (City) targetToHeal;
            allyCity.heal(healer.getHealAmount());

            System.out.printf("\n%s (%d,%d) agora tem %dHP.%n",
                    allyCity.getType() + allyCity.getCityCivNum(),
                    allyCity.getCoordX(), allyCity.getCoordY(),
                    allyCity.getLife());
        }
    }

}