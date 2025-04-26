package com.aula.joaoJava.Filmes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "tb_filmes")
public class FilmesModel {

    @Id
    @GeneratedValue(generator = "UUID")

    private UUID id;
    private String titulo;
    private String data; //  usar Date? precisa de import
    private String comentarios;
    private float nota;
    private boolean gostou; //  usar boolean?


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getGostou() {
        return gostou;
    }

    public void setGostou(boolean gostou) {
        this.gostou = gostou;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
