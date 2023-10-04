package com.ericsospedra.reciclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private final Country[] countries;
    public CountryAdapter(Country[] countries) {
        this.countries = countries;
    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bindCountry(countries[position]);
    }

    @Override
    public int getItemCount() {
        return countries.length;
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView flag;
        private final TextView countryName;
        private final TextView capital;
        private final TextView population;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag);
            countryName = itemView.findViewById(R.id.NombrePais);
            capital = itemView.findViewById(R.id.capital);
            population = itemView.findViewById(R.id.population);
        }

        public void bindCountry(Country country){
            String flagString = "_"+country.getCountryCode().toLowerCase();
            int flagId = flag.getContext().getResources().getIdentifier(flagString,"drawable",flag.getContext().getPackageName());
            if(flagId != 0){
                flag.setImageResource(flagId);
            }else{
                flagString = "_onu";
                flagId = flag.getContext().getResources().getIdentifier(flagString,"drawable",flag.getContext().getPackageName());
                flag.setImageResource(flagId);
            }
            countryName.setText(country.getCountryName());
            capital.setText(country.getCapital());
            population.setText(String.valueOf(country.getPopulation()));
        }
    }
}
