package com.ericsospedra.contactos;

import java.io.Serializable;

public class Contacto implements Serializable {
    private int id;
    private String nombre;
    private String apellidos;
    private String birthday;
    private String empresa;
    private String email;
    private String telefono1;
    private String telefono2;
    private String direccion;

    public Contacto(int id,String nombre, String apellidos, String birthday, String empresa, String email,
                    String telefono1, String telefono2, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.birthday = birthday;
        this.empresa = empresa;
        this.email = email;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public String getDireccion() {
        return direccion;
    }
}
