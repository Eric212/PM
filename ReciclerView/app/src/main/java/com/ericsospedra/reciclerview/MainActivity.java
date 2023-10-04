package com.ericsospedra.reciclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlParser parser = new XmlParser(this);
        if(parser.parseXml()){
            RecyclerView paises = findViewById(R.id.rvPaises);
            Country[] countries = parser.getCountries();
            CountryAdapter adapter = new CountryAdapter(countries);
            paises.setAdapter(adapter);
            paises.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            paises.setHasFixedSize(true);
        }
    }
}