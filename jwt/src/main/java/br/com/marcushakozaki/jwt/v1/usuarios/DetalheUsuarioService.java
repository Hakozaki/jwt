package br.com.marcushakozaki.jwt.v1.usuarios;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.marcushakozaki.jwt.v1.usuarios.entidade.Usuario;

@Service
public class DetalheUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalheUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new DetalheUsuario(usuario);
    }

}
