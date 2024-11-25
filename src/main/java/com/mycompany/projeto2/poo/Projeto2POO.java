/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto2.poo;

import java.io.IOException;

/**
 *
 * @author Admin
 */
public class Projeto2POO {
    private static Map map;
    public static void main(String[] args) throws IOException {
        map = new Map();
        
        
        
        
        //teste provisorio
        
        int coordX = 8;
        int coordY = 8;
        
        if(coordX<3 || coordX > (map.getMap().length-4) || coordY<3 || coordY>(map.getMap()[0].length-4)){
            System.out.println("O centro da cidade tem de estar a pelo menos 4 celulas de distancia das bordas do mapa");
        }
        else if(!canBuildCity(coordX,coordY)){
            System.out.println("A cidade nao pode estar em cima de agua escolha outras coordenadas");
        }
        else{
            City cidade1 = new City(coordX,coordY,map.getMap(),1);
        
            
        
            map.showMap();
        }
    }
    
    
    private static boolean canBuildCity(int coordX,int coordY){
        int index = 1;
        Cell[][] world = map.getMap();
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    if(world[x+coordX][y+coordY].getEntryCost() == -1){
                        return false;
                    }
                    
                                   
                }         
            }
            index++;  

        }
        return true;
    }
}
