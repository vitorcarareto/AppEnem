package com.example.jl_fa.appenem.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl_fa.appenem.MainActivity;
import com.example.jl_fa.appenem.Model.Entities.Usuarios;
import com.example.jl_fa.appenem.R;

public class CadastrarUsuario extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtCelular;
    Button btnCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtCelular = (EditText) findViewById(R.id.edtCelular);
        btnCadastrarUsuario = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuarios cadUsuarios = new Usuarios();

                cadUsuarios.setNome(edtNome.getText().toString());
                cadUsuarios.setEmail(edtEmail.getText().toString());
                cadUsuarios.setCelular(edtCelular.getText().toString());
                MainActivity.usuarios.add(cadUsuarios);

                Toast.makeText(getApplicationContext(), "Usuário Cadastrado", Toast.LENGTH_SHORT).show();
                edtNome.getText().clear();
                edtEmail.getText().clear();
                edtCelular.getText().clear();
            }
        });
    }
}
