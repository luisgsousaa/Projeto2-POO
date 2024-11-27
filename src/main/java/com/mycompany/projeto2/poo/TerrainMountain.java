/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class TerrainMountain implements ITerrain {
    private int productivity, entryCost, cyclesToTraverse, maxNumWorkers;
    private int foodProduction, industrialProduction;
    private static double goldProduction;
    private String type, typeShown;
    
    public TerrainMountain(){
        this.type = "Z ";
        this.typeShown = "Z ";
        this.productivity = 5;
        this.entryCost = 3;
        this.cyclesToTraverse = 2;
        this.maxNumWorkers = 6;
        this.foodProduction = 4;
        this.industrialProduction = 1;
        this.goldProduction = 2;
    }
    
    
    
    
    @Override
    public String getType(){return type;}
    @Override
    public String getTypeShown(){return typeShown;}
    @Override
    public int getProductivity(){return productivity;}
    @Override
    public int getEntryCost(){return entryCost;}
    @Override
    public int getCyclesToTraverse(){return cyclesToTraverse;}
    @Override
    public int getMaxNumWorkers(){return maxNumWorkers;}
    @Override
    public int getFoodProduction(){return foodProduction;}
    @Override
    public int getIndustrialProduction(){return industrialProduction;}
    @Override
    public double getGoldProduction(){return goldProduction;}
}
