import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Operacao operacao = new Operacao();

    public static void main(String[] args) throws IOException {
        operacao.dezJogadoresMaisVelhos().forEach(System.out::println);
    }
}