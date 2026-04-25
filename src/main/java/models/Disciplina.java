package models;

public class Disciplina {

    private int id;
    private String nome;
    private String descricao;
    private int idProfessor;

    public Disciplina() { }

    public Disciplina(int id, String nome, String descricao, int idProfessor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idProfessor = idProfessor;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    @Override
    public String toString() {
        return nome;
    }
}
