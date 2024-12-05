/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class TerrainPlains implements ITerrain {
    private int productivity, entryCost, cyclesToTraverse, maxNumWorkers;
    private double foodProduction, industrialProduction;
    private double goldProduction;
    private String type, typeShown;
    
    /**
     * Definição das váriaveis do tipo de terreno planicie usado na interface ITerrain
     */
    public TerrainPlains(){
        this.type = "- ";
        this.typeShown = "- ";
        this.productivity = 3;
        this.entryCost = 1;
        this.cyclesToTraverse = 1;
        this.maxNumWorkers = 10;
        this.foodProduction = 4;
        this.industrialProduction = 2;
        this.goldProduction = 0.5;
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

