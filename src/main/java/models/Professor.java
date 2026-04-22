package models;

public class Professor extends Pessoa {

    String grau;

    public Professor() {
        super();
        this.grau = "";
    }

    public Professor(int _id, String _nome, Gender _genero, int _idade, String _grau) {
        super(_id, _nome, _genero, _idade);
        this.grau = _grau;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    @Override
    public String falar() {
        String _fala = super.falar();
        return _fala + " dou aulas de ";
    }

    @Override
    public String toString() {
        return "" + this.nome;
    }
}
