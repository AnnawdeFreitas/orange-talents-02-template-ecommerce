package br.com.orange.mercadolivre.adicionapergunta;

import br.com.orange.mercadolivre.produtos.Produto;
import br.com.orange.mercadolivre.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "NovaPerguntaRequest [titulo=" + titulo + "]";
    }

    public Pergunta toModel(@NotNull @Valid Usuario interessada,
                            @NotNull @Valid Produto produto) {
        return new Pergunta(titulo, interessada, produto);
    }

}
