/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

import java.util.ArrayList;

/**
 *
 * @author Marlene
 */
public class UtilTerrains {
    
    ArrayList<ILand> terrains;
    
    public UtilTerrains(){
        terrains = new ArrayList<ILand>();
        
        terrains.add(new Water());
        terrains.add(new Plains());
        terrains.add(new Mountains());
        terrains.add(new CityTerrain());
    }
    
    public ILand matchSymbol(String symbol){
        for(ILand terrain : terrains){
            if(terrain.getType().equals(symbol)){
                return terrain;
            }
        }
        return null;
        
    }
}
