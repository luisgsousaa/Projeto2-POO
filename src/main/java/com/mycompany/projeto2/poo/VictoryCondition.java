/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

import java.util.ArrayList;
import java.util.Collections;


public class VictoryCondition {
    public static final double GOLD_TO_WIN = 10000;
    
    /**
     * 
     * @param civs lista de civilizações em jogo
     * @return  true se existe apenas uma, false caso contrario
     */
    private static boolean isOneCivLeft(ArrayList<Civilization> civs){
        if(civs.size()==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Verifica as civilizações que passaram o limite de ouro para ganhar, são postas numa lista e caso essa lista esteja vazia é retornado null, 
     * caso tenha 1 civilização é retornado o nome dessa, caso tenha mais que uma é verificado qual delas possui a maior quantidade de ouro e retorna o nome dessa.
     * @param civs lista de civilizações em jogo
     * @return O nome da civilização que tiver a cima do limite de ouro e caso haja mais que uma a que tiver mais ouro. ou null
     */
    private static String goldThreshold(ArrayList<Civilization> civs){
        ArrayList<Civilization> surpassesGoldTreshold = new ArrayList<Civilization>();
        
        for(Civilization civ : civs){
            if(civ.getGoldTreasure() >= GOLD_TO_WIN){
                surpassesGoldTreshold.add(civ);
            }
        }
        if(surpassesGoldTreshold.isEmpty()){
            return null;
        }
        else if(surpassesGoldTreshold.size() == 1){
            return surpassesGoldTreshold.get(0).getName();
        }
        
        else{
            double max = surpassesGoldTreshold.get(0).getGoldTreasure();
            String name = surpassesGoldTreshold.get(0).getName();
            
            for(Civilization civ : surpassesGoldTreshold){
                if(civ.getGoldTreasure() > max){
                   max = civ.getGoldTreasure();
                   name = civ.getName();
                }
                return name;    
            }
        }
        return null;
    }
    
    /**
     * Usa os métodos desta classe para determinar se o jogo ja acabou e qual dos jogadores venceu.
     * @param civs lista de civilizações em jogo
     * @return true se o jogo acabou, false caso contrario
     */
    public static boolean isEndGame(ArrayList<Civilization> civs){
        if(isOneCivLeft(civs)){
            System.out.println("A civilização " + civs.get(0).getName() + " ganhou pois e a ultima em jogo");
            return true;
        }
        else{
            String name = goldThreshold(civs);
            if(name == null){
                return false;
            }
            else{
                System.out.println("A civilização " + name + " ganhou pois possui mais de " + GOLD_TO_WIN + " de ouro na sua reserva");
                return true;
            }
        }
    }
    
}
