package com.aula.joaoJava.Filmes;

import com.aula.joaoJava.User.IUserRepository;
import com.aula.joaoJava.User.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")//localhost:8080/filmes

public class FilmesController {

    @Autowired
    private IFilmesRepository filmesRepository;

//  Criar filme
    @PostMapping("/criarfilme")
    private ResponseEntity<?> criarfilme(@RequestBody FilmesModel filmesModel) {
    Optional<FilmesModel> filmeExis = filmesRepository.findByTitulo(filmesModel.getTitulo());

    if (filmeExis.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Filme já existe na lista!");
    }

    FilmesModel criado = filmesRepository.save(filmesModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(criado);
}

//  Listar filme
    @GetMapping("/listafilme")
    public List<FilmesModel> listarFilmes(){
        List<FilmesModel> cadasfilm = filmesRepository.findAll();
        return cadasfilm;
    }

    //Deletar filme
    @DeleteMapping("/deletarfilme/{id}")
    public void deleteFilme(@PathVariable UUID id){
        filmesRepository.deleteById(id);
    }

    //Atualizar Filme
    @PutMapping("/atualizarfilme/{id}")
    public ResponseEntity atualizaFilme(@PathVariable UUID id, @RequestBody FilmesModel filmeModel) {
        return filmesRepository.findById(id).map(filmeExis -> {
            // Atualiza apenas os campos permitidos
            filmeExis.setTitulo(filmeModel.getTitulo());
            filmeExis.setData(filmeModel.getData());
            filmeExis.setComentarios(filmeModel.getComentarios());
            filmeExis.setNota(filmeModel.getNota());
            filmeExis.setGostou(filmeModel.getGostou());

            var atualizado = filmesRepository.save(filmeExis);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}


//User
//{
//    "nome":"Joao Rodrigues",
//    "username":"john",
//    "senha":"123456",
//    "telefone":"505",
//    "email":"joao@gmail.com"
//}
//
//Filme
//{
//    "titulo":"Interestellar",
//    "data":"14/03/2025",
//    "comentarios":"Melhor filme da minha vida!",
//    "nota":"5.0",
//    "gostou":"Sim"
//}
