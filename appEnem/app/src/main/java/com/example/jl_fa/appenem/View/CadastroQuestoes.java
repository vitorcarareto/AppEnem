package com.example.jl_fa.appenem.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.jl_fa.appenem.Model.Entities.Questoes;
import com.example.jl_fa.appenem.Model.Entities.QuestoesModel;
import com.example.jl_fa.appenem.R;

public class CadastroQuestoes extends AppCompatActivity {

    private Spinner mySpinner;
    Button btnCadastrar;
    EditText edtEnunciado;
    EditText edtAutA;
    EditText edtAutB;
    EditText edtAutC;
    EditText edtAutD;
    EditText edtAutE;
    QuestoesModel crudQuestao;
    RadioGroup radioAlternativas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_questoes);

        edtEnunciado = (EditText) findViewById(R.id.edtEnunciado);
        edtAutA = (EditText) findViewById(R.id.edtAltA);
        edtAutB = (EditText) findViewById(R.id.edtAltB);
        edtAutC = (EditText) findViewById(R.id.edtAltC);
        edtAutD = (EditText) findViewById(R.id.edtAltD);
        edtAutE = (EditText) findViewById(R.id.edtAltE);
        radioAlternativas = (RadioGroup) findViewById(R.id.radioAlternativas);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrarQuestao);

        mySpinner = (Spinner) findViewById(R.id.mySpinner);

        crudQuestao = new QuestoesModel(CadastroQuestoes.this);


        final ArrayAdapter<CharSequence> adapterDisciplina = ArrayAdapter.createFromResource(getApplicationContext(), R.array.disciplinas, R.layout.spinner_item);
        mySpinner.setAdapter(adapterDisciplina);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Questoes cadQuestoes = new Questoes();

                cadQuestoes.setArea(mySpinner.getSelectedItem().toString());
                cadQuestoes.setPergunta(edtEnunciado.getText().toString());
                cadQuestoes.setAlternativaA(edtAutA.getText().toString());
                cadQuestoes.setAlternativaB(edtAutB.getText().toString());
                cadQuestoes.setAlternativaC(edtAutC.getText().toString());
                cadQuestoes.setAlternativaD(edtAutD.getText().toString());
                cadQuestoes.setAlternativaE(edtAutE.getText().toString());


                int radioButtonId = radioAlternativas.getCheckedRadioButtonId();
                View radioButton = radioAlternativas.findViewById(radioButtonId);
                int idx = radioAlternativas.indexOfChild(radioButton);

                cadQuestoes.setAlternativaCerta(idx);

                if (idx < 0) {
                    Toast.makeText(CadastroQuestoes.this, "Selecione a alternativa correta.", Toast.LENGTH_SHORT).show();
                } else {
                    if (crudQuestao.insertQuestoes(cadQuestoes)) {

                        edtEnunciado.getText().clear();
                        edtAutA.getText().clear();
                        edtAutB.getText().clear();
                        edtAutC.getText().clear();
                        edtAutD.getText().clear();
                        edtAutE.getText().clear();

                        Toast.makeText(CadastroQuestoes.this, "Questão Cadastrada", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(CadastroQuestoes.this, "Questão Não Cadastrada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
