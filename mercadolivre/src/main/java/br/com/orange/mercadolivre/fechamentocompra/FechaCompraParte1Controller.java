package br.com.orange.mercadolivre.fechamentocompra;

import br.com.orange.mercadolivre.adicionapergunta.Emails;
import br.com.orange.mercadolivre.produtos.Produto;
import br.com.orange.mercadolivre.usuario.RepositoryUsuario;
import br.com.orange.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechaCompraParte1Controller {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private RepositoryUsuario usuarioRepository;
    @Autowired
    private Emails emails;

    @PostMapping(value = "/api/compras")
    @Transactional
    //1
    public String cria(@RequestBody @Valid NovaCompraRequest request,
                       UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Produto produtoASerComprado = manager.find(Produto.class,
                request.getIdProduto());

        int quantidade = request.getQuantidade();
        boolean abateu = produtoASerComprado.abataEstoque(quantidade);

        if (abateu) {
            Usuario comprador = usuarioRepository
                    .findByEmail("seuemail2@deveficiente.com").get();
            GatewayPagamento gateway = request.getGateway();

            Compra novaCompra = new Compra(produtoASerComprado, quantidade,
                    comprador, gateway);
            manager.persist(novaCompra);
            emails.novaCompra(novaCompra);

            return novaCompra.urlRedirecionamento(uriComponentsBuilder);

        }

        BindException problemaComEstoque = new BindException(request,
                "novaCompraRequest");
        problemaComEstoque.reject(null,
                "Não foi possível realizar a compra por conta do estoque");

        throw problemaComEstoque;
    }
}