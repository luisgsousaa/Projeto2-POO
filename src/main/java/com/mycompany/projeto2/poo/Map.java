/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

import java.io.IOException;

/**
 *
 * @author Admin
 */
public class Map {
   private static Cell[][] map; // mudar para celulas
   private int width;
   private int height;
   
   public Map() throws IOException{
       MapBuilder mapBuilder = new MapBuilder();
       map = mapBuilder.getMap();
       
       height = map[0].length;
       width = map.length;
       
       showMap();
       
       
       
       
   }
   
   
   
   public void showMap(){
        System.out.print("   "); //Espaço antes das coordenadas X para alinhar com as células do map
        
        for(int i = 0; i<width;i++){ // Coordenadas X
            System.out.print(i);
            coordinatesOffset(i); // offset para alinhar com as células devido aos números das dezenas
            
        }
        System.out.println(); // Linha a baixo
        
        for(int i = 0; i<height;i++){
            System.out.print(i); //Coordenadas Y antes de começar as células
            coordinatesOffset(i); //offset para alinhar as células devido aos números das dezenas
            
            
            for(int j = 0; j<width;j++){ //Impressão das células do map deixando um espaço entre estas.
                
                System.out.print(map[j][i].getTypeShown());
                System.out.print(" ");
            }
            System.out.println(); // linha a baixo
        }
    }
   
   private void coordinatesOffset(int i){
        if(i<10){
                System.out.print("  ");
            }
            else{
                System.out.print(" ");
            }
    }
   
    public Cell getCell(int x, int y){ // mudar para cell
        
        try{
            return map[x][y];
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    public void setCell(int x, int y, Cell cell){ // mudar para cell
        map[x][y] = cell;
    }
    
    public static Cell[][] getMap(){
        return map;
    }
}
