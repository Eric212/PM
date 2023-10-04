package com.ericsospedra.adivinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final Partida partida = new Partida(3, crearNumeroSecreto(),false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(partida.getIntentos()!=0 && !partida.isHasGandado()) {
                    Button boton = (Button) v;
                    TextView intentos = findViewById(R.id.tvIntentos);
                    TextView screen = findViewById(R.id.tvScreen);
                    screen.setText(boton.getText().toString());
                    boton.setEnabled(false);
                    partida.setIntentos(partida.getIntentos() - 1);
                    partida.setBotones(boton);
                    comprobarNumero(Integer.parseInt(boton.getText().toString()), partida.getNumerosSecreto(), intentos);
                }else{
                    partida.setBotones((Button) v);
                    reset(v.getRootView());
                }
            }
        };
        Button reset = findViewById(R.id.bReset);
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        Button b7 = findViewById(R.id.b7);
        Button b8 = findViewById(R.id.b8);
        Button b9 = findViewById(R.id.b9);
        Button b10 = findViewById(R.id.b10);
        reset.setOnClickListener(listener);
        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);
        b4.setOnClickListener(listener);
        b5.setOnClickListener(listener);
        b6.setOnClickListener(listener);
        b7.setOnClickListener(listener);
        b8.setOnClickListener(listener);
        b9.setOnClickListener(listener);
        b10.setOnClickListener(listener);


    }

    private void reset(View v) {
        partida.setIntentos(3);
        TextView screen = v.findViewById(R.id.tvScreen);
        TextView tvIntentos = findViewById(R.id.tvIntentos);
        tvIntentos.setText(R.string.intentos_restantes_3);
        screen.setText("");
        for (Button boton : partida.getBotones()) {
            if (boton.getId()!=R.id.bReset) {
                boton.setEnabled(true);
            } else {
                boton.setVisibility(View.INVISIBLE);
            }
        }
    }

    private int crearNumeroSecreto() {
        return new Random().nextInt(10) + 1;
    }

    private void comprobarNumero(int number, int sercretNumber, TextView intentos) {
        if (number == sercretNumber) {
            mostrarResultado(0, intentos);
        } else {
            mostrarResultado(number - sercretNumber, intentos);
        }
    }

    private void mostrarResultado(int resultado, TextView intentos) {
        if(partida.getIntentos()==0){
            intentos.setText("Has perdido");
            Button reset = findViewById(R.id.bReset);
            reset.setVisibility(View.VISIBLE);
        }else {
            if (resultado == 0) {
                intentos.setText("Has ganado");
                intentos.setTextSize(intentos.getTextSize()+1.5f);
                partida.setHasGandado(true);
            } else {
                if (resultado > 0) {
                    intentos.setText("Has fallado, el numero es inferiro \nIntenos restantes: " + partida.getIntentos());
                } else {
                    intentos.setText("Has fallado, el numero es superior \nIntenos restantes: " + partida.getIntentos());
                }
            }
        }
    }
}