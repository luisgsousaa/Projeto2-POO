/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;
import java.util.*;

/**
 *
 * @author User
 */
public class BuildingType {

    private double buildCost;
    private int buildTime;
    private double upkeepCost;
    private String name;
    private int id;
    
    private static ArrayList<BuildingType> types = new ArrayList<>();
    public static ArrayList<BuildingType> getTypes() {return types;};
    
    public BuildingType(int id, double buildCost, int buildTime, double upkeepCost, String name) {
        this.buildCost = buildCost;
        this.buildTime = buildTime;
        this.upkeepCost = upkeepCost;
        this.name = name;
        this.id = id;
    }
    
    public double getBuildCost() {
        return this.buildCost;
    }
    public int getBuildTime() {
        return this.buildTime;
    }
    public int getId() {
        return this.id;
    }
    public double getUpkeepCost() {
        return this.upkeepCost;
    }
    public String getName() {
        return this.name;
    }
    
    public static void init() {
    
    types.add(new BuildingType(0,5.0,3,5.0,"Type-0"));
    types.add(new BuildingType(1,10.0,4,7.5,"Type-1"));
    
    
    
    
    
    
}
    
    
}
