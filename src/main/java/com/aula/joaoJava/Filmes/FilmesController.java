package com.aula.joaoJava.Filmes;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aula.joaoJava.Filmes.FilmesModel;

import java.util.List;
import java.util.Map;
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
        Optional<com.aula.joaoJava.Filmes.FilmesModel> filmeExis = filmesRepository.findByTitulo(filmesModel.getTitulo());

        if (filmeExis.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Filme já existe na lista!");
        }

        FilmesModel criado = filmesRepository.save(filmesModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Filme criado com sucesso!", "filme", criado));
    }

    //  Listar filme
    @GetMapping("/listafilme")
    public List<FilmesModel> listarFilmes() {
        List<FilmesModel> cadasfilm = filmesRepository.findAll();
        return cadasfilm;
    }

    //  Deletar filme
    @DeleteMapping("/deletarfilme/{id}")
    public ResponseEntity<?> deleteFilme(@PathVariable UUID id) {
        if (!filmesRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
        filmesRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


//  Atualiza Filme
    @PutMapping("/atualizarfilme/{id}")
    @Transactional
    public ResponseEntity<?> atualizaFilme(@PathVariable UUID id, @RequestBody FilmesModel filmeModel) {
        Optional<FilmesModel> filmeExistente = filmesRepository.findById(id);

        if (filmeExistente.isPresent()) {
            FilmesModel filmeExis = filmeExistente.get();

            filmeExis.setTitulo(filmeModel.getTitulo());
            filmeExis.setData(filmeModel.getData());
            filmeExis.setComentarios(filmeModel.getComentarios());
            filmeExis.setNota(filmeModel.getNota());
            filmeExis.setGostou(filmeModel.getGostou());

            filmesRepository.save(filmeExis);
            return ResponseEntity.ok(filmeExis);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
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
//    "gostou": true
//}
