package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Categoria.Categoria;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private String valor;
    @NotNull
    @Positive
    private String qtde;
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

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, String valor, String qtde, String descricao, Categoria categoria,
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
}
