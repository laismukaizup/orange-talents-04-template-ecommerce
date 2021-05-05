package br.com.academy.lais.mercadolivre.Pagamento;

public enum StatusPagSeguro {
    SUCESSO, ERRO;

    public Integer converter() {
        if (this.equals(SUCESSO))
            return 1;
        return 0;
    }
}
