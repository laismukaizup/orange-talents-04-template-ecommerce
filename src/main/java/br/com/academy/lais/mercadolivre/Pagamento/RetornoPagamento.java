package br.com.academy.lais.mercadolivre.Pagamento;

import br.com.academy.lais.mercadolivre.Compra.Compra;

public interface RetornoPagamento {
    Pagamento toModel(Compra compra);
}
