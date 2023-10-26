package com.ericsospedra.asignaturas.models.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ericsospedra.asignaturas.R;
import com.ericsospedra.asignaturas.adapters.AlumnosAdapter;
import com.ericsospedra.asignaturas.interfaces.IonClickListener;
import com.ericsospedra.asignaturas.models.Alumno;

import java.util.ArrayList;

public class FragmentoAlumnos extends Fragment {
    public interface IonAttach{
        ArrayList<Alumno> getDatosAlumnos();
        int getAlumnoSeleccionado();
    }
    private ArrayList<Alumno> alumnos;
    private int alumnoSeleccionado;
    private IonClickListener listener;

    public FragmentoAlumnos() {
        super(R.layout.fragmento_alumnos);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvAlumnos = view.findViewById(R.id.rvAlumnos);
        AlumnosAdapter adapter = new AlumnosAdapter(alumnos,listener);
        rvAlumnos.setAdapter(adapter);
        rvAlumnos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rvAlumnos.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rvAlumnos.scrollToPosition(alumnoSeleccionado);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IonAttach ionAttach = (IonAttach) context;
        alumnos = ionAttach.getDatosAlumnos();
        listener = (IonClickListener) context;
        alumnoSeleccionado = ionAttach.getAlumnoSeleccionado();
    }
}
