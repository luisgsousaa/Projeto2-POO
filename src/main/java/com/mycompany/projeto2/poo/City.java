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
    private int population, maxNumWorkers;
    private final int STARTING_POPULATION = 3;
    private final int STARTING_FOOD_RESOURCES = 6;
    private final int FOOD_PER_PERSON = 2;
    private double foodReserve,goldResources, industrialResources, foodResources, foodNecessity;
    private final double  POPULATION_GROWTH_FOOD_MARGIN = 0.1; // Margem para crescer a população
    private int cityNumber;
    
    private int foodWorkers, industrialWorkers, goldWorkers;
    
    private String[] layers;
    private Map map;
    private final int coordX, coordY;
    
    
    
    
    public City(int x, int y,Map map,int cityNumber){ // encapsulamento?
        super("C ");
        setCityVariables(map,cityNumber);
        coordX = x;
        coordY = y;
        
        
        
        createCity();
        setFirstWorkers();
        
        // teste
        addWorkers(1,200);
        addWorkers(3,200);
        produceResourcesForCycle();
        
        System.out.println("Food " + foodResources);
        System.out.println("Gold " + goldResources);
        System.out.println("Industrial " + industrialResources);
        //teste
        
        
    } 
    
    private void setLayers(){
         layers = new String[4];
         layers[1]="g ";
         layers[2]="i ";
         layers[3]="f ";
    }
    
    private void createCity(){
                
        map.setCell(coordX,coordY,this);
        String newType = this.getType().trim() + cityNumber ;
        this.setTypeShown(newType);
        
        
        layers[0] = newType;
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    String symbol = map.getCellTypeShown(x+coordX,y+coordY);
                    if(!symbol.equals(layers[0]) && !symbol.equals(layers[1]) && !symbol.equals(layers[2])){
                        map.setCellTypeShown(x+coordX,y+coordY,layers[index]);
                        map.setCellBelongsToCity(x+coordX,y+coordY, true);
                        maxNumWorkers+= map.getCellMaxNumWorkers(x+coordX,y+coordY);
                        setProductionType(x+coordX,y+coordY,index);
                    }                
                }         
            }
            index++;  

        }
    }

    private void setProductionType(int x,int y,int index){
        switch(index){
            case 1:
                map.setCellToGoldProduction(x, y);
                break;
            case 2:
                map.setCellToIndustrialProduction(x, y);
                break;
            case 3:
                map.setCellToFoodProduction(x, y);
                break;
            
        }
    }
    
    private void setFirstWorkers(){
        addWorkers(1,1);
        addWorkers(2,1);
        addWorkers(3,1);

    }
    
    
    
    
       // meh
    private void loopThroughEntireCity(){
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    map.getCellType(x+coordX,y+coordY);
                    
                                   
                }         
            }
            index++;  

        }
    }
    
    
    

    private void setCityVariables(Map map,int cityNumber){
        population = STARTING_POPULATION;
        foodResources = STARTING_FOOD_RESOURCES;
      
        foodReserve = 0;
        industrialResources = 0;
        maxNumWorkers = 0;
        this.map=map;
        this.cityNumber = cityNumber;
        this.setBelongsToCity(true);
        updateFoodNecessity();
        setLayers();
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
    
    
    @Override
    public int getNumWorkers(){
        return foodWorkers + industrialWorkers + goldWorkers;
    }
    
    
    
    
    public boolean addWorkers(int layer, int quantity){ // 1 gold, 2 industry 3 food
        if(quantity < 0){
            return false;
        }
        
        int workersAdded = 0;
        for(int y = -1*layer ; y <= 1*layer; y++){
            for(int x = -1*layer ; x <= 1*layer; x++){
                
                if(map.getCellTypeShown(x+coordX,y+coordY).equals(layers[layer])){
                    
                    
                    int maxWorkers = map.getCellMaxNumWorkers(x+coordX,y+coordY);
                    
                    if(map.getCellNumWorkers(x+coordX,y+coordY) == maxWorkers){continue;}
                    else{
                        while(map.getCellNumWorkers(x+coordX,y+coordY) < maxWorkers){
                            if(workersAdded<quantity){
                                map.changeCellNumWorkers(x+coordX,y+coordY,+1);
                                workersAdded++;
                                countWorkers(layer,1);
                            }
                            else {break;}
                        }
                    }  
                } 
            }            
        }         
        

        return workersAdded > 0;
    }
    
    public boolean removeWorkers(int layer, int quantity){ // 1 gold, 2 industry 3 food
        if(quantity < 0){
            return false;
        }
        int workersRemoved = 0;
        
        for(int y = -1*layer ; y <= 1*layer; y++){
            for(int x = -1*layer ; x <= 1*layer; x++){
               
                if(map.getCellTypeShown(x+coordX,y+coordY).equals(layers[layer])){
                    
                    
                    
                    
                    if(map.getCellNumWorkers(x+coordX,y+coordY) == 0){continue;}
                    else{
                        while(map.getCellNumWorkers(x+coordX,y+coordY) > 0){
                            if(workersRemoved<quantity){
                                map.changeCellNumWorkers(x+coordX,y+coordY,-1);
                                workersRemoved++;
                                countWorkers(layer,-1);
                            }
                            else {break;}
                        }
                    }  
                } 
            }            
        }         
        

        return workersRemoved > 0;
    }
    
    
    private void countWorkers(int layer,int action){
        if(action != 1 && action != -1){
            return;
        }
        else{
           switch(layer){
            case 1:
                goldWorkers+=1*action;
                break;
            case 2:
                industrialWorkers+=1*action;
                break;
            case 3:
                foodWorkers+=1*action;
                break;
            } 
        }
        
    }
    
    private void produceResourcesForCycle(){
        final int CITY_SIZE = 3;
        for(int y = -1*CITY_SIZE ; y <= 1*CITY_SIZE; y++){
            for(int x = -1*CITY_SIZE ; x <= 1*CITY_SIZE; x++){
                Cell currentCell = map.getCell(x+coordX, y+coordY);
                if(currentCell.getNumWorkers() == 0){continue;}            
                else{
                    ProduceResources.chooseProduceType(currentCell,this);
                }
            }         
        }
    }
    
    public void addFoodResources(double a){foodResources+=a;}
    public void addIndustrialResources(double a){industrialResources+=a;}
    public void addGoldResources(double a){goldResources+=a;}
}
