package com.mycompany.projeto2.poo;

import java.util.*;

public class Building {

private float buildCost;
private int buildTime;
private float upkeepCost;
private Cell location;
private String name;
private int type;
private City city;

    private static ArrayList<HashMap<String, Object>> types = new ArrayList<>();
    
public static void init() {
    
    HashMap<String,Object> temp = new HashMap<>();
    temp.put("buildCost",5.0f);
    temp.put("buildTime",Integer.valueOf(3));
    temp.put("upkeepCost",5.0f);
    temp.put("name","Type-0");
    
    types.add(temp);
    
    HashMap<String,Object> temp2 = new HashMap<>();
    temp2.put("buildCost",10.0f);
    temp2.put("buildTime",Integer.valueOf(4));
    temp2.put("upkeepCost",7.5f);
    temp2.put("name","Type-1");
    
    types.add(temp2);
    
}
public static ArrayList<HashMap<String, Object>> getTypes() {
    return types;
}


public static final int maxtype = 1;

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

    /*public Building(float buildCost, int buildTime, float upkeepCost, Cell location, int type) {

        this.buildCost = buildCost;
        this.buildTime = buildTime;
        this.upkeepCost = upkeepCost;
        this.location = location;
        this.type = type;
    }*/

     public Building(float buildCost, int buildTime, float upkeepCost, Cell location, City city, int type) {

        this.buildCost = buildCost;
        this.buildTime = buildTime;
        this.upkeepCost = upkeepCost;
        this.location = location;
        this.type = type;
        this.city = city;
        
        try{
        if(city.getResource("industrial") >= this.buildCost) {
            city.addIndustrialResources((-1)*this.buildCost);
            city.getCiv().addBuilding(this);
            location.setSomethingOnTop(true);
            location.setPreviousTypeShown(location.getTypeShown());
            location.setTypeShown("D");
        } else {
            System.out.println("Insufficient Resources"); //Péssima prática mas neste caso dá
        }
        } catch(Exception e) {
            //This WILL (not should) never happen
        }
        
        
    }

    
    public void applyUpkeepCost() {
        if(this.buildTime < 0) {
            this.city.addGoldResources((-1)*this.upkeepCost);
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
    public float getBuildCost() {
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
    public float getUpkeepCost() {
        return this.upkeepCost;
    }

    // Setter for upkeepCost
    public void setUpkeepCost(float upkeepCost) {
        this.upkeepCost = upkeepCost;
    }

    // toString method to return a string representation of the object
    @Override
    public String toString() {
        return "A";
        //Does nothing for now
    }





}
