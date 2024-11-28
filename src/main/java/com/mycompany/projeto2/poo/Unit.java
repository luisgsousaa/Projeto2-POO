package com.mycompany.projeto2.poo;

import com.mycompany.projeto2.poo.Direction;

public abstract class Unit {
    
    private String type;
    private Direction direction;
    private int coordX, coordY;
    private int steps; // nr passos por ciclo da unidade
    private double maintenanceCost; // em ouro
    private int productionCost; // em recursos industriais
    private int productionDelay; //nr ciclos a serem criados


    public Unit(String type, int steps, double maintenanceCost, int productionCost, int productionDelay) {
        this.type = type;
        this.steps = steps;
        this.productionCost = productionCost;
        this.productionDelay = productionDelay;
        this.maintenanceCost = maintenanceCost;
    }

    public double getMaintenanceCost(){return maintenanceCost;}
    public void setMaintenanceCost(double maintenanceCost){this.maintenanceCost=maintenanceCost;}

    public int getProductionCost(){return productionCost;}
    public void setProductionCost(int productionCost){this.productionCost=productionCost;}

    public int getProductionDelay(){return productionDelay;}
    public void setProductionDelay(int productionDelay){this.productionDelay=productionDelay;}

    public String getType(){return type;}
    public void setType(String type){this.type = type;}

    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}

    public int getCoordX() {return coordX;}
    public void setCoordX(int coordX) {this.coordX = coordX;}

    public int getCoordY() {return coordY;}
    public void setCoordY(int coordY) {this.coordY = coordY;}





    public void moveUnit(Map map) {
        for (int i = 0; i < steps; i++) {

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
                    if (newX >= map.getHeight()) {newX = 0;} //circ horz dir
                    break;

                case NONE:
                    break;

                default:
                    System.out.println("direcao inexstente");
                    return;
            }

            Cell currentCell = map.getCell(coordX, coordY); // celula em que se ta atualmente
            Cell targetCell = map.getCell(newX, newY); // nova celula que queremos nos mover

            if (targetCell == null) {
                System.out.println("celula fora limites");
                return;
            }

            if (targetCell.isSomethingOnTop()) {
                System.out.println("celula ocupada");
                return;
            }

            // como até agora nao pareceu existir problemas na posicao onde queremos nos mover:
            currentCell.setTypeShown(currentCell.getPreviousTypeShown()); // celula atual passa a mostrar o que tava antes
            currentCell.setSomethingOnTop(false);

            targetCell.setPreviousTypeShown(targetCell.getTypeShown()); // celula alvo guarda o que tava la
            targetCell.setTypeShown(this.getType()); // atualiza a celula alvo para a unidade em questao
            targetCell.setSomethingOnTop(true);

            // finalmente atualiza as coordenadas da unidade
            coordX = newX;
            coordY = newY;
        }
    }
}


