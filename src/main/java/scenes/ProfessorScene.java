package scenes;

import fabiorodrigues.bricks.components.Alert;
import fabiorodrigues.bricks.components.Align;
import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Card;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.DataTable;
import fabiorodrigues.bricks.components.LazyColumn;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.SelectionMode;
import fabiorodrigues.bricks.components.Spacer;
import fabiorodrigues.bricks.components.TableAction;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.components.TextField;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.BricksScene;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import fabiorodrigues.bricks.data.DB;
import fabiorodrigues.bricks.style.Modifier;
import java.util.List;
import models.Aluno;
import models.Disciplina;
import models.NotaDisciplina;
import models.Professor;

public class ProfessorScene extends BricksScene {

    protected final State<Professor> professorSelecionado;
    protected final State<Disciplina> disciplinaSelecionada = state(null);
    protected final StateList<Disciplina> disciplinasProfessor = stateList(List.of());
    protected final State<String> nomeAluno = state("");
    protected final State<String> notaAluno = state("");

    private final DataTable<Aluno> tabelaAlunos = new DataTable<Aluno>()
        .searchable()
        .columnToggle()
        .toolbarAction(
            "Apagar selecionadas",
            () -> apagarSelecionadas(),
            SelectionMode.REQUIRES_SELECTION
        )
        .column("Numero", a -> String.valueOf(a.getId()))
        .bold()
        .width(100)
        .column("Nome", Aluno::getNome)
        .wrapText()
        .width(250)
        .column("Media", a -> String.valueOf(a.getMedia()))
        .width(120)
        .align(Align.CENTER)
        .actionColumn(
            new TableAction<Aluno>()
                .icon("fas-tags")
                .tooltip("Editar Nota")
                .onClick(nota -> editarNota(nota.getId()))
        )
        .actionColumn(
            new TableAction<Aluno>()
                .icon("fas-trash")
                .tooltip("Eliminar Aluno")
                .danger()
                .onClick(nota -> apagarNota(nota.getId()))
        )
        .selectable()
        .pageSize(15);

    public ProfessorScene(BricksApplication app, State<Professor> professorSelecionado) {
        super(app);
        this.professorSelecionado = professorSelecionado;
        getDisciplinasProfessor();
    }

    public void getDisciplinasProfessor() {
        List<Disciplina> _disciplinas = DB.query()
            .select("id", "nome", "descricao", "id_professor")
            .from("disciplinas")
            .where("id_professor", "=", professorSelecionado.get().getId())
            .orderBy("id", "DESC")
            .execute(Disciplina.class);

        disciplinasProfessor.clear();
        disciplinasProfessor.addAll(_disciplinas);
    }

    public void getAlunosDisciplina() {
        if (disciplinaSelecionada.get() == null) {
            tabelaAlunos.items(List.of());
            return;
        }

        List<Aluno> alunos = DB.query()
            .select("a.id", "a.nome", "a.genero", "a.idade", "a.media")
            .from("Aluno a")
            .join("nota_disciplina nd", "nd.id_aluno = a.id")
            .where("nd.id_disciplina", "=", disciplinaSelecionada.get().getId())
            .execute(Aluno.class);

        tabelaAlunos.items(alunos);
    }

    private void apagarNota(int id) {
        DB.query()
            .deleteFrom("notes_disciplina")
            .where("id_aluno", "=", id)
            .where("id_disciplina", "=", disciplinaSelecionada.get().getId())
            .execute();
        DB.query().deleteFrom("Aluno").where("id", "=", id).execute();
        getAlunosDisciplina();
    }

    private void apagarSelecionadas() {
        tabelaAlunos
            .getSelected()
            .forEach(aluno -> {
                apagarNota(aluno.getId());
            });
        getAlunosDisciplina();
    }

    private void editarNota(int id) {
        List<NotaDisciplina> notas = DB.query()
            .select("id", "id_aluno", "id_disciplina", "nota")
            .from("nota_disciplina")
            .where("id_aluno", "=", id)
            .execute(NotaDisciplina.class);
    }

    @Override
    public Component render() {
        return new Column()
            .padding(20)
            .gap(12)
            .modifier(new Modifier().fillMaxWidth())
            .children(
                new Row()
                    .gap(10)
                    .children(
                        new Text("Área do Professor").fontSize(22.0),
                        new Spacer(),
                        new Button("Voltar").onClick(() ->
                            app.navigateTo(new LobbyScene(app, professorSelecionado))
                        )
                    ),
                new Row()
                    .gap(8)
                    .children(
                        new LazyColumn<Disciplina>()
                            .gap(2)
                            .items(disciplinasProfessor)
                            .emptyState(new Text("Nenhuma disciplina encontrada"))
                            .item(disciplina ->
                                new Button(disciplina.getNome()).onClick(() -> {
                                    disciplinaSelecionada.set(disciplina);
                                })
                            ),
                        new Column()
                            .gap(0)
                            .modifier(new Modifier().fillMaxWidth())
                            .children(tabelaAlunos),
                        new Column()
                            .gap(0)
                            .children(
                                new Card()
                                    .padding(16)
                                    .elevation(2)
                                    .children(
                                        new Text("Adicionar Aluno").fontSize(15),
                                        new TextField()
                                            .label("Nome:")
                                            .placeholder("Nome do aluno")
                                            .bindTo(nomeAluno),
                                        new TextField()
                                            .label("Nota:")
                                            .placeholder("Nota do aluno")
                                            .bindTo(notaAluno)
                                    )
                            )
                    )
            );
    }
}
