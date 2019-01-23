import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static LeituraDeCSV leitura = new LeituraDeCSV();

    public static void main(String[] args) throws IOException {
        q4();
    }

    // Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo? = OK
    private static int q1() throws IOException {
        return new HashSet<>(leitura.lerColuna("nationality"))
                .size();
    }

    // Quantos clubes (coluna `club`) diferentes existem no arquivo?
    // Obs: Existem jogadores sem clube.
    private static int q2() throws IOException {
        return (int) new HashSet<>(leitura.lerColuna("club"))
                .stream()
                .filter(s -> !s.equals(""))
                .count();
    }

    // Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
    private static List<String> q3() throws IOException {
        List<Object> nomes = new ArrayList<>();

        Object[] a = leitura.lerColuna("full_name")
                            .stream()
                            .map(s -> s.split(" "))
                            .map(strings -> strings[0])
                            .toArray();

        for(int i=0; i<10; i++){
            nomes.add(a[i]);
        }

        return nomes.stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
    }

    // Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
    // (utilize as colunas `full_name` e `eur_release_clause`)
    private static List<String> q4() throws IOException{

        int tamanhoArray = leitura.lerColuna("name").size();

        String []nomes = new String [tamanhoArray];
        String []clausulas = new String[tamanhoArray];
        Map<String, String> jogadores = new HashMap<>();

        List<String> listaNomes = new ArrayList<>();
        List<String> listaClausulas = leitura.lerColuna("eur_release_clause");
        List<String> topMaioresClausulas = new ArrayList<>();

        nomes = leitura.lerColuna("full_name").toArray(nomes);
        clausulas = listaClausulas.toArray(clausulas);

        for(int i=0; i<tamanhoArray; i++){
            jogadores.put(clausulas[i], nomes[i]);
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

    // Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
    // (utilize as colunas `full_name` e `birth_date`)
    private static List<String> q5() {
        return null;
    }

    // Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as
    // chaves são as idades e os valores a contagem.
    // (utilize a coluna `age`)
    private static Map<Integer, Integer> q6() {
        return null;
    }
}