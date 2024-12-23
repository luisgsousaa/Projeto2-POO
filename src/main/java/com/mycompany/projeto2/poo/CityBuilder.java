/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2.poo;

/**
 *
 * @author Admin
 */
public class CityBuilder {
    private Map map;
    private int coordX,coordY,cityNumber,life;
    /**
     * 
     * @param coordX coordenada x escolhida para o centro da cidade pelo jogador
     * @param coordY coordenada y escolhida para o centro da cidade pelo jogador
     * @param map referencia do objeto mapa
     * @param cityNumber número do jogador a quem irá pertencer a cidade
     */
    public CityBuilder(int coordX, int coordY, Map map,int cityNumber){
       this.coordX=coordX;
       this.coordY=coordY;
       this.cityNumber=cityNumber;
       this.map=map;
    }

    /**
     * Cria a cidade com os parametros desejados e retorna a sua referencia
     * @return referencia da cidade criada
     * @throws CoordinatesNotSuitableException informa que as coordenadas escolhidas não são adequadas
     */
    public City createCity(Civilization civilization)throws CoordinatesNotSuitableException{

        if(checkCoordinates()){
            City cidade = new City(coordX,coordY,map,cityNumber,civilization);
            return cidade;
        }
        return null;    
    }
    
    /**
     * Verifica se a cidade está a uma distancia adequada das bordas do mapa, para não ficar fora deste,
     * se o tereno é adequado, neste caso se o custo de entrada é -1 ou se tem algo em baixo, por exemplo uma unidade,
     * se esta à distancia permitida de outra cidade
     * @return booleano que informa se pode ser construida a cidade, apenas é retornado um valor se todas as condições anteriores são falsas
     * @throws CoordinatesNotSuitableException informa que as coordenadas escolhidas não são adequadas
     */
    private boolean checkCoordinates()throws CoordinatesNotSuitableException{
        if(coordX<3 || coordX > (map.getWidth()-4) || coordY<3 || coordY>(map.getHeight()-4)){
            System.out.println("O centro da cidade tem de estar a pelo menos 4 celulas de distancia das bordas do mapa");
            throw new CoordinatesNotSuitableException();
        }
        else if(!isTerrainAdequate(coordX,coordY)){
            System.out.println("O terreno nao e adequado para a construcao da cidade escolha outras coordenadas");
            System.out.println("A cidade ocupa uma area de 7X7 com centro nas coordenadas que escolher");
            throw new CoordinatesNotSuitableException();
        }
        else if(!isFarFromCities(coordX,coordY)){
            System.out.println("A cidade necessita de estar a uma distancia de pelo menos 3 celulas de outra cidade, escolha outras coordenadas");
            System.out.println("A cidade ocupa uma area de 7X7 com centro nas coordenadas que escolher");
            throw new CoordinatesNotSuitableException();
        }
        else{
           return true; 
        }
    }
    
        
    
    /**
     * Verifica se há algo onde a cidade irá ser construida, com valor de entryCost = -1 ou com a variavel isSomethinOnTop verdadeira
     * @param coordX coordenada x escolhida para o centro da cidade pelo jogador
     * @param coordY coordenada y escolhida para o centro da cidade pelo jogador
     * @return booleano que informa se a cidade pode ser construida ou não
     */
    private boolean isTerrainAdequate(int coordX,int coordY){
        int index = 1;
        while(index <= 3){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    if(map.getCellEntryCost(x+coordX, y+coordY) == -1 || map.getCellIsSomethingOnTop(x+coordX, y+coordY)){
                        return false;
                    }


                }         
            }
            index++;  

        }
        return true;
    }

    /**
     * Verifica se as coordenadas escolhidas estão à distancia necessária de outras cidades
     * @param coordX coordenada x escolhida para o centro da cidade pelo jogador
     * @param coordY coordenada y escolhida para o centro da cidade pelo jogador
     * @return booleano que informa se a cidade pode ser construida ou não
     */
    private boolean isFarFromCities(int coordX,int coordY){
        int index = 1;
        while(index <= 6){
            for(int y = -1*index ; y <= 1*index; y++){
                for(int x = -1*index ; x <= 1*index; x++){
                    try{
                        if(map.getCellBelongsToCity(x+coordX,y+coordY)){
                            return false;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                        continue;
                    }                 
                }         
            }
            index++;  
        }
        return true;

    }

}
