package com.mycompany.projeto2.poo;

import java.util.ArrayList;

public class Civilization {

    private String name;
    private int number;
    private ArrayList<City> controlledCities;
    private ArrayList<Unit> controlledUnits;
    private static ArrayList<String> civNames = new ArrayList<>();

    private int GoldTreasure;


    // mexer apenas nesta variavel (centralizado aqui, tudo mandado para qui retirado somado tudo aqui, cidade nao podem ter subreservas)







    static {
        civNames.add("Estepilhas");
        civNames.add("Lambricas");
        civNames.add("Fagulhas");
        civNames.add("Bragados");
        civNames.add("Tarugos");
        civNames.add("Cangalhos");
    }

    //construtor com indice
    public Civilization(int indexCivNames,int number) {

        // verifica se o indice passado no construtor está dentro do array com os nomes das civilizacoes
        if (indexCivNames < 0 || indexCivNames >= civNames.size()) {
            throw new IllegalArgumentException("Numero de civilizacao invalido. Tente novamente.");
        }

        this.name = civNames.get(indexCivNames);
        this.number = number; // id / nr civ / nr jogador
        this.controlledUnits = new ArrayList<>();
        this.controlledCities = new ArrayList<>();
    }

    // construtor com nome
    public Civilization(String name, int number) {
        this.name = name;
        this.number = number; // id / nr civ / nr jogador
        this.controlledCities = new ArrayList<>();
        this.controlledUnits = new ArrayList<>();
    }

    public int getNumber(){return number;}
    public String getName() {return name;}
    public static ArrayList<String> getCivNames() {return civNames;}
    public ArrayList<Unit> getControlledUnits() {return controlledUnits;}
    public ArrayList<City> getControlledCities() {return controlledCities;}

    public void addUnitToCiv(Unit unit) {controlledUnits.add(unit);}
    public void addCityToCiv(City city) {controlledCities.add(city);}




    public static void addCivName(String name) {
        if (name.isBlank()) {throw new IllegalArgumentException("Nome de civilizacao invalido. Tente novamente.");}
        if (civNames.contains(name)) {throw new IllegalArgumentException("Ja existe uma civilizacao com esse nome.");}
        civNames.add(name);
    }


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


    // por enquanto ainda nao foi usado, depois ver se sera preciso
    public static void showControlledCities(Civilization civ) {
        System.out.println("\nCidades controladas:");
        int index = 1;
        for (City c : civ.getControlledCities()) {
            System.out.println(index + " - " + c.getType() + c.getCityCivNum());
            index++;
        }

        if (index == 1) {
            System.out.println("Nao tem cidades na sua civilizacao.");
        }
    }



    /// em principio lixo
    /*
    public static void showControlledUnits(Civilization civ, Class<? extends Unit> unitClass) {
        if (unitClass == null) {
            System.out.println("\nClasse fornecida é nula");
            return;
        }

        String unitName = "unidade";
        for (Unit u : civ.getControlledUnits()) {
            if (unitClass.isInstance(u)) {
                unitName = u.getUnitName();
                break;
            }
        }
        System.out.println("\nUnidades " + unitName + " controladas:");

        int index = 1;
        for (Unit u : civ.getControlledUnits()) {
            if (unitClass.isInstance(u)) {
                System.out.println(index + " - " + u.getType() + u.getUnitCivNum() + " (" + u.getCoordX() + "," + u.getCoordY() + ")");
                index++;
            }
        }

        if (index == 1) {
            System.out.println("Nao tem unidades " + unitName + " na sua civilizacao.");
        }
    }*/







/// para testes
    public void printControlled() {
        System.out.println("civ " + this.name);
        System.out.println("num unidades controladas " + getControlledUnits().size());
        for (Unit u : controlledUnits) {
            System.out.println(u.getType() + u.getUnitCivNum());
        }
        System.out.println("num cid controladas " + getControlledCities().size());
        for (City c : controlledCities) {
            System.out.println(c.getType() + c.getCityCivNum());
        }
    }


    // notas

    //fazer a atualizacao do array list do controlled cells e units sempre que a celula ou unidade "morre" e quando é criada uma unidade (subclasses de unidade) ou celula (subclasses de celula)
    // a ideia depois sera atraves desse array list se a civilizacao perde (ou ganha?) e tbm para imprimir unidades e cidades que pertencem a civilizacao nos menus

}
