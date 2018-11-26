package com.example.jl_fa.appenem.Model.Entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioModel extends SQLiteOpenHelper {
    private static final String DBNAME = "DBENEM";
    private static final int VERSAO = 15;

    public UsuarioModel(Context context)
    {
        super(context, DBNAME, null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //String SQL para criar a tabela no banco de dados, caso o banco não exista
        String sqlCriarTabelaUsuario =
                "CREATE TABLE usuarios (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nome TEXT NOT NULL, " +
                        "email TEXT NOT NULL UNIQUE, " +
                        "celular TEXT NOT NULL " +
                        ")";

        db.execSQL(sqlCriarTabelaUsuario);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sqlDropTabelaUsuarios = "DROP TABLE IF EXISTS usuarios";
        db.execSQL(sqlDropTabelaUsuarios);

        onCreate(db);
    }


    public boolean insertUsuarios(Usuarios usuario){

        boolean retorno = false;

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", usuario.getNome());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("celular", usuario.getCelular());


        try{
            db.insert("usuarios",null,contentValues);
            retorno = true;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return(retorno);
    }

    public Cursor selectTodosUsuarios(){

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectTodos = "SELECT * FROM usuarios";

        Cursor cursor = null;
        try{
            cursor = db.rawQuery(sqlSelectTodos,null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                return cursor;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return cursor;
    }

    public Usuarios selectUmUsuario(Usuarios usuario){
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectUm = "SELECT * FROM usuarios WHERE _id = " + usuario.getId();

        Cursor cursor = null;
        Usuarios user = null;
        try{
            cursor = db.rawQuery(sqlSelectUm,null);

            if(cursor.moveToFirst()){
                user = new Usuarios();

                user.setId(cursor.getInt(0));
                user.setNome(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setCelular(cursor.getString(3));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return user;
    }

    public boolean deleteUsuario(Usuarios usuario){
        boolean retorno = false;

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.delete("usuarios","_id = " + usuario.getId(), null);
            retorno = true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return retorno;
    }


    public boolean updateUsuario(Usuarios usuario){
        boolean retorno = false;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nome",usuario.getNome());
        contentValues.put("email",usuario.getEmail());
        contentValues.put("celular",usuario.getCelular());

        try{
            db.update("usuarios",contentValues, "_id = " + usuario.getId(), null);
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

        String sqlSelectUm = "SELECT * FROM usuarios WHERE email = '" + email + "'";

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

}
