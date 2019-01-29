package testes;

import controller.LeituraDeCSV;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        Assert.assertEquals(nacao, leitura.lerColuna("nationality").stream().limit(19).collect(Collectors.toList()));
        Assert.assertEquals(liga, leitura.lerColuna("league").stream().limit(19).collect(Collectors.toList()));
    }

    @Test
    public void deveRetornarIndiceDoTitulo(){
        LeituraDeCSV leitura = new LeituraDeCSV();

        Assert.assertEquals(7, leitura.buscaIndiceTitulo("league"));
        Assert.assertEquals(16, leitura.buscaIndiceTitulo("eur_value"));
    }

    @Test
    public void deveRetornarTitulosDasColunas(){
        LeituraDeCSV leitura = new LeituraDeCSV();

        String[] titulos = {"ID", "name", "full_name", "club", "club_logo", "special", "age", "league",
                            "birth_date", "height_cm", "weight_kg", "body_type", "real_face", "flag",
                            "nationality","photo", "eur_value", "eur_wage", "eur_release_clause"};

        Assert.assertArrayEquals(titulos, Arrays.stream(leitura.lerTitulos()).limit(19).toArray());
    }
}
