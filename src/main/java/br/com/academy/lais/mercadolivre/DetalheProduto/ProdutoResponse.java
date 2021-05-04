package br.com.academy.lais.mercadolivre.DetalheProduto;

import br.com.academy.lais.mercadolivre.Caracteristica.Caracteristica;
import br.com.academy.lais.mercadolivre.ImagemProduto.ImagemProduto;
import br.com.academy.lais.mercadolivre.OpniaoProduto.Opiniao;
import br.com.academy.lais.mercadolivre.Pergunta.Pergunta;
import br.com.academy.lais.mercadolivre.Produto.Produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProdutoResponse {
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Integer totalNotas;
    private Double mediaNotas;
    private Set<ImagemProduto> links = new HashSet<>();
    private Set<Opiniao> opinioes = new HashSet<>();
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    private Set<Pergunta> perguntas = new HashSet<>();

    @Deprecated
    public ProdutoResponse(){}

    public ProdutoResponse(Produto p) {
        this.nome = p.getNome();
        this.valor = p.getValor();
        this.descricao = p.getDescricao();
        this.links = p.getImagens();
        this.opinioes = p.getOpinioes();
        this.caracteristicas = p.getCaracteristicas();
        this.perguntas = p.getPerguntas();
        this.totalNotas = p.getOpinioes().size();
        this.mediaNotas = p.getOpinioes().size() == 1 ? p.getOpinioes().stream().findFirst().get().getNota()
                : p.getOpinioes().stream().mapToDouble(o -> o.getNota()).average().getAsDouble();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<ImagemProduto> getLinks() {
        return links;
    }

    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }
}
