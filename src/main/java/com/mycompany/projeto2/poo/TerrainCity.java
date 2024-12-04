/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class TerrainCity implements ITerrain {
    private int productivity, entryCost, cyclesToTraverse, maxNumWorkers;
    private double foodProduction, industrialProduction;
    private static double goldProduction;
    private String type, typeShown;
    //private Civilization civilization;
    
    /**
     * Definição das váriaveis do tipo de terreno cidade usado na interface ITerrain
     */
    public TerrainCity(){
        this.type = "C ";
        this.typeShown = "C ";
        this.productivity = 0;
        this.entryCost = -1;
        this.cyclesToTraverse = 0;
        this.maxNumWorkers = 0;
        this.foodProduction = 0;
        this.industrialProduction = 0;
        this.goldProduction = 0;
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
