<<<<<<< Updated upstream
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class City extends Cell{
    private int population;
    private final int STARTING_POPULATION = 3;
    private final int STARTING_FOOD_RESOURCES = 6;
    private final int FOOD_PER_PERSON = 2;
    private double foodReserve, industrialResources, foodResources, foodNecessity;
    private final double  POPULATION_GROWTH_FOOD_MARGIN = 0.1; // Margem para crescer a população
    private int cityNumber;
    
    private Cell[][] cityCells;
    private Cell[][] map;
    
    private int coordX, coordY;
    
    public City(int x, int y,Cell[][] map,int cityNumber){ // encapsulamento?
        super("C ");
        setCityVariables();
        coordX = x;
        coordY = y;
        this.map=map;
        this.cityNumber = cityNumber;
        this.setBelongsToCity(true);
        createCity();
    } 
    
    private void createCity(){
                
        map[coordX][coordY] = this;
        String newType = this.getType().trim() + cityNumber ;
        this.setTypeShown(newType);
        
        
        String[] layers = {newType,"B ","A ","D "};
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    String symbol = map[x+coordX][y+coordY].getTypeShown();
                    if(!symbol.equals(layers[0]) && !symbol.equals(layers[1]) && !symbol.equals(layers[2])){
                        map[x+coordX][y+coordY].setTypeShown(layers[index]);
                        map[x+coordX][y+coordY].setBelongsToCity(true);
                        if(index == 3){
                            
                            
                        }
                    }                
                }         
            }
            index++;  

        }
    }
    
    private void loopThroughEntireCity(){
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    map[x+coordX][y+coordY].getType();
                    
                                   
                }         
            }
            index++;  

        }
    }

    private void setCityVariables(){
        population = STARTING_POPULATION;
        foodResources = STARTING_FOOD_RESOURCES;
      
        foodReserve = 0;
        industrialResources = 0;      
        updateFoodNecessity();
    }
    
    private void updateFoodNecessity(){
        foodNecessity = population * FOOD_PER_PERSON;
    }
    
    
    private void populationGrowth(){ // ao fim do turno
        double abundance = foodNecessity * (POPULATION_GROWTH_FOOD_MARGIN + 1);
        if(foodReserve > abundance ){
            population+=2;
        }
    }
    
    private void populationDecrease(){ // ao fim do turno
        if(foodReserve < 0){
            population-=1;
        }
        foodReserve = 0; // de modo a que nunca fique 0 aos olhos do user
    }
    
    public int getX(){return coordX;}
    public int getY(){return coordY;}
    


}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class City extends Cell{
    private int population;
    private final int STARTING_POPULATION = 3;
    private final int STARTING_FOOD_RESOURCES = 6;
    private final int FOOD_PER_PERSON = 2;
    private double foodReserve, industrialResources, foodResources, foodNecessity;
    private final double  POPULATION_GROWTH_FOOD_MARGIN = 0.1; // Margem para crescer a população
    private int cityNumber;

    private Cell[][] cityCells;
    private Cell[][] map;

    private int coordX, coordY;

    public City(int x, int y,Cell[][] map,int cityNumber){ // encapsulamento?
        super("C ");
        setCityVariables();
        coordX = x;
        coordY = y;
        this.map=map;
        this.cityNumber = cityNumber;
        this.setBelongsToCity(true);
        createCity();
    }

    private void createCity(){

        map[coordX][coordY] = this;
        String newType = this.getType().trim() + cityNumber ;
        this.setTypeShown(newType);


        String[] layers = {newType,"B ","A ","D "};
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    String symbol = map[x+coordX][y+coordY].getTypeShown();
                    if(!symbol.equals(layers[0]) && !symbol.equals(layers[1]) && !symbol.equals(layers[2])){
                        map[x+coordX][y+coordY].setTypeShown(layers[index]);
                        map[x+coordX][y+coordY].setBelongsToCity(true);
                        if(index == 3){


                        }
                    }
                }
            }
            index++;

        }
    }

    private void loopThroughEntireCity(){
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    map[x+coordX][y+coordY].getType();


                }
            }
            index++;

        }
    }

    private void setCityVariables(){
        population = STARTING_POPULATION;
        foodResources = STARTING_FOOD_RESOURCES;

        foodReserve = 0;
        industrialResources = 0;
        updateFoodNecessity();
    }

    private void updateFoodNecessity(){
        foodNecessity = population * FOOD_PER_PERSON;
    }


    private void populationGrowth(){ // ao fim do turno
        double abundance = foodNecessity * (POPULATION_GROWTH_FOOD_MARGIN + 1);
        if(foodReserve > abundance ){
            population+=2;
        }
    }

    private void populationDecrease(){ // ao fim do turno
        if(foodReserve < 0){
            population-=1;
        }
        foodReserve = 0; // de modo a que nunca fique 0 aos olhos do user
    }

    public int getX(){return coordX;}
    public int getY(){return coordY;}



}
>>>>>>> Stashed changes
