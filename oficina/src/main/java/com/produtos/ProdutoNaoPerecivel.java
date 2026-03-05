package com.produtos;

import java.time.format.DateTimeFormatter;

public class ProdutoNaoPerecivel extends Produto{

    public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro, int quantidadeEmEstoque){
        super(desc, precoCusto, margemLucro, quantidadeEmEstoque);
    }

    public ProdutoNaoPerecivel(String desc, double precoCusto, int quantidadeEmEstoque){
        super(desc, precoCusto, quantidadeEmEstoque);
    }

    /**
     * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2 casas
     decimais.
     * @return Uma string no formato "1; descrição;preçoDeCusto;margemDeLucro"
     */
    @Override
    public String gerarDadosTexto() {
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatada = String.format("%.2f", margemLucro).replace(",", ".");
        return String.format("1;%s;%s;%s", getDescricao(), precoFormatado, margemFormatada);
    }
}
