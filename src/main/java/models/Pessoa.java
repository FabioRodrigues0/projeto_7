package models;

import models.enums.Gender;

public class Pessoa {

    int id;
    String nome;
    Gender genero;
    int idade;

    public Pessoa() {
        this.id = 0;
        this.nome = "";
        this.genero = Gender.NONE;
        this.idade = 0;
    }

    public Pessoa(String name, Gender gender, int age) {
        this.nome = name;
        this.genero = gender;
        this.idade = age;
    }

    public Pessoa(int id, String name, Gender gender, int age) {
        this.id = id;
        this.nome = name;
        this.genero = gender;
        this.idade = age;
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

    public Gender getGenero() {
        return genero;
    }

    public void setGenero(Gender genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // mudado para metodo e retorno de String porque nao irá imprimir
    public String falar() {
        return (
            "Olá sou "
            + (this.genero == Gender.MALE ? "o" : "a")
            + this.nome
            + " com a idade de "
            + this.idade
        );
    }
}
