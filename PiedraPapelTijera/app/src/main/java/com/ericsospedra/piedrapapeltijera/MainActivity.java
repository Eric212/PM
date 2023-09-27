package com.ericsospedra.piedrapapeltijera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Partida partida = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button piedra = findViewById(R.id.bpiedra);
        Button papel = findViewById(R.id.bpapel);
        Button tijeras = findViewById(R.id.btijeras);
        Button reiniciar = findViewById(R.id.breiniciar);
        piedra.setOnClickListener(this);
        papel.setOnClickListener(this);
        tijeras.setOnClickListener(this);
        reiniciar.setOnClickListener(this);
        partida = new Partida(new int[]{0, 0},new int[]{1,2,3} ,-1,-1,findViewById(R.id.tvpuntuacion),new ImageView[]{findViewById(R.id.ivpcpu),findViewById(R.id.ivplayer)});


    }

    @Override
    public void onClick(View view) {
        try {
            int[] elecciones = {R.id.bpiedra,R.id.bpapel,R.id.btijeras};
            if(view.getId()==elecciones[0]){
                partida.setPlayer(1);
                partida.setPantallaJugadores(1,R.mipmap.piedra_foreground);
                partida.resultado();
            } else if (view.getId()==elecciones[1]) {
                partida.setPlayer(2);
                partida.setPantallaJugadores(1,R.mipmap.papel_foreground);
                partida.resultado();
            } else if (view.getId()==elecciones[2]) {
                partida.setPlayer(3);
                partida.setPantallaJugadores(1,R.mipmap.tijeras_foreground);
                partida.resultado();
            }else {
                partida.reiniciar(getResources().getString(R.string.textoInicial));
            }
        }catch (InputMismatchException ex){
            Toast.makeText(MainActivity.this,"Error al seleccionar una opcion", Toast.LENGTH_LONG).show();
        }
    }
}