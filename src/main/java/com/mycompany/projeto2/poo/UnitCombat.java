package com.mycompany.projeto2.poo;


/**
 * Classe abstrata que representa uma unidade de combate no jogo
 * Inclui lógica para ataques e confrontos
 */
public abstract class UnitCombat extends Unit {
    private static final int MAX_ATTACKS = 3; /// Número máximo de ataques disponíveis por ciclo.
    private int remainingAttacks; ///// Contador de ataques restantes para o ciclo


    /**
     * Construtor para criar uma unidade de combate
     *
     * @param name Nome da unidade
     * @param type Tipo da unidade
     * @param civilization Civilizacao à qual a unidade pertence
     * @param life Vida inicial da unidade
     * @param maxSteps Número máximo de passos por turno
     * @param maintenanceCost Custo de manutenção da unidade
     * @param productionCost Custo de produção da unidade
     * @param productionDelay Tempo de produção da unidade
     * @param attackDamage Dano de ataque da unidade
     */
    public UnitCombat(String name, String type, Civilization civilization, int life, int maxSteps,
                      double maintenanceCost, int productionCost, int productionDelay, int attackDamage) {
        super(name, type, civilization, life, maxSteps, maintenanceCost, productionCost, productionDelay, attackDamage);
        this.remainingAttacks = MAX_ATTACKS;
    }

    /**
     * Retorna o número de ataques restantes para esta unidade neste ciclo
     * @return Número de ataques restantes
     */
    public int getRemainingAttacks() {
        return remainingAttacks;
    }
    /**
     * Reset no número de ataques disponíveis para a mudança de ciclo
     */
    public void resetAttacks() {
        remainingAttacks = MAX_ATTACKS;
    }

    /**
     * Verifica se a unidade ainda pode atacar neste ciclo
     * @return true se há ataques restantes, false caso contrário
     */
    public boolean canAttack() {
        return remainingAttacks > 0;
    }

    /**
     * Executa uma ação de confronto (captura ou ataque), reduzindo o número de ataques restantes
     */
    public void executeConfrontation() {
        if (remainingAttacks > 0) {
            remainingAttacks--;
            System.out.println("Confrontos restantes para este ciclo: " + remainingAttacks);
        } else {
            System.out.println("Todos os confrontos desta unidade ja foram usados neste ciclo.");
        }
    }
}
