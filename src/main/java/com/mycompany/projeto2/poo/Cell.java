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
    
    public Cell(String type){
        temp(type);
    }
    
    private void temp(String type){
        switch(type){
            case "- ":
                this.type = "- ";
                this.typeShown = "- ";
                break;
            case "# ":
                this.type = "# ";
                this.typeShown = "# ";
                break;
            case "Z ":
                this.type = "Z ";
                this.typeShown = "Z ";
                break;
            default:
                System.out.println("Erroffsdsd");
        }
    }
    
    
    public String getType(){return type;}
    public String getTypeShown(){return typeShown;}
    public Cell getCell(){return this;}
    
    
    
    
}
