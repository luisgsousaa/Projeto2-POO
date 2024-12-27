package com.mycompany.projeto2.poo;

import java.util.*;

public class Building {

private double buildCost;
private int buildTime;
private double upkeepCost;
private Cell location;
private String name;
private int type;
private City city;

    



    public int getType() {
        return type;
    }

    public static int countType(ArrayList<Building> buildings, int type) {
        if(buildings == null) {
            return 0;
        }
        int count = 0;
         for (int i = 0; i < buildings.size(); i++) {
            if(buildings.get(i).getType() == type) {
                count = count + 1;
            }
        }
        return count;
    }

    

     public Building(Cell location, City city, BuildingType type) {

        this.buildCost = type.getBuildCost();
        this.buildTime = type.getBuildTime();
        this.upkeepCost = type.getUpkeepCost();
        this.location = location;
        this.type = type.getId();
        this.city = city;
        
    }

    
    public void applyUpkeepCost() {
        if(this.buildTime < 0) {
            this.city.addGoldToCivTreasure((-1)*this.upkeepCost);
            
        }
    }
    public void decBuildTime() {
        this.buildTime -= 1;
        //It's acknowledged that this variable can go under zero. It serves as a 'buildingAge' variable after that happens
    }
    
    public void processTurn() {
        this.applyUpkeepCost();
        this.decBuildTime();
    }

    // Getter for buildCost
    public double getBuildCost() {
        return this.buildCost;
    }

    // Setter for buildCost
    public void setBuildCost(float buildCost) {
        this.buildCost = buildCost;
    }

    // Getter for buildTime
    public int getBuildTime() {
        return this.buildTime;
    }

    // Setter for buildTime
    public void setBuildTime(int buildTime) {
        this.buildTime = buildTime;
    }

    // Getter for upkeepCost
    public double getUpkeepCost() {
        return this.upkeepCost;
    }

    // Setter for upkeepCost
    public void setUpkeepCost(float upkeepCost) {
        this.upkeepCost = upkeepCost;
    }

    





}
