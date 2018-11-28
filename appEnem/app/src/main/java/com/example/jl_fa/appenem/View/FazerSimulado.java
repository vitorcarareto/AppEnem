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
    public static int questaoAtual = 0;
    public static Questoes questao;
    ArrayList<Questoes> questoes = new ArrayList<>();
    int qtd_questoes = 0;

    TextView txtDisciplina;
    TextView txtEnunciado;

    RadioGroup radioAlternativas;
    Button btnResponderQuestao;
    private Cursor cursor;
//    Questoes quest;
//    Integer nrQuest;

    RadioButton RbA;
    RadioButton RbB;
    RadioButton RbC;
    RadioButton RbD;
    RadioButton RbE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_simulado);
        
        //Inicializacao das listas e do count
        questaoAtual = 0;
        questoes = new ArrayList<>();

        txtDisciplina = (TextView) findViewById(R.id.txtDisciplinaSimulado);
        txtEnunciado = (TextView) findViewById(R.id.txtEnunciadoSimulado);
        btnResponderQuestao = (Button) findViewById(R.id.btnResponderQuestao);
        radioAlternativas = (RadioGroup) findViewById(R.id.radioAlternativas);

        RbA = (RadioButton) findViewById(R.id.rdtSA);
        RbB = (RadioButton) findViewById(R.id.rdtSB);
        RbC = (RadioButton) findViewById(R.id.rdtSC);
        RbD = (RadioButton) findViewById(R.id.rdtSD);
        RbE = (RadioButton) findViewById(R.id.rdtSE);

        // Get todas as questões
        final QuestoesModel questoesModel = new QuestoesModel(FazerSimulado.this);
        cursor = questoesModel.selectTodasQuestoes();

        qtd_questoes = cursor.getCount();

        for(int i = 0; i < cursor.getCount(); i++) {
            // Criar Questão e adicionar na lista de questões
            Questoes q = new Questoes();

            q.setId(cursor.getInt(0));
            q.setArea(cursor.getString(1));
            q.setPergunta(cursor.getString(2));
            q.setAlternativaA(cursor.getString(3));
            q.setAlternativaB(cursor.getString(4));
            q.setAlternativaC(cursor.getString(5));
            q.setAlternativaD(cursor.getString(6));
            q.setAlternativaE(cursor.getString(7));
            q.setAlternativaCerta(cursor.getInt(8));

            questoes.add(q);
            cursor.moveToNext();
        }

        btnResponderQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonID = radioAlternativas.getCheckedRadioButtonId();
                View radioButton = radioAlternativas.findViewById(radioButtonID);
                int idx = radioAlternativas.indexOfChild(radioButton);

                System.out.println("Alternativa selecionada: " + Integer.toString(idx));

                if (idx < 0) {
                    Toast.makeText(getApplicationContext(), "Selecione uma alternativa.", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar resposta do usuário

                    questao.setRespostaUsuario(idx);
                    questoesModel.updateQuestaoUsuario(questao);

                    if (questao.getAlternativaCerta() == questao.getRespostaUsuario()) {
                        System.out.println("Resposta correta: " + Integer.toString(questao.getAlternativaCerta()));
                        Toast.makeText(getApplicationContext(), "Resposta Correta", Toast.LENGTH_SHORT).show();
                    } else {
                        // Não dizer a alternativa correta para o usuário (facilita muito)
                        // SE for mostrar a correta para o usuário, converter de (0, 1, ...) para (A, B, ...)
                        System.out.println("Responsta incorreta: " + Integer.toString(idx) + ". Correta: " + Integer.toString(questao.getAlternativaCerta()));
                        Toast.makeText(getApplicationContext(), "Resposta Incorreta", Toast.LENGTH_SHORT).show();
                    }

                    // Carregar nova questão
                    carregarQuestao();
                }
            }
        });

        carregarQuestao();

    }

    public void carregarQuestao() {
        // Verificar se existem questões
        if (qtd_questoes > 0) {
            // Verificar se não chegou no final do simulado
            if (questaoAtual < questoes.size()) {
                // Carregar próxima questão
                System.out.println(Integer.toString(qtd_questoes) + " questões encontradas.");

                questao = questoes.get(questaoAtual);

                txtDisciplina.setText(questao.getArea());
                txtEnunciado.setText(questao.getPergunta());
                RbA.setText(questao.getAlternativaA());
                RbB.setText(questao.getAlternativaB());
                RbC.setText(questao.getAlternativaC());
                RbD.setText(questao.getAlternativaD());
                RbE.setText(questao.getAlternativaE());

                questaoAtual += 1;

            } else {
                // Finalizar simulado
                Toast.makeText(getApplicationContext(), "Simulado Finalizado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FazerSimulado.this, MainActivity.class);
                startActivity(intent);
            }
        } else {
            // Nenhuma questão cadastrada, limpar tela
            Toast.makeText(getApplicationContext(), "Nenhuma questão encontrada.", Toast.LENGTH_SHORT).show();
            txtDisciplina.setText("");
            RbA.setText("");
            RbB.setText("");
            RbC.setText("");
            RbD.setText("");
            RbE.setText("");
            btnResponderQuestao.setVisibility(View.GONE);
        }
    }
}
