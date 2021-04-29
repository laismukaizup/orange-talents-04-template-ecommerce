package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Categoria.CategoriaRequest;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Caracteristica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nome;
    @NotEmpty @Length(max = 1000)
    private String descricao;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="produto_id")
    private Produto produto;

    public Caracteristica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica(CaracteristicaRequest categoriaRequest)
    {
        this.nome = categoriaRequest.getNome();
        this.descricao = categoriaRequest.getDescricao();
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
