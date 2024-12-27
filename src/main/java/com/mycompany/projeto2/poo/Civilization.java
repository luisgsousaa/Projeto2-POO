package com.mycompany.projeto2.poo;

import java.util.ArrayList;

/**
 * Classe que representa uma civilização dentro do jogo
 * Cada civilização controla unidades, cidades, e tem um tesouro de ouro
 */
public class Civilization {

    private String name; /// nome da civilizacao
    private int number; /// numero da civ que tbm pode nr do jogador
    private ArrayList<City> controlledCities; /// ArrayList de cidades controladas pela civilização
    private ArrayList<Unit> controlledUnits; /// ArrayList de unidades controladas pela civilização
    private static ArrayList<String> civNames = new ArrayList<>(); ///// ArrayList estático com os nomes predefinidos de civilizações
    private double GoldTreasure; /// quantidade de ouro que a civilizacao possui

    /// Bloco estático que inicializa a lista com nomes de civilizações predefinidas
    static {
        civNames.add("Estepilhas");
        civNames.add("Lambricas");
        civNames.add("Fagulhas");
        civNames.add("Bragados");
        civNames.add("Tarugos");
        civNames.add("Cangalhos");
    }

    /**
     * Construtor da civilização utilizando um índice para escolher o nome da civilização a partir de uma lista predefinida de nomes
     *
     * @param indexCivNames Índice do nome da civilização na lista predefinida
     * @param number Número que identifica a civilização (pode representar o ID ou número do jogador)
     * @throws IllegalArgumentException Se o índice da civilização for inválido.
     */
    public Civilization(int indexCivNames,int number) {

        // verifica se o indice passado no construtor está dentro do array com os nomes das civilizacoes
        if (indexCivNames < 0 || indexCivNames >= civNames.size()) {
            throw new IllegalArgumentException("Numero de civilizacao invalido. Tente novamente.");
        }

        this.name = civNames.get(indexCivNames);
        this.number = number; // id / nr civ / nr jogador
        this.controlledUnits = new ArrayList<>();
        this.controlledCities = new ArrayList<>();

        this.GoldTreasure = 0;
    }

    /**
     * Construtor da civilização utilizando o nome
     *
     * @param name Nome da civilização
     * @param number Número que identifica a civilização (pode representar o ID ou número do jogador)
     */
    public Civilization(String name, int number) {
        this.name = name;
        this.number = number; // id / nr civ / nr jogador
        this.controlledCities = new ArrayList<>();
        this.controlledUnits = new ArrayList<>();

        this.GoldTreasure = 0;
    }


    /**
     * Obtém o tesouro de ouro da civilização
     */
    public double getGoldTreasure() {return GoldTreasure;}
    public void addGoldTreasure(double a) {
        GoldTreasure += a;
        if (GoldTreasure < 0) {
            GoldTreasure = 0;
        }
    }


    public int getNumber(){return number;} ////Obtém o nr da civilização.
    public String getName() {return name;} ////Obtém o nome da civilização.
    public static ArrayList<String> getCivNames() {return civNames;} ////Obtém a lista de nomes das civilizações predefinidas.
    public ArrayList<Unit> getControlledUnits() {return controlledUnits;} ///Obtém a lista de unidades controladas pela civilização.
    public ArrayList<City> getControlledCities() {return controlledCities;} ///Obtém a lista de cidades controladas pela civilização.
    public void addUnitToCiv(Unit unit) {controlledUnits.add(unit);} ///adiciona uma unidade à lista de unidades controladas pela civilização
    public void addCityToCiv(City city) {controlledCities.add(city);} ///Adiciona uma cidade à lista de cidades controladas pela civilização

    /**
     * Adiciona um novo nome de civilização à lista predefinida de civilizações.
     * O nome não pode ser em branco e deve ser único.
     * @param name O nome da nova civilização.
     * @throws IllegalArgumentException Se o nome for em branco ou se já existir uma civilização com o mesmo nome.
     */
    public static void addCivName(String name) {
        if (name.isBlank()) {throw new IllegalArgumentException("Nome de civilizacao invalido. Tente novamente.");}
        if (civNames.contains(name)) {throw new IllegalArgumentException("Ja existe uma civilizacao com esse nome.");}
        civNames.add(name);
    }

    /**
     * Exibe todas as unidades controladas por uma civilização
     * @param civ A civilização cuja lista de unidades será exibida
     */
    public static void showControlledUnits(Civilization civ) {
        System.out.println("\nUnidades controladas:");
        int index = 1;
        for (Unit u : civ.getControlledUnits()) {
            System.out.println(index + " - " + u.getType() + u.getUnitCivNum() + " (" + u.getCoordX() + "," + u.getCoordY() + ")");
            index++;
        }

        if (index == 1) {
            System.out.println("A sua civilizacao nao tem unidades.");
        }
    }

    /**
     * Exibe todas as cidades controladas por uma civilização.
     * @param civ A civilização cuja lista de cidades será exibida.
     */
    public static void showControlledCities(Civilization civ) {
        System.out.println("\nCidades controladas:");
        int index = 1;
        for (City c : civ.getControlledCities()) {
            System.out.println(index + " - " + c.getType() + c.getCityCivNum() + " (" + c.getCoordX() + "," + c.getCoordY() + ")");
            index++;
        }

        if (index == 1) {
            System.out.println("Nao tem cidades na sua civilizacao.");
        }
    }

}
