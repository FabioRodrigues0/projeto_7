package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListaAlunos {

    private static ObservableList<Aluno> alunos = FXCollections.observableArrayList();

    /**
     * Devolve a lista global de alunos.
     *
     * @return Lista observável de Aluno.
     */
    public static ObservableList<Aluno> getAlunos() {
        return ListaAlunos.alunos;
    }

    /**
     * Adiciona um aluno à lista global.
     *
     * @param aluno
     *            Aluno a adicionar.
     */
    public static void setAlunos(Aluno aluno) {
        ListaAlunos.alunos.add(aluno);
    }
}
