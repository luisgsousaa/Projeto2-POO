package com.mycompany.projeto2.poo;

import java.io.IOException;
import java.util.Scanner;

public class StartMenu {
    private Map map;
    private static int playerNumbers;
    public StartMenu() {
        playerNumbers = 1;
    }
    
    
    public Map chooseMap()throws IOException{
        map = new Map();
        return map;
    }
    
    
    public Civilization chooseCivilization () {

        Scanner scanner = new Scanner(System.in);
        Civilization myCiv = null;

        while (myCiv == null) {

            System.out.println("\nEscolha uma civilizacao para o jogador " + playerNumbers + " desta lista ou escreva -1 para criar a sua.");

            for (int i = 0; i < Civilization.getCivNames().size(); i++) {
                System.out.println(i + ": " + Civilization.getCivNames().get(i));
            }

            int civ_num;

            try {
                civ_num = scanner.nextInt();
                scanner.nextLine();

                if (civ_num == -1) {
                    System.out.println("\nQual sera o nome da tua civilizacao?");
                    String civ_name = scanner.nextLine();
                    Civilization.addCivName(civ_name);
                    myCiv = new Civilization(civ_name,playerNumbers);
                } else {
                    myCiv = new Civilization(civ_num,playerNumbers);
                }

                System.out.println("\nO jogador "+  playerNumbers  +" vai jogar com a civilizacao " + myCiv.getName() + ".");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("\nNumero de civilizacao invalido. Tente novamente.");
                scanner.nextLine();
            }
        }
        playerNumbers++;
        return myCiv;
    }
    
    
    public City chooseFirstCity(Civilization civ){
        boolean success = false;
        CityBuilder builder = null;
        map.showMap();
        System.out.println("Escolha as coordenadas da cidade inicial do jogador " + civ.getNumber() + "\n Escolha a coordenada no eixo dos X, clique enter e depois escolha a do eixo dos Y");
        while(!success){
            try{
            
                int coordX,coordY;
                Scanner scanner = new Scanner(System.in);
                coordX = scanner.nextInt();
                coordY = scanner.nextInt();
                
            
                
                builder = new CityBuilder(coordX, coordY,map,civ.getNumber());
                
                return builder.createCity();
            }
            catch(CoordinatesNotSuitableException e){
                System.out.println("Tente novamente");
            }
        }
        
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}