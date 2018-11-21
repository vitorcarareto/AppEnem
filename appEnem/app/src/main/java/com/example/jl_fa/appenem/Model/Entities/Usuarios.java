package com.example.jl_fa.appenem.Model.Entities;

import java.util.ArrayList;

public class Usuarios {
    private String nome;
    private String email;
    private String celular;
    private int qdeAcertos;
    private int id;

    public Usuarios() {
        this.nome = null;
        this.email = null;
        this.celular = null;
        this.qdeAcertos = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getQdeAcertos() {
        return qdeAcertos;
    }

    public void setQdeAcertos(int qdeAcertos) {
        this.qdeAcertos = qdeAcertos;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", qdeAcertos=" + qdeAcertos +
                '}';
    }

}
