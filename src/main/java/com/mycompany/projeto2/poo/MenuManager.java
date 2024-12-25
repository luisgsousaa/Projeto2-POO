package com.mycompany.projeto2.poo;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {

    private GameMap gameMap;
    private Unit unit;
    private Civilization civilization;

    public MenuManager(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void showMenuManager(Civilization civilization) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\nEscolha uma opcao:");
            System.out.println("1 - Mover Unidade");
            System.out.println("2 - Atacar ou capturar inimigo");
            System.out.println("3 - Curar");
            System.out.println("4 - Criar unidade");
            System.out.println("5 - Criar cidade");
            System.out.println("0 - Terminar jogada.");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada invalida. Por favor, insira um numero.");
                scanner.next();
            }


            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    optionMoveUnit(scanner, civilization);
                    break;
                case 2:
                    optionAttack(scanner, civilization);
                    break;
                case 3:
                    optionHeal(scanner, civilization);
                    break;
                case 4:
                    //optionCreateUnit(scanner, civilization);
                    break;
                case 5:
                    optionCreateCity(scanner, civilization);
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
                    if (unit.move(step_dir, gameMap)) {
                        Cell currentCell = gameMap.getCell(unit.getCoordX(), unit.getCoordY());
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


        ArrayList<Unit> availableAttackers = getAvailableAttackers(attackingUnits);

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
            performAction(scanner, selectedAttacker);
        } else {
            performAction(scanner, availableAttackers.get(0)); // se so tiver uma opcao de atacante nao preciso escolher
        }
    }

    private void performAction(Scanner scanner, Unit attacker) {
        if (!attacker.canAttack()) {
            System.out.println("A sua unidade não pode enfrentar mais inimigos neste ciclo.");
            return;
        }

        System.out.println("Deseja capturar ou atacar? (1 - Capturar, 2 - Atacar)");
        int actionChoice = scanner.nextInt();

        if (actionChoice == 1) {
            performCapture(scanner, attacker);
        } else if (actionChoice == 2) {
            performAttack(scanner, attacker);
        } else {
            System.out.println("Opcao invalida. Tente novamente.");
            performAction(scanner, attacker);
        }
    }

    private void performCapture(Scanner scanner, Unit attacker) {
        int x = attacker.getCoordX();
        int y = attacker.getCoordY();

        ArrayList<Object> adjacentEnemies = getAdjacentEnemies(x, y, attacker);

        if (adjacentEnemies.size() == 1) {
            captureTarget(scanner, adjacentEnemies.get(0), attacker); // se so tiver uma opcao para capturar faz diretamente
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
            captureTarget(scanner, adjacentEnemies.get(choice - 1), attacker);
        }
        attacker.executeConfrontation(); // conta como um confronto, seja captura ou ataque para matar/ferir
        gameMap.showMap();
    }

    private void performAttack(Scanner scanner, Unit attacker) {
        int x = attacker.getCoordX();
        int y = attacker.getCoordY();


        ArrayList<Object> adjacentEnemies = getAdjacentEnemies(x, y, attacker);


        if (adjacentEnemies.size() == 1) {
            attackTarget(scanner, adjacentEnemies.get(0), attacker); // se so tiver uma opcao para atacar ataca diretamente
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
        attacker.executeConfrontation();
        gameMap.showMap();
    }


    private void captureTarget(Scanner scanner, Object targetToAttack, Unit attacker) {
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

    private void attackTarget(Scanner scanner, Object targetToAttack, Unit attacker) {
        if (targetToAttack instanceof Unit) {
            Unit enemyUnit = (Unit) targetToAttack;
            enemyUnit.takeDamage(attacker.getAttackDamage());

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

            Cell adjacentCell = gameMap.getCell(newX, newY);
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
        gameMap.showMap();
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

/*
    private void optionCreateUnit(Scanner scanner, Civilization civilization) {

        Civilization.showControlledCities(civilization);

        System.out.println("\nIndique a cidade onde pretende criar unidade(s):");

        try {
            int cityIndex = Integer.parseInt(scanner.nextLine().trim());

            if (cityIndex < 1 || cityIndex > civilization.getControlledCities().size()) {
                System.out.println("\nNumero invalido. Tente novamente.");
                return;
            }

            City city = civilization.getControlledCities().get(cityIndex - 1);

            System.out.printf("\nRecursos dessa cidade: %n", city.getIndustrialResources());


        }
    }*/



    private void optionCreateCity(Scanner scanner, Civilization civilization) {

        ArrayList<Unit> colonizerUnits = new ArrayList<>();
        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.isColonizer()) {
                colonizerUnits.add(unit);
            }
        }

        if (colonizerUnits.isEmpty()) {
            System.out.println("A sua civilizacao nao tem unidades colonizadoras.");
            return;
        }


        System.out.println("Escolha uma unidade colonizadora:");
        for (int i = 0; i < colonizerUnits.size(); i++) {
            Unit unit = colonizerUnits.get(i);
            System.out.printf("%d - %s (%d, %d)\n", i + 1, unit.getType() + unit.getUnitCivNum(), unit.getCoordX(), unit.getCoordY());
        }


        int choice = -1;
        while (choice < 1 || choice > colonizerUnits.size()) {
            choice = scanner.nextInt();
        }

        Unit selectedUnit = colonizerUnits.get(choice - 1);


        System.out.println("Onde deseja construir a cidade?");
        System.out.println("1 - Em cima");
        System.out.println("2 - À esquerda");
        System.out.println("3 - À direita");
        System.out.println("4 - Em baixo");
        int creationCordsDir = -1;
        while (creationCordsDir < 1 || creationCordsDir > 4) {
            creationCordsDir = scanner.nextInt();
        }


        int newCoordX = selectedUnit.getCoordX();
        int newCoordY = selectedUnit.getCoordY();

        switch (creationCordsDir) {
            case 1:
                newCoordY -= 4;
                break;
            case 2:
                newCoordX -= 4;
                break;
            case 3:
                newCoordX += 4;
                break;
            case 4:
                newCoordY += 4;
                break;
        }

        CityBuilder cityBuilder = new CityBuilder(newCoordX, newCoordY, gameMap, civilization.getNumber());
        try {
            if (civilization.getGoldTreasure() < 600) {
                System.out.println("Nao tem ouro suficiente para construir a cidade.");
                return;
            }

            City newCity = cityBuilder.createCity(civilization);
            if (newCity != null) {
                civilization.addGoldTreasure(-600);
                System.out.println("Cidade construida com sucesso.");
            }
        } catch (CoordinatesNotSuitableException e) {
            System.out.println("Nao foi possível construir a cidade aqui. Escolha outra localizacao.");
        }
    }

}