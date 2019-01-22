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
    private static void q4() throws IOException{

        int tamanhoArray = leitura.lerColuna("name").size();

        String []nomes = new String [tamanhoArray];
        Double []clausulas = new Double[tamanhoArray];
        Map<String, Double> jogadores = new HashMap<>();

        nomes = leitura.lerColuna("full_name").toArray(nomes);
        clausulas = leitura.lerColuna("eur_release_clause")
                            .stream()
                            .map(Double::new)
                            .collect(Collectors.toList())
                            .toArray(clausulas);


        for(int i=0; i<tamanhoArray; i++){
            jogadores.put(nomes[i], clausulas[i]);
        }

        for (Map.Entry<String, Double> entrada : jogadores.entrySet()) {
            System.out.println(entrada.getValue());
        }
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