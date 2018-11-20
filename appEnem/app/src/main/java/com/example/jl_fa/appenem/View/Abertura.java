package com.example.jl_fa.appenem.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jl_fa.appenem.MainActivity;
import com.example.jl_fa.appenem.R;

public class Abertura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*
        thread é um subsistema que ira rodar para fazer que as funções que definimos execute quando essa activity for chamada
         */
        Thread timerThred = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(Abertura.this,Autenticacao.class);
                    startActivity(intent);
                }
            }
        };
        timerThred.start();

    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

}