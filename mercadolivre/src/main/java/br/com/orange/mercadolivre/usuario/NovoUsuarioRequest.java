package br.com.orange.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class NovoUsuarioRequest {

    @Email
    @NotBlank
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequest( @Email @NotBlank String login, @NotBlank @Size(min = 6) String senha){
        this.login = login;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        return new Usuario(login, new SenhaLimpa(senha));
    }

    public Object getEmail() {
        return login;
    }
}

