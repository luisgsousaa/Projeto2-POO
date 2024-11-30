package com.mycompany.projeto2.poo;

import java.util.ArrayList;

public class Civilization {

    private String name;
    private int id;
    private ArrayList<Cell> controlledCells; // por enquanto apenas será apenas cidade
    private ArrayList<Unit> controlledUnits; // militares, explorrs, producer, spies etc

    private static ArrayList<String> civNames = new ArrayList<>();

    static {
        civNames.add("Estepilhas");
        civNames.add("Lambricas");
        civNames.add("Fagulhas");
        civNames.add("Bragados");
        civNames.add("Tarugos");
        civNames.add("Cangalhos");
    }

    //construtor com index
    public Civilization(int index) {

        if (index < 0 || index >= civNames.size()) {
            throw new IllegalArgumentException("Numero de civilizacao invalido. Tente novamente.");
        }

        this.name = civNames.get(index);
        this.id = index;
        this.controlledCells = new ArrayList<>();
        this.controlledUnits = new ArrayList<>();
    }

    // construtor com nome
    public Civilization(String name) {
        this.name = name;
        this.id = civNames.indexOf(name);
        this.controlledCells = new ArrayList<>();
        this.controlledUnits = new ArrayList<>();
    }


    public int getId() {return id;}
    public String getName() {return name;}
    public static ArrayList<String> getCivNames() {return civNames;}


    public void addCellToCiv(Cell cell) {controlledCells.add(cell);}
    public void addUnitToCiv(Unit unit) {controlledUnits.add(unit);}


    public static void addCivName(String name) {
        if (name.isBlank()) { //o is blank é um isempty mais completo pq diz que  '      ' ta vazio
            throw new IllegalArgumentException("Nome de civilizacao invalido. Tente novamente.");
        }
        if (civNames.contains(name)) {
            throw new IllegalArgumentException("Ja existe uma civilizacao com esse nome.");
        }
        civNames.add(name);
    }


    // notas

    //fazer a atualizacao do array list do controlled cells e units sempre que a celula ou unidade "morre" e quando é criada uma unidade (subclasses de unidade) ou celula (subclasses de celula)
    // a ideia depois sera atraves desse array list se a civilizacao perde (ou ganha?) e tbm para imprimir unidades e cidades que pertencem a civilizacao nos menus

}
