package com.mycompany.projeto2.poo;


/**
 * Classe que representa os construtores
 * Esta unidade pode aumentar a produtividade da célula onde está posicionada
 * e curar/reparar unidades ou cidades pertencentes à sua civilização
 */
public class UnitBuilder extends Unit {

    private GameMap gameMap;
    public static final String NAME = "Builder";
    private static final String TYPE = "B";
    public static final int PRODUCTION_COST = 150;
    private static final int LIFE = 80;
    private static final int MAX_STEPS = 15;
    private static final int ATTACK_DAMAGE = 0;

    private static final int HEAL_AMOUNT = 10; /// Quantidade de vida que a unidade pode curar/reparar por operação
    private static final int MAX_HEALS = 2; /// Número máximo de curas disponíveis por ciclo
    private int remainingHeals; /// Número de curas restantes para este ciclo

    private Cell currentCell; /// Referência à célula atual onde a unidade está localizada



    /**
     * Construtor para criar uma unidade Builder
     *
     * @param x Coordenada X inicial
     * @param y Coordenada Y inicial
     * @param gameMap O mapa do jogo onde a unidade será posicionada
     * @param direction A direção inicial da unidade
     * @param civilization A civilização à qual a unidade pertence
     */
    public UnitBuilder(int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {
        super(NAME,TYPE, civilization,  LIFE,MAX_STEPS, 0, PRODUCTION_COST, 2,ATTACK_DAMAGE);
        this.setCoordX(x);
        this.setCoordY(y);
        this.gameMap = gameMap;
        this.setDirection(direction);
        this.remainingHeals = MAX_HEALS;

        this.currentCell = gameMap.getCell(x, y);
        applyEffectToCell(currentCell); /// Aplica o efeito de aumento de produtividade à celula onde esta unidade esta por cima

    }

    /**
     * Aplica o efeito de aumento de produtividade à célula fornecida
     * @param cell A célula onde o efeito será aplicado
     */
    private void applyEffectToCell(Cell cell) {
        if (cell != null) {
            cell.increaseProductivityByMultiplier(1.5); /// aumento 50%
        }
    }


    /**
     * Remove o efeito de aumento de produtividade da célula fornecida
     * @param cell A célula de onde o efeito será removido
     */
    private void removeEffectFromCell(Cell cell) {
        if (cell != null) {
            cell.resetProductivityToOriginal();
        }
    }

    /**
     * Adiciona efeitos de produtividade no metodo de movimento herdado
     *
     * @param direction A direção para onde a unidade será movida
     * @param gameMap O mapa do jogo
     * @return true se a unidade foi movida com sucesso, false caso contrário
     */
    @Override
    public boolean move(Direction direction, GameMap gameMap) {
        Cell previousCell = currentCell;
        boolean moved = super.move(direction, gameMap);
        if (moved) {
            removeEffectFromCell(previousCell);
            currentCell = gameMap.getCell(getCoordX(), getCoordY());
            applyEffectToCell(currentCell);
        }
        return moved;
    }


    /**
     * getters e setters de curar/reparar unidades ou cidades da sua civilizacao
     */

    @Override
    public int getHealAmount() {return HEAL_AMOUNT;} /// Retorna a quantidade de vida que esta unidade pode curar/reparar.
    @Override
    public boolean canHeal() {return remainingHeals > 0;} /// Verifica se a unidade ainda pode curar/reparar neste ciclo
    @Override
    public int getRemainingHeals() {return remainingHeals;} /// Retorna o número de curas restantes para esta unidade neste ciclo
    @Override
    public void resetHeals() {remainingHeals = MAX_HEALS;} /// Da reset no número de curas disponíveis para o valor máximo- usado na mudança de ciclo
    @Override
    public void executeHeal() { /// Executa uma ação de cura/reparo, reduzindo o número de curas restantes.
        if (remainingHeals > 0) {
            remainingHeals--;
            System.out.println("Curas restantes para este ciclo: " + remainingHeals);
        } else {
            System.out.println("Todas as curas desta unidade ja foram usadas neste ciclo.");
        }
    }
}