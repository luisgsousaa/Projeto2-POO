package com.mycompany.projeto2.poo;

import java.util.Scanner;

public class MenuManager {

    private Map map;

    public MenuManager(Map map) {
        this.map = map;
    }

    public void showMenuManager() {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Mover Unidade");
            System.out.println("2 - nada");
            System.out.println("3 - nada");
            System.out.println("4 - nada");
            System.out.println("0 - Sair");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    optionMoveUnit(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 0);
        scanner.close();

    }


    private void optionMoveUnit(Scanner scanner) {

        System.out.println("\nInsira as coordenadas da unidade que deseja mover separado por vírgulas:");
        String[] coords_array = scanner.nextLine().split(","); // slipt serve para dividir string que tem separadores (no caso virgulas) em arrays, por isso vamos ter um array de 2 elementos para cada coordenada

        if (coords_array.length != 2) { // se o array nao tiver 2 elementos é pq algo ta mal
            System.out.println("\nCoordenadas inválidas. Tente novamente.");
            return;
        }

        try {
            // cobre o caso do utilizador fazer '  4   ,   20   ', retira espaços em branco e converte para int
            int x = Integer.parseInt(coords_array[0].trim());
            int y = Integer.parseInt(coords_array[1].trim());

            if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
                System.out.println("\nCoordenadas fora dos limites do mapa. Tente novamente.");
                return;
            }

            Cell cell = map.getCell(x, y);
            Unit unit = cell.getUnit();

            if (cell.getUnit() == null) {
                System.out.println("\nEssas coordenadas não são de uma unidade. Tente novamente.");
                return;
            }

            if (unit.getSteps() == 0) {
                System.out.println("\nEssa unidade é fixa. Tente novamente.");
                return;
            }

            int initialX = x;
            int initialY = y;

            if (unit.getSteps() == 1) {

                System.out.println("\nIndique a direção do passo.");
                String letter = scanner.nextLine().toLowerCase().trim();

                if (letter.length() != 1) {
                    System.out.println("\nInsira apenas uma direção. Tente novamente.");
                    return;
                }

                Direction step_dir = inputToEnumDirection(letter);

                if (step_dir != null) {
                    unit.setDirection(step_dir);
                    unit.moveUnit(map);
                } else {
                    System.out.println("\nDireção inválida: " + letter + ". Utilize apenas 'c', 'b', 'e' ou 'd'.");
                    return;
                }
            }

            if (unit.getSteps() > 1) {

                System.out.printf("\nPode dar até %d passos. Indique a direção do(s) passo(s) que pretende fazer sem espaços:%n", unit.getSteps()); //%n é para ir pa outra linha, no println e nao é preciso pq ja faz automaticamente
                String input_dirs = scanner.nextLine().toLowerCase().trim();

                if (input_dirs.length() > unit.getSteps() || input_dirs.length() <= 0) {
                    System.out.println("\nNúmero de direções inválido. Tente novamente.");
                    return;
                }

                for (char letter : input_dirs.toCharArray()) { // separa caracteres do input tipo cbee = c b e e

                    Direction step_dir = inputToEnumDirection(String.valueOf(letter));

                    if (step_dir != null) {
                        unit.setDirection(step_dir);
                        unit.moveUnit(map);
                    } else {
                        System.out.println("\nDireção inválida: " + letter + ". Utilize apenas 'c', 'b', 'e' ou 'd'.");
                        return;
                    }
                }
            }

            if (unit.getCoordX() == initialX && unit.getCoordY() == initialY) {
                System.out.println("\nA unidade permaneceu nas mesmas coordenadas.");
                return;
            }

            System.out.println("\nUnidade movida com sucesso.");
            map.showMap();

        } catch (NumberFormatException e) {
            System.out.println("\nCoordenadas inválidas. Tente novamente.");
        }
    }

    private Direction inputToEnumDirection(String input) {
        switch (input) {
            case "c": return Direction.UP;
            case "b": return Direction.DOWN;
            case "e": return Direction.LEFT;
            case "d": return Direction.RIGHT;
            default: return null;
        }
    }


}
