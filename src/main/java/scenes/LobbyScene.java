package scenes;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Dropdown;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.BricksScene;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.Effect;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import fabiorodrigues.bricks.data.DB;
import fabiorodrigues.bricks.style.Modifier;
import java.util.List;
import models.Professor;

public class LobbyScene extends BricksScene {

    // ── Estado local ──────────────────────────────────────────────────────────

    private final StateList<Professor> listProfessores = stateList(List.of());

    // ── Estado persistente (vem do App) ──────────────────────────────────────

    private final State<Professor> professorSelecionado;

    private final Effect loadProfessores = new Effect(() -> getProfessores());

    public LobbyScene(BricksApplication app, State<Professor> professorSelecionado) {
        super(app);
        this.professorSelecionado = professorSelecionado;
    }

    private void getProfessores() {
        List<Professor> professores = DB.query()
            .select("id", "nome", "genero", "idade", "grau")
            .from("professores")
            .orderBy("id", "DESC")
            .execute(Professor.class);

        listProfessores.clear();
        listProfessores.add(
            new Professor() {
                {
                    setId(0);
                    setNome("");
                }
            }
        ); // opção para não selecionar nenhum professor
        listProfessores.addAll(professores);
    }

    // ── render() ───────────────────────────────────────────────────────────────

    @Override
    public Component render() {
        return new Column()
            .padding(20)
            .gap(12)
            .modifier(new Modifier().fillMaxWidth())
            .children(
                new Text("Bem-vindo à Pauta de Alunos").fontSize(22.0),
                new Row()
                    .gap(10)
                    .children(
                        new Dropdown<>(listProfessores.get())
                            .label("Selecione perfil:")
                            .bindTo(professorSelecionado)
                    ),
                new Button("Entrar como Professor").onClick(() ->
                    app.navigateTo(new ProfessorScene(app, professorSelecionado))
                ),
                new Button("Entrar como Aluno").onClick(() ->
                    app.navigateTo(new AlunoScene(app, professorSelecionado))
                )
            );
    }
}
