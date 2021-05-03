package br.com.academy.lais.mercadolivre.OpniaoProduto;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;
    @NotNull
    @Range(min=1,max = 5)
    private Integer nota;
    @NotNull @ManyToOne
    private Usuario usuario;
    @NotNull @ManyToOne
    private Produto produto;
    private Double mediaNotas;

    @Deprecated
    public  Opiniao(){

    }

    public Opiniao(String titulo, String descricao, Integer nota, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.usuario = usuario;
    }


    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

}
