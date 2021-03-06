package com.example.jl_fa.appenem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jl_fa.appenem.Model.Entities.QuestoesModel;
import com.example.jl_fa.appenem.Model.Entities.Usuarios;
import com.example.jl_fa.appenem.View.CadastrarUsuario;
import com.example.jl_fa.appenem.View.CadastroQuestoes;
import com.example.jl_fa.appenem.View.FazerSimulado;
import com.example.jl_fa.appenem.View.VerAcertos;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Cursor cursor;
    TextView Acertos;
    TextView Erros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "E-mail com os resultados enviado.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Acertos = (TextView) findViewById(R.id.lblAcertos);
        Erros = (TextView) findViewById(R.id.lblErros);

        int nAcertos = 0;
        int nErros = 0;
        final QuestoesModel questoesModel = new QuestoesModel(MainActivity.this);

        cursor = questoesModel.selectTodasQuestoes();

         for(int i = 0; i < cursor.getCount(); i++) {
             System.out.println("Resposta correta: " + cursor.getString(8));
             System.out.println("Resposta digitada: " + cursor.getString(9));

            if(cursor.getInt(8) == cursor.getInt(9)){
                nAcertos ++;
            } else {
                nErros ++;
            }
            cursor.moveToNext();
        }

        Acertos.setText(Integer.toString(nAcertos));
        Erros.setText(Integer.toString(nErros));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_cadastrarUsuario){
            Intent intent = new Intent(MainActivity.this,CadastrarUsuario.class);
            startActivity(intent);

        }else if (id == R.id.nav_fazerSimulado){
            Intent intent = new Intent(MainActivity.this, FazerSimulado.class);
            startActivity(intent);
        }else if (id == R.id.nav_verAcertos){
            Intent intent = new Intent(MainActivity.this, VerAcertos.class);
            startActivity(intent);

        }else if (id == R.id.nav_cadastroQuestoes) {
            Intent intent = new Intent(MainActivity.this, CadastroQuestoes.class);
            startActivity(intent);

        }else if (id == R.id.nav_sair){
            finish();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
