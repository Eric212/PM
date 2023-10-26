package com.ericsospedra.asignaturas.models;

import java.io.Serializable;
import java.nio.file.SecureDirectoryStream;

public class Nota implements Serializable {
    private float nota;
    private String codAsignatura;
    private String nombreAsignatura;

    public Nota(float nota, String codAsignatura) {
        this.nota = nota;
        this.codAsignatura = codAsignatura;

    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }
}
