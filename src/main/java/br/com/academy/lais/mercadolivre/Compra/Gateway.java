package br.com.academy.lais.mercadolivre.Compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum Gateway {
    pagseguro{
        @Override
        String criaRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetorno = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl="
                    + urlRetorno;
        }
    },
    paypal{
        @Override
        String criaRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetorno = uriComponentsBuilder
                    .path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl="
                    + urlRetorno;
        }
    };

    abstract String criaRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
