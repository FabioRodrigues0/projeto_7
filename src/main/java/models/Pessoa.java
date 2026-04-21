package models;

public class Pessoa {

    String name;
    Gender gender;
    int age;

    public Pessoa() {
        this.name = "";
        this.gender = Gender.NONE;
        this.age = 0;
    }

    public Pessoa(String _name, Gender _gender, int _age) {
        this.name = _name;
        this.gender = _gender;
        this.age = _age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // mudado para metodo e retorno de String porque nao irá imprimir
    public String falar() {
        return ("Olá sou " + (this.gender == Gender.MALE ? "o" : "a") + this.name
                + " com a idade de " + this.age);
    }
}
