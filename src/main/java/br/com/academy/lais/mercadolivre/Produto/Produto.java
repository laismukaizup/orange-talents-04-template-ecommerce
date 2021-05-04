package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Caracteristica.Caracteristica;
import br.com.academy.lais.mercadolivre.Categoria.Categoria;
import br.com.academy.lais.mercadolivre.ImagemProduto.ImagemProduto;
import br.com.academy.lais.mercadolivre.OpniaoProduto.Opiniao;
import br.com.academy.lais.mercadolivre.Pergunta.Pergunta;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private Integer qtde;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "produto",fetch=FetchType.EAGER)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto",fetch=FetchType.EAGER)
    private Set<Opiniao> opinioes = new HashSet<>();
    @OneToMany(mappedBy = "produto",fetch=FetchType.EAGER)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto",fetch=FetchType.EAGER)
    private Set<Pergunta> perguntas = new HashSet<>();


    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
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

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer qtde, String descricao, Categoria categoria,
                   Usuario usuario, LocalDateTime dataCriacao) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public boolean abateEstque(Integer qtdeComprada) {
        if(qtde >= qtdeComprada) {
            this.qtde -= qtdeComprada;
        return true;
        }
        return false;
    }
}
