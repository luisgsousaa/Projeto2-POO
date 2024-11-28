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
    private int productivity, entryCost, cyclesToTraverse, maxNumWorkers, numWorkers;
    private int foodProduction, industrialProduction;
    private static double goldProduction;
    private String type, typeShown, previousTypeShown;//
    private boolean somethingOnTop, belongsToCity;

    private UtilTerrains terrains;
    private ITerrain selectedTerrain;

    public Cell(String type)throws NullPointerException{
        terrains = new UtilTerrains();
        selectedTerrain = terrains.matchSymbol(type);

        if(selectedTerrain == null){
            throw new IllegalArgumentException("Invalid terrain type: " + type);
        }

        setCellVariables();
    }

    private void setCellVariables(){
        this.type = selectedTerrain.getType();
        this.typeShown = selectedTerrain.getType();
        this.previousTypeShown = typeShown;
        this.productivity = selectedTerrain.getProductivity();
        this.entryCost = selectedTerrain.getEntryCost();
        this.cyclesToTraverse = selectedTerrain.getCyclesToTraverse();
        this.maxNumWorkers = selectedTerrain.getMaxNumWorkers();
        this.numWorkers = 0;
        this.foodProduction = selectedTerrain.getFoodProduction();
        this.industrialProduction = selectedTerrain.getIndustrialProduction();
        this.goldProduction = selectedTerrain.getGoldProduction();
        this.somethingOnTop = false;
        this.belongsToCity = false;
    }
    
    
    
    
    
    public Cell getCell(){return this;}

    public String getType(){return type;}
    public String getTypeShown(){return typeShown;}
    public String getPreviousTypeShown(){return previousTypeShown;}
    public int getProductivity(){return productivity;}
    public int getEntryCost(){return entryCost;}
    public int getCyclesToTraverse(){return cyclesToTraverse;}
    public int getMaxNumWorkers(){return maxNumWorkers;}
    public int getNumWorkers(){return numWorkers;}
    public int getFoodProduction(){return foodProduction;}
    public int getIndustrialProduction(){return industrialProduction;}
    public double getGoldProduction(){return goldProduction;}
    public boolean isSomethingOnTop(){return somethingOnTop;}
    public boolean getBelongsToCity(){return belongsToCity;}
    
    
    
    public void multiplyProductivity(double multiplier){this.productivity *=multiplier;}
    public void setBelongsToCity(boolean input){this.belongsToCity = input;}
    public void setTypeShown(String s){this.typeShown = s;}
    public void changeNumWorkers(int num){this.numWorkers += num;}
    public void setToFoodProduction(){this.foodProduction=1;}
    public void setToIndustrialProduction(){this.industrialProduction=1;}
    public void setToGoldProduction(){this.goldProduction=1;}
    
}
