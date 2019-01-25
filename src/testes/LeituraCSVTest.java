package testes;

import controller.LeituraDeCSV;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LeituraCSVTest {

    @Test
    public void deveRetornarListaComConteudoDaLista(){
        LeituraDeCSV leitura = new LeituraDeCSV();

        List<String> nacao = Arrays.asList("Portugal", "Argentina", "Brazil", "Uruguay", "Germany", "Poland",
                                            "Spain", "Belgium", "Germany", "Argentina", "Spain", "Belgium", "Belgium",
                                            "Chile", "Croatia", "Wales", "Argentina", "Italy", "Italy");

        List<String> liga = Arrays.asList("Spanish Primera División", "Spanish Primera División", "French Ligue 1",
                                            "Spanish Primera División", "German Bundesliga", "German Bundesliga",
                                            "English Premier League", "English Premier League", "Spanish Primera División",
                                            "Italian Serie A", "Spanish Primera División", "English Premier League",
                                            "English Premier League", "English Premier League", "Spanish Primera División",
                                            "Spanish Primera División", "English Premier League", "Italian Serie A",
                                            "Italian Serie A");

        Assert.assertEquals(nacao, leitura.lerColuna("nationality"));
        Assert.assertEquals(liga, leitura.lerColuna("league"));
    }

    @Test
    public void deveRetornarIndiceDoTitulo(){
        LeituraDeCSV leitura = new LeituraDeCSV();

        Assert.assertEquals(6, leitura.buscaIndiceTitulo("league"));
        Assert.assertEquals(13, leitura.buscaIndiceTitulo("eur_value"));
    }

    @Test
    public void deveRetornarTitulosDasColunas(){
        LeituraDeCSV leitura = new LeituraDeCSV();

        String[] titulos = {"ID", "name", "full_name", "club", "special", "age", "league",
                            "birth_date", "height_cm", "weight_kg", "body_type", "real_face",
                            "nationality", "eur_value", "eur_wage", "eur_release_clause"};

        Assert.assertArrayEquals(titulos, leitura.lerTitulos());
    }
}