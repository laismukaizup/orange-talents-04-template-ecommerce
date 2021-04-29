package br.com.academy.lais.mercadolivre.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class ImagemProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String link;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="produto_id")
    private Produto produto;

    public ImagemProduto(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }
}
