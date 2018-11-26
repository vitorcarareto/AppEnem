package com.example.jl_fa.appenem.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jl_fa.appenem.MainActivity;
import com.example.jl_fa.appenem.Model.Entities.Questoes;
import com.example.jl_fa.appenem.Model.Entities.QuestoesModel;
import com.example.jl_fa.appenem.Model.Entities.Usuarios;
import com.example.jl_fa.appenem.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FazerSimulado extends AppCompatActivity {

    TextView txtDisciplina;
    TextView txtEnunciado;

    RadioGroup radioAlternativas;
    Button btnResponderQuestao;
    private Cursor cursor;
    Questoes quest;
    Integer nrQuest;

    RadioButton RbA;
    RadioButton RbB;
    RadioButton RbC;
    RadioButton RbD;
    RadioButton RbE;


    ////// tentando



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_simulado);
        //Inicializacao das listas e do count

        txtDisciplina = (TextView) findViewById(R.id.txtDisciplinaSimulado);
        txtEnunciado = (TextView) findViewById(R.id.txtEnunciadoSimulado);
        btnResponderQuestao = (Button) findViewById(R.id.btnResponderQuestao);
        radioAlternativas = (RadioGroup) findViewById(R.id.radioAlternativas);

        RbA = (RadioButton) findViewById(R.id.rdtSA);
        RbB = (RadioButton) findViewById(R.id.rdtSB);
        RbC = (RadioButton) findViewById(R.id.rdtSC);
        RbD = (RadioButton) findViewById(R.id.rdtSD);
        RbE = (RadioButton) findViewById(R.id.rdtSE);

        // TODO Get todas as questões
        // quest = new Questoes();
        //cursor = Cursor = nil

        final QuestoesModel questoesModel = new QuestoesModel(FazerSimulado.this);


        cursor = questoesModel.selectTodasQuestoes();
        if (cursor.getCount() > 0) {

            for(int i = 0; i < cursor.getCount(); i++) {


                txtDisciplina.setText(cursor.getString(1));
                txtEnunciado.setText(cursor.getString(2));
                RbA.setText(cursor.getString(3));
                RbB.setText(cursor.getString(4));
                RbC.setText(cursor.getString(5));
                RbD.setText(cursor.getString(6));
                RbE.setText(cursor.getString(7));


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

                            quest = new Questoes();

                            quest.setId(Integer.parseInt(cursor.getString(0)));
                            System.out.println(quest.getId());


                            quest.setAlternativaCerta(Integer.parseInt(cursor.getString(8)));
                            System.out.println(quest.getAlternativaCerta());


                            quest.setRespostaUsuario(Integer.toString(idx));
                            System.out.println(quest.getRespostaUsuario());

                            if (quest.getAlternativaCerta() == Integer.parseInt(quest.getRespostaUsuario())) {
                                Toast.makeText(getApplicationContext(), "Resposta Certa", Toast.LENGTH_SHORT).show();
                                questoesModel.updateQuestaoUsuario(quest);
                            } else {
                                Toast.makeText(getApplicationContext(), "Resposta Errada, Correta = " + quest.getAlternativaCerta(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

                cursor.moveToNext();

            }

            /*Toast.makeText(getApplicationContext(), "Simulado Finalizado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FazerSimulado.this,MainActivity.class);
            startActivity(intent);*/
        }

    }
}
