/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class City extends Cell implements Life{
    private int population, maxNumWorkers;
    private final int STARTING_POPULATION = 3;
    private final int STARTING_FOOD_RESOURCES = 6;
    private final int FOOD_PER_PERSON = 2;
    private double foodReserve,goldResources, industrialResources, foodResources, foodNecessity;
    private final double  POPULATION_GROWTH_FOOD_MARGIN = 0.1; // Margem para crescer a população
    private int cityNumber;
    
    private int foodWorkers, industrialWorkers, goldWorkers;
    
    private String[] layers;
    private Map map;
    private final int coordX, coordY;

    private Civilization civilization;


    private static final int MAX_LIFE = 80;
    private int life;


    
    /**
     * É criada uma nova célula do tipo cidade usando o construtor da super classe, é criada a cidade e os primeiros trabalhadores são atribuidos aos terrenos
     * @param x coordenada x da cidade, escolhida pelo jogador
     * @param y coordenada y da cidade, escolhida pelo jogador
     * @param map referencia do objeto mapa
     * @param cityNumber número do jogador que é dado à cidade no mapa
     */
    public City(int x, int y,Map map,int cityNumber, Civilization civilization){
        super("C ");
        setCityVariables(map,cityNumber);
        coordX = x;
        coordY = y;
        this.civilization = civilization;

        
        
        createCity();
        setFirstWorkers();

        civilization.addCityToCiv(this);
        this.life = MAX_LIFE;

        // teste
        addWorkers(1,200);
        addWorkers(2,200);
        addWorkers(3,200);
        produceResourcesForCycle();
        
        System.out.println("Food " + foodResources);
        System.out.println("Gold " + goldResources);
        System.out.println("Industrial " + industrialResources);
        //teste
        
        
    }















    public int getCoordX() {return coordX;}
    public int getCoordY() {return coordY;}



    public int getCityMaxLife() {
        return MAX_LIFE; // Retorna o valor máximo de vida para esta unidade
    }
    // vida
    public int getLife() {return life;}
    public void takeDamage(int damage) {this.life -= damage; if (this.life < 0) {this.life = 0;}}
    public void heal(int amount) {this.life += amount;}
    public boolean isAlive() {return this.life > 0;}
    public void setLife(int life) {this.life = life;}

    public void die(Map map) {

        Cell c = map.getCell(coordX, coordY);

        if (c != null) {
            c.setSomethingOnTop(false);
            c.setTypeShown(c.getPreviousTypeShown());
        }

        removeCityFromCiv();
    }


    //civ
    public void removeCityFromCiv() {civilization.getControlledCities().remove(this);}
    public Civilization getCityCiv() {return civilization;}



  public int getCityCivNum() {return civilization.getNumber();}
    /**
     * Array que guarda as camadas de terrenos da cidade, o primeiro é (g)old, o segundo é (i)dustry e o terceiro é (f)ood
     */

    private void setLayers(){
         layers = new String[4];
         layers[1]="g ";
         layers[2]="i ";
         layers[3]="f ";
    }
    /**
     * Função que cria a cidade nas coordenadas passadas ao construtor.
     * É dado o número da cidade, e esse é apresentado no mapa.
     * Um ciclo percorre todas as células pertencentes à cidade e muda estas para o tipo de produção correspondente à camada, a função
     * também calcula o número máximo de trabalhadores da cidade, tendo em conta quantos terrenos possui e as suas caracteristicas.
     * É dado o valor true a belongs to city para distinguir as células que pertencem a cidades.
     */
    private void createCity(){
                
        map.setCell(coordX,coordY,this);
        String newType = this.getType().trim() + cityNumber ;
        this.setTypeShown(newType);

        Cell cell = map.getCell(coordX, coordY);
        cell.setSomethingOnTop(true);


        layers[0] = newType;
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    String symbol = map.getCellTypeShown(x+coordX,y+coordY);



                    if(!symbol.equals(layers[0]) && !symbol.equals(layers[1]) && !symbol.equals(layers[2])){
                        map.setCellTypeShown(x+coordX,y+coordY,layers[index]);
                        map.setCellBelongsToCity(x+coordX,y+coordY, true);



                        maxNumWorkers+= map.getCellMaxNumWorkers(x+coordX,y+coordY);
                        setProductionType(x+coordX,y+coordY,index);
                    }                
                }         
            }
            index++;  

        }
    }
    
    /**
     * Atribui o tipo de produção ás células.
     * @param x coordenada da célula correspondente
     * @param y coordenada da célula correspondente
     * @param index nível em que a célula se encontra que irá afetar qual tipo de produção terá
     */
    private void setProductionType(int x,int y,int index){
        switch(index){
            case 1:
                map.setCellToGoldProduction(x, y);
                break;
            case 2:
                map.setCellToIndustrialProduction(x, y);
                break;
            case 3:
                map.setCellToFoodProduction(x, y);
                break;
            
        }
    }
    
    /**
     * Adiciona os primeiros trabalhadores aos terrenos, 1 por cada tipo de produção
     */
    private void setFirstWorkers(){
        addWorkers(1,1);
        addWorkers(2,1);
        addWorkers(3,1);

    }
    
    
    
    
    /**
     * Apenas para ajudar
     */
    private void loopThroughEntireCity(){
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    map.getCellType(x+coordX,y+coordY);
                    
                                   
                }         
            }
            index++;  

        }
    }
    
    
    
    /**
     * É chamada no construtor, dá os valores às variáveis da cidade
     * @param map referencia do objeto mapa
     * @param cityNumber número do jogador a quem pertence a cidade
     */
    private void setCityVariables(Map map,int cityNumber){
        population = STARTING_POPULATION;
        foodResources = 0;
      
        foodReserve = STARTING_FOOD_RESOURCES;
        industrialResources = 0;
        maxNumWorkers = 0;
        this.map=map;
        this.cityNumber = cityNumber;
        this.setBelongsToCity(true);
        updateFoodNecessity();
        setLayers();
    }
    /**
     * Atualiza a necessidade de comida tendo em conta a população
     */
    private void updateFoodNecessity(){
        foodNecessity = population * FOOD_PER_PERSON;
    }
    
    /**
     * Determina se a população irá crescer ou não tendo em conta o excesso de comida na reserva
     */
    private void populationGrowth(){ // ao fim do turno
        double abundance = foodNecessity * (POPULATION_GROWTH_FOOD_MARGIN + 1);
        if(foodReserve > abundance ){
            population+=2;
        }
    }
    
    /**
     * Determina se a população irá diminuir ou não tendo em conta a falta de comida na reserva
     */
    private void populationDecrease(){ // ao fim do turno
        if(foodReserve < 0){
            population-=1;
        }
        foodReserve = 0; // de modo a que nunca fique 0 aos olhos do user
    }
    
    /*
    Métodos que retornam as coordenadas da cidade
    */
    public int getX(){return coordX;}
    public int getY(){return coordY;}
    
    /**
     * @return Número total de trabalhadores na cidade, é overwrite da classe cell que diz apenas o número de trabalhadores nessa célula
     */
    @Override
    public int getNumWorkers(){
        return foodWorkers + industrialWorkers + goldWorkers;
    }
    
    
    
    /**
     * A função percorre as células correspondentes ao tipo de produção escolhido e verifica se já tem o número máximo de trabalhadores, caso isto aconteça continua, caso contrário
     * adiciona trabalhadores a essa célula até ela estar cheia, se a quantidade de trabalhadores já adicionada for igual à pretendida o ciclo é quebrado não adicionando mais nenhum.
     * @param layer camada da cidade escolhida para adicionar trabalhadores
     * @param quantity quantidade de trabalhadores a serem adicionados
     * @return booleano que diz se adicionou algum trabalhador ou não
     */
    public boolean addWorkers(int layer, int quantity){ // 1 gold, 2 industry 3 food
        if(quantity < 0){
            return false;
        }
        
        int workersAdded = 0;
        for(int y = -1*layer ; y <= 1*layer; y++){
            for(int x = -1*layer ; x <= 1*layer; x++){
                
                if(map.getCellTypeShown(x+coordX,y+coordY).equals(layers[layer])){
                    
                    
                    int maxWorkers = map.getCellMaxNumWorkers(x+coordX,y+coordY);
                    
                    if(map.getCellNumWorkers(x+coordX,y+coordY) == maxWorkers){continue;}
                    else{
                        while(map.getCellNumWorkers(x+coordX,y+coordY) < maxWorkers){
                            if(workersAdded<quantity){
                                map.changeCellNumWorkers(x+coordX,y+coordY,+1);
                                workersAdded++;
                                countWorkers(layer,1);
                            }
                            else {break;}
                        }
                    }  
                } 
            }            
        }         
        

        return workersAdded > 0;
    }
    /**
     * A função percorre as células correspondentes ao tipo de produção escolhido e verifica se tem 0 trabalhadores, caso isto aconteça continua, caso contrário
     * remove trabalhadores nessa célula até ela estar vázia, se a quantidade de trabalhadores já removida for igual à pretendida o ciclo é quebrado não removendo mais nenhum.
     * @param layer camada da cidade escolhida para remover trabalhadores
     * @param quantity quantidade de trabalhadores a serem adicionados
     * @return booleano que diz se removeu algum trabalhador ou não
     */
    public boolean removeWorkers(int layer, int quantity){ // 1 gold, 2 industry 3 food
        if(quantity < 0){
            return false;
        }
        int workersRemoved = 0;
        
        for(int y = -1*layer ; y <= 1*layer; y++){
            for(int x = -1*layer ; x <= 1*layer; x++){
               
                if(map.getCellTypeShown(x+coordX,y+coordY).equals(layers[layer])){
                    if(map.getCellNumWorkers(x+coordX,y+coordY) == 0){continue;}
                    else{
                        while(map.getCellNumWorkers(x+coordX,y+coordY) > 0){
                            if(workersRemoved<quantity){
                                map.changeCellNumWorkers(x+coordX,y+coordY,-1);
                                workersRemoved++;
                                countWorkers(layer,-1);
                            }
                            else {break;}
                        }
                    }  
                } 
            }            
        }         
        

        return workersRemoved > 0;
    }
    
    /**
     * Esta função conta os trabalhadores em cada tipo de produção, é chamada quando são removidos ou adicionados trabalhadores
     * @param layer camada correspondente ao tipo de produção pretendido
     * @param action se remove ou adiciona um trabalhador
     */
    private void countWorkers(int layer,int action){
        if(action != 1 && action != -1){
            return;
        }
        else{
           switch(layer){
            case 1:
                goldWorkers+=1*action;
                break;
            case 2:
                industrialWorkers+=1*action;
                break;
            case 3:
                foodWorkers+=1*action;
                break;
            } 
        }
        
    }
    
    
    /**
     * Percorre todas as células da cidade, se uma célula tiver 0 trabalhadores continua, caso contrário chama a função da classe utilitaria ProduceResources,
     * que irá calcular a produção dessa célula e adicionar à cidade.
     */
    public void produceResourcesForCycle(){
        final int CITY_SIZE = 3;
        for(int y = -1*CITY_SIZE ; y <= 1*CITY_SIZE; y++){
            for(int x = -1*CITY_SIZE ; x <= 1*CITY_SIZE; x++){
                Cell currentCell = map.getCell(x+coordX, y+coordY);
                if(currentCell.getNumWorkers() == 0){continue;}            
                else{
                    ProduceResources.chooseProduceType(currentCell,this);
                }
            }         
        }
    }
    
    /**
     * Funções usadas para adicionar os recursos que foram produzidos à cidade
     */

    public void addFoodResources(double a){ // isto é chamado varias vezes no mesmo ciclo para as varias celulas da cidade
        foodResources+=a;
        foodReserve+=a;} // adiciona comida do ciclo à reserva de comida da cidade
    public void addIndustrialResources(double a){industrialResources+=a;}
    public void addGoldToCivTreasure(double a){
        civilization.addGoldTreasure(a);
    }




    public double getFoodReserve() {
        return foodReserve;
    }


    public double getGoldResources() {
        return goldResources;
    }


    public double getIndustrialResources() {
        return industrialResources;
    }


    public double getFoodResources() {
        return foodResources;
    }


    public void resetFood() {
        this.foodResources = 0;
    } // da reset na comida do ciclo da cidade

    public void resetIndustrialResources() {
        this.industrialResources = 0;
    }



}
