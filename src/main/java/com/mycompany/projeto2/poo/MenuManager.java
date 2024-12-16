package com.mycompany.projeto2.poo;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {

    private Map map;
    private Unit unit;
    private Civilization civilization;

    public MenuManager(Map map) {
        this.map = map;
    }

    public void showMenuManager(Civilization civilization) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\nEscolha uma opcao:");
            System.out.println("1 - Mover Unidade");
            System.out.println("2 - Atacar");
            System.out.println("3 - nada");
            System.out.println("4 - nada");
            System.out.println("0 - Terminar jogada.");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    optionMoveUnit(scanner,civilization);
                    break;
                case 2:
                    optionAttack(scanner,civilization);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (choice != 0);
        scanner.close();
    }





    private void optionMoveUnit(Scanner scanner, Civilization civilization) {

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

            System.out.printf("\nPode dar ate %d passos. Indique a direcao do(s) passo(s):%n", unit.getStepsRemaining());
            String input_dirs = scanner.nextLine().toLowerCase().trim();

            if (input_dirs.length() > unit.getStepsRemaining() || input_dirs.length() <= 0) {
                System.out.println("\nNumero de direções invalido. Tente novamente.");
                return;
            }

            for (char letter : input_dirs.toCharArray()) {
                Direction step_dir = inputToEnumDirection(String.valueOf(letter));
                if (step_dir != null) {
                    if (!unit.move(step_dir, map)) {
                        return; // Sai se não conseguir mover
                    }
                } else {
                    System.out.println("\nDirecao invalida: " + letter + ". Utilize apenas 'c', 'b', 'e' ou 'd'.");
                    return;
                }
            }

            System.out.println("\nUnidade movida com sucesso. Passos restantes: " + unit.getStepsRemaining());
            map.showMap();

        } catch (NumberFormatException e) {
            System.out.println("\nOpcao invalida. Tente novamente.");
        }
    }





    private Direction inputToEnumDirection(String input) {
        switch (input) {
            case "c": return Direction.UP;
            case "b": return Direction.DOWN;
            case "e": return Direction.LEFT;
            case "d": return Direction.RIGHT;
            default: return null;
        }
    }



    private void optionAttack(Scanner scanner, Civilization civilization) {

        ArrayList<Unit> attackingUnits = new ArrayList<>();

        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.getAttackDamage() > 0) {
                attackingUnits.add(unit);
            }
        }

        if (attackingUnits.isEmpty()) {
            System.out.println("\nNao tem unidades atacantes na sua civilizacao.");
            return;
        }


        boolean foundEnemy = false;

        for (Unit attacker : attackingUnits) {
            int x = attacker.getCoordX();
            int y = attacker.getCoordY();

            Object targetToAttack = null;
            ArrayList<Object> adjacentEnemies = new ArrayList<>();

            int[][] directions = {
                    {0, -1}, // c
                    {0, 1},  // b
                    {-1, 0}, // e
                    {1, 0}   // d
            };

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                Cell adjacentCell = map.getCell(newX, newY);
                if (adjacentCell.isSomethingOnTop()) {
                    if (adjacentCell.getUnit() != null) {
                        Unit potentialEnemy = adjacentCell.getUnit();
                        if (!potentialEnemy.getUnitCiv().equals(attacker.getUnitCiv())) {
                            adjacentEnemies.add(potentialEnemy);
                        }
                    }

                    if (adjacentCell.getBelongsToCity() && adjacentCell.getCity() != null) {
                        City potentialEnemyCity = adjacentCell.getCity();
                        if (!potentialEnemyCity.getCityCiv().equals(attacker.getUnitCiv())) {
                            adjacentEnemies.add(potentialEnemyCity);
                        }
                    }






                    /*

                    Unit potentialEnemy = adjacentCell.getUnit();
                    if (!potentialEnemy.getUnitCiv().equals(attacker.getUnitCiv())) {
                        adjacentEnemies.add(potentialEnemy);
                    }*/
                }
            }

            if (adjacentEnemies.isEmpty()) {
                continue;
            }

            foundEnemy = true;

            if (adjacentEnemies.size() == 1) {
                targetToAttack = adjacentEnemies.get(0);
            } else {
                System.out.println("\nIndique o inimigo que pretende atacar:");
                for (int i = 0; i < adjacentEnemies.size(); i++) {
                    Object enemy = adjacentEnemies.get(i);
                    if (enemy instanceof Unit) {
                        Unit enemyUnit = (Unit) enemy;
                        System.out.printf("%d - %s (%d, %d) : %dHP%n",
                                i + 1, enemyUnit.getType() + enemyUnit.getUnitCivNum(),
                                enemyUnit.getCoordX(), enemyUnit.getCoordY(), enemyUnit.getLife());
                    } else if (enemy instanceof City) {
                        City enemyCity = (City) enemy;
                        System.out.printf("%d - %s (%d, %d) : %dHP%n",
                                i + 1, enemyCity.getType() + enemyCity.getCityCivNum(),
                                enemyCity.getCoordX(), enemyCity.getCoordY(), enemyCity.getLife());
                    }
                }

                int choice = -1;
                while (choice < 1 || choice > adjacentEnemies.size()) {
                    choice = scanner.nextInt();
                }
                targetToAttack = adjacentEnemies.get(choice - 1);
            }

            if (targetToAttack != null) {
                if (targetToAttack instanceof Unit) {
                    Unit enemyUnit = (Unit) targetToAttack;
                    enemyUnit.takeDamage(attacker.getAttackDamage());

                    if (!enemyUnit.isAlive()) {
                        System.out.printf("\n%s (%d,%d) morreu.%n",
                                enemyUnit.getType() + enemyUnit.getUnitCivNum(),
                                enemyUnit.getCoordX(), enemyUnit.getCoordY());
                        enemyUnit.die(map);
                    } else {
                        System.out.printf("\n%s (%d,%d) agora tem %dHP.%n",
                                enemyUnit.getType() + enemyUnit.getUnitCivNum(),
                                enemyUnit.getCoordX(), enemyUnit.getCoordY(),
                                enemyUnit.getLife());
                    }
                } else if (targetToAttack instanceof City) {
                    City enemyCity = (City) targetToAttack;
                    enemyCity.takeDamage(attacker.getAttackDamage());

                    if (!enemyCity.isAlive()) {
                        System.out.printf("\n%s (%d,%d) foi destruida.%n",
                                enemyCity.getType() + enemyCity.getCityCivNum(),
                                enemyCity.getCoordX(), enemyCity.getCoordY());
                        enemyCity.die(map);
                    } else {
                        System.out.printf("\n%s (%d,%d) agora tem %dHP.%n",
                                enemyCity.getType() + enemyCity.getCityCivNum(),
                                enemyCity.getCoordX(), enemyCity.getCoordY(),
                                enemyCity.getLife());
                    }
                }
                map.showMap();
            }
        }

        if (!foundEnemy) {
            System.out.println("Nao tem unidades atacantes adjacentes a um inimigo. Aproxime-se da entidade que pretende atacar e tente novamente.");
        }
    }






}
