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
    
    ArrayList<ITerrain> terrains;
    
    public UtilTerrains(){
        terrains = new ArrayList<ITerrain>();
        
        terrains.add(new TerrainWater());
        terrains.add(new TerrainPlains());
        terrains.add(new TerrainMountain());
        terrains.add(new TerrainCity());
    }

    public ITerrain matchSymbol(String symbol) throws NullPointerException{
        for(ITerrain terrain : terrains){
            if(terrain.getType().equals(symbol)){
                return terrain;
            }
        }
        return null;
        
    }
}
