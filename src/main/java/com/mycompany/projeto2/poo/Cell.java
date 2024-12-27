/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class Cell {

    private int productivity, cyclesToTraverse, maxNumWorkers, numWorkers;
    private double entryCost;
    private double foodProduction, industrialProduction;
    private double goldProduction;
    private String type, typeShown, previousTypeShown;//
    private boolean somethingOnTop, belongsToCity;
    private Unit unit;
    private UtilTerrains terrains;
    private ITerrain selectedTerrain;
    private int originalProductivity;

    /**
     * O construtor usa uma classe utilitaria para obter os tipos de terreno que existem e comparar o tipo passado como parâmetro com o tipo que define o terreno
     * @param type os caracteres tirados do ficheiro do mapa que irão dizer qual o tipo de terreno
     * @throws NullPointerException 
     */
    public Cell(String type) throws NullPointerException{

        terrains = new UtilTerrains();
        selectedTerrain = terrains.matchSymbol(type);

        if(selectedTerrain == null){
            throw new IllegalArgumentException("Invalid terrain type: " + type);
        }

        setCellVariables();
    }
    /**
     * Dá os valores à célula consoante os valores definidos no tipo de terreno
     */
    private void setCellVariables(){
        this.type = selectedTerrain.getType();
        this.typeShown = selectedTerrain.getType();
        this.previousTypeShown = typeShown;
        this.productivity = selectedTerrain.getProductivity();
        this.originalProductivity = productivity;
        this.entryCost = selectedTerrain.getEntryCost();
        this.cyclesToTraverse = selectedTerrain.getStepsToTraverse();
        this.maxNumWorkers = selectedTerrain.getMaxNumWorkers();
        this.numWorkers = 0;
        this.foodProduction = selectedTerrain.getFoodProduction();
        this.industrialProduction = selectedTerrain.getIndustrialProduction();
        this.goldProduction = selectedTerrain.getGoldProduction();
        this.somethingOnTop = false;
        this.belongsToCity = false;
        this.unit = null;
    }
    /**
     * Setters e Getters para gerir as células
     */


    public City getCity() {return (this instanceof City) ? (City) this : null;}



    public Cell getCell(){return this;}
    public Unit getUnit(){return unit;}
    public String getType(){return type;}
    public String getTypeShown(){return typeShown;}
    public String getPreviousTypeShown(){return previousTypeShown;}
    public int getProductivity(){return productivity;}
    public double getEntryCost(){return entryCost;}
    public int getCyclesToTraverse(){return cyclesToTraverse;}
    public int getMaxNumWorkers(){return maxNumWorkers;}
    public int getNumWorkers(){return numWorkers;}
    public double getFoodProduction(){return foodProduction;}
    public double getIndustrialProduction(){return industrialProduction;}
    public double getGoldProduction(){return goldProduction;}
    public boolean isSomethingOnTop(){return somethingOnTop;}
    public boolean getBelongsToCity(){return belongsToCity;}
    public void removeUnit() {
        this.unit = null;
        this.somethingOnTop = false;
    }

    public void setUnit(Unit unit) {this.unit = unit;}
    public void setSomethingOnTop(boolean input){this.somethingOnTop = input;} // usado para indicar que ta alguma coisa por "cima" de uma celula
    public void setPreviousTypeShown (String input){this.previousTypeShown = input;};

    public void multiplyProductivity(double multiplier){this.productivity *=multiplier;}


    public void setBelongsToCity(boolean input){this.belongsToCity = input;}
    public void setTypeShown(String s){this.typeShown = s;}
    public void changeNumWorkers(int num){this.numWorkers += num;}
    
    
    
    public void setToFoodProduction(){
        this.goldProduction=0;
        this.industrialProduction = 0;
    }
    public void setToIndustrialProduction(){
        this.goldProduction=0;
        this.foodProduction=0;
    }
    public void setToGoldProduction(){
        this.foodProduction=0;
        this.industrialProduction=0;
    }

    public ITerrain getTerrain() {return this.selectedTerrain;}

    public void setProductivity(int productivity) {this.productivity = productivity;}
    public void increaseProductivityByMultiplier(double multiplier) {this.productivity *= multiplier;}
    public void resetProductivityToOriginal() {this.productivity = originalProductivity;}

    
}