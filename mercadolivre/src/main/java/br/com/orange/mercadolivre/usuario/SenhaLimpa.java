package br.com.orange.mercadolivre.usuario;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Representa uma senha limpa no sistema
 * */
public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        Assert.hasLength(senha, "senha não pode ser em branco");
        Assert.isTrue(senha.length() >= 6," Senha tem que ter no mínimo 6 caracteres");
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}

