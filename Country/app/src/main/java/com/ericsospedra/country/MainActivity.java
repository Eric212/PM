package com.ericsospedra.country;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlParser parser = new XmlParser(this);
        ListView lista = findViewById(R.id.lvCountries);
        mostrarDatos(parser.parseXml(), lista, parser.getCountries());
    }

    private void mostrarDatos(boolean check, ListView lista, Country[] datos) {

        if (check) {
            ArrayAdapter<Country> countries = new ArrayAdapter<Country>(this, R.layout.country, R.id.NombrePais, datos) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View layout = super.getView(position, convertView, parent);

                    TextView nombrePais = layout.findViewById(R.id.NombrePais);
                    TextView capital = layout.findViewById(R.id.capital);
                    TextView population = layout.findViewById(R.id.population);
                    ImageView flag = layout.findViewById(R.id.flag);
                    nombrePais.setText(datos[position].getCountryName());
                    capital.setText("Capital: " + datos[position].getCapital());
                    population.setText("Poblacion: " + datos[position].getPopulation());
                    if (layout.getContext().getResources().getIdentifier("_" + datos[position].getCountryCode().toLowerCase(Locale.ROOT)
                            , "drawable", layout.getContext().getPackageName()) != 0) {
                        flag.setImageResource(layout.getContext().getResources().getIdentifier("_" + datos[position].getCountryCode().toLowerCase(Locale.ROOT)
                                , "drawable", layout.getContext().getPackageName()));
                    } else {
                        flag.setImageResource(R.mipmap.ic_launcher);
                    }
                    return layout;
                }
            };
            lista.setAdapter(countries);
        }
    }
}