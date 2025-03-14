package com.aula.joaoJava.Filmes;

import com.aula.joaoJava.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFilmesRepository extends JpaRepository<FilmesModel, UUID> {
    Optional<FilmesModel> findByTitulo(String titulo);
}
