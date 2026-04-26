package models;

public class Aluno extends Pessoa {

    public Aluno() {
        super();
    }

    private double media;

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    @Override
    public String falar() {
        String fala = super.falar();
        return fala + " estou inscrito em";
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
