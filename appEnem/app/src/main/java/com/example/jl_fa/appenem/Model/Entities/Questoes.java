package com.example.jl_fa.appenem.Model.Entities;

public class Questoes {
    private String area;
    private String pergunta;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String alternativaE;
    private int alternativaCerta;
    private int respostaUsuario;
    private int id;

    public Questoes() {
        this.alternativaA = null;
        this.alternativaB = null;
        this.alternativaC = null;
        this.alternativaD = null;
        this.alternativaE = null;
        this.area = null;
        this.pergunta = null;
        this.alternativaCerta = -1;
        this.respostaUsuario = -1;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public int getAlternativaCerta() {
        return alternativaCerta;
    }

    public void setAlternativaCerta(int alternativaCerta) {
        this.alternativaCerta = alternativaCerta;
    }

    public int getRespostaUsuario() {
        return respostaUsuario;
    }

    public void setRespostaUsuario(int respostaUsuario) {
        this.respostaUsuario = respostaUsuario;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public String getAlternativaE() {
        return alternativaE;
    }

    public void setAlternativaE(String alternativaE) {
        this.alternativaE = alternativaE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
