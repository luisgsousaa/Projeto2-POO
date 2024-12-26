package com.mycompany.projeto2.poo;

public abstract class Unit implements ILife {
    
    private String type;
    private Direction direction;
    private int coordX, coordY;
    private int steps; // nr passos por ciclo da unidade
    private double maintenanceCost; // em ouro
    private int productionCost; // em recursos industriais
    private int productionDelay; //nr ciclos a serem criados
    private Civilization civilization;
    private int life, attackDamage;
    private int stepsRemaining;
    private int maxSteps;
    private boolean isColonizer;
    private String name;
    private int maxLife;


    public Unit(String name, String type, Civilization civilization, int life, int maxSteps, double maintenanceCost, int productionCost, int productionDelay, int attackDamage) {
        this.name = name;
        this.type = type;
        this.civilization = civilization;
        this.steps = steps;
        this.productionCost = productionCost;
        this.life = life;
        this.productionDelay = productionDelay;
        this.maintenanceCost = maintenanceCost;
        this.maxLife = life;
        this.attackDamage = attackDamage;
        civilization.addUnitToCiv(this);
        this.isColonizer = false;
        this.stepsRemaining = maxSteps;
        this.maxSteps = maxSteps;
    }



    public void setType(String type){this.type = type;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public void setCoordX(int coordX) {this.coordX = coordX;}
    public void setCoordY(int coordY) {this.coordY = coordY;}


    // definição se a unidade pode criar cidades ou nao
    public boolean isColonizer() {return isColonizer;} // verifica se a unidade pode fundar cidades ou nao
    public void setColonizer(boolean colonizer) {this.isColonizer = colonizer;} // para possibilitar uma unidade a poder fundar novas cidades


    // passos
    public int getStepsRemaining() {
        return stepsRemaining;
    }
    public void resetSteps() {
        this.stepsRemaining = maxSteps;
    }
    public int getMaxSteps() {
        return maxSteps;
    }
    public int getSteps(){return steps;}

    // dados basicos da unidade
    public String getUnitName() {return name;}
    public String getType(){return type;}
    public Direction getDirection() {return direction;}
    public int getCoordX() {return coordX;}
    public int getCoordY() {return coordY;}


    // confronto (ataque+captura)
    public void executeConfrontation(){};
    public boolean canAttack() {return false;}
    public int getRemainingAttacks() {return 0;}
    public void resetAttacks() {};
    public int getAttackDamage(){return this.attackDamage;}
    public void takeDamage(int damage) {this.life -= damage; if (this.life < 0) {this.life = 0;}}


    // cura
    public int getHealAmount() {return 0;} // definida a zero por default
    public void executeHeal(){};
    public boolean canHeal() {return false;}
    public int getRemainingHeals() {return 0;}
    public void resetHeals() {};
    public void heal(int amount) {
        this.life += amount;
        if (this.life > maxLife) {
            this.life = maxLife;
        }
    }


    // vida
    public int getLife() {return this.life;}
    public int getUnitMaxLife() {return maxLife;}
    public boolean isAlive() {return this.life > 0;}
    public void setLife(int life) {this.life = life;}


    // civilization
    public int getUnitCivNum() {return civilization.getNumber();}
    public Civilization getUnitCiv() {return civilization;}
    public String getUnitCivName() {return civilization.getName();}
    public void setCivilization(Civilization civilization) {this.civilization = civilization;}
    public void removeUnitFromCiv() {civilization.getControlledUnits().remove(this);}

    public void die(GameMap gameMap) {
        Cell c = gameMap.getCell(coordX, coordY);
        if (c != null) {
            c.removeUnit();
            c.setTypeShown(c.getPreviousTypeShown());
        }
        removeUnitFromCiv();
    }


    // manutenção, produtividade e tempo de criação
    public double getMaintenanceCost(){return maintenanceCost;}
    public int getProductionCost(){return productionCost;}
    public int getProductionDelay(){return productionDelay;}
    public void setMaintenanceCost(double maintenanceCost){this.maintenanceCost=maintenanceCost;}
    public void setProductionCost(int productionCost){this.productionCost=productionCost;}
    public void setProductionDelay(int productionDelay){this.productionDelay=productionDelay;}



    public boolean move(Direction direction, GameMap gameMap) {
        int originalX = coordX;
        int originalY = coordY;

        this.setDirection(direction);
        this.moveUnit(gameMap);

        if (coordX != originalX || coordY != originalY) {
            Cell currentCell = gameMap.getCell(coordX, coordY);
            if (currentCell.getTerrain() != null) {
                int stepsToMove = currentCell.getTerrain().getStepsToTraverse();
                if (stepsRemaining >= stepsToMove) {
                    stepsRemaining -= stepsToMove;
                } else {
                    stepsRemaining = 0;
                }
            }
            return true;
        }
        return false;
    }



    public static Unit createUnit(String unitType, int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {

        if (x < 0 || x >= gameMap.getWidth() || y < 0 || y >= gameMap.getHeight()) {
            System.out.println("Coordenadas invalidas.");
            return null;
        }

        Cell targetCell = gameMap.getCell(x,y);

        if (targetCell.isSomethingOnTop()) {
            System.out.println("A celula (" + x + "," + y + ") ja esta ocupada.");
            return null;
        }

        if (targetCell.getEntryCost() == -1) {
            System.out.println("A celula (" + x + "," + y + ") e inacessivel.");
            return null;
        }
        Unit newUnit = UnitFactoryRegistry.createUnit(unitType.trim(), x, y, gameMap, direction, civilization);
        //Unit newUnit = createUnitOfType(unitType.trim(), x, y, gameMap, direction, civilization);
        if (newUnit == null) {
            System.out.println("Unidade desconhecida.");
            return null;
        }

        targetCell.setPreviousTypeShown(targetCell.getTypeShown());
        targetCell.setUnit(newUnit);
        targetCell.setTypeShown(newUnit.getType() + newUnit.getUnitCivNum()); //+get get civId
        targetCell.setSomethingOnTop(true);

        return newUnit;
    }


    public void moveUnit(GameMap gameMap) {

            int newX = coordX;
            int newY = coordY;

            switch (direction) {
                case UP:
                    newY--;
                    if (newY < 0) {newY = gameMap.getHeight() - 1;} // Circ vert cim
                    break;

                case DOWN:
                    newY++;
                    if (newY >= gameMap.getHeight()) {newY = 0;} // circ vert baix
                    break;

                case LEFT:
                    newX--;
                    if (newX < 0) {coordX = gameMap.getWidth() - 1;} // Circ horz esq
                    break;

                case RIGHT:
                    newX++;
                    if (newX >= gameMap.getWidth()) {newX = 0;} //circ horz dir
                    break;

                case NONE:
                    break;

                default:
                    return;
            }

            Cell currentCell = gameMap.getCell(coordX, coordY); // celula em que se ta atualmente
            Cell targetCell = gameMap.getCell(newX, newY); // nova celula que queremos nos mover

            if (targetCell == null) {
                System.out.println("A celula de destino (" + newX + ", " + newY + ") nao existe.");
                return;
            }
            if (targetCell.isSomethingOnTop()) {
                System.out.println("A celula (" + newX + "," + newY + ") ja esta ocupada.");
                return;
            }

            if (targetCell.getEntryCost() == -1) {
                System.out.println("A celula (" + newX + "," + newY + ") e inacessivel.");
                return;
            }

            currentCell.setTypeShown(currentCell.getPreviousTypeShown()); // celula atual passa a mostrar o que tava antes
            currentCell.setSomethingOnTop(false);
            currentCell.setUnit(null); // retira referencia da celula atual

            targetCell.setPreviousTypeShown(targetCell.getTypeShown()); // celula alvo guarda o que tava la
            targetCell.setTypeShown(this.getType() + this.getUnitCivNum()); // atualiza a celula alvo para a unidade em questao
            targetCell.setSomethingOnTop(true);
            targetCell.setUnit(this); // coloca referencia na celula alvo

            coordX = newX;
            coordY = newY;
    }

}


