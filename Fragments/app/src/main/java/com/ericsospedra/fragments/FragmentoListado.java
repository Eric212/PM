package com.ericsospedra.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentoListado extends Fragment {
    public interface IOnAttach{
        ArrayList<Correo> getCorreos();
        int getCorreoSeleccionado();
    }
    private ArrayList<Correo> correos;
    private int correoSeleccionado;
    private IonClickListener listener;
    public FragmentoListado() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvCorreo = view.findViewById(R.id.rvCorreo);
        CorreoAdapter correoAdapter = new CorreoAdapter(correos,listener);
        rvCorreo.setAdapter(correoAdapter);
        rvCorreo.setHasFixedSize(true);
        rvCorreo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rvCorreo.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rvCorreo.scrollToPosition(correoSeleccionado);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttach iOnAttach = (IOnAttach) context;
        correos = iOnAttach.getCorreos();
        listener = (IonClickListener) context;
        correoSeleccionado = iOnAttach.getCorreoSeleccionado();
    }
}
