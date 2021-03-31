package br.com.orange.mercadolivre.detalheproduto;

import br.com.orange.mercadolivre.produtos.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {

    private String nome;
    private String descricao;

    public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

