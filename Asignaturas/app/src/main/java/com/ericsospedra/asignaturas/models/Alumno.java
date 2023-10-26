package com.ericsospedra.asignaturas.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

public class Alumno implements Serializable {
    private int nia;
    private String nombre;
    private String apellidos;
    private int fechaNacimiento;
    private String email;
    private Nota[] notas;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Alumno(int nia, String nombre, String apellidos, Date fechaNacimiento, String email, Nota[] notas) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        setFechaNacimiento(fechaNacimiento);
        this.email = email;
        this.notas = notas;
    }

    public int getNia() {
        return nia;
    }

    public void setNia(int nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre+" "+apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setFechaNacimiento(Date fechaNacimiento) {
        LocalDate localBirthDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCurrentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.fechaNacimiento = Period.between(localBirthDate, localCurrentDate).getYears();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Nota[] getNotas() {
        return notas;
    }

    public void setNotas(Nota[] notas) {
        this.notas = notas;
    }
}
