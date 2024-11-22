package com.mycompany.projeto2.poo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class MapBuilder {
    
    private static final int  MIN_MAP_WIDTH = 20;
    private static final int MAX_MAP_WIDTH = 60;
    private static final int MIN_MAP_HEIGHT = 7;
    private static final int MAX_MAP_HEIGHT = 20;
    private static String[][] mapa;
    private static File[] textFiles;
    
    
    public MapBuilder() throws IOException{
        initializeGameMap();
        
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Lendo do array bidimensional");
        
        printMap();
        printMap();
        printMap();
        printMap();
    }
    
    private static void getMapSelection(){
        File folder = new File("."); //pasta do projeto onde se econtram os ficheiros
        
        
        textFiles = folder.listFiles((dir, name) -> name.endsWith(".txt")); //array com os ficheiros que acabam com .txt

        if (textFiles != null && textFiles.length > 0) {
            System.out.println("Mapas Disponiveis:");
            for (int i = 0; i < textFiles.length;i++) {
                System.out.print(i+1 + " - " 
                                 + textFiles[i].getName().replace(".txt", "") 
                                 + "\n"); // apresentar um número para poder escolher uma opção, retirar ".txt" do fim.                
            }
        } else {
            System.out.println("Nao existem mapas na pasta. Verifique se estes la estao.");
            setDefaultMap();
        }
        
    }
    
    private static int selectMap(){
        
        System.out.println("Escolha um dos mapas");
        int input = -1;
        boolean sair = false;
        Scanner scan = new Scanner(System.in);
        do{
            try{

                input = scan.nextInt();
                if(input < 1 || input > textFiles.length){
                    System.out.println("Escolha uma das opcoes disponiveis");
                }
                else{
                    sair = true;
                    input-=1; // igualar ao indice
                }
            }


            catch(InputMismatchException e){
                System.out.println("Escolha uma das opcoes disponiveis");
                scan.next();
            }

        }while(!sair);  
        
        return input;
    }
    
    private static String[][] getDefaultMap(){
        String[][] map ={
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",},
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",},
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "# ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",} ,
                {"- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "- ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",  "Z ",}};
        
        int originalWidth = map.length;
        int originalHeight = map[0].length;
        
        String[][] newMap = new String[originalHeight][originalWidth];
        
        
        for (int i = 0; i < originalWidth; i++) {
            for (int j = 0; j < originalHeight; j++) {
                newMap[j][i] = map[i][j];  // Swap row and column indices
            }
        }
        
        return newMap;
    }
    
    private static void setDefaultMap(){
        System.out.println("Se quiser carregar o mapa predefinido clique no enter");
            Scanner scan = new Scanner(System.in);
            if(scan.nextLine() == ""){
                getDefaultMap();
            }
    }
    
    
    private static boolean initializeMapArray(String file) throws FileNotFoundException, IOException{
        try{
            FileReader inStream = new FileReader(file); // falta meter os try catch 
            BufferedReader reader = new BufferedReader(inStream);

            //medidas do mapa
            int width = 0; 
            int height = 0; 

            //Ver a width
            String line = reader.readLine();
            int tamanho = line.length();

            int index = 0;
            while(index < tamanho){
                    width++;
                    index+=3;
            }

            //Ver a height
            while(line != null){
                height++;
                line = reader.readLine();           
            }
            
            if(width < MIN_MAP_WIDTH || width > MAX_MAP_WIDTH){
                System.out.println("O mapa precisa de ter entre " + MIN_MAP_WIDTH + " e " + MAX_MAP_WIDTH + " celulas de comprimento, escolha outro mapa ou corrija as dimensoes deste");
                initializeGameMap();
                return false;
            }

            if(height < MIN_MAP_HEIGHT || height > MAX_MAP_HEIGHT ){
                System.out.println("O mapa precisa de ter entre " + MIN_MAP_HEIGHT + " e " + MAX_MAP_HEIGHT + " celulas de largura, escolha outro mapa ou corrija as dimensoes deste");
                initializeGameMap();
                return false;
            }

            
            mapa = new String[width][height];
            reader.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("O ficheiro não foi encontrado, verifique se este está na pasta correta.");
            setDefaultMap();
        }   
        catch(IOException er){
             System.out.println("Houve um problema ao ler o ficheiro");
             setDefaultMap();
            }    
        return true;
    }
        
        
        
        
        
        
    
    private static void readMapFile(String file) throws FileNotFoundException, IOException{
        try{
        FileReader inStream = new FileReader(file);
        BufferedReader reader = new BufferedReader(inStream);
        
        System.out.println("Lendo do input do ficheiro");
        
        //Indices para preencher o array bidimensional.
        int indexX = 0;
        int indexY = 0;
        
        String text = reader.readLine();
        
        while(text != null){ // percorrer o ficheiro linha a linha
            System.out.println(text);
            
            
            int tamanho = text.length(); //tamanho da linha usado para definir quais caracteres guardar no array
            
            int index = 0; 

            while(index < tamanho){ //percorrer a linha do ficheiro para preencher cada linha do array
                
                String teste ="" + text.charAt(index) + text.charAt(index+1); //copia a letra e o espaço(regra geral) se uma célula do mapa for duas letras também permite (ex. C1)
                
                mapa[indexX][indexY] = teste; //preenche o array
                indexX++; //aumenta o indice para preencher a proxima "coluna"
                index+=3; //valor usado como o mapa usa dois espaços por célula 
                
            }
            text = reader.readLine(); // próxima linha do text
            indexY++; // próxima linha do array
            indexX=0; // reset para a primeira "coluna" do array
        }
        reader.close(); //o ficheiro não é mais necessário
        }
        catch(FileNotFoundException e){
            System.out.println("O ficheiro não foi encontrado, verifique se este está na pasta correta.");
            setDefaultMap();
        }   
        catch(IOException er){
             System.out.println("Houve um problema ao ler o ficheiro");
             setDefaultMap();
            }    
    }
    
    private static void printMap(){
        System.out.print("   "); //Espaço antes das coordenadas X para alinhar com as células do mapa
        
        for(int i = 0; i<mapa.length;i++){ // Coordenadas X
            System.out.print(i);
            coordinatesOffset(i); // offset para alinhar com as células devido aos números das dezenas
            
        }
        System.out.println(); // Linha a baixo
        
        for(int i = 0; i<mapa[0].length;i++){
            System.out.print(i); //Coordenadas Y antes de começar as células
            coordinatesOffset(i); //offset para alinhar as células devido aos números das dezenas
            
            
            for(int j = 0; j<mapa.length;j++){ //Impressão das células do mapa deixando um espaço entre estas.
                System.out.print(mapa[j][i]);
                System.out.print(" ");
            }
            System.out.println(); // linha a baixo
        }
    }
    
           
    private static void coordinatesOffset(int i){
        if(i<10){
                System.out.print("  ");
            }
            else{
                System.out.print(" ");
            }
    }        
 
    private static void initializeGameMap() throws IOException{
        getMapSelection();
        int input = selectMap();

        if(initializeMapArray("" + textFiles[input])){
            readMapFile("" + textFiles[input]);      
        }
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

