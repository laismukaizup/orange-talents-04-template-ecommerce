package br.com.academy.lais.mercadolivre.ImagemProduto;

import br.com.academy.lais.mercadolivre.Produto.Produto;

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

    @Deprecated
    public ImagemProduto(){

    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public ImagemProduto(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }
}
