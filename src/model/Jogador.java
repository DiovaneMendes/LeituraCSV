package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador{
    private LeituraDeCSV leitura = new LeituraDeCSV();
    private String nome;
    private LocalDate dataNascimento;
    private Double salario;
    private BigDecimal clausula;

    public Jogador(String nome, LocalDate dataNascimento, BigDecimal clausula){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.clausula = clausula;
    }

    public Jogador(String nome, LocalDate dataNascimento, Double salario) {

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

    public BigDecimal getClausula() {
        return clausula;
    }
}