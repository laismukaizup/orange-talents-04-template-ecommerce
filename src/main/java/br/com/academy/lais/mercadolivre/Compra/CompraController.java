package br.com.academy.lais.mercadolivre.Compra;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.validation.BindException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ProibeCompraDeProdutoSemEstoque proibeCaracteristicasIguaisParaOMesmoProdutoValidator;

    @InitBinder()
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(proibeCaracteristicasIguaisParaOMesmoProdutoValidator);
    }


    @PostMapping()
    @Transactional
    public String Compra(@RequestBody @Valid CompraRequest compraRequest, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produto = entityManager.find(Produto.class, compraRequest.idProduto);
        if (produto == null)
            Assert.isNull("ID do produto não cadastrado");

        Optional<Usuario> usuarioLogado = (Optional<Usuario>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (usuarioLogado.isPresent()) {
            Compra compra = compraRequest.toModel(produto, usuarioLogado.get());

            boolean abateu = produto.abateEstque(compraRequest.getQtde());
            if(abateu) {
                entityManager.persist(produto);
                entityManager.persist(compra);
                return compra.retornoURL(uriComponentsBuilder);
            }
            BindException problemaComEstoque = new BindException(compraRequest,
                    "CompraRequest");
            problemaComEstoque.reject(null,
                    "Não foi possível realizar a compra por conta do estoque");

            throw problemaComEstoque;
        }
        return "erro";
    }
}
