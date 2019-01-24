import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static LeituraDeCSV leitura = new LeituraDeCSV();

    public static void main(String[] args) throws IOException {
        q5().forEach(System.out::println);
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

        String []nome = new String[tamanhoArray()];
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
    private static List<String> q5() throws IOException {

        List<Jogador> listaJogadores = new ArrayList<>();
        List<String> listaJogadoresMaisVelhos = new ArrayList<>();
        List<Integer> idadesJaVerificadas = new ArrayList<>();

        String []nome = new String[tamanhoArray()];
        LocalDate []dataNascimento = new LocalDate[tamanhoArray()];
        Double []salario = new Double[tamanhoArray()];

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

    // Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as
    // chaves são as idades e os valores a contagem.
    // (utilize a coluna `age`)
    private static Map<Integer, Integer> q6() {
        return null;
    }

    private static int tamanhoArray() throws IOException {
        return leitura.lerColuna("name").size();
    }

    private static int calculaIdade(LocalDate dataNascimento){
        return Period.between(dataNascimento, LocalDate.now())
                .getYears();
    }
}