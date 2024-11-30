package com.mycompany.projeto2.poo;

import java.util.Scanner;

public class MenuMain {

    public MenuMain() {}

    public static Civilization chooseCivilization () {

        Scanner scanner = new Scanner(System.in);
        Civilization myCiv = null;

        while (myCiv == null) {

            System.out.println("\nEscolha uma civilizacao desta lista ou escreva -1 para criar a sua.");

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
                    myCiv = new Civilization(civ_name);
                } else {
                    myCiv = new Civilization(civ_num);
                }

                System.out.println("\nVai jogar com a civilizacao " + myCiv.getName() + ".");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("\nNumero de civilizacao invalido. Tente novamente.");
                scanner.nextLine();
            }
        }
        return myCiv;
    }
}