/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto2.poo;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Projeto2POO {

    private static Map map;

    public static void main(String[] args) throws IOException {

        StartMenu startMenu = new StartMenu();
        
        
        Map map = startMenu.chooseMap();
        Civilization civP1 = startMenu.chooseCivilization();
        Civilization civP2 = startMenu.chooseCivilization();
        
        City c1 = startMenu.chooseFirstCity(civP1);
        City c2 = startMenu.chooseFirstCity(civP2);
        
        map.showMap();
        /*
        

        Civilization civilization = menuMain.chooseCivilization();

        Unit.createUnit("M", 23, 2, map, Direction.NONE, civilization);
        Unit.createUnit("E", 20, 1, map, Direction.NONE, civilization);
        */
        
        
        
        /*
        MenuManager menuManager = new MenuManager(map);
        menuManager.showMenuManager();

        
        //teste unidades


        map.showMap();

        /*
        unit.moveUnit(map);
        unit2.moveUnit(map);

        map.showMap();

        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("direcao indica: ");
        input = scanner.nextLine().toUpperCase();

        Direction direction = Direction.valueOf(input); // converter por causa do enum

        unit.setDirection(direction);
        unit.moveUnit(map);

        unit2.setDirection(direction);
        unit2.moveUnit(map);

        map.showMap();


        // caso do militar que se pode mover duas celulas:
        // permitir ao utilizador escolher se quer se mover 1 ou 2 casas
        // caso escolha mover-se duas casas, unica opção é ser na mesma direção? indicar tipo UP, LEFT?
        // o metodo de movimento da unidade tem um loop com base nos steps por isso anda 2 de uma vez, depois ver se sera preciso adaptar dependendo do que decidir
        // enum mudar para L, R, U, D ou E, D, C, B


        /*


        */

    }

    






    

   



}
