package br.com.academy.lais.mercadolivre.Usuario;

import br.com.academy.lais.mercadolivre.Validacao.UniqueValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UsuarioRequest {
    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "E-mail já cadastrado.")
    private String login;
    @NotBlank @Size(min=6)
    private String senha;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario converter() {
        return new Usuario(login, codificaSenha(senha), dataCriacao);
    }

    public String codificaSenha(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
