package com.ericsospedra.asignaturas.models.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ericsospedra.asignaturas.R;
import com.ericsospedra.asignaturas.adapters.AsignaturasAdapter;
import com.ericsospedra.asignaturas.models.Alumno;
import com.ericsospedra.asignaturas.models.Nota;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentoAsignaturas extends Fragment {
    public interface IonAttach{
       Nota[] getDatosAsignaturas();
    }
    private Nota[] notas;
    private AsignaturasAdapter adapter;

    public FragmentoAsignaturas() {
        super(R.layout.fragmento_asignaturas);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(notas != null) {
            RecyclerView rvAsignaturas = view.findViewById(R.id.rvAsignaturas);
            adapter = new AsignaturasAdapter(notas);
            rvAsignaturas.setAdapter(adapter);
            rvAsignaturas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rvAsignaturas.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IonAttach ionAttach = (IonAttach) context;
        notas = ionAttach.getDatosAsignaturas();
    }
    public void updateNotas(Nota[] notas){
        if(adapter!=null){
            adapter.updateNotas(notas);
        }
    }
}
