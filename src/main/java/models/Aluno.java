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
        String _fala = super.falar();
        return _fala + " estou inscrito em";
    }
}
