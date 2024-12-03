package com.mycompany.projeto2.poo;

public abstract class Unit implements Life {
    
    private String type;
    private Direction direction;
    private int coordX, coordY;
    private int steps; // nr passos por ciclo da unidade
    private double maintenanceCost; // em ouro
    private int productionCost; // em recursos industriais
    private int productionDelay; //nr ciclos a serem criados
    private Civilization civilization;
    private int life;

    public Unit(String type, Civilization civilization, int steps, double maintenanceCost, int productionCost, int productionDelay, int life) {
        this.type = type;
        this.civilization = civilization;
        this.steps = steps;
        this.productionCost = productionCost;
        this.productionDelay = productionDelay;
        this.maintenanceCost = maintenanceCost;
        this.life = 100;
        civilization.addUnitToCiv(this);
    }

    public double getMaintenanceCost(){return maintenanceCost;}
    public int getProductionCost(){return productionCost;}
    public int getProductionDelay(){return productionDelay;}
    public String getType(){return type;}
    public Direction getDirection() {return direction;}
    public int getCoordX() {return coordX;}
    public int getCoordY() {return coordY;}
    public int getSteps(){return steps;}
    public int getUnitCivNum() {return civilization.getNumber();}
    public Civilization getUnitCiv() {return civilization;}
    public String getUnitCivName() {return civilization.getName();}
    public int getLife() {return this.life;}
    public void takeDamage(int damage) {this.life -= damage; if (this.life < 0) {this.life = 0;}}
    public void heal(int amount) {this.life += amount;}
    public boolean isAlive() {return this.life > 0;}

    public void die() {
        System.out.println("A unidade " + this.type + " (" + this.coordX + ", " + this.coordY + ") do jogador " + getUnitCivNum() + " (" + getUnitCivName() + ") moreu.");
    }
    public abstract String getUnitName();
    public void setLife(int life) {this.life = life;}
    public void setMaintenanceCost(double maintenanceCost){this.maintenanceCost=maintenanceCost;}
    public void setProductionCost(int productionCost){this.productionCost=productionCost;}
    public void setProductionDelay(int productionDelay){this.productionDelay=productionDelay;}
    public void setType(String type){this.type = type;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public void setCoordX(int coordX) {this.coordX = coordX;}
    public void setCoordY(int coordY) {this.coordY = coordY;}








    public static Unit createUnit(String unitType, int x, int y, Map map, Direction direction, Civilization civilization) {

        if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
            System.out.println("Coordenadas invalidas.");
            return null;
        }

        Cell targetCell = map.getCell(x,y);

        if (targetCell.isSomethingOnTop()) {
            System.out.println("A celula (" + x + "," + y + ") ja esta ocupada.");
            return null;
        }

        if (targetCell.getEntryCost() == -1) {
            System.out.println("A celula (" + x + "," + y + ") e inacessivel.");
            return null;
        }

        Unit newUnit;

        switch (unitType.trim()) {
            case "M":
                newUnit = new UnitMilitary(x, y, map, direction, civilization);
                break;
            case "E":
                newUnit = new UnitExplorer(x, y, map, direction, civilization);
                break;
            case "B":
                newUnit = new UnitBuilder(x, y, map, direction, civilization);
                break;
            case "S":
                newUnit = new UnitSpier(x, y, map, direction, civilization);
                break;
            case "P":
                newUnit = new UnitProducer(x, y, map, direction, civilization);
                break;
            default:
                System.out.println("Unidade desconhecida.");
                return null;
        }

        targetCell.setPreviousTypeShown(targetCell.getTypeShown());
        targetCell.setUnit(newUnit);
        targetCell.setTypeShown(newUnit.getType() + newUnit.getUnitCivNum()); //+get get civId
        targetCell.setSomethingOnTop(true);

        return newUnit;
    }


    public void moveUnit(Map map) {

            int newX = coordX;
            int newY = coordY;

            switch (direction) {
                case UP:
                    newY--;
                    if (newY < 0) {newY = map.getHeight() - 1;} // Circ vert cim
                    break;

                case DOWN:
                    newY++;
                    if (newY >= map.getHeight()) {newY = 0;} // circ vert baix
                    break;

                case LEFT:
                    newX--;
                    if (newX < 0) {coordX = map.getWidth() - 1;} // Circ horz esq
                    break;

                case RIGHT:
                    newX++;
                    if (newX >= map.getWidth()) {newX = 0;} //circ horz dir
                    break;

                case NONE:
                    break;

                default:
                    return;
            }

            Cell currentCell = map.getCell(coordX, coordY); // celula em que se ta atualmente
            Cell targetCell = map.getCell(newX, newY); // nova celula que queremos nos mover

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


