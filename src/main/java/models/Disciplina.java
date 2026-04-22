package models;

public class Disciplina {

    private int id;
    private String nome;
    private String descricao;
    private int id_professor;

    public Disciplina() {}

    public Disciplina(int _id, String _nome, String _descricao, int _id_professor) {
        this.id = _id;
        this.nome = _nome;
        this.descricao = _descricao;
        this.id_professor = _id_professor;
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
        return id_professor;
    }

    public void setIdProfessor(int id_professor) {
        this.id_professor = id_professor;
    }

    @Override
    public String toString() {
        return nome;
    }
}
