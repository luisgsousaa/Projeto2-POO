/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class Mountains implements ILand {
    String type = "Z ";
    String typeShown = "Z ";
    
    
    @Override
    public String getType(){
        return type;
    }
    @Override
    public String getTypeShown(){
        return typeShown;
    }
}
