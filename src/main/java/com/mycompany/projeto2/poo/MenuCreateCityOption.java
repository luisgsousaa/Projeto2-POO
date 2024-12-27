package com.mycompany.projeto2.poo;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * jogador escolhe uma unidade colonizadora, selecione a direção para fundar uma cidade e realiza a construção da cidade.
 */
public class MenuCreateCityOption implements MenuOption {

    @Override
    public String getDescription() {return "Criar cidade";}

    @Override
    public void execute(Scanner scanner, Civilization civilization, GameMap gameMap) {

        // vai buscar unidades que podem fundar cidades
        ArrayList<Unit> colonizerUnits = new ArrayList<>();
        for (Unit unit : civilization.getControlledUnits()) {
            if (unit.isColonizer()) {
                colonizerUnits.add(unit);
            }
        }

        if (colonizerUnits.isEmpty()) {
            System.out.println("A sua civilizacao nao tem unidades colonizadoras.");
            return;
        }


        System.out.println("Escolha uma unidade colonizadora:");
        for (int i = 0; i < colonizerUnits.size(); i++) {
            Unit unit = colonizerUnits.get(i);
            System.out.printf("%d - %s (%d, %d)\n", i + 1, unit.getType() + unit.getUnitCivNum(), unit.getCoordX(), unit.getCoordY());
        }


        int choice = -1;
        while (choice < 1 || choice > colonizerUnits.size()) {
            choice = scanner.nextInt();
        }

        Unit selectedUnit = colonizerUnits.get(choice - 1);


        System.out.println("Onde deseja construir a cidade?");
        System.out.println("1 - Em cima");
        System.out.println("2 - À esquerda");
        System.out.println("3 - À direita");
        System.out.println("4 - Em baixo");
        int creationCordsDir = -1;
        while (creationCordsDir < 1 || creationCordsDir > 4) {
            creationCordsDir = scanner.nextInt();
        }


        int newCoordX = selectedUnit.getCoordX();
        int newCoordY = selectedUnit.getCoordY();

        switch (creationCordsDir) {
            case 1:
                newCoordY -= 4;
                break;
            case 2:
                newCoordX -= 4;
                break;
            case 3:
                newCoordX += 4;
                break;
            case 4:
                newCoordY += 4;
                break;
        }

        CityBuilder cityBuilder = new CityBuilder(newCoordX, newCoordY, gameMap, civilization.getNumber());
        try {
            if (civilization.getGoldTreasure() < 600) {
                System.out.println("Nao tem ouro suficiente para construir a cidade.");
                return;
            }

            City newCity = cityBuilder.createCity(civilization);
            if (newCity != null) {
                civilization.addGoldTreasure(-600);
                System.out.println("Cidade construida com sucesso.");
            }
        } catch (CoordinatesNotSuitableException e) {
            System.out.println("Nao foi possível construir a cidade aqui. Escolha outra localizacao.");
        }
    }

}
