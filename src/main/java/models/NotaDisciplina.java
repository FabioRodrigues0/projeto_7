package models;

public class NotaDisciplina {

    private int id;
    private double nota;
    private int id_aluno;
    private int id_disciplina;

    public NotaDisciplina() {}

    /**
     * Cria uma nova nota associada a uma disciplina.
     *
     * @param _id ID da nota.
     * @param nota Valor da nota (entre 0 e 20).
     * @param _id_aluno ID do aluno associado à nota.
     * @param _id_disciplina ID da disciplina associada à nota.
     */
    public NotaDisciplina(int _id, double _nota, int _id_aluno, int _id_disciplina) {
        this.id = _id;
        this.nota = _nota;
        this.id_aluno = _id_aluno;
        this.id_disciplina = _id_disciplina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolve o valor da nota.
     *
     * @return Nota da disciplina.
     */
    public double getNota() {
        return nota;
    }

    /**
     * Define o valor da nota.
     *
     * @param nota Novo valor da nota.
     */
    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdAluno() {
        return id_aluno;
    }

    public void setIdAluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public int getIdDisciplina() {
        return id_disciplina;
    }

    public void setIdDisciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }
}
