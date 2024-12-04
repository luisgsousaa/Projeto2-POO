/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public interface ITerrain {
    public String getType(); //tipo da celula
    public String getTypeShown(); // tipo a mostra, por exemplo se tem uma unidade Militar em cima, a celula ainda e - mas o TypeShown e M
    public int getProductivity();
    public int getEntryCost();
    public int getCyclesToTraverse();
    public int getMaxNumWorkers();
    public double getFoodProduction();
    public double getIndustrialProduction();
    public double getGoldProduction();
}
