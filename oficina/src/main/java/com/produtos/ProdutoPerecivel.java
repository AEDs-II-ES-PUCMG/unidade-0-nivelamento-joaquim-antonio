package com.produtos;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

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

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public double valorDeVenda(){
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
    public String toString() {

        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format("NOME: " + getDescricao() + ": " + moeda.format(valorDeVenda())) + "\nVALIDADE: " + dataDeValidade.format(formatoData);
    }

    /**
     * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2 casas
     decimais.
     * Data de validade vai no formato dd/mm/aaaa
     * @return Uma string no formato "2; descrição;preçoDeCusto;margemDeLucro;dataDeValidade"
     */
    @Override
    public String gerarDadosTexto() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatada = String.format("%.2f", margemLucro).replace(",", ".");
        String dataFormatada = formato.format(dataDeValidade);
        return String.format("2;%s;%s;%s;%s", getDescricao(), precoFormatado, margemFormatada, dataFormatada);
    }

}
