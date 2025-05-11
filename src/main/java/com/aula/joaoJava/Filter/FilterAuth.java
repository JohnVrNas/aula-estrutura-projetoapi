package com.aula.joaoJava.Filter;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.aula.joaoJava.User.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Base64;

@Component
public class FilterAuth extends OncePerRequestFilter {

    @Autowired
    IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String path = request.getRequestURI();

        // Ignora autenticação para rotas públicas
        if (path.startsWith("/h2-console")
                || path.equals("/user/novouser")
                || path.equals("/filmes/criarfilme")) {
            filterChain.doFilter(request, response);
            return;
        }

        var authorization = request.getHeader("Authorization");

        // Verifica se o cabeçalho existe e começa com "Basic"
        if (authorization == null || !authorization.startsWith("Basic ")) {
            response.sendError(401, "Cabeçalho de autorização ausente ou inválido");
            return;
        }

        try {
            var authEncode = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncode);
            var authString = new String(authDecode);
            String[] credenciais = authString.split(":");

            String username = credenciais[0];
            String senha = credenciais[1];

            var user = userRepository.findByUsername(username);

            if (user.isEmpty()) {
                response.sendError(401, "Usuário sem autorização");
                return;
            }

            var verificaSenha = BCrypt.verifyer().verify(senha.toCharArray(), user.get().getSenha());
            if (!verificaSenha.verified) {
                response.sendError(401, "Senha incorreta");
                return;
            }

            // Se passou por tudo, segue com a requisição
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(500, "Erro ao processar autenticação");
        }
    }
    }


