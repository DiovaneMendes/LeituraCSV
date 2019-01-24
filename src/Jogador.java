import java.time.LocalDate;

public class Jogador {

    private String nome;
    private LocalDate dataNascimento;
    private Double salario;

    public Jogador(String nome, LocalDate dataNascimento, Double salario) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", salario=" + salario +
                '}';
    }
}
