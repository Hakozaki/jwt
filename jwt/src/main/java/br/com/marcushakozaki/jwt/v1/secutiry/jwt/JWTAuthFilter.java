package br.com.marcushakozaki.jwt.v1.secutiry.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.marcushakozaki.jwt.v1.usuarios.DetalheUsuario;
import br.com.marcushakozaki.jwt.v1.usuarios.entidade.Usuario;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

    public final static int TOKEN_EXPIRES = 600_000;

    public final static String TOKEN_PASSWORD = "123";

    private final AuthenticationManager authenticationManager;

    public JWTAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            
            System.out.println(usuario);
            
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuario.getEmail(),
                            usuario.getSenha(),
                            new ArrayList<>()));

        } catch (StreamReadException e) {            
            throw new RuntimeException("Falha ao autenticar usuário : StreamReadException");
        } catch (DatabindException e) {            
            throw new RuntimeException("Falha ao autenticar usuário : DatabindException");
        } catch (IOException e) {            
            throw new RuntimeException("Falha ao autenticar usuário : IOException");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        DetalheUsuario usuario = (DetalheUsuario) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();
    }

}
