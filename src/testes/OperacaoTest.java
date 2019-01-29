package testes;

import controller.Operacao;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OperacaoTest {

    @Test
    public void deveRetornarDozeNacoes() {
        Operacao operacao = new Operacao();

        Assert.assertEquals(164, operacao.numeroDeNacoes());
    }

    @Test
    public void deveRetornarNoveClubs() {
        Operacao operacao = new Operacao();

        Assert.assertEquals(647, operacao.numeroDeclubs());
    }

    @Test
    public void deveRetornarPrimeiroNomeDosDezPrimeirosJogadores() {
        Operacao operacao = new Operacao();

        List<String> nomes = Arrays.asList("C. Ronaldo dos Santos Aveiro", "Lionel Messi", "Neymar da Silva Santos Jr.", "Luis Suárez", "Manuel Neuer", "Robert Lewandowski", "David De Gea Quintana", "Eden Hazard", "Toni Kroos", "Gonzalo Higuaín", "Sergio Ramos García", "Kevin De Bruyne", "Thibaut Courtois", "Alexis Sánchez", "Luka Modrić", "Gareth Bale", "Sergio Agüero", "Giorgio Chiellini", "Gianluigi Buffon", "Paulo Dybala");

        Assert.assertEquals(nomes, operacao.primeiroNomeDosDezPrimeirosJogadores());
    }

    @Test
    public void deveRetornarNomeDosDezJogadoresComMaioresRescisoes(){
        Operacao operacao = new Operacao();

        List<String> nomes =  Arrays.asList("Neymar da Silva Santos Jr.", "Lionel Messi", "Luis Suárez", "C. Ronaldo dos Santos Aveiro", "Eden Hazard", "Toni Kroos", "Kevin De Bruyne", "Antoine Griezmann", "Robert Lewandowski", "Gareth Bale");

        Assert.assertEquals(nomes, operacao.nomeJogadoresDezMaioresRescisoes());
    }

    @Test
    public void deveRetornarNomeDosDezJogadoresMaisVelhos(){
        Operacao operacao = new Operacao();

        List<String> nomes = Arrays.asList("Barry Richardson", "Essam El Hadary", "Óscar Pérez", "Jimmy Walker", "Danny Coyne", "Chris Day", "Joaquim Manuel Sampaio Silva", "Kjetil Wæhler", "Timmy Simons", "Benjamin Nivet");

        Assert.assertEquals(nomes, operacao.dezJogadoresMaisVelhos());
    }

    @Test
    public void deveRetornarIdadeseQuantiaDelas(){
        Operacao operacao = new Operacao();

        Map<Integer, Integer> mapaIdade = new HashMap<>();

        mapaIdade.put(16, 18);
        mapaIdade.put(17, 270);
        mapaIdade.put(18, 682);
        mapaIdade.put(19, 1088);
        mapaIdade.put(20, 1252);
        mapaIdade.put(21, 1275);
        mapaIdade.put(22, 1324);
        mapaIdade.put(23, 1395);
        mapaIdade.put(24, 1321);
        mapaIdade.put(25, 1515);
        mapaIdade.put(26, 1199);
        mapaIdade.put(27, 1153);
        mapaIdade.put(28, 1053);
        mapaIdade.put(29, 1127);
        mapaIdade.put(30, 807);
        mapaIdade.put(31, 666);
        mapaIdade.put(32, 506);
        mapaIdade.put(33, 610);
        mapaIdade.put(34, 271);
        mapaIdade.put(35, 188);
        mapaIdade.put(36, 137);
        mapaIdade.put(37, 69);
        mapaIdade.put(39, 18);
        mapaIdade.put(38, 38);
        mapaIdade.put(40, 4);
        mapaIdade.put(41, 3);
        mapaIdade.put(43, 2);
        mapaIdade.put(44, 2);
        mapaIdade.put(47, 1);

        Assert.assertEquals(mapaIdade, operacao.mapaIdadesEQuantidade());
    }
}
