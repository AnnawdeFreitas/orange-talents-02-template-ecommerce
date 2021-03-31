package br.com.orange.mercadolivre.adicionaopiniao;

import br.com.orange.mercadolivre.produtos.Produto;
import br.com.orange.mercadolivre.usuario.Usuario;
import br.com.orange.mercadolivre.validacoes.UsuarioLogado;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class AdicionaOpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/api/produtos/{id}/opiniao")
    @Transactional
    public String adiciona(@RequestBody @Valid NovaOpiniaoRequest request,
                           @PathVariable("id") Long id,@AuthenticationPrincipal UsuarioLogado usuarioLogado ) {

        Produto produto = manager.find(Produto.class, id);

        Usuario consumidor = usuarioLogado.get();

        Opiniao novaOpiniao = request.toModel(produto, consumidor);
        manager.persist(novaOpiniao);

        return novaOpiniao.toString();
    }

}