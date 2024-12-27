/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto2.poo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Projeto2POO {

    private static GameMap gameMap;
    private static ArrayList<Civilization> civs;
    private static ArrayList<City> cities;
    private static int numberOfPlayers;
    private static int currentTurn = 1;


    /**
     * Retorna turno atual
     */
    public static int getCurrentTurn() {return currentTurn;}




    /**
     * Inicializa o primeiro turno do jogo.
     */
    public static void firstTurn() { // metodo para o primeiro turno
        System.out.println("Inicio do turno " + Projeto2POO.getCurrentTurn());

        for (Civilization c : civs) {
            double totalMaintenanceCost = 0;
            for (Unit unit : c.getControlledUnits()) {
                if (unit.getMaintenanceCost() > 0) {
                    totalMaintenanceCost += unit.getMaintenanceCost();
                }
            }

            System.out.println("Recursos da Civilizacao " + c.getName() + " (Jogador " + c.getNumber() + "):");
            System.out.println("Ouro da civilizacao: " + c.getGoldTreasure());
            for (City city : c.getControlledCities()) {
                city.setFirstWorkers();
                System.out.println("Cidade nas coordenadas (" + city.getCoordX() + ", " + city.getCoordY() + ")");
                System.out.println("  Ouro da civilizacao: " + city.getIndustrialResources());
                System.out.println("  Comida produzida no ciclo: " + city.getFoodResources());
                System.out.println("  Comida da reserva da cidade: " + city.getFoodReserve());
            }
            c.addGoldTreasure(-totalMaintenanceCost); // ja retira o custo de manuntencao das unidades existentes que tenham de ser pagas
        }
    }


    /**
     * Executa as ações relacionadas à mudança de turno. atualiza o estado das civilizações, unidades e cidades
     */
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

                int population = city.getPopulation();
                int workers = city.getNumWorkers();

                if (workers < population) {
                    int remainingWorkers = population - workers;
                    distributeWorkers(city, remainingWorkers, civ);
                }

                city.populationGrowth();
                city.populationDecrease();
            }
        }
    }


    /**
     * Remove civilizacoes vazias
     */
    private static void removeEmptyCivs(){
        for(Civilization civ : civs){
                if(civ.getControlledCities().isEmpty()){
                    civs.remove(civ);
                }
            }
    }

    /**
     * Distribui trabalhadores nas camadas de uma cidade
     *
     * @param city - city onde os trabalhadores serão distribuídos
     * @param remainingWorkers  - nr de pessoas restantes que podem ser metidas a trabalhar
     */
    private static void distributeWorkers(City city, int remainingWorkers, Civilization civilization) {
        Scanner scanner = new Scanner(System.in);

        while (remainingWorkers > 0) {
            try {
                System.out.println("\nJogador: " + civilization.getNumber() + " (" + civilization.getName() + ") | Pessoas disponveis para trabalhar: " + remainingWorkers);
                System.out.println("\nDeseja distribuir trabalhadores numa funcao/camada especifica? (1 - Sim, 0 - Não)");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("Selecione a camada onde deseja colocar os trabalhadores: (1 - Ouro, 2 - Indústria, 3 - Comida)");
                    int layer = scanner.nextInt();

                    if (layer < 1 || layer > 3) {
                        System.out.println("Camada invalida. Escolha entre 1 (Ouro), 2 (Indústria) ou 3 (Comida).");
                        continue;
                    }

                    System.out.println("Qual o numero de trabalhadores que deseja adicionar?");
                    int quantity = scanner.nextInt();

                    if (quantity <= 0) {
                        System.out.println("Entrada invalida. O numero de trabalhadores nao pode ser negativo.");
                        continue;
                    }

                    if (quantity > remainingWorkers) {
                        System.out.println("Colocou um numero superior as pessoas disponiveis para trabalhar. O valor foi ajustado para " + remainingWorkers + ".");
                        quantity = remainingWorkers;
                    }

                    if (city.addWorkers(layer, quantity)) {
                        remainingWorkers -= quantity;
                        System.out.println(quantity + " trabalhadores foram adicionados a camada " + layer);
                    } else {
                        System.out.println("Nao foi possivel adicionar trabalhadores a camada " + layer + ".");
                    }
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Entrada invalida. Escreva 1 para sim ou 0 para nao.");
                }
            } catch (Exception e) {
                System.out.println("Entrada invalida. Escreva apenas numeros inteiros.");
                scanner.nextLine();
            }
        }
    }









    public static void main(String[] args) throws IOException {
        civs = new ArrayList<>();
        cities = new ArrayList<>();

        StartMenu startMenu = new StartMenu();

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
        boolean endGame = false;

        firstTurn(); // executa acoes do primeiro turno

        while (!endGame) { // loop ate ao final do jogo
            System.out.println("\nVez: Jogador " + civs.get(currentPlayer).getNumber() + " (" + civs.get(currentPlayer).getName() + ")");
            menuManager.showMenuManager(civs.get(currentPlayer));
            currentPlayer = (currentPlayer + 1) % numberOfPlayers; // para ir passando 0,1,2 e depois voltar para  0,1,2 e assim sucessivamente graças ao resto da divisao

            if (currentPlayer == 0) {
                Projeto2POO.nextTurn(); // executa acoes periodicas do segundo turno para a frente
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

