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
    @UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "E-mail já cadastrado.")
    private String email;
    @NotBlank @Size(min=6)
    private String senha;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public UsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converter() {
        return new Usuario(email, codificaSenha(senha), dataCriacao);
    }

    public String codificaSenha(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
