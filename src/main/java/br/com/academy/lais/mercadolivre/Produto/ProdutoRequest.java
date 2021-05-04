package br.com.academy.lais.mercadolivre.Produto;

import br.com.academy.lais.mercadolivre.Caracteristica.CaracteristicaRequest;
import br.com.academy.lais.mercadolivre.Categoria.Categoria;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import br.com.academy.lais.mercadolivre.Validacao.ExistValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {
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

    public ProdutoRequest(String nome, BigDecimal valor, Integer qtde, String descricao, Long idCategoria,
                          Long idUsuario, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
        this.caracteristicas = caracteristicas;
    }

    public Produto converter(EntityManager entityManager) {
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        Assert.notNull(categoria, "Categoria não pode ser nulo");
        Usuario Usuario = entityManager.find(Usuario.class, idUsuario);
        Assert.notNull(categoria, "Usuario não pode ser nulo.");

        return new Produto(nome, valor, qtde, descricao, categoria, Usuario, dataCriacao);
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
