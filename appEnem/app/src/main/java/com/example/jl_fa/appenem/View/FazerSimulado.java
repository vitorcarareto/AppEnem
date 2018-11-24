package com.example.jl_fa.appenem.View;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jl_fa.appenem.Model.Entities.Usuarios;
import com.example.jl_fa.appenem.R;

import org.w3c.dom.Text;

public class FazerSimulado extends AppCompatActivity {

    TextView txtDisciplina;
    RadioGroup radioAlternativas;
    Button btnResponderQuestao;

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_simulado);

        btnResponderQuestao = (Button) findViewById(R.id.btnResponderQuestao);
        radioAlternativas = (RadioGroup) findViewById(R.id.radioAlternativas);

        // TODO Get todas as questões

        btnResponderQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonID = radioAlternativas.getCheckedRadioButtonId();
                View radioButton = radioAlternativas.findViewById(radioButtonID);
                int idx = radioAlternativas.indexOfChild(radioButton);

                System.out.println(idx);

                if (idx < 0) {
                    Toast.makeText(getApplicationContext(), "Selecione uma alternativa.", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO Guardar resposta do usuário

                    // Resetar formulário

                    // TODO Preencher com a próxima questão


                }
            }
        });

    }
}
