package controller;

import model.Jogador;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Operacao {

    private LeituraDeCSV leitura = new LeituraDeCSV();

    public Operacao(){

    }

    //q1: Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo? = OK
    public int numeroDeNacoes(){
        return new HashSet<>(leitura.lerColuna("nationality"))
                    .size();
    }

    //q2: Quantos clubes (coluna `club`) diferentes existem no arquivo?
    // Obs: Existem jogadores sem clube.
    public int numeroDeclubs(){
        return (int) new HashSet<>(leitura.lerColuna("club"))
                    .stream()
                    .filter(s -> !s.isEmpty())
                    .count();
    }

    //q3: Liste o primeiro nome (coluna `full_name`) dos 10 primeiros jogadores.
    public List<String> primeiroNomeDosDezPrimeirosJogadores(){
        return leitura.lerColuna("full_name")
                    .stream()
                    .map(s -> s.split(" "))
                    .map(strings -> strings[0])
                    .limit(10)
                    .collect(Collectors.toList());
    }

    //q4: Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
    // (utilize as colunas `full_name` e `eur_release_clause`)
    public List<String> nomeJogadoresDezMaioresRescisoes(){
        List<String> listaClausulas = leitura.lerColuna("eur_release_clause");
        String[] nome = new String [tamanhoArray()];
        String[] clausula = new String[tamanhoArray()];
        Map<String, String> jogadores = new HashMap<>();

        List<String> listaNomes = new ArrayList<>();
        List<String> topMaioresClausulas = new ArrayList<>();


        nome = leitura.lerColuna("full_name").toArray(nome);
        clausula = listaClausulas.toArray(clausula);

        for(int i=0; i<tamanhoArray(); i++){
            jogadores.put(clausula[i], nome[i]);
        }

        for(int i=0; i<10; i++){
            String maiorClausula = listaClausulas.stream()
                    .filter(s -> !topMaioresClausulas.contains(s))
                    .max(Comparator.comparingDouble(Double::valueOf))
                    .get();

            topMaioresClausulas.add(maiorClausula);
        }

        for (Map.Entry<String, String> mapaJogador : jogadores.entrySet()) {
            if(topMaioresClausulas.contains(mapaJogador.getKey())){
                listaNomes.add(mapaJogador.getValue());
            }
        }
        return listaNomes;
    }

    //q5: Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
    // (utilize as colunas `full_name` e `birth_date`)
    public List<String> dezJogadoresMaisVelhos(){
        List<Jogador> listaJogadores = new ArrayList<>();
        List<String> listaJogadoresMaisVelhos = new ArrayList<>();
        List<Integer> idadesJaVerificadas = new ArrayList<>();

        String[] nome = new String[tamanhoArray()];
        LocalDate[] dataNascimento = new LocalDate[tamanhoArray()];
        Double[] salario = new Double[tamanhoArray()];

        nome = leitura.lerColuna("full_name").toArray(nome);

        dataNascimento = leitura.lerColuna("birth_date").stream()
                .map(string -> string.split("-"))
                .map(data -> LocalDate.of(Integer.valueOf(data[0]),
                        Integer.valueOf(data[1]),
                        Integer.valueOf(data[2])))
                .collect(Collectors.toList())
                .toArray(dataNascimento);

        salario = leitura.lerColuna("eur_wage").stream()
                .map(Double::new)
                .collect(Collectors.toList())
                .toArray(salario);

        List<Integer> listaIdades = Arrays.stream(dataNascimento)
                .mapToInt(idade -> calculaIdade(idade))
                .boxed()
                .collect(Collectors.toList());

        for(int i=0; i<tamanhoArray(); i++) {
            listaJogadores.add(new Jogador(nome[i], dataNascimento[i], salario[i]));
        }

        for(int i=0; i<10; i++){
            List<Jogador> listaApoio = new ArrayList<>();

            int maisVelho = listaIdades.stream()
                    .filter(idade -> !idadesJaVerificadas.contains(idade))
                    .max(Comparator.comparingInt(Integer::intValue))
                    .orElse(0);

            idadesJaVerificadas.add(maisVelho);

            for(Jogador jogador : listaJogadores){
                if(maisVelho != 0 && calculaIdade(jogador.getDataNascimento()) == maisVelho){
                    listaApoio.add(jogador);
                }
            }

            Double maiorSalarioDaListaApoio = listaApoio.stream()
                    .mapToDouble(Jogador::getSalario)
                    .max()
                    .orElse(0L);

            for(Jogador jogador: listaApoio){
                if(jogador.getSalario().equals(maiorSalarioDaListaApoio)){
                    listaJogadoresMaisVelhos.add(jogador.getNome());
                }
            }
        }

        return listaJogadoresMaisVelhos;
    }

    //q6: Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as
    // chaves são as idades e os valores a contagem.
    // (utilize a coluna `age`)
    public Map<Integer, Integer> mapaIdadesEQuantidade() {

        Map<Integer, Integer> mapaIdade = new HashMap<>();
        List<Integer> listaIdades;
        List<Integer> idadesSemRepeticao = new ArrayList<>();

        listaIdades = leitura.lerColuna("age").stream()
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());

        idadesSemRepeticao.addAll(new HashSet<>(listaIdades));

        for(Integer idade : idadesSemRepeticao){
            mapaIdade.put(idade, 0);
        }

        for(Map.Entry<Integer, Integer> map: mapaIdade.entrySet()){
            int contador = (int) listaIdades.stream()
                    .filter(idade -> idade.equals(map.getKey()))
                    .count();

            map.setValue(contador);
        }
        return mapaIdade;
    }

    private int tamanhoArray() {
        return leitura.lerColuna("ID").size();
    }

    private int calculaIdade(LocalDate dataNascimento){
        return Period.between(dataNascimento, LocalDate.now())
                .getYears();
    }
}
