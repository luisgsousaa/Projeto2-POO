package com.mycompany.projeto2.poo;

public abstract class Unit implements ILife {
    
    private String type;
    private Direction direction;
    private int coordX, coordY;
    private int steps; // nr passos por ciclo da unidade
    private double maintenanceCost; // em ouro
    private int productionCost; // em recursos industriais
    private int productionDelay; //nr ciclos a serem criados
    private Civilization civilization;
    private int life, attackDamage;
    private int stepsRemaining;
    private int maxSteps;
    private boolean isColonizer;
    private String name;
    private int maxLife;

    /**
     * Construtor de unidade
     * @param name Nome da unidade
     * @param type Tipo da unidade
     * @param civilization Civilização à qual a unidade pertence
     * @param life Vida inicial da unidade
     * @param maxSteps Número máximo de passos que a unidade pode dar
     * @param maintenanceCost Custo de manutenção da unidade
     * @param productionCost Custo de produção da unidade
     * @param productionDelay Tempo de produção da unidade
     * @param attackDamage Dano de ataque da unidade
     */
    public Unit(String name, String type, Civilization civilization, int life, int maxSteps, double maintenanceCost, int productionCost, int productionDelay, int attackDamage) {
        this.name = name;
        this.type = type;
        this.civilization = civilization;
        this.steps = steps;
        this.productionCost = productionCost;
        this.life = life;
        this.productionDelay = productionDelay;
        this.maintenanceCost = maintenanceCost;
        this.maxLife = life;
        this.attackDamage = attackDamage;
        civilization.addUnitToCiv(this);
        this.isColonizer = false;
        this.stepsRemaining = maxSteps;
        this.maxSteps = maxSteps;
    }



    public void setType(String type){this.type = type;} ///  Define o tipo da unidade
    public void setDirection(Direction direction) {this.direction = direction;} /// Define a direção da unidade

    ///  Definem as coordenadas da unidade no mapa
    public void setCoordX(int coordX) {this.coordX = coordX;}
    public void setCoordY(int coordY) {this.coordY = coordY;}


    public boolean isColonizer() {return isColonizer;} ////Verifica se a unidade pode fundar cidades.
    public void setColonizer(boolean colonizer) {this.isColonizer = colonizer;} ///  define uma unidade a poder fundar novas cidades


    // passos
    public int getStepsRemaining() {return stepsRemaining;} /// numero de passos que a unidade ainda pode dar
    public void resetSteps() {this.stepsRemaining = maxSteps;} /// reset dos passos para a mudança de turno
    public int getMaxSteps() {return maxSteps;} /// passos maximos para o turno
    public int getSteps(){return steps;} /// passos por ciclo

    // dados basicos da unidade
    public String getUnitName() {return name;}
    public String getType(){return type;}
    public Direction getDirection() {return direction;}
    public int getCoordX() {return coordX;}
    public int getCoordY() {return coordY;}


    // confronto (ataque+captura)
    public void executeConfrontation(){};
    public boolean canAttack() {return false;} /// por padrao as unidades tao definidas como false no canattack
    public int getRemainingAttacks() {return 0;} /// por padrao os ataques restantes sao 0 para todas as unidades
    public void resetAttacks() {}; ///  método vazio, que pode ser sobrescrito para redefinir os ataques restantes
    public int getAttackDamage(){return this.attackDamage;} ///Retorna o valor de dano de ataque da unidade
    public void takeDamage(int damage) {this.life -= damage; if (this.life < 0) {this.life = 0;}} ///Reduz a vida da unidade pelo valor de dano, garantindo que a vida não caia inferior a 0


    // cura
    public int getHealAmount() {return 0;} ///Retorna a quantidade de cura que a unidade pode realizar. Por padrão, retorna 0
    public void executeHeal(){}; ///  nao implementado, precisar ser subscrito executará a cura
    public boolean canHeal() {return false;} ///Retorna se a unidade pode curar. Por padrão, retorna false
    public int getRemainingHeals() {return 0;} ///Retorna o número de curas restantes. é  0 por padrao
    public void resetHeals() {}; /// a ser subcrito nas subclasses que querem redefinir serve pa dar reset na mudança de ciclo das curas
    /**
     * Realiza a cura da unidade, limita aumento da vida até a um maximo
     * @param amount Quantidade de cura a ser aplicada.
     */
    public void heal(int amount) {
        this.life += amount;
        if (this.life > maxLife) {
            this.life = maxLife;
        }
    }


    // vida
    public int getLife() {return this.life;} ///Retorna a vida atual da unidade
    public int getUnitMaxLife() {return maxLife;} ///Retorna a vida máxima da unidade
    public boolean isAlive() {return this.life > 0;} /// Verifica se a unidade está viva
    public void setLife(int life) {this.life = life;} ///Define a vida da unidade


    // civilization
    public int getUnitCivNum() {return civilization.getNumber();} ///Retorna o número da civilização à qual a unidade pertence.
    public Civilization getUnitCiv() {return civilization;} ///Retorna a civilização da unidade
    public String getUnitCivName() {return civilization.getName();} //// Retorna o nome da civilização da unidade
    public void setCivilization(Civilization civilization) {this.civilization = civilization;} ///Define a civilização da unidade
    public void removeUnitFromCiv() {civilization.getControlledUnits().remove(this);} ///Remove a unidade da lista de unidades da sua civilização

    /**
     * Método chamado quando a unidade morre. A unidade é removida do mapa e da civilização
     * @param gameMap Mapa onde a unidade está localizada.
     */
    public void die(GameMap gameMap) {
        Cell c = gameMap.getCell(coordX, coordY);
        if (c != null) {
            c.removeUnit();
            c.setTypeShown(c.getPreviousTypeShown());
        }
        removeUnitFromCiv();
    }


    // manutenção, produtividade e tempo de criação
    public double getMaintenanceCost(){return maintenanceCost;} ///Retorna o custo de manutenção da unidade
    public int getProductionCost(){return productionCost;} ////Retorna o custo de produção da unidade
    public int getProductionDelay(){return productionDelay;} ///Retorna o tempo de produção da unidade
    public void setMaintenanceCost(double maintenanceCost){this.maintenanceCost=maintenanceCost;} ////Define o custo de manutenção da unidade
    public void setProductionCost(int productionCost){this.productionCost=productionCost;} ///Define o custo de produção da unidade
    public void setProductionDelay(int productionDelay){this.productionDelay=productionDelay;} ///Define o tempo de produção da unidade


    /**
     * Move a unidade para uma nova direção no mapa, verificando o custo de entrada
     * @param direction Nova direção da unidade
     * @param gameMap Mapa onde a unidade está localizada
     * @return true se a unidade se moveu com sucesso, false caso contrário
     */
    public boolean move(Direction direction, GameMap gameMap) {
        int originalX = coordX;
        int originalY = coordY;

        this.setDirection(direction);
        this.moveUnit(gameMap);

        if (coordX != originalX || coordY != originalY) {
            Cell currentCell = gameMap.getCell(coordX, coordY);
            if (currentCell.getTerrain() != null) {
                int stepsToMove = currentCell.getTerrain().getStepsToTraverse();
                if (stepsRemaining >= stepsToMove) {
                    stepsRemaining -= stepsToMove;
                } else {
                    stepsRemaining = 0;
                }
            }
            return true;
        }
        return false;
    }


     /**
     * Cria uma nova unidade no mapa nas coordenadas especificadas.
     * @param unitType Tipo da unidade a ser criada.
     * @param x Coordenada X no mapa.
     * @param y Coordenada Y no mapa.
     * @param gameMap Mapa onde a unidade será colocada.
     * @param direction Direção inicial da unidade.
     * @param civilization Civilização da qual a unidade fará parte.
     * @return A unidade criada ou null em caso de erro.
     */
    public static Unit createUnit(String unitType, int x, int y, GameMap gameMap, Direction direction, Civilization civilization) {

        if (x < 0 || x >= gameMap.getWidth() || y < 0 || y >= gameMap.getHeight()) {
            System.out.println("Coordenadas invalidas.");
            return null;
        }

        Cell targetCell = gameMap.getCell(x,y);

        if (targetCell.isSomethingOnTop()) {
            System.out.println("A celula (" + x + "," + y + ") ja esta ocupada.");
            return null;
        }

        if (targetCell.getEntryCost() == -1) {
            System.out.println("A celula (" + x + "," + y + ") e inacessivel.");
            return null;
        }
        Unit newUnit = UnitFactoryRegistry.createUnit(unitType.trim(), x, y, gameMap, direction, civilization);
        if (newUnit == null) {
            System.out.println("Unidade desconhecida.");
            return null;
        }

        targetCell.setPreviousTypeShown(targetCell.getTypeShown());
        targetCell.setUnit(newUnit);
        targetCell.setTypeShown(newUnit.getType() + newUnit.getUnitCivNum()); //+get get civId
        targetCell.setSomethingOnTop(true);

        return newUnit;
    }

    /**
     * Método responsável por mover a unidade no mapa para a nova posição com base na direção atual
     * A movimentação pode envolver a transição entre células adjacentes e inclui verificações para
     * garantir que a célula de destino é válida e acessível
     *
     * @param gameMap O mapa do jogo onde a unidade se encontra
     */
    public void moveUnit(GameMap gameMap) {

            int newX = coordX;
            int newY = coordY;

            switch (direction) {
                case UP:
                    newY--;
                    if (newY < 0) {newY = gameMap.getHeight() - 1;} // Circ vert cim
                    break;

                case DOWN:
                    newY++;
                    if (newY >= gameMap.getHeight()) {newY = 0;} // circ vert baix
                    break;

                case LEFT:
                    newX--;
                    if (newX < 0) {coordX = gameMap.getWidth() - 1;} // Circ horz esq
                    break;

                case RIGHT:
                    newX++;
                    if (newX >= gameMap.getWidth()) {newX = 0;} //circ horz dir
                    break;

                case NONE:
                    break;

                default:
                    return;
            }

            Cell currentCell = gameMap.getCell(coordX, coordY); // celula em que se ta atualmente
            Cell targetCell = gameMap.getCell(newX, newY); // nova celula que queremos nos mover

            if (targetCell == null) {
                System.out.println("A celula de destino (" + newX + ", " + newY + ") nao existe.");
                return;
            }
            if (targetCell.isSomethingOnTop()) {
                System.out.println("A celula (" + newX + "," + newY + ") ja esta ocupada.");
                return;
            }

            if (targetCell.getEntryCost() == -1) {
                System.out.println("A celula (" + newX + "," + newY + ") e inacessivel.");
                return;
            }

            currentCell.setTypeShown(currentCell.getPreviousTypeShown()); // celula atual passa a mostrar o que tava antes
            currentCell.setSomethingOnTop(false);
            currentCell.setUnit(null); // retira referencia da celula atual

            targetCell.setPreviousTypeShown(targetCell.getTypeShown()); // celula alvo guarda o que tava la
            targetCell.setTypeShown(this.getType() + this.getUnitCivNum()); // atualiza a celula alvo para a unidade em questao
            targetCell.setSomethingOnTop(true);
            targetCell.setUnit(this); // coloca referencia na celula alvo

            coordX = newX;
            coordY = newY;
    }

}


