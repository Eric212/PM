package com.ericsospedra.asignaturas.models;

import java.io.Serializable;

public class Asignatura implements Serializable {
    private String codAsignaturas;
    private String nombreAsignatura;

    public Asignatura(String codAsignaturas, String nombreAsignatura) {
        this.codAsignaturas = codAsignaturas;
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getCodAsignaturas() {
        return codAsignaturas;
    }

    public void setCodAsignaturas(String codAsignaturas) {
        this.codAsignaturas = codAsignaturas;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }
}
