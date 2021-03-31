package br.com.orange.mercadolivre.adicionapergunta;


import br.com.orange.mercadolivre.produtos.Produto;
import br.com.orange.mercadolivre.usuario.RepositoryUsuario;
import br.com.orange.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class NovaPerguntaController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    //1
    private RepositoryUsuario usuarioRepository;
    //1
    @Autowired
    private Emails emails;

    @PostMapping(value = "/api/produtos/{id}/perguntas")
    @Transactional
    //1
    public String cria(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id) {
        //1
        Produto produto = manager.find(Produto.class,id);
        //1
        Usuario interessada = usuarioRepository.findByEmail("seuemail3@deveficiente.com").get();

        //1
        Pergunta novaPergunta = request.toModel(interessada,produto);
        manager.persist(novaPergunta);

        emails.novaPergunta(novaPergunta);

        return novaPergunta.toString();
    }

}