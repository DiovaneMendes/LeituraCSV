package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeituraDeCSV {
    public LeituraDeCSV(){

    }

    public List<String> lerColuna(String titulo){
        int indice = buscaIndiceTitulo(titulo);

        try {
            return Files.lines(Paths.get("data.csv"))
                    .skip(1)
                    .map(linha -> linha.split(","))
                    .map(s -> s[indice])
                    .collect(Collectors.toList());

        }catch(IOException e){
            erro();
        }
        return new ArrayList<>();
    }

    public int buscaIndiceTitulo(String titulo){
        int contador = 0;
        String[] titulos = lerTitulos();

        for (int i=0; i<titulos.length; i++){
            if(!titulos[i].equals(titulo)){
                contador++;
            }else{
                break;
            }
        }
        return contador;
    }

    public String[] lerTitulos(){
        try {
            return Files.lines(Paths.get("data.csv"))
                    .findFirst()
                    .map(s -> s.split(","))
                    .get();

        }catch(IOException e){
            erro();
        }
        return new String[0];
    }

    private void erro(){
        System.out.println("Erro ao ler arquivo CSV!");
    }
}