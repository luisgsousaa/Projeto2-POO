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
   /**
    * Cria o mapa e apresenta-o na consola
    */
   public Map() throws IOException{
       MapBuilder mapBuilder = new MapBuilder();
       map = mapBuilder.getMap();
       
       height = map[0].length;
       width = map.length;
       
       showMap();
       
       
       
       
   }
   /**
    * Função que imprime o mapa na consola, mostrando os simbolos de cada célula, percorre o array bidimensional para apresentar o mapa e escreve as coordenadas
    * x e y para facilitar a visualização
    */
   public void showMap(){

       System.out.println(); // pa separar do input

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
                //System.out.print(map[j][i].getNumWorkers()); //teste ------------------------------------------------------------------------
                System.out.print(" ");
            }
            System.out.println(); // linha a baixo
        }
    }
   
   /**
    * Dá um espaço maior ou mais pequeno consoante o número das coordenadas, é usado para as dezenas para manter o alinhamento dos números e das células
    * @param i número apresentado, usado para determinar se é necessario 2 espaços ou apenas 1.
    */
   private void coordinatesOffset(int i){
        if(i<10){
                System.out.print("  ");
            }
            else{
                System.out.print(" ");
            }
    }
   /**
    * Retorna a célula das coordenadas passadas como parametro
    * @param x coordenada x da célula pretendida
    * @param y coordenada y da célula pretendida
    * @return célula pretendida
    */
    public Cell getCell(int x, int y){
        
        try{
            return map[x][y];
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    public static Cell getCellStatic(int x, int y){
        
        try{
            return map[x][y];
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    
    
    /**
     * Getters e Setters para gerir o mapa
     */
    public int getWidth(){return map.length;}
    public int getHeight(){return map[0].length;}
    public int getCellEntryCost(int x, int y){return map[x][y].getEntryCost();}
    public boolean getCellIsSomethingOnTop(int x, int y){return map[x][y].isSomethingOnTop();}   
    public boolean getCellBelongsToCity(int x, int y){return map[x][y].getBelongsToCity();}
    public String getCellTypeShown(int x, int y){return map[x][y].getTypeShown();}
    public String getCellType(int x, int y){return map[x][y].getType();}
    public int getCellMaxNumWorkers(int x, int y){return map[x][y].getMaxNumWorkers();}
    public int getCellNumWorkers(int x, int y){return map[x][y].getNumWorkers();}
    
    
    public void setCell(int x, int y, Cell cell){map[x][y] = cell;}
    public void setCellTypeShown(int x, int y, String type){map[x][y].setTypeShown(type);}
    public void setCellBelongsToCity(int x, int y, boolean bool){map[x][y].setBelongsToCity(bool);}
    public void setCellToGoldProduction(int x,int y){map[x][y].setToGoldProduction();}
    public void setCellToIndustrialProduction(int x,int y){map[x][y].setToIndustrialProduction();}
    public void setCellToFoodProduction(int x,int y){map[x][y].setToFoodProduction();}
    public void changeCellNumWorkers(int x,int y,int num){map[x][y].changeNumWorkers(num);}
    
    
    
}
