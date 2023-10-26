package com.ericsospedra.asignaturas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ericsospedra.asignaturas.R;
import com.ericsospedra.asignaturas.models.Alumno;
import com.ericsospedra.asignaturas.models.Nota;

import java.util.ArrayList;

public class AsignaturasAdapter extends RecyclerView.Adapter<AsignaturasAdapter.AsignaturaViewHolder> {
    private Nota[] notas;

    public AsignaturasAdapter(Nota[] notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public AsignaturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AsignaturaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_asignaturas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AsignaturaViewHolder holder, int position) {
        holder.bindAsignaturas(notas[position]);
    }

    @Override
    public int getItemCount() {
        return notas.length;
    }

    public class AsignaturaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSiglas;
        private TextView tvAsignatura;
        private TextView tvNota;

        public AsignaturaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSiglas = itemView.findViewById(R.id.tvSiglas);
            tvAsignatura = itemView.findViewById(R.id.tvAsignaturas);
            tvNota = itemView.findViewById(R.id.tvNota);
        }

        public void bindAsignaturas(Nota nota) {
            tvSiglas.setText(nota.getCodAsignatura());
            tvAsignatura.setText(nota.getNombreAsignatura());
            tvNota.setText(String.valueOf(nota.getNota()));
        }
    }
    public void updateNotas(Nota[] notas){
        this.notas = notas;
        notifyDataSetChanged();
    }
}
