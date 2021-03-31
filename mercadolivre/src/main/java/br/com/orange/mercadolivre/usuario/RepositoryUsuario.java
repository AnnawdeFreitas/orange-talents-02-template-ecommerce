package br.com.orange.mercadolivre.usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(Object login);
}
