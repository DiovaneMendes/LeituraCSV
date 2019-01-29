package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador{
    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal clausula;

    public Jogador(String nome, LocalDate dataNascimento, BigDecimal clausula){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.clausula = clausula;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getClausula() {
        return clausula;
    }
}