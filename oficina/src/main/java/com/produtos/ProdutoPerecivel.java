package com.produtos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProdutoPerecivel extends Produto {

    private final double DESCONTO = 0.25;
    private final int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade){

        super(desc, precoCusto, margemLucro);
        if(validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Produto vencido nao pode ser cadastrado");
        }
        dataDeValidade = validade;

    }

    @Override
    public double valorVenda(){
        LocalDate diaAtual = LocalDate.now();

        //testa se produto vencido
        if(dataDeValidade.isBefore(diaAtual))
            throw new IllegalArgumentException("Produto vencido não pode ser vendido");

        //testa se produto elegivel para desconto
        if(ChronoUnit.DAYS.between(diaAtual, dataDeValidade) <= PRAZO_DESCONTO)
            return (precoCusto + (precoCusto * margemLucro)) * (1 - DESCONTO);
        else
            return precoCusto + (precoCusto * margemLucro);
    }

    @Override
    public String toString(){
        return getDescricao() +
                "\nO custo é: " + valorVenda() +
                "\nE a margem de lucro é: " + margemLucro +
                "\nA data de validade é: " + dataDeValidade +
                "\nO prazo para desconto é de " + PRAZO_DESCONTO + " dias";

    }





}
