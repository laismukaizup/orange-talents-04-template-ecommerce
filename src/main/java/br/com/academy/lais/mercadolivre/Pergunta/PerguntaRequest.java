package br.com.academy.lais.mercadolivre.Pergunta;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PerguntaRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    public PerguntaRequest(){}

    public PerguntaRequest(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pergunta toModel() {
        return new Pergunta(titulo,descricao, dataCriacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
