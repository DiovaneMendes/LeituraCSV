import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        LeituraDeCSV leitura = new LeituraDeCSV();

        leitura.lerColuna("nationality")
                .forEach(System.out::println);
    }
}