package br.com.academy.lais.mercadolivre.Produto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CaracteristicaRequest {
    @NotBlank
    private String nome;
    @NotBlank @Size(max = 1000)
    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica converter(){
        return  new Caracteristica(nome, descricao);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
