package com.ericsospedra.asignaturas.parsers;

import android.content.Context;

import com.ericsospedra.asignaturas.models.Alumno;
import com.ericsospedra.asignaturas.models.Asignatura;
import com.ericsospedra.asignaturas.R;
import com.ericsospedra.asignaturas.models.Nota;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AsignaturasParser {
    private final Context context;
    private ArrayList<Alumno> alumnos;

    public AsignaturasParser(Context context, ArrayList<Alumno> alumnos) {
        this.context = context;
        this.alumnos = alumnos;
    }

    public boolean parsear() {
        InputStream file = getContext().getResources().openRawResource(R.raw.asignaturas);
        String jsonAsignaturas = null;
        try {
            int sizeAsignaturas = file.available();
            byte[] bufferAsignaturas = new byte[sizeAsignaturas];
            file.read(bufferAsignaturas);
            jsonAsignaturas = new String(bufferAsignaturas, StandardCharsets.UTF_8);
            JSONTokener jsonTokener = new JSONTokener(jsonAsignaturas);
            JSONArray jsonArray = new JSONArray(jsonTokener);
            for (int i = 0; i < alumnos.size(); i++) {
                Nota[] notas = alumnos.get(i).getNotas();
                for(int j = 0; j < notas.length; j ++) {
                    JSONObject jsonAsignatura = jsonArray.getJSONObject(j);
                    String nombreAsignatura;
                    nombreAsignatura = jsonAsignatura.getString("nomAsig");
                    notas[j].setNombreAsignatura(nombreAsignatura);
                }
                alumnos.get(i).setNotas(notas);
            }
            return true;
        } catch (IOException | JSONException e) {
            return false;
        }
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Alumno> getAlumnosActualizado() {
        return alumnos;
    }
}
