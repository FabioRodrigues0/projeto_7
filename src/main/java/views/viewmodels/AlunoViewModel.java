package views.viewmodels;

import java.util.List;

import fabiorodrigues.bricks.core.BricksViewModel;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import fabiorodrigues.bricks.data.DB;
import models.Aluno;
import models.Disciplina;
import models.dtos.AlunoNota;
import models.enums.Gender;

// views/viewmodels/AlunoViewModel.java
public class AlunoViewModel extends BricksViewModel implements IAlunosTableViewModel {
    public final State<Aluno> alunoAtual = state(null);
    public final State<Integer> idAluno = state(0);
    public final State<String> nomeAluno = state("");
    public final State<Gender> generoAluno = state(Gender.NONE);
    public final State<String> idadeAluno = state("");
    public final StateList<AlunoNota> notas = stateList(List.of());
    public final StateList<Boolean> isSideColumnOpen = stateList(List.of(false));

    public final State<Boolean> isEditOrAdd = state(false);
    public final StateList<Disciplina> disciplinas = stateList(List.of());
    public final State<Disciplina> disciplinaSelecionada = state(null);

    @Override
    public List<AlunoNota> getAlunosDisciplina() {
        carregarNotas(alunoAtual.get().getId());
        return this.notas.get();
    }

    @Override
    public void selecionarDisciplina(Disciplina disciplina) {
        disciplinaSelecionada.set(disciplina);
        getAlunosDisciplina();
    }

    @Override
    public void guardarAluno() {

    }

    @Override
    public void adicionarAluno() {

    }

    @Override
    public void setDisciplinas() {
        List<Disciplina> listDisciplinas = DB.query()
                .select("d.id as id", "d.nome as nome", "d.descricao as descricao", "d.id_professor as id_professor")
                .from("disciplinas d")
                .join("notas_disciplina nd", "d.id = nd.id_disciplina")
                .where("nd.id_aluno", "=", alunoAtual.get().getId())
                .orderBy("d.id", "DESC")
                .execute(Disciplina.class);
        System.out.println(listDisciplinas);

        //disciplinas.clear();
        disciplinaSelecionada.set(listDisciplinas.getFirst());
        disciplinas.addAll(listDisciplinas);
    }

    @Override
    public StateList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    @Override
    public void apagarNota(int alunoId, int disciplinaId) {
        //
    }

    @Override
    public State<Disciplina> getDisciplinaSelecionada() {
        return null;
    }

    @Override
    public State<Integer> getIdAluno() {
        return this.idAluno;
    }

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
    public State<String> getNotaAluno() {
        return this.nomeAluno;
    }

    @Override
    public State<Boolean> getIsEditOrAdd() {
        return this.isEditOrAdd;
    }

    @Override
    public StateList<Boolean> getIsSideColumnOpen() {
        return this.isSideColumnOpen;
    }

    public void carregarNotas(int alunoId) {
        List<AlunoNota> lista = DB.query()
                .select("a.id", "a.nome", "a.genero", "a.idade", "a.media", "nd.nota")
                .from("alunos a")
                .join("notas_disciplina nd", "nd.id_aluno = a.id")
                .where("a.id", "=", alunoId)
                .execute(AlunoNota.class);
        notas.clear();
        notas.addAll(lista);
    }

    public void guardarPerfil() {
        DB.query()
                .update("alunos")
                .value("nome", nomeAluno.get())
                .value("genero", generoAluno.get().label)
                .value("idade", idadeAluno.get())
                .where("id", "=", alunoAtual.get().getId())
                .execute();
    }
}
