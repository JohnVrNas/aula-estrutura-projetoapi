package com.aula.joaoJava.Filmes;

import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import jakarta.persistence.GeneratedValue;

@Entity(name = "tb_filmes")
public class FilmesModel {

    @Id
    @GeneratedValue(generator = "UUID")

    private UUID id;
    private String titulo;
    private String data; //  usar Date? precisa de import
    private String comentarios;
    private float nota;
    private String gostou; //  usar boolean?


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGostou() {
        return gostou;
    }

    public void setGostou(String gostou) {
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
