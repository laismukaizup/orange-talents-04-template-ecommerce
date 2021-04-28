package br.com.academy.lais.mercadolivre.Categoria;

import br.com.academy.lais.mercadolivre.Validacao.ExistValue;
import br.com.academy.lais.mercadolivre.Validacao.UniqueValue;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaRequest {
    @NotEmpty
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class, message = "Categoria já cadastrada.")
    private String nome;
    @ExistValue(fieldName = "id", domainClass = Categoria.class, message = "Categoria mãe não cadastrada.")
    private Long idCategoriaMae;

    public CategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            System.out.println(idCategoriaMae);
            Optional<Categoria> categoriaMae = categoriaRepository.findById(idCategoriaMae);
            if (categoriaMae.isPresent())
                categoria.setCategoriaMae(categoriaMae.get());
            else
                Assert.notNull(categoriaMae, "O id da categoria mãe precisa ser válido");
        }
        return categoria;
    }


}
