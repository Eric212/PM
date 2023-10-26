package com.ericsospedra.contactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {
    private final ArrayList<Contacto> contactos;
    private final IonClickListener listener;

    public ContactoAdapter(ArrayList<Contacto> contactos, IonClickListener listener) {
        this.contactos = contactos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listado, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.bindContacto(contactos.get(position));
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }
    public class ContactoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombre;
        private TextView telefono;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvContact);
            this.telefono = itemView.findViewById(R.id.tvPhone);
            itemView.setOnClickListener(this);
        }

        public void bindContacto(Contacto contacto) {
            this.nombre.setText(contacto.getNombre());
            this.telefono.setText(contacto.getTelefono1());
        }
        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onClick(getAdapterPosition());
            }
        }
    }
}
