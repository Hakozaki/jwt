package br.com.marcushakozaki.jwt.v1.usuarios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcushakozaki.jwt.v1.usuarios.dto.UsuarioView;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioView> buscarPorId(@PathVariable("id") Long id) {
        Optional<UsuarioView> usuario = usuarioService.buscarPorId(id, UsuarioView.class);

        return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
    }
}
