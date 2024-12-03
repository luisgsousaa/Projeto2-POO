/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto2.poo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Projeto2POO {

    private static Map map;
    private static ArrayList<Civilization> civs;
    private static ArrayList<City> cities;
    private static int numberOfPlayers;


    public static void main(String[] args) throws IOException {

        civs = new ArrayList<>();
        cities = new ArrayList<>();

        StartMenu startMenu = new StartMenu();

        //startMenu.startingText();

        numberOfPlayers = startMenu.chooseHowManyPlayers();

        Map map = startMenu.chooseMap();
        MenuManager menuManager = new MenuManager(map);

        for(int i = 0; i < numberOfPlayers; i++){
            civs.add(startMenu.chooseCivilization());
        }

        for(Civilization c : civs){
            cities.add(startMenu.chooseFirstCity(c));
        }

        Unit.createUnit("M", 23, 2, map, Direction.NONE, civs.get(0));
        Unit.createUnit("M", 27, 3, map, Direction.NONE, civs.get(0));
        Unit.createUnit("B", 15, 7, map, Direction.NONE, civs.get(0));
        Unit.createUnit("E", 20, 1, map, Direction.NONE, civs.get(1));

        map.showMap();

        int currentPlayer = 0;
        boolean endGame = false; // no futuro isto tem de ser talvez um getter para um sitio que tem a verifica a condicao de vitoria

        // para terminar jogada clicar no 0 de sair e passa para o outro jogador
        while (!endGame) {
            System.out.println("Vez: Jogador " + civs.get(currentPlayer).getNumber() + " (" + civs.get(currentPlayer).getName() + ")");
            menuManager.showMenuManager(civs.get(currentPlayer));
            currentPlayer = (currentPlayer + 1) % numberOfPlayers; // para ir passando 0,1,2 e depois voltar para  0,1,2 e assim sucessivamente graças ao resto da divisao
        }



        //menuManager.showMenuManager();
        

        for(Civilization c : civs){
            c.printControlled();
        }
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
