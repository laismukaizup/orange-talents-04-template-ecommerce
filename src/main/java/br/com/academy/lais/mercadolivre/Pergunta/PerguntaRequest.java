package br.com.academy.lais.mercadolivre.Pergunta;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PerguntaRequest {
    @NotBlank
    private String titulo;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    public PerguntaRequest(){}

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel() {
        return new Pergunta(titulo, dataCriacao);
    }
}
