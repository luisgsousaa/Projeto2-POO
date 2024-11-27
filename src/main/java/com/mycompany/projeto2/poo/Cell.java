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
    private int productivity, entryCost, cyclesToTraverse, maxNumWorkers;
    private int foodProduction, industrialProduction;
    private static double goldProduction;
    private String type, typeShown, previousTypeShown;//
    private boolean somethingOnTop, belongsToCity;
    
    private UtilTerrains terrains;
    private ILand selectedTerrain;
    
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
    public int getFoodProduction(){return foodProduction;}
    public int getIndustrialProduction(){return industrialProduction;}
    public double getGoldProduction(){return goldProduction;}
    public boolean isSomethingOnTop(){return somethingOnTop;}
    public boolean getBelongsToCity(){return belongsToCity;}
    
    public void setBelongsToCity(boolean input){belongsToCity = input;}
    public void setTypeShown(String s){this.typeShown = s;}
    
}
