package views.viewmodels;

import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import java.util.List;
import models.Disciplina;
import models.dtos.AlunoNota;
import models.enums.Gender;

public interface IAlunosTableViewModel {
    List<AlunoNota> getAlunosDisciplina();
    void setDisciplinas();
    StateList<Disciplina> getDisciplinas();
    void apagarNota(int alunoId, int disciplinaId);
    State<Disciplina> getDisciplinaSelecionada();
    State<Integer> getIdAluno();
    State<String> getNomeAluno();
    State<Gender> getGeneroAluno();
    State<String> getIdadeAluno();
    State<String> getNotaAluno();
    State<Boolean> getIsEditOrAdd();
    StateList<Boolean> getIsSideColumnOpen();
    void selecionarDisciplina(Disciplina disciplina);
    void guardarAluno();
    void adicionarAluno();
    void refreshListTable();
}
