import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    private static LeituraDeCSV leitura = new LeituraDeCSV();

    public static void main(String[] args) throws IOException {
        q5();
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

        String []nome = new String [tamanhoArray()];
        String []clausula = new String[tamanhoArray()];
        Map<String, String> jogadores = new HashMap<>();

        List<String> listaNomes = new ArrayList<>();
        List<String> listaClausulas = leitura.lerColuna("eur_release_clause");
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

    // Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
    // (utilize as colunas `full_name` e `birth_date`)
    private static void q5() throws IOException {
        leitura.lerColuna("full_name");
        leitura.lerColuna("birth_date");
        leitura.lerColuna("eur_wage");

        String []nome = new String [tamanhoArray()];
        String []dataNascimento = new String[tamanhoArray()];
        Map<String, String> jogadores = new HashMap<>();

        List<String> listaNomes = new ArrayList<>();
        List<String> maisVelhos = new ArrayList<>();
        List<LocalDate> listaDataNascimento = leitura.lerColuna("birth_date").stream()
                .map(data -> data.split("-"))
                .map(data -> LocalDate.of(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2])))
                .collect(Collectors.toList());

        nome = leitura.lerColuna("full_name").toArray(nome);
        dataNascimento = listaDataNascimento.toArray(dataNascimento);

        for(int i=0; i<tamanhoArray(); i++){
            jogadores.put(dataNascimento[i], nome[i]);
        }

        for(int i=0; i<10; i++){
            String maiorClausula = listaDataNascimento.stream()
                                                    .filter(s -> !maisVelhos.contains(s))
                                                    .max(Comparator.comparingDouble(Double::valueOf))
                                                    .get();

            maisVelhos.add(maiorClausula);
        }

        for (Map.Entry<String, String> mapaJogador : jogadores.entrySet()) {
            if(maisVelhos.contains(mapaJogador.getKey())){
                listaNomes.add(mapaJogador.getValue());
            }
        }
        ;
        Function<LocalDate, Integer> calculadoraIdade = (dataNascimento) -> Period
                .between(dataNascimento, LocalDate.now())
                .getYears();

        listaNomes.forEach(System.out::println);
    }

    // Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as
    // chaves são as idades e os valores a contagem.
    // (utilize a coluna `age`)
    private static Map<Integer, Integer> q6() {
        return null;
    }

    private static int tamanhoArray() throws IOException {
        return leitura.lerColuna("name").size();
    }
}