import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class OperacaoTest {

    @Test
    public void deveRetornarDozeNacoes() {
        Operacao operacao = new Operacao();

        assertEquals(12, operacao.numeroDeNacoes());
    }

    @Test
    public void deveRetornarNoveClubs() {
        Operacao operacao = new Operacao();

        assertEquals(9, operacao.numeroDeclubs());
    }

    @Test
    public void deveRetornarPrimeiroNomeDosDezPrimeirosJogadores() {
        Operacao operacao = new Operacao();

        List<String> nomes = Arrays.asList("C.", "Lionel", "Neymar", "Luis", "Manuel", "Robert",
                                            "David", "Eden", "Toni", "Gonzalo");

        assertEquals(nomes, operacao.primeiroNomeDosDezPrimeirosJogadores());
    }

    @Test
    public void deveRetornarNomeDosDezJogadoresComMaioresRescisoes(){
        Operacao operacao = new Operacao();

        List<String> nomes = Arrays.asList("Neymar da Silva Santos Jr.", "Luis Suárez", "Robert Lewandowski",
                                            "Toni Kroos", "Kevin De Bruyne", "Gonzalo Higuaín", "Eden Hazard",
                                            "Gareth Bale", "Lionel Messi", "C. Ronaldo dos Santos Aveiro");

        assertEquals(nomes, operacao.nomeJogadoresDezMaioresRescisoes());
    }

    @Test
    public void deveRetornarNomeDosDezJogadoresMaisVelhos(){
        Operacao operacao = new Operacao();

        List<String> nomes = Arrays.asList("Gianluigi Buffon","Giorgio Chiellini", "C. Ronaldo dos Santos Aveiro",
                                            "Luis Suárez", "Lionel Messi", "Robert Lewandowski", "Gareth Bale",
                                            "Eden Hazard", "Kevin De Bruyne", "Neymar da Silva Santos Jr.");

        assertEquals(nomes, operacao.dezJogadoresMaisVelhos());
    }

    @Test
    public void deveRetornarIdadeseQuantiaDelas(){
        Operacao operacao = new Operacao();

        Map<Integer, Integer> mapaIdade = new HashMap<>();

        mapaIdade.put(32, 2);
        mapaIdade.put(39, 1);
        mapaIdade.put(25, 2);
        mapaIdade.put(26, 3);
        mapaIdade.put(27, 2);
        mapaIdade.put(28, 2);
        mapaIdade.put(29, 2);
        mapaIdade.put(30, 2);
        mapaIdade.put(31, 3);

        assertEquals(mapaIdade, operacao.mapaIdadesEQuantidade());
    }
}
