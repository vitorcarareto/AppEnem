package com.example.jl_fa.appenem.View;

import android.content.Intent;
import android.database.Cursor;
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

import static com.example.jl_fa.appenem.MainActivity.usuarios;

public class Autenticacao extends AppCompatActivity {

    EditText edtEmailAut;
    Button btnEntrar;
    Button btnCadastrarAut;
    Usuarios aUsuarios ;

    private Cursor cursor;


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

                UsuarioModel buscarUser = new UsuarioModel(getBaseContext());
                cursor = buscarUser.selectTodosUsuarios();
                boolean lAchou = false;
                if (cursor.getCount() > 0){


                    cursor.moveToFirst();
                    for(int i =0 ;i<cursor.getCount(); i++){
                        System.out.println(cursor.getString(2).trim() + " = " + edtEmailAut.getText().toString().trim());
                        if (cursor.getString(2).trim().equals(edtEmailAut.getText().toString().trim())){
                            Intent intent = new Intent(Autenticacao.this, MainActivity.class);
                            startActivity(intent);
                            lAchou = true;

                        } else {
                            cursor.moveToNext();
                        }
                    }


                    if(lAchou == false){
                        Toast.makeText(getApplicationContext(), "Usuário não Cadastrado", Toast.LENGTH_SHORT).show();
                    }

                }






                    //Toast.makeText(getApplicationContext(), usuarios.getItem(0).getEmail(), Toast.LENGTH_SHORT).show();

                /*
                boolean lAchou = false;
                int i = 0;
                // verificar se o email está cadastrado, se estiver chamar o main.
              //  for(int i =0; i < usuarios.getCount()-1; i++){

                    if(usuarios.getItem(i).getEmail().isEmpty() == false){
                        if (usuarios.getItem(i).getEmail() == edtEmailAut.getText().toString()){
                            Intent intent = new Intent(Autenticacao.this, MainActivity.class);
                            startActivity(intent);
                            lAchou = true;
                        }
                    }
               // }

                if(lAchou == false){
                    Toast.makeText(getApplicationContext(), "Usuário não Cadastrado", Toast.LENGTH_SHORT).show();
                }
*/

            }

        });
    }
}
