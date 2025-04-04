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
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

              var authorization = request.getHeader("Authorization");
              filterChain.doFilter(request, response);
              System.out.println(authorization);

              var authEncode = authorization.substring("Basic".length()).trim();
              System.out.println(authEncode);

              byte[] authDecode = Base64.getDecoder().decode(authEncode);
              System.out.println(authDecode);

              var authString = new String(authDecode);
              System.out.println(authString);
              String [] credenciais = authString.split(":");

              String username = credenciais[0];
              String senha = credenciais[1];

              System.out.println(username);
              System.out.println(senha);

    //        Validação  de usuario
              var user = userRepository.findByUsername(username);
              if(user == null){
                  response.sendError(401, "Usuário sem autorização");
              }else{
                  var verificaSenha = BCrypt.verifyer().verify(senha.toCharArray(), user.get().getSenha());
                  if(verificaSenha.verified){
                      filterChain.doFilter(request, response);
                  }else{
                      response.sendError(401);
                  }
              }

        }
    }


