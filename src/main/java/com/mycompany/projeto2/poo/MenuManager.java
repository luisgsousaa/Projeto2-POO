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
            System.out.println("0 - Sair");

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
        Civilization.showControlledUnits(civilization, UnitMilitary.class); //teste

        System.out.println("\nInsira as coordenadas da unidade que deseja mover separado por virgulas:");
        String[] coords_array = scanner.nextLine().split(","); // slipt serve para dividir string que tem separadores (no caso virgulas) em arrays, por isso vamos ter um array de 2 elementos para cada coordenada

        if (coords_array.length != 2) { // se o array nao tiver 2 elementos é pq algo ta mal
            System.out.println("\nCoordenadas invalidas. Tente novamente.");
            return;
        }

        try {
            int x = Integer.parseInt(coords_array[0].trim());
            int y = Integer.parseInt(coords_array[1].trim());

            if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
                System.out.println("\nCoordenadas fora dos limites do mapa. Tente novamente.");
                return;
            }

            Cell cell = map.getCell(x, y);
            unit = cell.getUnit();

            if (cell.getUnit() == null) {
                System.out.println("\nEssas coordenadas nao sao de uma unidade. Tente novamente.");
                return;
            }
            

            if (unit.getUnitCiv() != civilization){
                System.out.println("\nEssa unidade nao é da sua civilizacao. Tente novamente.");
                return;
            }

            if (unit.getSteps() == 0) {
                System.out.println("\nEssa unidade e fixa. Tente novamente.");
                return;
            }

            int initialX = x;
            int initialY = y;

            if (unit.getSteps() == 1) {

                System.out.println("\nIndique a direcao do passo.");
                String letter = scanner.nextLine().toLowerCase().trim();

                if (letter.length() != 1) {
                    System.out.println("\nInsira apenas uma direcao. Tente novamente.");
                    return;
                }

                Direction step_dir = inputToEnumDirection(letter);

                if (step_dir != null) {
                    unit.setDirection(step_dir);
                    unit.moveUnit(map);
                } else {
                    System.out.println("\nDirecao invalida: " + letter + ". Utilize apenas 'c', 'b', 'e' ou 'd'.");
                    return;
                }
            }

            if (unit.getSteps() > 1) {

                System.out.printf("\nPode dar ate %d passos. Indique a direcao do(s) passo(s) que pretende fazer sem espacos:%n", unit.getSteps()); //%n é para ir pa outra linha, no println e nao é preciso pq ja faz automaticamente
                String input_dirs = scanner.nextLine().toLowerCase().trim();

                if (input_dirs.length() > unit.getSteps() || input_dirs.length() <= 0) {
                    System.out.println("\nNumero de direcoes invalido. Tente novamente.");
                    return;
                }

                for (char letter : input_dirs.toCharArray()) { // separa caracteres do input tipo cbee = c b e e

                    Direction step_dir = inputToEnumDirection(String.valueOf(letter));

                    if (step_dir != null) {
                        unit.setDirection(step_dir);
                        unit.moveUnit(map);
                    } else {
                        System.out.println("\nDirecao invalida: " + letter + ". Utilize apenas 'c', 'b', 'e' ou 'd'.");
                        return;
                    }
                }
            }

            if (unit.getCoordX() == initialX && unit.getCoordY() == initialY) {
                System.out.println("\nA unidade permaneceu nas mesmas coordenadas.");
                return;
            }

            System.out.println("\nUnidade movida com sucesso.");
            map.showMap();

        } catch (NumberFormatException e) {
            System.out.println("\nCoordenadas invalidas. Tente novamente.");
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
            System.out.println("Nao tem unidades atacantes na sua civilizacao.");
            return;
        }


        boolean foundEnemy = false;

        for (Unit attacker : attackingUnits) {
            int x = attacker.getCoordX();
            int y = attacker.getCoordY();

            Unit enemyToAttack = null;
            ArrayList<Unit> adjacentEnemies = new ArrayList<>();

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
                    Unit potentialEnemy = adjacentCell.getUnit();
                    if (!potentialEnemy.getUnitCiv().equals(attacker.getUnitCiv())) {
                        adjacentEnemies.add(potentialEnemy);
                    }
                }
            }

            if (adjacentEnemies.isEmpty()) {
                continue;
            }

            foundEnemy = true;

            if (adjacentEnemies.size() == 1) {
                enemyToAttack = adjacentEnemies.get(0);
            } else {
                System.out.println("Escolha qual inimigo atacar:");
                for (int i = 0; i < adjacentEnemies.size(); i++) {
                    Unit enemy = adjacentEnemies.get(i);
                    System.out.printf("%d - %s (%d, %d) | %d%n",
                            i + 1, enemy.getType() + enemy.getUnitCivNum(), enemy.getCoordX(), enemy.getCoordY(), enemy.getLife());
                }

                int choice = -1;
                while (choice < 1 || choice > adjacentEnemies.size()) {
                    choice = scanner.nextInt();
                }
                enemyToAttack = adjacentEnemies.get(choice - 1);
            }

            if (enemyToAttack != null) {
                enemyToAttack.takeDamage(attacker.getAttackDamage());

                if (!enemyToAttack.isAlive()) {
                    System.out.printf("%s (%d, %d) foi morto.%n", enemyToAttack.getType()+ enemyToAttack.getUnitCivNum(), enemyToAttack.getCoordX(), enemyToAttack.getCoordY());
                    enemyToAttack.die(map);
                } else {
                    System.out.printf("%s (%d, %d) agora tem %d de vida.%n", enemyToAttack.getType()+ enemyToAttack.getUnitCivNum(), enemyToAttack.getCoordX(), enemyToAttack.getCoordY(), enemyToAttack.getLife());
                }
            }
        }


        if (!foundEnemy) {
            System.out.println("Nao tem unidades atacantes adjacentes a um inimigo. Aproxime-se da entidade que pretende atacar e tente novamente.");
        }
    }


}
