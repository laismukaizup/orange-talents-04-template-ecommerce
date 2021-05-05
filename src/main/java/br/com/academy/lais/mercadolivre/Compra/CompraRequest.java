package br.com.academy.lais.mercadolivre.Compra;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CompraRequest {
    public Long idProduto;
    @NotNull @Positive
    public Integer qtde;
    public BigDecimal valor;
    public Long idUsuario;
    public Gateway gateway;
    public StatusCompra status = StatusCompra.INICIADO;

    public CompraRequest(Long idProduto, Integer qtde, BigDecimal valor, Long idUsuario, Gateway gateway) {
        this.idProduto = idProduto;
        this.qtde = qtde;
        this.valor = valor;
        this.idUsuario = idUsuario;
        this.gateway = gateway;
    }

    public Integer getQtde() {
        return qtde;
    }

    public Compra toModel(Produto produto, Usuario usuarioLogado) {
        return new Compra(produto, qtde, valor, usuarioLogado, gateway, status);
    }

}
