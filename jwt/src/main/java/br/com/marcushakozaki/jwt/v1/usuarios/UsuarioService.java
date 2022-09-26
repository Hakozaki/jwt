package br.com.marcushakozaki.jwt.v1.usuarios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcushakozaki.jwt.v1.usuarios.dto.UsuarioView;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<UsuarioView> buscarPorId(Long id, Class<UsuarioView> classe) {
        return usuarioRepository.findById(id, classe);
    }

}
