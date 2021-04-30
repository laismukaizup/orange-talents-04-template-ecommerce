package br.com.academy.lais.mercadolivre.Pergunta;

import br.com.academy.lais.mercadolivre.Produto.Produto;
import br.com.academy.lais.mercadolivre.Usuario.Usuario;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao;
    @ManyToOne
    @NotNull
    private Usuario usuario;
    @ManyToOne @NotNull
    private Produto produto;


    public Pergunta(String titulo,LocalDateTime dataCriacao) {
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
