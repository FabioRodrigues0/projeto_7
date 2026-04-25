package scenes;

import fabiorodrigues.bricks.components.Alert;
import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.Spacer;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.BricksScene;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.style.Modifier;
import models.Professor;

public class ProfessorScene extends BricksScene {

    private final ProfessorViewModel vm;

    public ProfessorScene(
            BricksApplication app,
            ProfessorViewModel vm,
            Professor professorSelecionado) {
        super(app);
        use(vm);
        this.vm = vm;
        this.vm.professorSelecionado.set(professorSelecionado);
        vm.getDisciplinas();
    }

    // ── render() ───────────────────────────────────────────────────────────────

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
                        new Button("Adicionar Aluno").onClick(() -> {
                            if (vm.disciplinaSelecionada.get() == null) {
                                Alert.warning("Aviso", "Selecione uma disciplina primeiro");
                                return;
                            }
                            vm.isEditOrAdd.set(true);
                            vm.openSideColumn();
                        }),
                        new Spacer(),
                        new Button("Voltar").onClick(() ->
                            app.navigateTo(new LobbyScene(app, new LobbyViewModel()))
                        )
                    ),
                new Row()
                    .gap(8)
                    .children(
                        new DisciplinasColumn(vm).render(),
                        new AlunosTable(vm).render(),
                        new AdicionarAlunoPanel(vm).render()
                    )
            );
    }
}
