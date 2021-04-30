package br.com.academy.lais.mercadolivre.OpniaoProduto;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/opiniaoProduto")
public class OpiniaoProdutoController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping(value = "{id}")
    @Transactional
    public String inserirOpniao( @PathVariable("id") Long id, @RequestBody @Valid OpiniaoRequest opiniaoRequest){

        Produto produto = entityManager.find(Produto.class, id);
        Assert.notNull(produto, "Produto não existe no banco de dados.");
        Opiniao opiniao = opiniaoRequest.toModel();
        opiniao.setProduto(produto);
        entityManager.persist(opiniao);
        return "inserido";
    }


}
