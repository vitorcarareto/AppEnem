package com.example.jl_fa.appenem.Model.Entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestoesModel extends SQLiteOpenHelper {
    private static final String DBNAME = "DBENEM";
    private static final int VERSAO = 17;

    public QuestoesModel(Context context)
    {
        super(context, DBNAME, null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db){

        //String SQL para criar a tabela no banco de dados, caso o banco não exista
        String sqlCriarTabelaQuestoes =
                "CREATE TABLE questoes (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "area TEXT NOT NULL, " +
                        "pergunta TEXT NOT NULL, " +
                        "alternativaA TEXT NOT NULL, " +
                        "alternativaB TEXT NOT NULL, " +
                        "alternativaC TEXT NOT NULL, " +
                        "alternativaD TEXT NOT NULL, " +
                        "alternativaE TEXT NOT NULL, " +
                        "alternativaCerta integer NOT NULL, " +
                        "respostaUsuario integer " +
                        ")";

        db.execSQL(sqlCriarTabelaQuestoes);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sqlDropTabelaQuestoes = "DROP TABLE IF EXISTS questoes";
        db.execSQL(sqlDropTabelaQuestoes);

        onCreate(db);
    }


    public boolean insertQuestoes(Questoes questao){

        boolean retorno = false;

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("area", questao.getArea());
        contentValues.put("pergunta", questao.getPergunta());
        contentValues.put("alternativaA", questao.getAlternativaA());
        contentValues.put("alternativaB", questao.getAlternativaB());
        contentValues.put("alternativaC", questao.getAlternativaC());
        contentValues.put("alternativaD", questao.getAlternativaD());
        contentValues.put("alternativaE", questao.getAlternativaE());
        contentValues.put("alternativaCerta", questao.getAlternativaCerta());
        contentValues.put("respostaUsuario", questao.getRespostaUsuario());


        try{
            db.insert("questoes",null, contentValues);
            retorno = true;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return(retorno);
    }

    public Cursor selectTodasQuestoes(){

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectTodos = "SELECT * FROM questoes ORDER BY RANDOM()";

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sqlSelectTodos,null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                return cursor;
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return cursor;
    }

    public Questoes selectUmaQuestao(Questoes questao){
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectUm = "SELECT * FROM questoes WHERE _id = " + questao.getId();

        Cursor cursor = null;
        Questoes q = null;
        try{
            cursor = db.rawQuery(sqlSelectUm,null);

            if(cursor.moveToFirst()){
                q = new Questoes();

                q.setId(cursor.getInt(0));
                q.setArea(cursor.getString(1));
                q.setPergunta(cursor.getString(2));
                q.setAlternativaA(cursor.getString(3));
                q.setAlternativaB(cursor.getString(4));
                q.setAlternativaC(cursor.getString(5));
                q.setAlternativaD(cursor.getString(6));
                q.setAlternativaE(cursor.getString(7));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return q;
    }

    public boolean deleteQuestao(Questoes questao){
        boolean retorno = false;

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.delete("questoes","_id = " + questao.getId(), null);
            retorno = true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return retorno;
    }


    public boolean updateQuestao(Questoes questao){
        boolean retorno = false;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("area", questao.getArea());
        contentValues.put("pergunta", questao.getPergunta());
        contentValues.put("alternativaA", questao.getAlternativaA());
        contentValues.put("alternativaB", questao.getAlternativaB());
        contentValues.put("alternativaC", questao.getAlternativaC());
        contentValues.put("alternativaD", questao.getAlternativaD());
        contentValues.put("alternativaE", questao.getAlternativaE());
        contentValues.put("alternativaCerta", questao.getAlternativaCerta());
        contentValues.put("respostaUsuario", questao.getRespostaUsuario());


        try{
            db.update("questoes",contentValues, "_id = " + questao.getId(), null);
            retorno = true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return retorno;
    }

    public boolean verificaEmailCadastrado(String email){
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectUm = "SELECT * FROM questoes WHERE email = '" + email + "'";

        Cursor cursor = null;

        cursor = db.rawQuery(sqlSelectUm,null);
        if(cursor.getCount() > 0){
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

    public boolean updateQuestaoUsuario(Questoes questao){
        boolean retorno = false;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("respostaUsuario", questao.getRespostaUsuario());


        try{
            db.update("questoes",contentValues, "_id = " + questao.getId(), null);
            retorno = true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return retorno;
    }

}