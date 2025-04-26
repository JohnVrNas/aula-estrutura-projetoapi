package com.aula.joaoJava.Filmes;

import com.aula.joaoJava.Filmes.FilmesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFilmesRepository extends JpaRepository<FilmesModel, UUID> {
    Optional<com.aula.joaoJava.Filmes.FilmesModel> findByTitulo(String titulo);
}
