/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;


public class ProduceResources {
    /**
     * Verifica qual é o tipo de produção dá célula e calcula a produção desta adicionando-a à respetiva váriavel da cidade
     * @param currentCell referencia célula a calcular
     * @param city referencia da cidade 
     */
    public static void chooseProduceType(Cell currentCell,City city){
        if(currentCell.getFoodProduction() != 0){
            city.addFoodResources(produce(currentCell.getFoodProduction(), currentCell.getProductivity(), currentCell.getNumWorkers()));
        }
        else if(currentCell.getIndustrialProduction()!= 0){
            city.addIndustrialResources(produce(currentCell.getIndustrialProduction(), currentCell.getProductivity(), currentCell.getNumWorkers()));
        }
        else if(currentCell.getGoldProduction() != 0){
            city.addGoldToCivTreasure(produce(currentCell.getGoldProduction(), currentCell.getProductivity(), currentCell.getNumWorkers()));
        }
    }
    
    /**
     * 
     * @param production quantidade de produção da célula em questão
     * @param productivity produtividade da célula em questão
     * @param numWorkers quantidade de trabalhadores da célula em questão
     * @return cálculo da quantidade de recursos produzida
     */
    private static double produce(double production,int productivity, int numWorkers){
        double produced = production*productivity*numWorkers;
        return produced;
    }
    
}
