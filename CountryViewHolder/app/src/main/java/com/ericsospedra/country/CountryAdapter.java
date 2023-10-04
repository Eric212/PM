package com.ericsospedra.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryAdapter extends ArrayAdapter<Country> {

    private final Country[] countries;
    public CountryAdapter(@NonNull Context context, int resource, @NonNull Country[] countries) {
        super(context, resource, countries);
        this.countries = countries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View layout = convertView;
        ViewHolder viewHolder;

        if(layout==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            layout = inflater.inflate(R.layout.country,null);
            viewHolder = new ViewHolder();
            viewHolder.nombrePais = layout.findViewById(R.id.NombrePais);
            viewHolder.capital = layout.findViewById(R.id.capital);
            viewHolder.poblacion = layout.findViewById(R.id.population);
            viewHolder.bandera = layout.findViewById(R.id.flag);
            layout.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) layout.getTag();
        }
        try{
            String flag = "_"+countries[position].getCountryCode().toLowerCase();
            int flagId = getContext().getResources().getIdentifier(flag,"drawable",getContext().getPackageName());
            if(flagId != 0){
                viewHolder.bandera.setImageResource(flagId);
            }else{
                flag = "_onu";
                flagId = getContext().getResources().getIdentifier(flag,"drawable",getContext().getPackageName());
                viewHolder.bandera.setImageResource(flagId);
            }
        }catch (Exception e){
            Toast.makeText(getContext(),"Fallo al rellenar los datos de las banderas",Toast.LENGTH_LONG).show();
        }
        viewHolder.nombrePais.setText(countries[position].getCountryName());
        viewHolder.capital.setText("Capital: "+countries[position].getCapital());
        viewHolder.poblacion.setText("Poblacion: "+String.valueOf(countries[position].getPopulation()));
        return layout;
    }

    static class ViewHolder{
        TextView nombrePais;
        TextView capital;
        TextView poblacion;
        ImageView bandera;
    }
}
