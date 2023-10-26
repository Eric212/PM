package com.ericsospedra.asignaturas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.ericsospedra.asignaturas.adapters.AsignaturasAdapter;
import com.ericsospedra.asignaturas.interfaces.IonClickListener;
import com.ericsospedra.asignaturas.models.Alumno;
import com.ericsospedra.asignaturas.models.Nota;
import com.ericsospedra.asignaturas.models.fragments.FragmentoAlumnos;
import com.ericsospedra.asignaturas.models.fragments.FragmentoAsignaturas;
import com.ericsospedra.asignaturas.parsers.AlumnosParser;
import com.ericsospedra.asignaturas.parsers.AsignaturasParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentoAlumnos.IonAttach, FragmentoAsignaturas.IonAttach, IonClickListener {
    private static final String ALUMNO_SELECCIONADO = "AlumnoSeleccionado";
    private static final String ALUMNO  = "Alumno";
    private ArrayList<Alumno> alumnos;
    private int alumnoSeleccionado;
    private boolean hayDetalle;
    private FragmentoAsignaturas fragmentoAsignaturas;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState !=null){
            alumnoSeleccionado = savedInstanceState.getInt(ALUMNO_SELECCIONADO);
            alumnos = (ArrayList<Alumno>) savedInstanceState.getSerializable(ALUMNO);
        }else{
            getDatosAlumnos();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        hayDetalle = findViewById(R.id.fcvAsignaturas) != null;
        if (hayDetalle) {
            if(alumnoSeleccionado>0) {
                fragmentoAsignaturas = (FragmentoAsignaturas) manager.findFragmentById(R.id.fcvAsignaturas);
                if (manager.findFragmentById(R.id.fcvAlumnos) instanceof FragmentoAsignaturas) {
                    manager.beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fcvAlumnos, FragmentoAlumnos.class, null)
                            .commit();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(ALUMNO_SELECCIONADO, alumnoSeleccionado);
        outState.putSerializable(ALUMNO, alumnos);
        super.onSaveInstanceState(outState);
    }
    @Override
    public ArrayList<Alumno> getDatosAlumnos() {
        AlumnosParser alumnosParser = new AlumnosParser(this);
        if (alumnosParser.parsear()) {
            alumnos = alumnosParser.getAlumnos();
            AsignaturasParser asignaturasParser = new AsignaturasParser(this, alumnos);
            if (asignaturasParser.parsear()) {
                alumnos = asignaturasParser.getAlumnosActualizado();
                return alumnos;
            } else {
                return alumnos;
            }
        }else {
            return null;
        }
    }

    @Override
    public int getAlumnoSeleccionado() {
        if(alumnoSeleccionado > 0){
            return alumnoSeleccionado;
        }
        alumnoSeleccionado = -1;
        return alumnoSeleccionado;
    }

    @Override
    public Nota[] getDatosAsignaturas() {
        if(alumnoSeleccionado !=-1) {
            return alumnos.get(alumnoSeleccionado).getNotas();
        }else {
            return null;
        }
    }

    @Override
    public void onClick(int position) {
        alumnoSeleccionado = position;
        if(hayDetalle) {
            manager.beginTransaction().add(R.id.fcvAsignaturas,FragmentoAsignaturas.class,null).commit();
            fragmentoAsignaturas = (FragmentoAsignaturas) manager.findFragmentById(R.id.fcvAsignaturas);
            fragmentoAsignaturas.updateNotas(alumnos.get(alumnoSeleccionado).getNotas());
        }else{
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.fcvAlumnos, FragmentoAsignaturas.class, null)
                    .commit();
        }
    }
}