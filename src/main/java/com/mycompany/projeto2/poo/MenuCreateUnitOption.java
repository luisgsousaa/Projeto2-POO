package com.mycompany.projeto2.poo;

import java.util.Map;
import java.util.Scanner;

public class MenuCreateUnitOption implements MenuOption {

    @Override
    public String getDescription() {return "Criar unidade";}

    @Override
    public void execute(Scanner scanner, Civilization civilization, GameMap gameMap) {

        Civilization.showControlledCities(civilization); // mostra as cidades controladas pela civilizacao

        System.out.println("\nIndique a cidade onde pretende criar unidade(s):");

        try {
            int cityIndex = Integer.parseInt(scanner.nextLine().trim());

            if (cityIndex < 1 || cityIndex > civilization.getControlledCities().size()) {
                System.out.println("\nNumero invalido. Tente novamente.");
                return;
            }

            City city = civilization.getControlledCities().get(cityIndex - 1);  // obtem a cidade escolhida

            System.out.println("\nRecursos dessa cidade: " + city.getIndustrialResources());

            System.out.println("\nEscolha o tipo de unidade a ser criada (escreva a primeira letra do seu nome):");
            showAvailableUnits();

            String unitType = scanner.nextLine().trim().toUpperCase();

            UnitFactory selectedFactory = UnitFactoryRegistry.getFactories().get(unitType);

            if (selectedFactory == null) {
                System.out.println("\nUnidade desconhecida. Tente novamente.");
                return;
            }

            int productionCost = selectedFactory.getProductionCost();
            if (city.getIndustrialResources() < productionCost) {
                System.out.println("\nNao tem recursos suficientes para criar esta unidade.");
                return;
            }

            System.out.println("\nIndique as coordenadas (x,y) onde pretende criar a unidade:");
            String[] coordinates = scanner.nextLine().split(",");

            if (coordinates.length != 2) {
                System.out.println("Formato invalido. Por favor, insira as coordenadas no formato x,y.");
                return;
            }

            try {

                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);


                Direction direction = Direction.NONE;
                Unit newUnit = Unit.createUnit(unitType, x, y, gameMap, direction, civilization);

                if (newUnit != null) {
                    System.out.println("A unidade " + newUnit.getUnitName() + " foi criada nas coordenadas (" + x + ", " + y + ").");
                    city.setIndustrialResources(city.getIndustrialResources() - productionCost);
                    gameMap.showMap();
                } else {
                    System.out.println("A criacao da unidade falhou.");
                }



            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. As coordenadas devem ser numeros inteiros.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Escolha um indice da lista");
        }

    }

    private void showAvailableUnits() {
        Map<String, UnitFactory> availableUnits = UnitFactoryRegistry.getFactories();
        for (Map.Entry<String, UnitFactory> entry : availableUnits.entrySet()) {
            UnitFactory unitFactory = entry.getValue();
            System.out.printf("Unidade: %s - Custo: %d \n", unitFactory.getUnitName(), unitFactory.getProductionCost());
        }
    }

}
