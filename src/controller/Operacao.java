package controller;

import java.math.BigDecimal;
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
                .limit(20)
                .collect(Collectors.toList());
    }

    //q4: Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
    // (utilize as colunas `full_name` e `eur_release_clause`)
    public List<String> nomeJogadoresDezMaioresRescisoes(){
        List<String> topMaioresClausulas = leitura.lerColuna("eur_release_clause").stream()
                .filter(s -> !s.isEmpty())
                .map(s -> new BigDecimal(s))
                .sorted((n1, n2) -> n2.compareTo(n1))
                .limit(10)
                .map(String::valueOf)
                .collect(Collectors.toList());

        List<String> nomes = new ArrayList<>();

        for(Map.Entry<String, String> m : leitura.lerDuasColunas("eur_release_clause", "full_name").entrySet()){
            if(topMaioresClausulas.contains(m.getKey()))
                nomes.add(m.getValue());
        }

        Collections.reverse(nomes);

        return nomes;
    }

    //q5: Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
    // (utilize as colunas `full_name` e `birth_date`)
    public List<String> dezJogadoresMaisVelhos(){
        return leitura.listaJogadoresQ5().stream()
                .sorted((j1, j2) -> j1.getDataNascimento().compareTo(j2.getDataNascimento()))
                .map(jogador -> jogador.getNome())
                .limit(10)
                .collect(Collectors.toList());
    }

    //q6: Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as
    // chaves são as idades e os valores a contagem.
    // (utilize a coluna `age`)
    public Map<Integer, Integer> mapaIdadesEQuantidade() {
        Map<Integer, Integer> mapaIdade = new HashMap<>();

        List<Integer> listaIdades = leitura.lerColuna("age").stream()
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());

        for(Integer idade : new HashSet<>(listaIdades)){

            int contador = (int) listaIdades.stream()
                    .filter(n -> n.equals(idade))
                    .count();

            mapaIdade.put(idade, contador);
        }

        return mapaIdade;
    }
}