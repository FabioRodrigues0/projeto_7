package models;

public class NotaDisciplina {

    private String nomeDisciplina;
    private double nota;

    /**
     * Cria uma nova nota associada a uma disciplina.
     *
     * @param nomeDisciplina
     *            Nome da disciplina.
     * @param nota
     *            Valor da nota (entre 0 e 20).
     */
    public NotaDisciplina(String nomeDisciplina, double nota) {
        this.nomeDisciplina = nomeDisciplina;
        this.nota = nota;
    }

    /**
     * Devolve o nome da disciplina.
     *
     * @return Nome da disciplina.
     */
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    /**
     * Define o nome da disciplina.
     *
     * @param nomeDisciplina
     *            Novo nome da disciplina.
     */
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
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
     * @param nota
     *            Novo valor da nota.
     */
    public void setNota(double nota) {
        this.nota = nota;
    }
}
