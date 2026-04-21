package models;

public class Professor extends Pessoa {

    String grau;
    Disciplina disciplina;

    public Professor() {
        super();
        this.grau = "";
    }

    public Professor(String _name, int _age, Gender _gender, String _grau, Disciplina _disciplina) {
        super(_name, _gender, _age);
        this.grau = _grau;
        this.disciplina = _disciplina;
    }

    @Override
    public String falar() {
        String _fala = super.falar();
        return _fala + " dou aulas de " + this.disciplina;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }
}
