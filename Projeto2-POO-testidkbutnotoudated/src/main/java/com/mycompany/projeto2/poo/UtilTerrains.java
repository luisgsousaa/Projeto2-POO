/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

import java.util.ArrayList;


public class UtilTerrains {
    //Classe utilitaria para gerir os tipos de terreno que podem ser apresentados nos mapas e nos seus ficheiros
    ArrayList<ITerrain> terrains;
    /**
     * Inicialização do arrayList que guarda todos os tipos de terreno e adição destes.
     */
    public UtilTerrains(){

        terrains = new ArrayList<ITerrain>();
        
        terrains.add(new TerrainWater());
        terrains.add(new TerrainPlains());
        terrains.add(new TerrainMountain());
        terrains.add(new TerrainCity());

    }
    
    /**
     * Verifica se o simbolo recebido como parametro pertence a algum dos tipos de terreno que está no arrayList
     * @param symbol simbolo lido no ficheiro do mapa que normalmente corresponde a um dos tipos de terreno aqui
     * @return Caso encontre o pretendido retorna esse tipo de terreno caso contrario retorna null
     */
    public ITerrain matchSymbol(String symbol)throws NullPointerException{
        for(ITerrain terrain : terrains){
            if(terrain.getType().equals(symbol)){
                return terrain;
            }
        }
        return null;
    }

}
