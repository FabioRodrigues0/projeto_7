package models;

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

    public Pessoa(String _name, Gender _gender, int _age) {
        this.nome = _name;
        this.genero = _gender;
        this.idade = _age;
    }

    public Pessoa(int _id, String _name, Gender _gender, int _age) {
        this.id = _id;
        this.nome = _name;
        this.genero = _gender;
        this.idade = _age;
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
            "Olá sou " +
            (this.genero == Gender.MALE ? "o" : "a") +
            this.nome +
            " com a idade de " +
            this.idade
        );
    }
}
