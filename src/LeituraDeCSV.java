import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LeituraDeCSV {
    LeituraDeCSV(){

    }

    private String[] lerTitulos() throws IOException {
        return Files.lines(Paths.get("data.csv"))
                    .findFirst()
                    .toString()
                    .split(",");
    }

    private int buscaIndiceTitulo(String titulo) throws IOException {
        int contador = 0;
        String[] titulos = lerTitulos();

        for (int i=0; i<titulos.length; i++){
            if(!titulos[i].equals(titulo)){
                contador++;
            }else{
                break;
            }
        }
        return contador;
    }

    public List<String> lerColuna(String titulo) throws IOException {
        int quero = buscaIndiceTitulo(titulo);

        return Files.lines(Paths.get("data.csv"))
                    .skip(1)
                    .map(linha -> linha.split(","))
                    .map(s -> s[quero])
                    .collect(Collectors.toList());
    }
}