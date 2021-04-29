package br.com.academy.lais.mercadolivre.Produto;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeCaracteristicasIguaisParaOMesmoProdutoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors())
            return;

        ProdutoRequest produtoRequest = (ProdutoRequest) target;
        boolean existe = produtoRequest.temCaracteristicasIguais();

        Assert.state(!existe, "Duplicidade de caracteristicas");

    }
}
