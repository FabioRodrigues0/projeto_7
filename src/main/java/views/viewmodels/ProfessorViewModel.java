package views.viewmodels;

import fabiorodrigues.bricks.core.BricksViewModel;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import fabiorodrigues.bricks.data.DB;

import java.util.List;
import java.util.Map;

import models.Aluno;
import models.Disciplina;
import models.NotaDisciplina;
import models.Professor;
import models.dtos.AlunoNota;
import models.enums.Gender;

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
    public final StateList<AlunoNota> alunosDisciplina = stateList(List.of());
    public final State<Integer> tableRefresh = state(0);

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

    @Override
    public void refreshListTable() {
        List<AlunoNota> lista = DB.query()
                .select("a.id", "a.nome", "a.genero", "a.idade", "a.media", "nd.nota")
                .from("alunos a")
                .join("notas_disciplina nd", "nd.id_aluno = a.id")
                .where("nd.id_disciplina", "=", getDisciplinaSelecionada().get().getId())
                .execute(AlunoNota.class);
        alunosDisciplina.clear();
        alunosDisciplina.addAll(lista);
    }

    public List<AlunoNota> getAlunosDisciplina() {
        return DB.query()
                .select("a.id", "a.nome", "a.genero", "a.idade", "a.media", "nd.nota") // ← nota desta disciplina
                .from("alunos a")
                .join("notas_disciplina nd", "nd.id_aluno = a.id")
                .where("nd.id_disciplina", "=", disciplinaSelecionada.get().getId())
                .execute(AlunoNota.class);
    }

    @Override
    public void apagarNota(int alunoId, int disciplinaId) {
        // Apaga a nota da disciplina
        DB.query()
                .deleteFrom("notas_disciplina")
                .where("id_aluno", "=", alunoId)
                .where("id_disciplina", "=", disciplinaId)
                .execute();

        // Apaga o aluno se não tiver mais notas em nenhuma disciplina
        long totalNotas = DB.query()
                .select("COUNT(*) as total")
                .from("notas_disciplina")
                .where("id_aluno", "=", alunoId)
                .executeRaw()
                .first().size();

        if (totalNotas == 0) {
            DB.query()
                    .deleteFrom("alunos")
                    .where("id", "=", alunoId)
                    .execute();
        }

        // Atualiza a tabela
        refreshListTable();
        tableRefresh.update(v -> v + 1);
    }

    @Override
    public State<Disciplina> getDisciplinaSelecionada() {
        return this.disciplinaSelecionada;
    }

    private List<NotaDisciplina> getNotasAluno() {
        return DB.query()
                .select("nota", "id_aluno", "id_disciplina")
                .from("notas_disciplina")
                .where("id_aluno", "=", idAluno.get())
                .execute(NotaDisciplina.class);
    }

    private Double calcMedia(List<NotaDisciplina> notas) {
        double media;

        if (notas.isEmpty()) {
            media = Double.parseDouble(notaAluno.get());
        } else {
            double soma = notas.stream().mapToDouble(NotaDisciplina::getNota).sum();
            // Inclui a nova nota no cálculo
            media = soma / notas.size();
        }
        return Double.parseDouble(String.format("%.2f", media));
    }

    @Override
    public void guardarAluno() {
        DB.query()
                .update("notas_disciplina")
                .set(Map.of("nota", String.format("%.2f", notaAluno.get())))
                .where("id_aluno", "=", idAluno.get())
                .where("id_disciplina", "=", disciplinaSelecionada.get().getId())
                .execute();

        List<NotaDisciplina> notas = getNotasAluno();
        double media = calcMedia(notas);

        DB.query()
                .update("alunos")
                .set(
                        Map.of(
                                "nome",
                                nomeAluno.get(),
                                "genero",
                                generoAluno.get().label,
                                "idade",
                                idadeAluno.get(),
                                "media",
                                media
                        )
                )
                .where("id", "=", idAluno.get())
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
}
