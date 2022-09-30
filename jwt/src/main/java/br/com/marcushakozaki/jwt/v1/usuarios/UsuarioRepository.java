package br.com.marcushakozaki.jwt.v1.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.marcushakozaki.jwt.v1.usuarios.dto.UsuarioView;
import br.com.marcushakozaki.jwt.v1.usuarios.entidade.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<UsuarioView> findById(Long id, Class<UsuarioView> classe);

    @Query("select u from Usuario u where u.email like :email")
    Optional<Usuario> findByEmail(@Param("email") String email);

}
