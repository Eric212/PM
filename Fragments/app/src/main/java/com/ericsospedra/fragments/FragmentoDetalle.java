package com.ericsospedra.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentoDetalle extends Fragment {
    private String mensaje;

    private TextView tvMensaje;

    public interface IOnAttach{
        String getMensaje();
    }
    public FragmentoDetalle() {
        super(R.layout.fragmento_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.tvMensaje = view.findViewById(R.id.tvMensaje);
        mostrarMensaje(mensaje);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttach iOnAttach = (IOnAttach) context;
        this.mensaje = iOnAttach.getMensaje();
    }

    public void mostrarMensaje (String mensaje){
        this.mensaje = mensaje;
        this.tvMensaje.setText(mensaje);
    }
}
