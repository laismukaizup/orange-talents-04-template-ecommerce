package br.com.academy.lais.mercadolivre.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    ProibeCaracteristicasIguaisParaOMesmoProdutoValidator proibeCaracteristicasIguaisParaOMesmoProdutoValidator;

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(proibeCaracteristicasIguaisParaOMesmoProdutoValidator);
    }

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest) {

        Produto produto = produtoRequest.converter(entityManager);
        Assert.notNull(produto, "Produto não pode ser nulo.");

        List<Caracteristica> caracteristicaList = produtoRequest.getCaracteristicas().stream()
                .map(Caracteristica::new).collect(Collectors.toList());

        for (Caracteristica caracteristica : caracteristicaList) {
            caracteristica.setProduto(produto);
            entityManager.persist(caracteristica);
        }
        entityManager.persist(produto);
        return "cadastrado";
    }

    @PostMapping(value = "{id}/imagens")
    public String insereImage(@PathVariable("id") Long id, @Valid ImagemProdutoRequest imagemProdutoRequest) {

        Produto produto = entityManager.find(Produto.class, id);
        Assert.notNull(produto, "Produto não pode ser nulo.");

        List<ImagemProduto> imagemProdutoList = imagemProdutoRequest.converter(produto);
        for (ImagemProduto imagem: imagemProdutoList) {
            entityManager.persist(imagem);
        }

        return "inserido";
    }
}