package com.ericsospedra.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CorreoAdapter extends RecyclerView.Adapter<CorreoAdapter.CorreoViewHolder>{
    private final ArrayList<Correo> correos;
    private final IonClickListener listener;

    public CorreoAdapter(ArrayList<Correo> correos,IonClickListener listener) {
        this.correos = correos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CorreoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CorreoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.correo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CorreoViewHolder holder, int position) {
        holder.bindCorreo(correos.get(position));
    }

    @Override
    public int getItemCount() {
        return correos.size();
    }


    public class CorreoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView asunto;

        private final TextView texto;

        public CorreoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.asunto = itemView.findViewById(R.id.tvAsunto);
            this.texto = itemView.findViewById(R.id.tvTexto);
            itemView.setOnClickListener(this);
        }
        public void bindCorreo(Correo correo){
            this.asunto.setText(correo.getAsunto());
            this.texto.setText(correo.getTexto());
        }
        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onClick(getAdapterPosition());
            }
        }
    }
}
