package com.produtos;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

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

    public double valorDeVenda(){
        return precoCusto + (precoCusto*margemLucro);
    }

    @Override
    public String toString() {

        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        return String.format("NOME: " + descricao + ": " + moeda.format(valorDeVenda()));
    }

    /**
            * Igualdade de produtos: caso possuam o mesmo nome/descrição.
* @param obj Outro produto a ser comparado
* @return booleano true/false conforme o parâmetro possua a descrição igual ou não a este produto.
            */
    @Override
    public boolean equals(Object obj){
        Produto outro = (Produto)obj;
        return this.descricao.toLowerCase().equals(outro.descricao.toLowerCase());
    }

    /**
            * Gera uma linha de texto a partir dos dados do produto
* @return Uma string no formato "tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade]"
            */
    public abstract String gerarDadosTexto();

    /**
     * Cria um produto a partir de uma linha de dados em formato texto. A linha de dados deve estar de acordo com a
     formatação
     * "tipo;descrição;preçoDeCusto;margemDeLucro;[dataDeValidade]"
     * ou o funcionamento não será garantido. Os tipos são 1 para produto não perecível e 2 para perecível.
     * @param linha Linha com os dados do produto a ser criado.
     * @return Um produto com os dados recebidos
     */
    static Produto criarDoTexto(String linha){
        Produto novoProduto = null;
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] atributos = linha.split(";");
        int tipo = Integer.parseInt(atributos[0]);
        String descricao = atributos[1];
        double precoCusto = Double.parseDouble(atributos[2]);
        double margemDeLucro = Double.parseDouble(atributos[3]);
        if(tipo==1){
            novoProduto = new ProdutoNaoPerecivel(descricao, precoCusto, margemDeLucro);
        }else{
            LocalDate dataValidade = LocalDate.parse(atributos[4], formatoData);
            novoProduto = new ProdutoPerecivel(descricao, precoCusto, margemDeLucro, dataValidade);
        }
        return novoProduto;
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
