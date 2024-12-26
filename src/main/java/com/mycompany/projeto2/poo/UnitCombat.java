package com.mycompany.projeto2.poo;

public abstract class UnitCombat extends Unit {
    private static final int MAX_ATTACKS = 3;
    private int remainingAttacks;

    public UnitCombat(String name, String type, Civilization civilization, int life, int maxSteps,
                      double maintenanceCost, int productionCost, int productionDelay, int attackDamage) {
        super(name, type, civilization, life, maxSteps, maintenanceCost, productionCost, productionDelay, attackDamage);
        this.remainingAttacks = MAX_ATTACKS;
    }

    public int getRemainingAttacks() {
        return remainingAttacks;
    }

    public void resetAttacks() {
        remainingAttacks = MAX_ATTACKS;
    }

    public boolean canAttack() {
        return remainingAttacks > 0;
    }

    public void executeConfrontation() {
        if (remainingAttacks > 0) {
            remainingAttacks--;
            System.out.println("Confrontos restantes para este ciclo: " + remainingAttacks);
        } else {
            System.out.println("Todos os confrontos desta unidade ja foram usados neste ciclo.");
        }
    }
}
