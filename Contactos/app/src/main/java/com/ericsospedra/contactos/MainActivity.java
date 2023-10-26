package com.ericsospedra.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentoDetalle.IonAttach, FragmentoListado.IonAttach,IonClickListener{

    private static final String CONTACTO_SELECCIONADO = "ContactoSeleccionado";
    private static final String CONTACTO = "Contacto";
    private boolean hayDetalle;
    private ArrayList<Contacto> contactos;
    private FragmentoDetalle fragmentoDetalle;

    private FragmentManager manager;
    private int contactoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState !=null){
            contactoSeleccionado = savedInstanceState.getInt(CONTACTO_SELECCIONADO);
            contactos = (ArrayList<Contacto>) savedInstanceState.getSerializable(CONTACTO);
        }else{
            contactos = getContactos();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        hayDetalle = findViewById(R.id.fcvDetalle) != null;
        if (hayDetalle) {
            fragmentoDetalle = (FragmentoDetalle) manager.findFragmentById(R.id.fcvDetalle);
            if (manager.findFragmentById(R.id.fcvListado) instanceof FragmentoDetalle) {
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fcvListado, FragmentoListado.class, null)
                        .commit();
            }
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CONTACTO_SELECCIONADO, contactoSeleccionado);
        outState.putSerializable(CONTACTO, contactos);
        super.onSaveInstanceState(outState);
    }
    public ArrayList<Contacto> getContactos() {
        JsonParser parser = new JsonParser(this);
        if(contactos == null){
            if(parser.parsear()){
                contactos = parser.getContactos();
            }
        }
        return contactos;
    }

    @Override
    public int getContactoSeleccionado() {
        return contactoSeleccionado;
    }

    @Override
    public String[] getDatos() {
        if (contactoSeleccionado<0){
            contactoSeleccionado = 0;
        }
        return new String[]{contactos.get(contactoSeleccionado).getNombre()
                        ,contactos.get(contactoSeleccionado).getApellidos()
                        ,contactos.get(contactoSeleccionado).getBirthday()
                        ,contactos.get(contactoSeleccionado).getEmpresa()
                        ,contactos.get(contactoSeleccionado).getEmail()
                        ,contactos.get(contactoSeleccionado).getTelefono1()
                        ,contactos.get(contactoSeleccionado).getTelefono2()
                        ,contactos.get(contactoSeleccionado).getDireccion()};
    }

    @Override
    public void onClick(int position) {
        contactoSeleccionado = position;
        if(hayDetalle){
            String[] datos = {contactos.get(contactoSeleccionado).getNombre()
                    ,contactos.get(contactoSeleccionado).getApellidos()
                    ,contactos.get(contactoSeleccionado).getBirthday()
                    ,contactos.get(contactoSeleccionado).getEmpresa()
                    ,contactos.get(contactoSeleccionado).getEmail()
                    ,contactos.get(contactoSeleccionado).getTelefono1()
                    ,contactos.get(contactoSeleccionado).getTelefono2()
                    ,contactos.get(contactoSeleccionado).getDireccion()};
            fragmentoDetalle.mostrarContacto(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],datos[7]);
        } else {
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.fcvListado, FragmentoDetalle.class, null)
                    .commit();
        }
    }
}