package br.com.academy.lais.mercadolivre.Hanking;

public class HankingRequest {
    public Long idCompra;
    public Long idVendedor;

    public HankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "HankingRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
