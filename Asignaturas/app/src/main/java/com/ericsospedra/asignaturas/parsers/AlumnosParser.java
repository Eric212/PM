package com.ericsospedra.asignaturas.parsers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.ericsospedra.asignaturas.models.Alumno;
import com.ericsospedra.asignaturas.models.Nota;
import com.ericsospedra.asignaturas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AlumnosParser {
    private final Context context;
    private ArrayList<Alumno> alumnos;

    public AlumnosParser(Context context) {
        this.context = context;
    }

    public boolean parsear() {
        alumnos = new ArrayList<>();
        InputStream file = getContext().getResources().openRawResource(R.raw.alumnos_notas);
        String jsonAlumnos = null;
        try {
            int sizeAlumnos = file.available();
            byte[] bufferAlumnos = new byte[sizeAlumnos];
            file.read(bufferAlumnos);
            jsonAlumnos = new String(bufferAlumnos, StandardCharsets.UTF_8);
            JSONTokener jsonTokener = new JSONTokener(jsonAlumnos);
            JSONArray jsonArray = new JSONArray(jsonTokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonAlumno = jsonArray.getJSONObject(i);
                int nia;
                String nombre, apellidos, email;
                Date fechaNacimiento;
                nia = jsonAlumno.getInt("nia");
                nombre = jsonAlumno.getString("nombre");
                apellidos = jsonAlumno.getString("apellido1") + " " + jsonAlumno.getString("apellido2");
                fechaNacimiento =  new SimpleDateFormat("yyyy-MM-dd").parse(jsonAlumno.getString("fechaNacimiento"));
                email = jsonAlumno.getString("email");
                JSONArray temporal = jsonAlumno.getJSONArray("notas");
                Nota[] notas = new Nota[temporal.length()];
                for (int j = 0; j < temporal.length(); j++) {
                    JSONObject jsonNota = temporal.getJSONObject(j);
                    float nota;
                    String codAsignatura;
                    nota = Float.parseFloat(jsonNota.getString("calificacion"));
                    codAsignatura = jsonNota.getString("codAsig");
                    notas[j] = new Nota(nota,codAsignatura);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    alumnos.add(new Alumno(nia,nombre,apellidos,fechaNacimiento,email,notas));
                }
            }
            return true;
        } catch (IOException | JSONException | ParseException e) {
            Log.d("Info",e.getMessage());
            return false;
        }
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }
}
