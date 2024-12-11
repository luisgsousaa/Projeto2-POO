package com.mycompany.projeto2.poo;

import java.util.ArrayList;

public class Civilization {

    private String name;
    //private int id;
    private int number;
    private ArrayList<Cell> controlledCells; // por enquanto apenas será apenas cidade
    private ArrayList<Building> controlledBuildings; 
    private ArrayList<Unit> controlledUnits; // militares, explorrs, producer, spies etc
    private static ArrayList<String> chosenCivs;

    public addBuilding(Building b) {this.controlledBuildings.add(b);};

    private static ArrayList<String> civNames = new ArrayList<>();

    static {
        civNames.add("Estepilhas");
        civNames.add("Lambricas");
        civNames.add("Fagulhas");
        civNames.add("Bragados");
        civNames.add("Tarugos");
        civNames.add("Cangalhos");
        chosenCivs = new ArrayList<>();
    }

    //construtor com indice
    public Civilization(int indexCivNames,int number) {

        // verifica se o indice passado no construtor está dentro do array com os nomes das civilizacoes
        if (indexCivNames < 0 || indexCivNames >= civNames.size()) {
            throw new IllegalArgumentException("Numero de civilizacao invalido. Tente novamente.");
        }

        this.name = civNames.get(indexCivNames); // nome da civilizacao
        this.number = number; // id/numero da civilizacao (nr do jogador)
        this.controlledCells = new ArrayList<>();
        this.controlledUnits = new ArrayList<>();
    }

    // construtor com nome
    public Civilization(String name, int number) {
        this.name = name;
        //this.id = civNames.indexOf(name);
        this.number = number; // id/numero da civilizacao (nr do jogador)
        this.controlledCells = new ArrayList<>();
        this.controlledUnits = new ArrayList<>();
    }


    //public int getId() {return id;}
    public int getNumber(){return number;}
    public String getName() {return name;}
    public static ArrayList<String> getCivNames() {return civNames;}
    public ArrayList<Unit> getControlledUnits() {return controlledUnits;}

    public void addCellToCiv(Cell cell) {controlledCells.add(cell);}
    public void addUnitToCiv(Unit unit) {controlledUnits.add(unit);}

    public static void addChosenCiv(String civName) {
        if (!chosenCivs.contains(civName)) {
            chosenCivs.add(civName);
        } else {
            throw new IllegalArgumentException("Essa civilizacao ja foi escolhida.");
        }
    }
    public static boolean isCivChosen(String civName) {return chosenCivs.contains(civName);}


    public static void addCivName(String name) {
        if (name.isBlank()) { //o is blank é um isempty mais completo pq diz que  '      ' ta vazio
            throw new IllegalArgumentException("Nome de civilizacao invalido. Tente novamente.");
        }
        if (civNames.contains(name)) {
            throw new IllegalArgumentException("Ja existe uma civilizacao com esse nome.");
        }
        civNames.add(name);
    }


    // verificar se ta a funcionar

    public void printControlledUnits() {
        System.out.println("civ " + this.name);
        System.out.println("num unidades controladas " + getControlledUnits().size());
        for (Unit u : controlledUnits) {
            System.out.println(u.getType() + u.getUnitCivNum());
        }
    }







    // notas

    //fazer a atualizacao do array list do controlled cells e units sempre que a celula ou unidade "morre" e quando é criada uma unidade (subclasses de unidade) ou celula (subclasses de celula)
    // a ideia depois sera atraves desse array list se a civilizacao perde (ou ganha?) e tbm para imprimir unidades e cidades que pertencem a civilizacao nos menus

}
