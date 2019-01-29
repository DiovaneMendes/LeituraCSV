package controller;

import model.Jogador;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LeituraDeCSV {
    public LeituraDeCSV(){

    }

    public List<String> lerColuna(String titulo){
        int indice = buscaIndiceTitulo(titulo);

        try {
            return Files.lines(Paths.get(caminhoArquivo()))
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
            return Files.lines(Paths.get(caminhoArquivo()))
                    .findFirst()
                    .map(s -> s.split(","))
                    .get();

        }catch(IOException e){
            erro();
        }
        return new String[0];
    }

    public Map<String, String> lerDuasColunas(String titulo1, String titulo2){
        Map<String, String> mapa = new TreeMap<>();

        try {
            Files.lines(Paths.get(caminhoArquivo()))
                    .skip(1)
                    .map(s -> s.split(","))
                    .peek(s -> mapa.put(s[buscaIndiceTitulo(titulo1)], s[buscaIndiceTitulo(titulo2)]))
                    .collect(Collectors.toList());
        }catch(IOException e){
            erro();
        }
        return mapa;
    }

    public List<Jogador> listaJogadoresQ5(){
        List<Jogador> listaJogador = new ArrayList<>();
        try {
            listaJogador = Files.lines(Paths.get(caminhoArquivo()))
                    .skip(1)
                    .map(s -> s.split(","))
                    .map(s -> {
                        String data = s[buscaIndiceTitulo("birth_date")];

                        return new Jogador(s[buscaIndiceTitulo("full_name")],

                                LocalDate.of(Integer.parseInt(data.substring(0, 4)),
                                        Integer.parseInt(data.substring(5, 7)),
                                        Integer.parseInt(data.substring(8, 10))) ,

                                new BigDecimal(s[buscaIndiceTitulo("eur_wage")]));
                    })
                    .collect(Collectors.toList());
        }catch(IOException e){
            erro();
        }
        return listaJogador;
    }

    private void erro(){
        System.out.println("Erro ao ler arquivo CSV!");
    }

    private String caminhoArquivo(){
        return "/home/diovane/codenation/java-3/src/main/resources/data.csv";
    }
}
