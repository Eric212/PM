package com.ericsospedra.caraocruz;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bcruz = null;
    Button bcara = null;
    TextView tvresultado = null;
    ImageView ivmoneda = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bcruz = findViewById(R.id.bcruz);
        bcara = findViewById(R.id.bcara);
        tvresultado = findViewById(R.id.tvresultado);
        ivmoneda = findViewById(R.id.ivmoneda);
        bcruz.setOnClickListener(this);
        bcara.setOnClickListener(this);
    }

    private void partida() {
        if(new Random().nextInt(10)+1<5){
            tvresultado.setText("Has perdido");
        }else {
            tvresultado.setText("Has ganado");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==bcara.getId()){
            ivmoneda.setImageDrawable(getDrawable(R.mipmap.cara_foreground));
        }else{
            ivmoneda.setImageDrawable(getDrawable(R.mipmap.cruz_foreground));
        }
        partida();
    }



}