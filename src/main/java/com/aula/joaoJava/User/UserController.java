package com.aula.joaoJava.User;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")//localhost:8080/user/novo
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    //Criar usuario
    @PostMapping("/novouser")
    public ResponseEntity<?> criar(@RequestBody UserModel userModel, HttpServletRequest request) {

        Optional<UserModel> usuarioExistente = userRepository.findByUsername(userModel.getUsername());

        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe!");
        }

        var hashsenha = BCrypt.withDefaults().hashToString(12, userModel.getSenha().toCharArray());
        userModel.setSenha(hashsenha);

        UserModel criado = userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    //  Listar usuario
    @GetMapping("/usercadastrados")
    public List<UserModel> listarCursos(){
        List<UserModel> usuariocad = userRepository.findAll();
        return usuariocad;
    }

    //Deletar usuario
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable UUID id){
        userRepository.deleteById(id);
    }

    //Atualizar usuario
    @PutMapping("/atualiza/{id}")
    public ResponseEntity atualizaUser(@PathVariable UUID id, @RequestBody UserModel userModel) {
        return userRepository.findById(id).map(usuarioExistente -> {
            // Atualiza apenas os campos permitidos
            usuarioExistente.setNome(userModel.getNome());
            usuarioExistente.setUsername(userModel.getUsername());
            usuarioExistente.setSenha(userModel.getSenha());
            usuarioExistente.setTelefone(userModel.getTelefone());
            usuarioExistente.setEmail(userModel.getEmail());

            var atualizado = userRepository.save(usuarioExistente);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}