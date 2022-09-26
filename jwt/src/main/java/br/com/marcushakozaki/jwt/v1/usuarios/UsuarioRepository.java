package br.com.marcushakozaki.jwt.v1.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcushakozaki.jwt.v1.usuarios.entidade.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
