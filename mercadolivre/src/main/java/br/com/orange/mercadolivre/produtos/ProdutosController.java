package br.com.orange.mercadolivre.produtos;

import br.com.orange.mercadolivre.usuario.RepositoryUsuario;
import br.com.orange.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ProdutosController {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    //1
    private RepositoryUsuario usuarioRepository;
    @Autowired
    //1
    private Uploader uploaderFake;

    @InitBinder(value = "novoProdutoRequest")
    public void init(WebDataBinder webDataBinder) {
        //1
        webDataBinder.addValidators((Validator) new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping(value = "/api/produtos")
    @Transactional
    //1
    public String cria(@RequestBody @Valid NovoProdutoRequest request) {
        //simulando o usuario logado
        //1
        Usuario dono = usuarioRepository.findByEmail("alberto@deveficiente.com").get();
        //1
        Produto produto = request.toModel(manager,dono);

        manager.persist(produto);
        return produto.toString();
    }

    @PostMapping(value = "/produtos/{id}/imagens")
    @Transactional
    //1
    public String adicionaImagens(@PathVariable("id") Long id,@Valid NovaImagemRequest request) {

        Usuario dono = usuarioRepository.findByEmail("alberto@deveficiente.com").get();
        Produto produto = manager.find(Produto.class, id);

        if(!produto.pertenceAoUsuario(dono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);

        return produto.toString();
    }
}

