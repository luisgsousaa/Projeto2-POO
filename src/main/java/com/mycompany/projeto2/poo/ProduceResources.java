/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class ProduceResources {
    public static void chooseProduceType(Cell currentCell,City city){
        if(currentCell.getFoodProduction() != 0){
            city.addFoodResources(produce(currentCell.getFoodProduction(), currentCell.getProductivity(), currentCell.getNumWorkers()));
        }
        else if(currentCell.getIndustrialProduction()!= 0){
            city.addIndustrialResources(produce(currentCell.getIndustrialProduction(), currentCell.getProductivity(), currentCell.getNumWorkers()));
        }
        else if(currentCell.getGoldProduction() != 0){
            city.addGoldResources(produce(currentCell.getGoldProduction(), currentCell.getProductivity(), currentCell.getNumWorkers()));
        }
    }
    
    
    private static double produce(double production,int productivity, int numWorkers){
        double produced = production*productivity*numWorkers;
        return produced;
    }
    
}
