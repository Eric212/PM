package com.ericsospedra.piedrapapeltijera;

import android.renderscript.Allocation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.InputMismatchException;
import java.util.Random;

public class Partida {
    private int[] puntuacion;
    private int[] elecciones;
    private int player;
    private int cpu;
    private TextView marcador;
    private ImageView[] pantallaJugadores;

    public Partida(int[] puntuacion, int[] elecciones, int player, int cpu, TextView marcador, ImageView[] pantallaJugadores) {
        this.puntuacion = puntuacion;
        this.elecciones = elecciones;
        this.player = player;
        this.cpu = cpu;
        this.marcador = marcador;
        this.pantallaJugadores = pantallaJugadores;
    }

    public int[] getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int[] puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getCpu() {
        return cpu;
    }

    public int getPlayer() {
        return player;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public void setMarcador(String marcador) {
        this.marcador.setText(marcador);
    }

    public void setPantallaJugadores(int jugador, int id) {
        this.pantallaJugadores[jugador].setImageResource(id);
    }

    private int playCpu() {
        float comodin = new Random().nextFloat();
        if (comodin <= 0.33) {
            setPantallaJugadores(0, R.mipmap.piedra_foreground);
            return this.elecciones[0];
        } else if (comodin > 0.33 && comodin <= 0.66) {
            setPantallaJugadores(0, R.mipmap.papel_foreground);
            return this.elecciones[1];
        } else {
            setPantallaJugadores(0, R.mipmap.tijeras_foreground);
            return this.elecciones[2];
        }
    }

    public void resultado() {
        setCpu(playCpu());
        String marcadorActual = null;
        if (player == -1 || cpu == -1) {
            throw new InputMismatchException();
        }
        if (getPlayer() != getCpu()) {
            if (getCpu() == 1 && getPlayer() != 2) {
                setPuntuacion(new int[]{this.puntuacion[0], this.puntuacion[1] + 1});
                marcadorActual = "Player: " + getPuntuacion()[0] + " " + "CPU: " + getPuntuacion()[1];
                setMarcador(marcadorActual);
            } else if (getCpu() == 2 && getPlayer() != 3) {
                setPuntuacion(new int[]{this.puntuacion[0], this.puntuacion[1] + 1});
                marcadorActual = "Player: " + getPuntuacion()[0] + " " + "CPU: " + getPuntuacion()[1];
                setMarcador(marcadorActual);
            } else if (getCpu() == 3 && getPlayer() != 1) {
                setPuntuacion(new int[]{this.puntuacion[0], this.puntuacion[1] + 1});
                marcadorActual = "Player: " + getPuntuacion()[0] + " " + "CPU: " + getPuntuacion()[1];
                setMarcador(marcadorActual);
            } else {
                setPuntuacion(new int[]{this.puntuacion[0] + 1, this.puntuacion[1]});
                marcadorActual = "Player: " + getPuntuacion()[0] + " " + "CPU: " + getPuntuacion()[1];
                setMarcador(marcadorActual);
            }
        } else {
            marcadorActual = "Player: " + getPuntuacion()[0] + " " + "CPU: " + getPuntuacion()[1];
            setMarcador(marcadorActual);
        }
    }

    public void reiniciar(String marcador) {
        setMarcador(marcador);
        setPantallaJugadores(0, R.mipmap.question_foreground);
        setPantallaJugadores(1, R.mipmap.question_foreground);
        setPlayer(-1);
        setCpu(-1);
        setPuntuacion(new int[]{0, 0});
    }
}

