package com.ericsospedra.showmyname;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bsaludo = findViewById(R.id.bsaludo);
        EditText etnombre = findViewById(R.id.etnombre);
        EditText etapellidos = findViewById(R.id.etapellido);

        bsaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Saludos"+etnombre.getText()+" "+etapellidos.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }
}