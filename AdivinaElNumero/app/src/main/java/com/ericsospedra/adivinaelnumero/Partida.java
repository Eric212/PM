package com.ericsospedra.adivinaelnumero;

import android.widget.Button;

import java.util.ArrayList;

public class Partida {
    private int intentos;
    private int numerosSecreto;

    private boolean hasGandado;

    private ArrayList<Button> botones;
    public Partida(int intentos, int numerosSecreto,boolean hasGandado) {
        this.intentos = intentos;
        this.numerosSecreto = numerosSecreto;
        this.hasGandado = hasGandado;
        this.botones = new ArrayList<Button>();
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setHasGandado(boolean hasGandado) {
        this.hasGandado = hasGandado;
    }

    public int getNumerosSecreto() {
        return numerosSecreto;
    }


    public boolean isHasGandado() {
        return hasGandado;
    }


    public ArrayList<Button> getBotones() {
        return botones;
    }

    public void setBotones(Button boton) {
        this.botones.add(boton);
    }
}

