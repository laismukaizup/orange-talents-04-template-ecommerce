package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Categoria.Categoria;
import br.com.academy.lais.mercadolivre.Categoria.CategoriaRepository;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import br.com.academy.lais.mercadolivre.Usuario.UsuarioRepository;
import br.com.academy.lais.mercadolivre.Validacao.ExistValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @ExistValue(fieldName = "id", domainClass = Usuario.class, message = "Não existe usuário cadastrado com essse id.")
    private Long idUsuario;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas;

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public ProdutoRequest(String nome, String valor, String qtde, String descricao, Long idCategoria,
                          Long idUsuario,List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
        this.caracteristicas = caracteristicas;
    }

    public Produto converter(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (categoria.isPresent() && usuario.isPresent())
            return new Produto(nome, valor, qtde, descricao, categoria.get(),usuario.get(), dataCriacao);

        if (categoria.isEmpty())
        Assert.isNull(categoria, "Não existe categoria com esse id");
        if (usuario.isEmpty())
        Assert.isNull(usuario, "Não existe usuário com esse id");

        return null;
    }

    public boolean temCaracteristicasIguais() {

        List<String> nomeExiste = new ArrayList<String>();
        for (CaracteristicaRequest caracteristica : getCaracteristicas()) {
            if (!nomeExiste.contains(caracteristica.getNome().trim()))
                nomeExiste.add(caracteristica.getNome().trim());
            else {
                return true;
            }
        }
        return false;
    }
}
