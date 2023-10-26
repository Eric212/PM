package com.ericsospedra.asignaturas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ericsospedra.asignaturas.R;
import com.ericsospedra.asignaturas.interfaces.IonClickListener;
import com.ericsospedra.asignaturas.models.Alumno;

import java.util.ArrayList;

public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.AlumnoViewHolder> {
    //TODO: Darle el control del click al adapter
    private ArrayList<Alumno> alumnos;

    private IonClickListener listener;

    public AlumnosAdapter(ArrayList<Alumno> alumnos, IonClickListener listener) {
        this.alumnos = alumnos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlumnoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_alumnos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        holder.bindAlumno(alumnos.get(position));
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvNombre;
        private final TextView tvEmail;
        private final TextView tvEdad;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            itemView.setOnClickListener(this);
        }

        public void bindAlumno(Alumno alumno) {
            tvNombre.setText(alumno.getNombre());
            tvEmail.setText(alumno.getEmail());
            tvEdad.setText(String.valueOf(alumno.getFechaNacimiento()));
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onClick(getAdapterPosition());
            }
        }
    }
}
