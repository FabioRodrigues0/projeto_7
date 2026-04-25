package models;

public class NotaDisciplina {

    private int id;
    private double nota;
    private int idAluno;
    private int idDisciplina;

    public NotaDisciplina() { }

    /**
     * Cria uma nova nota associada a uma disciplina.
     *
     * @param id ID da nota.
     * @param nota Valor da nota (entre 0 e 20).
     * @param idAluno ID do aluno associado à nota.
     * @param idDisciplina ID da disciplina associada à nota.
     */
    public NotaDisciplina(int id, double nota, int idAluno, int idDisciplina) {
        this.id = id;
        this.nota = nota;
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
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
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
}
