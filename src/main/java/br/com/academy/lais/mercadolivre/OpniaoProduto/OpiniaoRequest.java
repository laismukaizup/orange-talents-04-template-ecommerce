package br.com.academy.lais.mercadolivre.OpniaoProduto;

import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

public class OpiniaoRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @Range(min = 1, max = 5)
    private Integer nota;

    public OpiniaoRequest(String titulo, String descricao, Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public Opiniao toModel() {
        Optional<Usuario> usuarioLogado = (Optional<Usuario>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (usuarioLogado.isEmpty())
            Assert.isNull("Usuário não cadastrado.");

        return new Opiniao(titulo, descricao, nota, usuarioLogado.get());
    }
}
