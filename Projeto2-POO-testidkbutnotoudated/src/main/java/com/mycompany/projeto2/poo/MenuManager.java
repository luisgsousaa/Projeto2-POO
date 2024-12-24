package com.mycompany.projeto2.poo;

import java.util.*;

public class MenuManager {

    private Map map;
    private Unit unit;
    private Civilization civilization;

    public MenuManager(Map map) {
        this.map = map;
    }

    public void showMenuManager(Civilization civilization) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            

            System.out.println("\nEscolha uma opcao:");
            System.out.println("1 - Mover Unidade");
            System.out.println("2 - Atacar");
            System.out.println("3 - Nova cidade");
            System.out.println("4 - Nova construcao");
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
                    optionBuild(scanner,civilization);
                    break;
                case 4:
                    optionBuildBuilding(scanner,civilization);
                    break;
                case 0:
                    civilization.getBuildings();
                    return;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (choice != 0);
        scanner.close();
    }



    private void optionBuildBuilding(Scanner scanner, Civilization civ) {
        ArrayList<Unit> temp = civ.getControlledUnits();
        
        if(temp == null) {
            System.out.println("Não tens nenhuma unidade construtora");
            return;
        };
        
        ArrayList<Unit> builders = new ArrayList<Unit>();
        
        for(int i=0;i<temp.size();i++) {
            if(temp.get(i).getClass().getSimpleName().equals("UnitBuilder")) {
                builders.add(temp.get(i));
            }
            
        }
        if(builders == null || builders.size() == 0) {
            System.out.println("Não tens nenhuma unidade construtora");
            return;
        };
        
        try{
                System.out.println("Escolha as coordenadas para a nova construção");
                int coordX,coordY;
                System.out.println("Coordenada X: ");
                coordX = scanner.nextInt();
                System.out.println("Coordenada Y: ");
                coordY = scanner.nextInt();
                
            boolean foundvalidbuilder = false;
            for(int i=0;i<builders.size();i++) {
                
                Unit temp2 = builders.get(i);
                if((Math.abs(temp2.getCoordX()-coordX) < 4 && Math.abs(temp2.getCoordY()-coordY) < 4) || Map.taxiCabDistanceTo(temp2.getCoordX(),temp2.getCoordY(),coordX,coordY) < 4) { //Este cálculo de distância tem de ser atualizado para considerar a circularidade do mapa
                    foundvalidbuilder = true;
                    break;
                }
            
            }
            if(!foundvalidbuilder) {
                throw new CoordinatesNotSuitableException();
            }
                System.out.println("Indique o tipo de construcao");
                ArrayList<HashMap<String, Object>> bTypes = Building.getTypes();
                for(int j=0;j<bTypes.size();j++) {
                    String text = Integer.toString(j) + " - " + bTypes.get(j).get("name");
                    System.out.println(text);
                }
                
                
                boolean success = false;
                
                //TODO -> ADD TERRAIN CHECK
                int choice = scanner.nextInt();
                
                for(int i=0;i<Building.getTypes().size();i++) {
                    
                    if(choice == i) {
                        System.out.println(i);
                        HashMap <String,Object> temp2 = Building.getTypes().get(i);
                        Building b = new Building((float)temp2.get("buildCost"),(int)temp2.get("buildTime"),(float)temp2.get("upkeepCost"),Map.getCellStatic(coordX,coordY),civ.getControlledCities().get(0),i);
                        civ.addBuilding(b);//Ir buscar os recursos à primeira cidade é temporário
                        System.out.println("Construção iniciada com sucesso");
                        success = true;
                        
                        break;
                    }
                }
                
                
                
                
                
            }
        
            catch(CoordinatesNotSuitableException e){
                System.out.println("Coordenadas inadequadas");
            }
            catch(InputMismatchException e){
                System.out.println("Introduza numeros inteiros para as coordenadas");
            }
    }
    

    private void optionMoveUnit(Scanner scanner, Civilization civilization) {

        Civilization.showControlledUnits(civilization);

        System.out.println("\nIndique a unidade que pretende mover:");

        try {
            int unitIndex = Integer.parseInt(scanner.nextLine().trim());

            if (unitIndex < 1 || unitIndex > civilization.getControlledUnits().size()) {
                System.out.println("\nNúmero inválido. Tente novamente.");
                return;
            }

            Unit unit = civilization.getControlledUnits().get(unitIndex - 1);

            if (unit.getSteps() == 0) {
                System.out.println("\nEssa unidade é fixa. Tente novamente.");
                return;
            }

            int initialX = unit.getCoordX();
            int initialY = unit.getCoordY();

            if (unit.getSteps() == 1) {

                System.out.println("\nIndique a direcao do passo (c = cima, b = baixo, e = esquerda, d = direita):");
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

            else {
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
    
    private void optionBuild(Scanner scanner, Civilization civ) {
        
        ArrayList<Unit> temp = civ.getControlledUnits();
        
        if(temp == null) {
            System.out.println("Não tens nenhuma unidade construtora");
            return;
        };
        
        ArrayList<Unit> builders = new ArrayList<Unit>();
        
        for(int i=0;i<temp.size();i++) {
            if(temp.get(i).getClass().getSimpleName().equals("UnitBuilder")) {
                builders.add(temp.get(i));
            }
            
        }
        if(builders == null || builders.size() == 0) {
            System.out.println("Não tens nenhuma unidade construtora");
            return;
        };
        CityBuilder builder;
        try{
                System.out.println("Escolha as coordenadas para a nova cidade");
                int coordX,coordY;
                System.out.println("Coordenada X: ");
                coordX = scanner.nextInt();
                System.out.println("Coordenada Y: ");
                coordY = scanner.nextInt();
                
            boolean foundvalidbuilder = false;
            for(int i=0;i<builders.size();i++) {
                
                Unit temp2 = builders.get(i);
                if(Math.abs(temp2.getCoordX()-coordX) < 4 && Math.abs(temp2.getCoordY()-coordY) < 4) { //Este cálculo de distância tem de ser atualizado para considerar a circularidade do mapa
                    foundvalidbuilder = true;
                    break;
                }
            
            }
            if(!foundvalidbuilder) {
                throw new CoordinatesNotSuitableException();
            }
                builder = new CityBuilder(coordX, coordY,map,civ.getNumber());
                
                builder.createCity(civ);
            }
        
            catch(CoordinatesNotSuitableException e){
                System.out.println("Coordenadas inadequadas");
            }
            catch(InputMismatchException e){
                System.out.println("Introduza numeros inteiros para as coordenadas");
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
                System.out.println("\nIndique o inimigo que pretende atacar:");
                for (int i = 0; i < adjacentEnemies.size(); i++) {
                    Unit enemy = adjacentEnemies.get(i);
                    System.out.printf("%d - %s (%d, %d) : %dHP%n",
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
                    System.out.printf("\n%s (%d,%d) morreu.%n", enemyToAttack.getType()+ enemyToAttack.getUnitCivNum(), enemyToAttack.getCoordX(), enemyToAttack.getCoordY());
                    enemyToAttack.die(map);
                    map.showMap();
                } else {
                    System.out.printf("\n%s (%d,%d) agora tem %dHP.%n", enemyToAttack.getType()+ enemyToAttack.getUnitCivNum(), enemyToAttack.getCoordX(), enemyToAttack.getCoordY(), enemyToAttack.getLife());
                    map.showMap();
                }
            }
        }

        if (!foundEnemy) {
            System.out.println("Nao tem unidades atacantes adjacentes a um inimigo. Aproxime-se da entidade que pretende atacar e tente novamente.");
        }
    }
    
    public void showMenuManager2(Civilization civ) {
        try {
            this.showMenuManager(civ);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro. Tente novamente");
            showMenuManager2(civ);
        }
        
    }






}
