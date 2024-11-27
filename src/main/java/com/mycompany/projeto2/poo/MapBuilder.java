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

    private final int  MIN_MAP_WIDTH = 20;
    private final int MAX_MAP_WIDTH = 70;
    private final int MIN_MAP_HEIGHT = 7;
    private final int MAX_MAP_HEIGHT = 22;
    private Cell[][] map;
    private File[] textFiles;


    public MapBuilder() throws IOException{
        initializeGameMap();


    }
<<<<<<< Updated upstream
    
=======

>>>>>>> Stashed changes
    private boolean getMapSelection(){
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
            System.out.println("Nao existem maps na pasta. Verifique se estes la estao.");
            setDefaultMap();
            return false;
        }
        return true;
    }

    private int selectMap(){

        System.out.println("Escolha um dos maps");
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
<<<<<<< Updated upstream
    
=======

>>>>>>> Stashed changes
    private Cell[][] getDefaultMap(){
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
<<<<<<< Updated upstream
        
        Cell[][] newMap = new Cell[originalHeight][originalWidth];
        
        
        for (int i = 0; i < originalWidth; i++) {
            for (int j = 0; j < originalHeight; j++) {
                String c =map[i][j];
           
=======

        Cell[][] newMap = new Cell[originalHeight][originalWidth];


        for (int i = 0; i < originalWidth; i++) {
            for (int j = 0; j < originalHeight; j++) {
                String c =map[i][j];

>>>>>>> Stashed changes
                newMap[j][i] = new Cell(c);  // Swap row and column indices
            }
        }

        return newMap;
    }

    private void setDefaultMap(){
        System.out.println("Se quiser carregar o map predefinido clique no enter");
<<<<<<< Updated upstream
            Scanner scan = new Scanner(System.in);
            if(scan.nextLine() != null){
                map = getDefaultMap();
                
            }
=======
        Scanner scan = new Scanner(System.in);
        if(scan.nextLine() != null){
            map = getDefaultMap();

        }
>>>>>>> Stashed changes
    }


    private boolean initializeMapArray(String file) throws FileNotFoundException, IOException{
        try{
            FileReader inStream = new FileReader(file); // falta meter os try catch
            BufferedReader reader = new BufferedReader(inStream);

            //medidas do map
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
                System.out.println("O map precisa de ter entre " + MIN_MAP_WIDTH + " e " + MAX_MAP_WIDTH + " celulas de comprimento, escolha outro map ou corrija as dimensoes deste");
                initializeGameMap();
                return false;
            }

            if(height < MIN_MAP_HEIGHT || height > MAX_MAP_HEIGHT ){
                System.out.println("O map precisa de ter entre " + MIN_MAP_HEIGHT + " e " + MAX_MAP_HEIGHT + " celulas de largura, escolha outro map ou corrija as dimensoes deste");
                initializeGameMap();
                return false;
            }


            map = new Cell[width][height];
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







    private void readMapFile(String file) throws FileNotFoundException, IOException{
        try{
<<<<<<< Updated upstream
        FileReader inStream = new FileReader(file);
        BufferedReader reader = new BufferedReader(inStream);
        
        
        
        //Indices para preencher o array bidimensional.
        int indexX = 0;
        int indexY = 0;
        
        String text = reader.readLine();
            try{
            while(text != null){ // percorrer o ficheiro linha a linha


                int tamanho = text.length(); //tamanho da linha usado para definir quais caracteres guardar no array

                int index = 0; 

                while(index < tamanho){ //percorrer a linha do ficheiro para preencher cada linha do array

                        String c ="" + text.charAt(index) + text.charAt(index+1); //copia a letra e o espaço(regra geral) se uma célula do map for duas letras também permite (ex. C1)
                        
                         
                        try{
                            map[indexX][indexY] = new Cell(c);//preenche o array
                        } 
                        catch(IllegalArgumentException e){
                            map[indexX][indexY] = new Cell("- ");
                        }
                        
                        indexX++; //aumenta o indice para preencher a proxima "coluna"
                        index+=3; //valor usado como o map usa dois espaços por célula 
                    }

                text = reader.readLine(); // próxima linha do text
                indexY++; // próxima linha do array
                indexX=0; // reset para a primeira "coluna" do array
            }
            
            reader.close(); //o ficheiro não é mais necessário
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Existe algum problema na formatacao do ficheiro do mapa que escolheu");
                setDefaultMap();
            }
        }    
        catch(FileNotFoundException e){
            System.out.println("O ficheiro não foi encontrado, verifique se este está na pasta correta.");
            setDefaultMap();
        }   
        catch(IOException er){
             System.out.println("Houve um problema ao ler o ficheiro");
             setDefaultMap();
            }
        
        
        int height = map[0].length;
        int width = map.length;
        
        boolean error = false;
        for(int i = 0; i<height;i++){
            for(int j = 0; j<width;j++){ 
                if(map[j][i] == null){
                    error = true;
                }
            }           
        }
        if(error){
            System.out.println("Existe algum problema na formatacao do ficheiro do mapa que escolheu");
            setDefaultMap();
        }
        
        
    }
    
    
 
    private void initializeGameMap() throws IOException{
        if(getMapSelection()){
            int input = selectMap();

            if(initializeMapArray("" + textFiles[input])){
                readMapFile("" + textFiles[input]);      
            }
        
        }
        
=======
            FileReader inStream = new FileReader(file);
            BufferedReader reader = new BufferedReader(inStream);



            //Indices para preencher o array bidimensional.
            int indexX = 0;
            int indexY = 0;

            String text = reader.readLine();
            try{
                while(text != null){ // percorrer o ficheiro linha a linha


                    int tamanho = text.length(); //tamanho da linha usado para definir quais caracteres guardar no array

                    int index = 0;

                    while(index < tamanho){ //percorrer a linha do ficheiro para preencher cada linha do array

                        String c ="" + text.charAt(index) + text.charAt(index+1); //copia a letra e o espaço(regra geral) se uma célula do map for duas letras também permite (ex. C1)


                        try{
                            map[indexX][indexY] = new Cell(c);//preenche o array
                        }
                        catch(IllegalArgumentException e){
                            map[indexX][indexY] = new Cell("- ");
                        }

                        indexX++; //aumenta o indice para preencher a proxima "coluna"
                        index+=3; //valor usado como o map usa dois espaços por célula
                    }

                    text = reader.readLine(); // próxima linha do text
                    indexY++; // próxima linha do array
                    indexX=0; // reset para a primeira "coluna" do array
                }

                reader.close(); //o ficheiro não é mais necessário
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Existe algum problema na formatacao do ficheiro do mapa que escolheu");
                setDefaultMap();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("O ficheiro não foi encontrado, verifique se este está na pasta correta.");
            setDefaultMap();
        }
        catch(IOException er){
            System.out.println("Houve um problema ao ler o ficheiro");
            setDefaultMap();
        }


        int height = map[0].length;
        int width = map.length;

        boolean error = false;
        for(int i = 0; i<height;i++){
            for(int j = 0; j<width;j++){
                if(map[j][i] == null){
                    error = true;
                }
            }
        }
        if(error){
            System.out.println("Existe algum problema na formatacao do ficheiro do mapa que escolheu");
            setDefaultMap();
        }


>>>>>>> Stashed changes
    }



    private void initializeGameMap() throws IOException{
        if(getMapSelection()){
            int input = selectMap();

            if(initializeMapArray("" + textFiles[input])){
                readMapFile("" + textFiles[input]);
            }

        }

    }

    public Cell[][] getMap(){
        return map;
    }
}


















