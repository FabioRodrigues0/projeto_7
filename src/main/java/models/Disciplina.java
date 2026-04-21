package models;

public enum Disciplina {
    MATEMATICA("Matemática"),
    PORTUGUES("Português"),
    INFORMATICA("Informática"),
    INGLES("Inglês"),
    FISICA("Fisíca"),
    DEFAULT("");

    private String descricao;
    public Professor professor;

    Disciplina(String descricao) {
        this.descricao = descricao;
    }

    Disciplina(String descricao, Professor professor) {
        this(descricao);
        this.professor = professor;
    }

    /**
     * Devolve o nome legível da disciplina.
     *
     * @return Descrição da disciplina.
     */
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static Disciplina[] getValues() {
        return VALUES;
    }

    private static final Disciplina[] VALUES = values();

    /**
     * Devolve todos os valores do enum como array.
     *
     * @return Array de Disciplina.
     */
    public static Disciplina[] getDisciplinas() {
        return VALUES;
    }
}
