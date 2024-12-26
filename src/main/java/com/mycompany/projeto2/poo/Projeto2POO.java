/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto2.poo;

import java.io.IOException;
import java.util.ArrayList;


public class Projeto2POO {

    private static GameMap gameMap;
    private static ArrayList<Civilization> civs;
    private static ArrayList<City> cities;
    private static int numberOfPlayers;

    private static int currentTurn = 1;
    public static int getCurrentTurn() {return currentTurn;}

    public static void firstTurn() { // metodo para o primeiro turno
        System.out.println("Inicio do turno " + Projeto2POO.getCurrentTurn());

        for (Civilization c : civs) {
            double totalMaintenanceCost = 0;
            for (Unit unit : c.getControlledUnits()) {
                if (unit.getMaintenanceCost() > 0) {
                    totalMaintenanceCost += unit.getMaintenanceCost();
                }
            }
            c.addGoldTreasure(-totalMaintenanceCost); // ja retira o custo de manuntencao das unidades existentes que tenham de ser pagas

            System.out.println("Recursos da Civilizacao " + c.getName() + " (Jogador " + c.getNumber() + "):");
            System.out.println("Ouro da civilizacao: " + c.getGoldTreasure());
            for (City city : c.getControlledCities()) {
                System.out.println("Cidade nas coordenadas (" + city.getCoordX() + ", " + city.getCoordY() + ")");
                System.out.println("  Ouro da civilizacao: " + city.getIndustrialResources());
                System.out.println("  Comida produzida no ciclo: " + city.getFoodResources());
                System.out.println("  Comida da reserva da cidade: " + city.getFoodReserve());
            }
        }
    }


    public static void nextTurn() { // tudo que acontece quando muda de turno
        currentTurn++;
        for (Civilization civ : civs) {
            double totalMaintenanceCost = 0;
            for (Unit unit : civ.getControlledUnits()) {
                unit.resetSteps(); // da reset nos passos usados por todas as unidades
                if (unit.getMaintenanceCost() > 0) {
                    totalMaintenanceCost += unit.getMaintenanceCost();
                }
                unit.resetAttacks();
                unit.resetHeals();
            }
            civ.addGoldTreasure(-totalMaintenanceCost);

            for (City city : civ.getControlledCities()) {
                city.resetFood();
                city.resetIndustrialResources();
                city.produceResourcesForCycle();
            }
        }
    }
    private static void removeEmptyCivs(){
        for(Civilization civ : civs){
                if(civ.getControlledCities().isEmpty()){
                    civs.remove(civ);
                }
            }
    }

    public static void main(String[] args) throws IOException {
        civs = new ArrayList<>();
        cities = new ArrayList<>();

        StartMenu startMenu = new StartMenu();

        //startMenu.startingText();

        numberOfPlayers = startMenu.chooseHowManyPlayers();

        GameMap gameMap = startMenu.chooseMap();
        MenuManager menuManager = new MenuManager(gameMap);

        for(int i = 0; i < numberOfPlayers; i++){
            civs.add(startMenu.chooseCivilization());
        }

        for(Civilization c : civs){
            cities.add(startMenu.chooseFirstCity(c));
        }

        Unit.createUnit("M", 23, 2, gameMap, Direction.NONE, civs.get(0));
        Unit.createUnit("M", 24, 2, gameMap, Direction.NONE, civs.get(1));
        Unit.createUnit("B", 25, 2, gameMap, Direction.NONE, civs.get(1));
        //Unit.createUnit("B", 23, 3, map, Direction.NONE, civs.get(1));
        //Unit.createUnit("B", 24, 3, map, Direction.NONE, civs.get(0));
        Unit.createUnit("S", 30, 3, gameMap, Direction.NONE, civs.get(1));
        Unit.createUnit("E", 25, 3, gameMap, Direction.NONE, civs.get(1));
        Unit.createUnit("E", 24, 4, gameMap, Direction.NONE, civs.get(0));

        gameMap.showMap();

        int currentPlayer = 0;
        boolean endGame = false; // no futuro isto tem de ser talvez um getter para um sitio que tem a verifica a condicao de vitoria

        firstTurn();


        // para terminar jogada clicar no 0 de sair e passa para o outro jogador
        while (!endGame) {
            System.out.println("\nVez: Jogador " + civs.get(currentPlayer).getNumber() + " (" + civs.get(currentPlayer).getName() + ")");
            menuManager.showMenuManager(civs.get(currentPlayer));
            currentPlayer = (currentPlayer + 1) % numberOfPlayers; // para ir passando 0,1,2 e depois voltar para  0,1,2 e assim sucessivamente graças ao resto da divisao


            // DO SEGUNDO TURNO PA FRENTE É ISTO QUE APARECE
            if (currentPlayer == 0) {
                Projeto2POO.nextTurn();
                gameMap.showMap();
                System.out.println("Fim do turno " + (Projeto2POO.getCurrentTurn() - 1));
                System.out.println("Inicio do turno " + Projeto2POO.getCurrentTurn());

                for (Civilization c : civs) {
                    System.out.println("Recursos da Civilizacao " + c.getName() + " (Jogador " + c.getNumber() + "):");
                    System.out.println("  Ouro da civilizacao: " + c.getGoldTreasure());
                    for (City city : c.getControlledCities()) {
                        System.out.println("Cidade nas coordenadas (" + city.getCoordX() + ", " + city.getCoordY() + ")");
                        System.out.println("  Recursos/Producao da cidade no ciclo: " + city.getIndustrialResources());
                        System.out.println("  Comida produzida no ciclo: " + city.getFoodResources());
                        System.out.println("  Comida da reserva da cidade: " + city.getFoodReserve());
                    }
                }

            }
            ArrayList<Civilization> copyCivs = civs;
            endGame = VictoryCondition.isEndGame(copyCivs);
        }

    }

}

