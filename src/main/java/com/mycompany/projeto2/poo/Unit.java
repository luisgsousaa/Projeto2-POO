package com.mycompany.projeto2.poo;

import com.mycompany.projeto2.poo.Direction;

public abstract class Unit {

    private double maintenanceCost; // em ouro
    private int productionCost; // em recursos industriais
    private int productionDelay; //nr ciclos a serem criados
    private String type;
    private int coordX, coordY;

    Direction direction;
    
    public Unit(String type, int x, int y, double maintenanceCost, int productionCost, int productionDelay, Direction direction) {
        this.type = type;
        this.coordX = x;
        this.coordY = y;
        this.maintenanceCost = maintenanceCost;
        this.productionCost = productionCost;
        this.productionDelay = productionDelay;
        this.direction = direction;
    }

    public double getMaintenanceCost(){return maintenanceCost;}
    public void setMaintenanceCost(double maintenanceCost){this.maintenanceCost=maintenanceCost;}

    public int getProductionCost(){return productionCost;}
    public void setProductionCost(int productionCost){this.productionCost=productionCost;}

    public int getProductionDelay(){return productionDelay;}
    public void setProductionDelay(int productionDelay){this.productionDelay=productionDelay;}

    public String getType(){return type;}
    public void setType(String type){this.type = type;}


    public void moveUnit() {

        switch (direction) {
            case UP:
                Cell.getTypeShown()
                coordY--;
                break;

            case DOWN:
                coordY++;
                break;

            case LEFT:
                coordX--;
                break;

            case RIGHT:
                coordX++;
                break;

            default:
                break;
        }
    }






    // metodo para mover unidade
    // usar direction (enum - ja criado em direction) com um case switch
    // unidade vai poder andar para cima, baixo, esq e dir
    // regras:
    // se ja tiver alguma cidade, unidade na celula ou tiver no limite superior ou inferior nao consegue avançar
    // se for para a esquerda/direita no inicio/fim dos eixo dos x's aparece no outro lado
    // como a unidade será a unica coisa a se mover faz sentido a parte da circularidade ja ser feita aqui

    // normalmente todas as unidades movem-se 1 celula de cada vez, mas militar pode andar até duas se quiser (sempre na mesma direção)
    // provavelmente a solucao aqui vai ser ter este metodo override na classe militar

    // typeshown e previousetype shown usar para quando unidade passar por cima
    //exemplo:
    // para mudar:
    // previoustype = getTypeshown
    // typeshown =
    // para voltar:
    // typeshown = previoustype
}


