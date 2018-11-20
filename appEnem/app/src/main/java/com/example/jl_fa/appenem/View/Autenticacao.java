package com.example.jl_fa.appenem.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl_fa.appenem.MainActivity;
import com.example.jl_fa.appenem.Model.Entities.Usuarios;
import com.example.jl_fa.appenem.R;

import static com.example.jl_fa.appenem.MainActivity.usuarios;

public class Autenticacao extends AppCompatActivity {

    EditText edtEmailAut;
    Button btnEntrar;
    Button btnCadastrarAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);

        edtEmailAut = (EditText) findViewById(R.id.edtEmailAut);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnCadastrarAut = (Button) findViewById(R.id.btnCadastrarAut);

        btnCadastrarAut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Autenticacao.this, CadastrarUsuario.class);
                startActivity(intent);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verificar se o email está cadastrado, se estiver chamar o main.



            }

        });
    }
}
