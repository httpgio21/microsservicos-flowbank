package com.ms.usuario.repository;

import com.ms.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // você pode adicionar métodos customizados se precisar, ex:
    // Optional<Usuario> findByEmail(String email);
}
