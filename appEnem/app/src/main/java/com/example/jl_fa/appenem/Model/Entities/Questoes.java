package com.example.jl_fa.appenem.Model.Entities;

public class Questoes {
    private String area;
    private String pergunta;
    private String alternativas;
    private String alternativasCerta;
    private String respostaUsuario;

    public Questoes() {
        this.area = null;
        this.pergunta = null;
        this.alternativas = null;
        this.alternativasCerta = null;
        this.respostaUsuario = null;
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

    public String getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String alternativas) {
        this.alternativas = alternativas;
    }

    public String getAlternativasCerta() {
        return alternativasCerta;
    }

    public void setAlternativasCerta(String alternativasCerta) {
        this.alternativasCerta = alternativasCerta;
    }

    public String getRespostaUsuario() {
        return respostaUsuario;
    }

    public void setRespostaUsuario(String respostaUsuario) {
        this.respostaUsuario = respostaUsuario;
    }
}
