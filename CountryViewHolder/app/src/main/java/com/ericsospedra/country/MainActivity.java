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
import android.widget.Toast;

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
        ListView listaPaises = findViewById(R.id.lvCountries);
        if (parser.parseXml()) {
            CountryAdapter adapter = new CountryAdapter(this, R.layout.country, parser.getCountries());
            listaPaises.setAdapter(adapter);
        } else {
            Toast.makeText(MainActivity.this, "Error al conseguir los datos", Toast.LENGTH_LONG).show();
        }
    }
}