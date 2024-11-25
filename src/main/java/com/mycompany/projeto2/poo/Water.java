/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class Water implements ILand {
    String type = "# ";
    String typeShown = "# ";
    
    
    @Override
    public String getType(){
        return type;
    }
    @Override
    public String getTypeShown(){
        return typeShown;
    }
}
