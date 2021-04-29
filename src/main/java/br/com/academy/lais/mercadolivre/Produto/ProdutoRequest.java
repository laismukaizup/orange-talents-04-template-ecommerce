package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Categoria.Categoria;
import br.com.academy.lais.mercadolivre.Categoria.CategoriaRepository;
import br.com.academy.lais.mercadolivre.Validacao.ExistValue;
import br.com.academy.lais.mercadolivre.Validacao.UniqueValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private String valor;
    @NotNull @Positive
    private String qtde;
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull
    @ExistValue(fieldName = "id", domainClass = Categoria.class, message = "Não existe categoria cadastrada com essse id.")
    private Long idCategoria;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas;

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public ProdutoRequest(String nome, String valor, String qtde, String descricao, Long idCategoria, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        if (categoria.isPresent())
            return new Produto(nome, valor, qtde, descricao, categoria.get(), dataCriacao);


        Assert.isNull(categoria, "não existe categoria com esse id");
        return null;
    }
}
