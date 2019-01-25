
public class Main {
    private static Operacao operacao = new Operacao();

    public static void main(String[] args){
        operacao.primeiroNomeDosDezPrimeirosJogadores().forEach(System.out::println);;
    }
}