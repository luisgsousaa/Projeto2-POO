package com.mycompany.projeto2.poo;

public class Building {

private Map map; //Mudar o nome para mapa;
private float buildCost;
private int buildTime;
private float upkeepCost;
private Cell location;
private String name;

    public Building(Map map, float buildCost, int buildTime, float upkeepCost, Cell location) {
        this.map = map;
        this.buildCost = buildCost;
        this.buildTime = buildTime;
        this.upkeepCost = upkeepCost;
        this.location = location;
    }

     public Building(Map map, float buildCost, int buildTime, float upkeepCost, Cell location, City city) {
        this.map = map;
        this.buildCost = buildCost;
        this.buildTime = buildTime;
        this.upkeepCost = upkeepCost;
        this.location = location;
        city.addIndustrialResources((-1)*this.buildCost);
        city.getCiv().addBuilding(this);
    }

    public Map getMap() {
        return map;
    }

    // Setter for map
    public void setMap(Map map) {
        this.map = map;
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
        //Does nothing for now
    }





}
