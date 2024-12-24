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
            System.out.println("3 - Curar");
            System.out.println("4 - Capturar");
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
                    optionHeal(scanner,civilization);
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

            double totalCost = 0;
            int actualSteps = 0;

            for (char letter : input_dirs.toCharArray()) {
                Direction step_dir = inputToEnumDirection(String.valueOf(letter));
                if (step_dir != null) {
                    if (unit.move(step_dir, map)) {
                        Cell currentCell = map.getCell(unit.getCoordX(), unit.getCoordY());
                        if (currentCell.getTerrain() != null) {
                            totalCost += currentCell.getTerrain().getEntryCost();
                        }
                        actualSteps++;
                    } else {
                        continue; // Sai do loop se encontrar um obstáculo
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
                    System.out.printf("\nUnidade movida com sucesso. Passos dados: %d Passos restantes: %d.%n", actualSteps, unit.getStepsRemaining());
                    System.out.println("customovimento testar " + totalCost + " /////APAGAR/////" + civilization.getGoldTreasure());
                    map.showMap();
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
            case "c": return Direction.UP;
            case "b": return Direction.DOWN;
            case "e": return Direction.LEFT;
            case "d": return Direction.RIGHT;
            default: return null;
        }
    }



    private void optionAttack(Scanner scanner, Civilization civilization) {
        ArrayList<Unit> attackingUnits = new ArrayList<>();

        // Filtra as unidades que podem atacar
        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.getAttackDamage() > 0) {
                attackingUnits.add(unit);
            }
        }

        if (attackingUnits.isEmpty()) {
            System.out.println("\nNao tem unidades atacantes na sua civilizacao.");
            return;
        }

        // Encontra unidades atacantes adjacentes a inimigos
        ArrayList<Unit> availableAttackers = getAvailableAttackers(attackingUnits);

        if (availableAttackers.isEmpty()) {
            System.out.println("Nao tem unidades atacantes adjacentes a um inimigo. Aproxime-se da entidade que pretende atacar e tente novamente.");
            return;
        }

        // Se houver mais de um atacante disponível, pergunta ao jogador qual escolher
        if (availableAttackers.size() > 1) {
            System.out.println("\nTem mais do que uma unidade em posicao de ataque. Escolha a que pretende utilizar:");
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
            performAttack(scanner, selectedAttacker);
        } else {
            performAttack(scanner, availableAttackers.get(0)); // Ataca imediatamente se houver apenas um atacante
        }
    }

    private ArrayList<Unit> getAvailableAttackers(ArrayList<Unit> attackingUnits) {
        ArrayList<Unit> availableAttackers = new ArrayList<>();
        boolean foundEnemy = false;

        for (Unit attacker : attackingUnits) {
            int x = attacker.getCoordX();
            int y = attacker.getCoordY();


            ArrayList<Object> adjacentEnemies = getAdjacentEnemies(x, y, attacker);

            if (!adjacentEnemies.isEmpty()) {
                availableAttackers.add(attacker);
                foundEnemy = true;
            }
        }

        return availableAttackers;
    }

    private ArrayList<Object> getAdjacentEnemies(int x, int y, Unit attacker) {
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
            }
        }

        return adjacentEnemies;
    }

    private void performAttack(Scanner scanner, Unit attacker) {
        if (!attacker.canAttack()) {
            System.out.println("A sua unidade não pode atacar mais neste ciclo.");
            return;
        }
        int x = attacker.getCoordX();
        int y = attacker.getCoordY();


        ArrayList<Object> adjacentEnemies = getAdjacentEnemies(x, y, attacker);


        if (adjacentEnemies.size() == 1) {
            attackTarget(scanner, adjacentEnemies.get(0), attacker);
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
            attackTarget(scanner, adjacentEnemies.get(choice - 1), attacker);
        }
        attacker.executeAttack();
        map.showMap();
    }

    private void attackTarget(Scanner scanner, Object targetToAttack, Unit attacker) {
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
    }



    /*
    private void optionHeal(Scanner scanner, Civilization civilization) {

        ArrayList<Unit> healingUnits = new ArrayList<>();

        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.getHealAmount() > 0) {
                healingUnits.add(unit);
            }
        }

        if (healingUnits.isEmpty()) {
            System.out.println("\nNao tem unidades curantes na sua civilizacao.");
            return;
        }


        boolean foundEntityToHeal = false;

        for (Unit healer : healingUnits) {
            int x = healer.getCoordX();
            int y = healer.getCoordY();

            Object entityToHeal = null;
            ArrayList<Object> adjacentEntities = new ArrayList<>();

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
                        Unit potentialAlly = adjacentCell.getUnit();
                        if (potentialAlly.getUnitCiv().equals(healer.getUnitCiv())) {
                            adjacentEntities.add(potentialAlly);
                        }
                    }

                    if (adjacentCell.getBelongsToCity() && adjacentCell.getCity() != null) {
                        City potentialAllyCity = adjacentCell.getCity();
                        if (potentialAllyCity.getCityCiv().equals(healer.getUnitCiv())) {
                            adjacentEntities.add(potentialAllyCity);
                        }
                    }

                }
            }

            if (adjacentEntities.isEmpty()) {
                continue;
            }

            foundEntityToHeal = true;

            if (adjacentEntities.size() == 1) {
                entityToHeal = adjacentEntities.get(0);
            } else {
                System.out.println("\nIndique a entidade que pretende curar:");
                for (int i = 0; i < adjacentEntities.size(); i++) {
                    Object ally = adjacentEntities.get(i);
                    if (ally instanceof Unit) {
                        Unit allyUnit = (Unit) ally;
                        System.out.printf("%d - %s (%d, %d) : %dHP%n",
                                i + 1, allyUnit.getType() + allyUnit.getUnitCivNum(),
                                allyUnit.getCoordX(), allyUnit.getCoordY(), allyUnit.getLife());
                    } else if (ally instanceof City) {
                        City enemyCity = (City) ally;
                        System.out.printf("%d - %s (%d, %d) : %dHP%n",
                                i + 1, enemyCity.getType() + enemyCity.getCityCivNum(),
                                enemyCity.getCoordX(), enemyCity.getCoordY(), enemyCity.getLife());
                    }
                }

                int choice = -1;
                while (choice < 1 || choice > adjacentEntities.size()) {
                    choice = scanner.nextInt();
                }
                entityToHeal = adjacentEntities.get(choice - 1);
            }

            if (entityToHeal != null) {
                if (entityToHeal instanceof Unit) {
                    Unit allyUnit = (Unit) entityToHeal;
                    int newLife = Math.min(allyUnit.getLife() + healer.getHealAmount(), allyUnit.getUnitMaxLife());
                    allyUnit.setLife(newLife);
                    System.out.printf("\n%s (%d,%d) agora tem %dHP.%n",
                            allyUnit.getType() + allyUnit.getUnitCivNum(),
                            allyUnit.getCoordX(), allyUnit.getCoordY(),
                            allyUnit.getLife());

                } else if (entityToHeal instanceof City) {
                    City allyCity = (City) entityToHeal;
                    int newLife = Math.min(allyCity.getLife() + healer.getHealAmount(), allyCity.getCityMaxLife());
                    allyCity.setLife(newLife);
                    System.out.printf("\n%s (%d,%d) agora tem %dHP.%n",
                            allyCity.getType() + allyCity.getCityCivNum(),
                            allyCity.getCoordX(), allyCity.getCoordY(),
                            allyCity.getLife());
                }
                map.showMap();
            }
        }

        if (!foundEntityToHeal) {
            System.out.println("Nao tem unidades curantes adjacentes a entidades da sua civilizacao. Aproxime-se da entidade que pretende curar e tente novamente.");
        }

    }*/



    private void optionHeal(Scanner scanner, Civilization civilization) {
        ArrayList<Unit> healingUnits = new ArrayList<>();

        // Filtra as unidades que podem curar
        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.getHealAmount() > 0) {
                healingUnits.add(unit);
            }
        }

        if (healingUnits.isEmpty()) {
            System.out.println("\nNao tem unidades curadoras na sua civilizacao.");
            return;
        }


        ArrayList<Unit> availableHealers = getAvailableHealers(healingUnits);

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
            performHealing(scanner, selectedHealer);
        } else {
            performHealing(scanner, availableHealers.get(0)); //cura logo se so tiver um
        }
    }

    private ArrayList<Unit> getAvailableHealers(ArrayList<Unit> healingUnits) {
        ArrayList<Unit> availableHealers = new ArrayList<>();
        boolean foundAlly = false;

        for (Unit healer : healingUnits) {
            int x = healer.getCoordX();
            int y = healer.getCoordY();

            ArrayList<Object> adjacentAllies = getAdjacentAllies(x, y, healer);

            if (!adjacentAllies.isEmpty()) {
                availableHealers.add(healer);
                foundAlly = true;
            }
        }

        return availableHealers;
    }

    private ArrayList<Object> getAdjacentAllies(int x, int y, Unit healer) {
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

            Cell adjacentCell = map.getCell(newX, newY);
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

    private void performHealing(Scanner scanner, Unit healer) {
        if (!healer.canHeal()) {
            System.out.println("A sua unidade não pode curar mais neste ciclo.");
            return;
        }



        int x = healer.getCoordX();
        int y = healer.getCoordY();

        ArrayList<Object> adjacentAllies = getAdjacentAllies(x, y, healer);

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
        map.showMap();
    }

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
