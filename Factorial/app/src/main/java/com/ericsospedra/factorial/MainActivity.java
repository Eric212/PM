package com.ericsospedra.factorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etnumber = findViewById(R.id.etnumber);
        Button bcalcula = findViewById(R.id.bcalcula);
        TextView tvresultado = findViewById(R.id.tvresultado);

        bcalcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvresultado.setText(factorizar(Integer.parseInt(etnumber.getText().toString())));
            }
        });
    }

    private String factorizar(int etnumber) {
        int fact = 1;
        for (int i = 1; i <= etnumber; i++) {
            fact = fact * i;
        }
        return String.valueOf(fact);
    }
}