package com.example.jl_fa.appenem.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl_fa.appenem.MainActivity;
import com.example.jl_fa.appenem.Model.Entities.UsuarioModel;
import com.example.jl_fa.appenem.Model.Entities.Usuarios;
import com.example.jl_fa.appenem.R;

public class CadastrarUsuario extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtCelular;
    Button btnCadastrarUsuario;
    UsuarioModel crudUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtCelular = (EditText) findViewById(R.id.edtCelular);
        btnCadastrarUsuario = (Button) findViewById(R.id.btnCadastrar);

        crudUser = new UsuarioModel(CadastrarUsuario.this );

        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(edtEmail.getText().toString().trim());
                if(crudUser.verificaEmailCadastrado(edtEmail.getText().toString())){
                    Toast.makeText(CadastrarUsuario.this, "E-mail já Cadastrado", Toast.LENGTH_SHORT).show();

                } else {

                    Usuarios cadUsuarios = new Usuarios();

                    cadUsuarios.setNome(edtNome.getText().toString());
                    cadUsuarios.setEmail(edtEmail.getText().toString());
                    cadUsuarios.setCelular(edtCelular.getText().toString());

                    if (crudUser.insertUsuarios(cadUsuarios)) {
                        Toast.makeText(CadastrarUsuario.this, "Usuário Cadastrado", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(CadastrarUsuario.this, Autenticacao.class);
                        //startActivity(intent);

                    } else {
                        Toast.makeText(CadastrarUsuario.this, "Usuário Não Cadastrado", Toast.LENGTH_SHORT).show();
                    }

                    edtNome.getText().clear();
                    edtEmail.getText().clear();
                    edtCelular.getText().clear();



                }

            }
        });
    }
}
