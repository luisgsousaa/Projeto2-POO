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
    private double foodProduction, industrialProduction;
    private double goldProduction;
    private String type, typeShown;
    
    
    /**
     * Definição das váriaveis do tipo de terreno mountain usado na interface ITerrain
     */
    public TerrainMountain(){
        this.type = "Z ";
        this.typeShown = "Z ";
        this.productivity = 5;
        this.entryCost = 3;
        this.cyclesToTraverse = 2;
        this.maxNumWorkers = 6;
        this.foodProduction = 1.5;
        this.industrialProduction = 1;
        this.goldProduction = 1;
    }
    
    
    
    /**
     * Getters para as váriaveis deste tipo de terreno
     */
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
    public double getFoodProduction(){return foodProduction;}
    @Override
    public double getIndustrialProduction(){return industrialProduction;}
    @Override
    public double getGoldProduction(){return goldProduction;}
}
