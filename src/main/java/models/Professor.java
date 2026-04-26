package models;

import models.enums.Gender;

public class Professor extends Pessoa {

    String grau;

    public Professor() {
        super();
        this.grau = "";
    }

    public Professor(int id, String nome, Gender genero, int idade, String grau) {
        super(id, nome, genero, idade);
        this.grau = grau;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    @Override
    public String falar() {
        String fala = super.falar();
        return fala + " dou aulas de ";
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
