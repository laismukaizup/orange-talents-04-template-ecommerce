package br.com.academy.lais.mercadolivre.Seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }


    public UsernamePasswordAuthenticationToken converter() {

        return new UsernamePasswordAuthenticationToken(email, senha);
    }


}
