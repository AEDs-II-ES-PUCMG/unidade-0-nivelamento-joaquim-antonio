package com.produtos;

import java.util.Locale;

public abstract class Produto {

    final double MARGEM_PADRAO = 0.2;
    private String descricao;
    protected double precoCusto;
    protected double margemLucro;

    public Produto(){}

    protected Produto(String desc, double precoCusto, double margemLucro){
        init(desc, precoCusto, margemLucro);
    }

    protected Produto(String desc, double precoCusto){
        init(desc, precoCusto, MARGEM_PADRAO);
    }

    private void init(String desc, double precoCusto, double margemLucro){
        if(precoCusto < 0)
            throw new IllegalArgumentException("preco negativo");

        if(margemLucro < 0)
            throw new IllegalArgumentException("margem negativa");

        this.descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
    }

    public double valorVenda(){
        return precoCusto + (precoCusto*margemLucro);
    }

    @Override
    public String toString(){
        return String.format(Locale.of("pt", "BR"), "Produto %s, o custo é R$ %.2f, e a margem de lucro é: %.2f", descricao, valorVenda(), margemLucro);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }
}
