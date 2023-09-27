package com.ericsospedra.spinner2elements;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Integer[] datos = new Integer[100];

        for(int i = 1; i<=100; i++){
            datos[i-1]= i;
        }

        Spinner spNumeros = findViewById(R.id.spNumeros);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_2,android.R.id.text1,datos){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View layout = super.getView(position, convertView, parent);
                System.out.println(layout.getAccessibilityClassName()+" GetView");
                TextView textView1 = layout.findViewById(android.R.id.text1);
                TextView textView2 = layout.findViewById(android.R.id.text2);
                textView1.setText("numero "+datos[position]);
                textView2.setText("numero "+datos[position]);

                return layout;
            }

//            @Override
//            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View dropdown = super.getDropDownView(position, convertView, parent);
//                System.out.println(dropdown.getAccessibilityClassName()+" Dropdown");
//                TextView textView1 = dropdown.findViewById(android.R.id.text1);
//                TextView textView2 = dropdown.findViewById(android.R.id.text2);
//                textView1.setText("numero "+datos[position]);
//                textView2.setText("numero "+datos[position]);
//                return dropdown;
//            }
        };
        spNumeros.setAdapter(adapter);
       //adapter.setDropDownViewResource(android.R.layout.simple_list_item_2);


    }
}