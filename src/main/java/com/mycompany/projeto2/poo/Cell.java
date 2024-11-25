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
    private static float goldProduction;
    private String type, typeShown, previousTypeShown;
    boolean somethingOnTop;
    UtilTerrains terrains;
    
    public Cell(String type){
        terrains = new UtilTerrains();
        ILand selectedTerrain = terrains.matchSymbol(type);
        this.type = selectedTerrain.getType();
        this.typeShown = selectedTerrain.getType();
    }
    
    
    
    
    public String getType(){return type;}
    public String getTypeShown(){return typeShown;}
    public Cell getCell(){return this;}
    
    
    
    
}
