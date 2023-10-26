package com.ericsospedra.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.PersistableBundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentoListado.IOnAttach, FragmentoDetalle.IOnAttach, IonClickListener {

    private static final String CORREO_SELECCIONADO = "CorreoSeleccionado";
    private static final String CORREOS = "Correos";
    private boolean hayDetalle;
    private ArrayList<Correo> correos;

    private FragmentoDetalle fragmentoDetalle;

    private FragmentManager manager;

    private int correoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            correoSeleccionado = savedInstanceState.getInt(CORREO_SELECCIONADO);
            correos = (ArrayList<Correo>) savedInstanceState.getSerializable(CORREOS);
        } else {
            correos = getCorreos();
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
        outState.putInt(CORREO_SELECCIONADO, correoSeleccionado);
        outState.putSerializable(CORREOS, correos);
        super.onSaveInstanceState(outState);
    }

    @Override
    public ArrayList<Correo> getCorreos() {
        CorreoParser parser = new CorreoParser(this);
        if (correos == null) {
            if (parser.parser()) {
                correos = parser.getCorreos();
            }
        }
        return correos;
    }

    @Override
    public int getCorreoSeleccionado() {
        return correoSeleccionado;
    }

    @Override
    public String getMensaje() {
        if (correoSeleccionado < 0) {
            correoSeleccionado = 0;
        }
        return correos.get(correoSeleccionado).getTexto();
    }

    @Override
    public void onClick(int position) {
        correoSeleccionado = position;
        if (hayDetalle) {
            fragmentoDetalle.mostrarMensaje(correos.get(correoSeleccionado).getTexto());
        } else {
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.fcvListado, FragmentoDetalle.class, null)
                    .commit();
        }
    }
}