package com.mycompany.projeto2.poo;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Opção jogador pode escolher entre atacar unidades ou capturar unidades e cidades inimigas adjacentes.
 */
public class MenuConfrontOption implements MenuOption {

    @Override
    public String getDescription() {return "Atacar ou capturar inimigo";}

    @Override
    public void execute(Scanner scanner, Civilization civilization, GameMap gameMap) {
        ArrayList<Unit> attackingUnits = new ArrayList<>();

        // vai buscar unidades que podem atacar
        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.getAttackDamage() > 0) {
                attackingUnits.add(unit);
            }
        }

        if (attackingUnits.isEmpty()) {
            System.out.println("\nNao tem unidades atacantes na sua civilizacao.");
            return;
        }


        ArrayList<Unit> availableAttackers = getAvailableAttackers(attackingUnits, gameMap);

        if (availableAttackers.isEmpty()) {
            System.out.println("Nao tem unidades atacantes adjacentes a um inimigo. Aproxime-se da entidade que pretende enfrentar e tente novamente.");
            return;
        }


        if (availableAttackers.size() > 1) {
            System.out.println("\nTem mais do que uma unidade atacante em posicao de confronto. Escolha a que pretende utilizar:");
            for (int i = 0; i < availableAttackers.size(); i++) {
                Unit attacker = availableAttackers.get(i);
                System.out.printf("%d - %s (%d, %d)\n", i + 1, attacker.getType() + attacker.getUnitCivNum(),
                        attacker.getCoordX(), attacker.getCoordY());
            }

            int choice = -1;
            while (choice < 1 || choice > availableAttackers.size()) {
                choice = scanner.nextInt();
            }

            Unit selectedAttacker = availableAttackers.get(choice - 1);
            performAction(scanner, selectedAttacker, gameMap);
        } else {
            performAction(scanner, availableAttackers.get(0), gameMap); // se so tiver uma opcao de atacante nao preciso escolher
        }
    }

    /**
     * Pergunta a ação de ataque ou captura escolhida pelo jogador.
     *
     */
    private void performAction(Scanner scanner, Unit attacker, GameMap gameMap) {
        if (!attacker.canAttack()) {
            System.out.println("A sua unidade não pode enfrentar mais inimigos neste ciclo.");
            return;
        }

        int actionChoice = -1;
        while (actionChoice != 1 && actionChoice != 2) {
            System.out.println("Deseja capturar ou atacar? (1 - Capturar, 2 - Atacar)");

            if (scanner.hasNextInt()) {
                actionChoice = scanner.nextInt();
                if (actionChoice == 1) {
                    performCapture(scanner, attacker, gameMap);
                } else if (actionChoice == 2) {
                    performAttack(scanner, attacker, gameMap);
                } else {
                    System.out.println("Opção invalida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada invalida. Por favor, insira um numero (1 ou 2).");
                scanner.next();
            }
        }
    }

    /**
     * Mostra opcoes captura de uma unidade ou cidade inimiga adjacente.
     *
     */
    private void performCapture(Scanner scanner, Unit attacker, GameMap gameMap) {
        int x = attacker.getCoordX();
        int y = attacker.getCoordY();

        ArrayList<Object> adjacentEnemies = getAdjacentEnemies(x, y, attacker, gameMap);
        adjacentEnemies.removeIf(enemy -> enemy instanceof Unit && ((Unit) enemy).getAttackDamage() > 0); // não permite a unidades atacantes serem capturas, o que é logico pois podem se defender

        if (adjacentEnemies.size() == 1) {
            captureTarget(scanner, adjacentEnemies.get(0), attacker, gameMap); // se so tiver uma opcao para capturar faz diretamente
        } else {
            System.out.println("\nIndique o inimigo que pretende capturar:");
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
            captureTarget(scanner, adjacentEnemies.get(choice - 1), attacker, gameMap);
        }
        attacker.executeConfrontation(); // conta como um confronto, seja captura ou ataque para matar/ferir
        gameMap.showMap();
    }

    /**
     * Mostra opcoes de ataque a uma unidade ou cidade inimiga adjacente.
     *
     */
    private void performAttack(Scanner scanner, Unit attacker, GameMap gameMap) {
        int x = attacker.getCoordX();
        int y = attacker.getCoordY();


        ArrayList<Object> adjacentEnemies = getAdjacentEnemies(x, y, attacker, gameMap);


        if (adjacentEnemies.size() == 1) {
            attackTarget(scanner, adjacentEnemies.get(0), attacker, gameMap); // se so tiver uma opcao para atacar ataca diretamente
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
            attackTarget(scanner, adjacentEnemies.get(choice - 1), attacker, gameMap);
        }
        attacker.executeConfrontation();
        gameMap.showMap();
    }


    /**
     * Captura a unidade ou cidade inimiga selecionada pelo jogador.
     *
     */
    private void captureTarget(Scanner scanner, Object targetToAttack, Unit attacker, GameMap gameMap) {
        if (targetToAttack instanceof Unit) {
            Unit enemyUnit = (Unit) targetToAttack;

            if (Math.random() < 0.25) { // 25% chance de capturar unidade
                System.out.printf("Captura bem-sucedida. A unidade (%d,%d) agora pertence à sua civilizacao.\n", enemyUnit.getCoordX(), enemyUnit.getCoordY());
                enemyUnit.removeUnitFromCiv();
                attacker.getUnitCiv().addUnitToCiv(enemyUnit);
                enemyUnit.setCivilization(attacker.getUnitCiv());


                Cell unitCell = gameMap.getCell(enemyUnit.getCoordX(), enemyUnit.getCoordY());
                if (unitCell != null) {
                    unitCell.setTypeShown(enemyUnit.getType() + enemyUnit.getUnitCivNum()); // atualizar o numero mostrado pela unidade
                }


            } else {
                System.out.println("A captura falhou.");
            }

        } else if (targetToAttack instanceof City) {
            City enemyCity = (City) targetToAttack;

            if (Math.random() < 0.05) { // 5% chance de capturar cidade
                System.out.printf("Captura bem-sucedida. A cidade (%d,%d) agora pertence à sua civilizacao.\n", enemyCity.getCoordX(), enemyCity.getCoordY());
                enemyCity.removeCityFromCiv();
                attacker.getUnitCiv().addCityToCiv(enemyCity);
                enemyCity.setCivilization(attacker.getUnitCiv());

                Cell cityCell = gameMap.getCell(enemyCity.getCoordX(), enemyCity.getCoordY());
                if (cityCell != null) {
                    cityCell.setTypeShown(enemyCity.getType() + attacker.getUnitCivNum()); // atualizar o numero mostrado pela unidade
                }


            } else {
                System.out.println("A captura falhou.");
            }
        }
    }


    /**
     * Ataca a unidade ou cidade inimiga selecionada pelo jogador.
     *
     */
    private void attackTarget(Scanner scanner, Object targetToAttack, Unit attacker, GameMap gameMap) {
        if (targetToAttack instanceof Unit) {
            Unit enemyUnit = (Unit) targetToAttack;
             double temp = attacker.getAttackDamage()*Math.pow(0.8,Building.countType(enemyUnit.getUnitCiv().getBuildings(),1));
                    int temp2 = (int)Math.round(temp);
            enemyUnit.takeDamage(temp2);

            if (!enemyUnit.isAlive()) {
                System.out.printf("\n%s (%d,%d) morreu.%n",
                        enemyUnit.getType() + enemyUnit.getUnitCivNum(),
                        enemyUnit.getCoordX(), enemyUnit.getCoordY());
                enemyUnit.die(gameMap);
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
                enemyCity.die(gameMap);
            } else {
                System.out.printf("\n%s (%d,%d) agora tem %dHP.%n",
                        enemyCity.getType() + enemyCity.getCityCivNum(),
                        enemyCity.getCoordX(), enemyCity.getCoordY(),
                        enemyCity.getLife());
            }
        }
    }

    /**
     * Obtém uma lista das unidades que podem atacar, ou seja, unidades que têm inimigos adjacentes.
     * @param attackingUnits - lista de unidades controladas pela civilização que podem ser atacantes
     * @return uma lista de unidades disponíveis para atacar (unidades com inimigos adjacentes)
     */
    private ArrayList<Unit> getAvailableAttackers(ArrayList<Unit> attackingUnits, GameMap gameMap) {
        ArrayList<Unit> availableAttackers = new ArrayList<>();
        boolean foundEnemy = false;

        for (Unit attacker : attackingUnits) {
            int x = attacker.getCoordX();
            int y = attacker.getCoordY();


            ArrayList<Object> adjacentEnemies = getAdjacentEnemies(x, y, attacker, gameMap);

            if (!adjacentEnemies.isEmpty()) {
                availableAttackers.add(attacker);
                foundEnemy = true;
            }
        }

        return availableAttackers;
    }

    /**
     * Obtém os inimigos adjacentes a uma unidade em um determinado ponto do mapa
     * @return uma lista de objetos adjacentes (que podem ser unidades ou cidades) que pertencem a uma civilização inimiga
     */
    private ArrayList<Object> getAdjacentEnemies(int x, int y, Unit attacker, GameMap gameMap) {
        ArrayList<Object> adjacentEnemies = new ArrayList<>();

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
            if (adjacentCell != null && adjacentCell.isSomethingOnTop()) {
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
            }
        }

        return adjacentEnemies;
    }

}









