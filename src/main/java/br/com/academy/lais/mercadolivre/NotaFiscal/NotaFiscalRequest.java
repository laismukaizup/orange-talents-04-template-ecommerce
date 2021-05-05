package br.com.academy.lais.mercadolivre.NotaFiscal;

public class NotaFiscalRequest {
    private Long idCompra;
    private Long idComprador;

    public NotaFiscalRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
