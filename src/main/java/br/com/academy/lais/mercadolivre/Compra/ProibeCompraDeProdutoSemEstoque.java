package br.com.academy.lais.mercadolivre.Compra;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ProibeCompraDeProdutoSemEstoque implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors())
            return;

        CompraRequest produtoRequest = (CompraRequest) target;
        Produto produto = manager.find(Produto.class, produtoRequest.idProduto);
        boolean possuiEstoque = produto.getQtde() >= produtoRequest.getQtde() ? true : false;
        Assert.state(possuiEstoque, "Produto não possui estoque de " + produtoRequest.getQtde() + " produtos");
    }
}
