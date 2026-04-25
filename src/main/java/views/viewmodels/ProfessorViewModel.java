package views.viewmodels;

import fabiorodrigues.bricks.components.Alert;
import fabiorodrigues.bricks.core.BricksViewModel;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import fabiorodrigues.bricks.data.DB;

import java.util.List;

import models.Aluno;
import models.Disciplina;
import models.dtos.AlunoNota;
import models.enums.Gender;
import models.NotaDisciplina;
import models.Professor;

public class ProfessorViewModel extends BricksViewModel implements IAlunosTableViewModel {
    public final State<Professor> professorSelecionado = state(null);
    public final State<Disciplina> disciplinaSelecionada = state(null);
    public final StateList<Disciplina> disciplinas = stateList(List.of());
    public final State<Integer> idAluno = state(0);
    public final State<String> nomeAluno = state("");
    public final State<String> notaAluno = state("");
    public final State<Gender> generoAluno = state(Gender.NONE);
    public final State<String> idadeAluno = state("");
    public final StateList<Boolean> isSideColumnOpen = stateList(List.of(false));

    // add = true; edit = false
    public final State<Boolean> isEditOrAdd = state(false);


    public void selecionarDisciplina(Disciplina disciplina) {
        disciplinaSelecionada.set(disciplina);
        getAlunosDisciplina();
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
        return this.notaAluno;
    }

    @Override
    public State<Boolean> getIsEditOrAdd() {
        return this.isEditOrAdd;
    }

    @Override
    public StateList<Boolean> getIsSideColumnOpen() {
        return this.isSideColumnOpen;
    }
    @Override
    public void setDisciplinas() {
        List<Disciplina> listDisciplinas = DB.query()
                .select("id", "nome", "descricao", "id_professor")
                .from("disciplinas")
                .where("id_professor", "=", professorSelecionado.get().getId())
                .orderBy("id", "DESC")
                .execute(Disciplina.class);

        //disciplinas.clear();
        disciplinaSelecionada.set(listDisciplinas.getFirst());
        disciplinas.addAll(listDisciplinas);
    }

    @Override
    public StateList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public List<AlunoNota> getAlunosDisciplina() {
        return DB.query()
                .select("a.id", "a.nome", "a.genero", "a.idade", "a.media",
                        "nd.nota")              // ← nota desta disciplina
                .from("alunos a")
                .join("notas_disciplina nd", "nd.id_aluno = a.id")
                .where("nd.id_disciplina", "=", disciplinaSelecionada.get().getId())
                .execute(AlunoNota.class);
    }

    @Override
    public void apagarNota(int alunoId, int disciplinaId) {
        DB.query()
                .deleteFrom("notas_disciplina")
                .where("id_aluno", "=", alunoId)
                .where("id_disciplina", "=", disciplinaId)
                .execute();
    }

    @Override
    public State<Disciplina> getDisciplinaSelecionada() {
        return this.disciplinaSelecionada;
    }

    private List<NotaDisciplina> getNotasAluno(){
        return DB.query()
                .select("nota", "id_aluno", "id_disciplina")
                .from("notas_disciplina")
                .where("id", "=", idAluno.get())
                .execute(NotaDisciplina.class);

    }

    private Double calcMedia(List<NotaDisciplina> notas){
        double media;

        if (notas.isEmpty()) {
            media = Double.parseDouble(notaAluno.get());
        } else {
            double soma = notas.stream()
                    .mapToDouble(NotaDisciplina::getNota)
                    .sum();
            // Inclui a nova nota no cálculo
            media = (soma + Double.parseDouble(notaAluno.get())) / (notas.size() + 1);
        }
        return media;
    }
    @Override
    public void guardarAluno() {
        List<NotaDisciplina> notas = getNotasAluno();
        double media = calcMedia(notas);

        DB.query()
                .update("alunos")
                .value("nome", nomeAluno.get())
                .value("genero", generoAluno.get())
                .value("idade", idadeAluno.get())
                .value("media", media)
                .execute();
    }

    @Override
    public void adicionarAluno() {
        try {
            List<NotaDisciplina> notas = getNotasAluno();
            double media = calcMedia(notas);

            DB.query()
                    .insertInto("alunos")
                    .value("nome", nomeAluno.get())
                    .value("genero", generoAluno.get().label)
                    .value("idade", idadeAluno.get())
                    .value("media", media)
                    .execute();

            Aluno aluno = DB.query()
                    .select("id", "nome", "genero", "idade", "media")
                    .from("alunos")
                    .where("nome", "=", nomeAluno.get())
                    .orderBy("id", "DESC")
                    .limit(1)
                    .execute(Aluno.class)
                    .get(0);

            DB.query()
                    .insertInto("notas_disciplina")
                    .value("id_aluno", aluno.getId())
                    .value("id_disciplina", disciplinaSelecionada.get().getId())
                    .value("nota", notaAluno.get())
                    .execute();

        } catch (Exception e) {
            System.err.println("[adicionarAluno] Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void apagarAluno(int alunoId, int disciplinaId) {
        DB.query()
                .deleteFrom("notas_disciplina")
                .where("id_aluno", "=", alunoId)
                .where("id_disciplina", "=", disciplinaId)
                .execute();
    }
}
