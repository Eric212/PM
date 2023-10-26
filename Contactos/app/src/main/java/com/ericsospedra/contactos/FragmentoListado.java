package com.ericsospedra.contactos;


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
    public interface IonAttach{
        ArrayList<Contacto> getContactos();
        int getContactoSeleccionado();
    }
    private ArrayList<Contacto> contactos;
    private int contactoSeleccionado;
    private IonClickListener listener;
    public FragmentoListado() {
        super(R.layout.fragmento_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvContacto = view.findViewById(R.id.rvListado);
        ContactoAdapter contactoAdapter = new ContactoAdapter(contactos,listener);
        rvContacto.setAdapter(contactoAdapter);
        rvContacto.setHasFixedSize(true);
        rvContacto.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rvContacto.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rvContacto.scrollToPosition(contactoSeleccionado);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IonAttach ionAttach = (IonAttach) context;
        contactos = ionAttach.getContactos();
        listener = (IonClickListener) context;
        contactoSeleccionado = ionAttach.getContactoSeleccionado();
    }
}
