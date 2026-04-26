package views.viewmodels;

import fabiorodrigues.bricks.core.BricksViewModel;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import fabiorodrigues.bricks.data.DB;

import java.util.List;
import java.util.Map;

import models.Aluno;
import models.Disciplina;
import models.dtos.AlunoNota;
import models.enums.Gender;

public class AlunoViewModel extends BricksViewModel implements IAlunosTableViewModel {

    public final State<Aluno> alunoAtual = state(null);
    public final State<String> nomeAluno = state("");
    public final State<Gender> generoAluno = state(Gender.NONE);
    public final State<String> idadeAluno = state("");
    public final StateList<AlunoNota> notas = stateList(List.of());
    public final StateList<Boolean> isSideColumnOpen = stateList(List.of(false));
    public final State<Boolean> isEditOrAdd = state(false);

    public void carregarNotas(int alunoId) {
        List<AlunoNota> lista = DB.query()
                .select("a.id", "d.nome", "a.genero", "a.idade", "a.media", "nd.nota")
                .from("alunos a")
                .join("notas_disciplina nd", "nd.id_aluno = a.id")
                .join("disciplinas d", "d.id = nd.id_disciplina")
                .where("a.id", "=", alunoId)
                .execute(AlunoNota.class);
        notas.clear();
        notas.addAll(lista);
    }

    @Override
    public List<AlunoNota> getAlunosDisciplina() { return this.notas.get();}
    @Override
    public State<String> getNomeAluno() {
        return this.nomeAluno;
    }
    @Override
    public State<Gender> getGeneroAluno() {
        return this.generoAluno;
    }
    @Override
    public State<String> getIdadeAluno() {
        return this.idadeAluno;
    }
    @Override
    public State<String> getNotaAluno() { return state("");}


    @Override
    public State<Boolean> getIsEditOrAdd() {
        return this.isEditOrAdd;
    }

    @Override
    public StateList<Boolean> getIsSideColumnOpen() {
        return this.isSideColumnOpen;
    }

    @Override
    public void guardarAluno() {
        DB.query()
                .update("alunos")
                .set(
                        Map.of(
                                "nome",
                                nomeAluno.get(),
                                "genero",
                                generoAluno.get().label,
                                "idade",
                                idadeAluno.get()
                        )
                )
                .where("id", "=", alunoAtual.get().getId())
                .execute();
        carregarNotas(alunoAtual.get().getId());
    }

    @Override
    public void adicionarAluno() {}
    @Override
    public void setDisciplinas() {}
    @Override
    public StateList<Disciplina> getDisciplinas() {
        return stateList(List.of());
    }
    @Override
    public void selecionarDisciplina(Disciplina disciplina) {}
    @Override
    public void apagarNota(int alunoId, int disciplinaId) { }
    @Override
    public State<Disciplina> getDisciplinaSelecionada() { return null;}
    @Override
    public State<Integer> getIdAluno() { return state(0);}

    @Override
    public void refreshListTable() {
        if (alunoAtual.get() != null) {
            carregarNotas(alunoAtual.get().getId());
        }
    }
}
