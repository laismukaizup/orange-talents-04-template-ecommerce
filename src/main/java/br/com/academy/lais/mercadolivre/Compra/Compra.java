package br.com.academy.lais.mercadolivre.Compra;

import br.com.academy.lais.mercadolivre.Pagamento.Pagamento;
import br.com.academy.lais.mercadolivre.Pagamento.RetornoPagamento;
import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Compra {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @ManyToOne
    public Produto produto;
    @NotNull
    @Positive
    public Integer qtde;
    public BigDecimal valor;
    @NotNull @ManyToOne
    public Usuario usuario;
    public Gateway gateway;
    public StatusCompra status;
    @OneToMany(mappedBy = "compra",fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    public Set<Pagamento> pagamentos = new HashSet<>();

    @Deprecated
    public Compra(){}

    public Long getId() {
        return id;
    }

    public Compra(Produto produto, Integer qtde, BigDecimal valor, Usuario usuario, Gateway gateway, StatusCompra status) {
        this.produto = produto;
        this.qtde = qtde;
        this.valor = valor;
        this.usuario = usuario;
        this.gateway = gateway;
        this.status = status;
    }

    public Produto getProduto() {
        return produto;
    }

    public String retornoURL(UriComponentsBuilder uriComponentsBuilder) {
        return gateway.criaRetorno(this, uriComponentsBuilder);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", qtde=" + qtde +
                ", valor=" + valor +
                ", usuario=" + usuario +
                ", gatway='" + gateway + '\'' +
                '}';
    }


    public void atualizaStatus(Pagamento pagamento) {

        this.pagamentos.add(pagamento);
        boolean statusPagamentoSucessso = pagamento.statusPagamentoSucessso();
        this.status = statusPagamentoSucessso ? StatusCompra.SUCESSO : StatusCompra.ERRO;

    }
}
