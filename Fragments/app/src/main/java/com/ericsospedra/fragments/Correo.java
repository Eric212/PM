package com.ericsospedra.fragments;

import java.io.Serializable;

public class Correo implements Serializable {
    private String asunto;
    private String texto;

    public Correo(String asunto, String texto) {
        this.asunto = asunto;
        this.texto = texto;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getTexto() {
        return texto;
    }
}
