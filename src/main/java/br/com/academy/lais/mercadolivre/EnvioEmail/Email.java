package br.com.academy.lais.mercadolivre.EnvioEmail;

import org.springframework.stereotype.Component;

@Component
public class Email {
    private String email;
    private String titulo;
    private String descricao;

    @Deprecated
    public Email(){}

    public Email(String email, String titulo, String descricao) {
        this.email = email;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
