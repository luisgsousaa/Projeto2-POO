/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author User
 */
public class MenuCreateBuildingOption implements MenuOption {
    @Override
    public String getDescription() {
        return "Criar edifício";
    }
    @Override
    public void execute(Scanner scanner, Civilization civ, GameMap gameMap) {
        ArrayList<Unit> temp = civ.getControlledUnits();
        
        if(temp == null) {
            System.out.println("Não tens nenhuma unidade construtora");
            return;
        };
        
        ArrayList<Unit> builders = new ArrayList<Unit>();
        
        for(int i=0;i<temp.size();i++) {
            if(temp.get(i).getClass().getSimpleName().equals("UnitBuilder")) {
                builders.add(temp.get(i));
            }
            
        }
        if(builders == null || builders.size() == 0) {
            System.out.println("Não tens nenhuma unidade construtora");
            return;
        };
        
        try{
                System.out.println("Escolha as coordenadas para a nova construção");
                int coordX,coordY;
                System.out.println("Coordenada X: ");
                coordX = scanner.nextInt();
                System.out.println("Coordenada Y: ");
                coordY = scanner.nextInt();
                
                Cell tempcell = gameMap.getCell(coordX,coordY);
                //System.out.println(tempcell.getType());
                if(tempcell.getType().equals("# ") || tempcell.getType().equals("C ")) {
                    System.out.println("Terreno inadequado");
                    return;
                }
                
                
                
                
                
            boolean foundvalidbuilder = false;
            for(int i=0;i<builders.size();i++) {
                
                Unit temp2 = builders.get(i);
                if((Math.abs(temp2.getCoordX()-coordX) < 4 && Math.abs(temp2.getCoordY()-coordY) < 4) || GameMap.taxiCabDistanceTo(temp2.getCoordX(),temp2.getCoordY(),coordX,coordY,gameMap.getWidth(),gameMap.getHeight()) < 4) { //Este cálculo de distância tem de ser atualizado para considerar a circularidade do mapa
                    foundvalidbuilder = true;
                    break;
                }
            
            }
            if(!foundvalidbuilder) {
                throw new CoordinatesNotSuitableException();
            }
                System.out.println("Indique o tipo de construcao");
                ArrayList<BuildingType> bTypes = BuildingType.getTypes();
                
                for(int j=0;j<bTypes.size();j++) {
                    String text = Integer.toString(j) + " - " + bTypes.get(j).getName();
                    System.out.println(text);
                }
                
                
                boolean success = false;
                
                //TODO -> ADD TERRAIN CHECK
                int choice = scanner.nextInt();
                
                for(int i=0;i<BuildingType.getTypes().size();i++) {
                    
                    if(choice == i) {
                        System.out.println(i);
                        BuildingType temp2 = BuildingType.getTypes().get(i);
                        for(int k =0;k<civ.getControlledCities().size();k++) {
                            City tempcity = civ.getControlledCities().get(i);
                            try {
                            if(tempcity.getResource("industrial") >= temp2.getBuildCost()) {
                                tempcity.addIndustrialResources(temp2.getBuildCost()*(-1));
                                Building b = new Building(gameMap.getCell(coordX,coordY),tempcity,temp2);
                                
                                civ.addBuilding(b);
                                success = true;
                                break;
                            } else {
                                //System.out.println(tempcity.getResource("industrial"));
                                continue;
                            }
                            } catch (Exception e) {
                                //This will never happen
                            };
                        }
                        
                        if(success) {
                            System.out.println("Construção iniciada com sucesso");
                            gameMap.showMap();
                        } else {
                            System.out.println("Recursos insuficientes");
                        }
                        
                        
                        break;
                    }
                }
                
                
                
                
                
            }
        
            catch(CoordinatesNotSuitableException e){
                System.out.println("Coordenadas inadequadas");
            }
            catch(InputMismatchException e){
                System.out.println("Introduza numeros inteiros para as coordenadas");
            }
    }
}

