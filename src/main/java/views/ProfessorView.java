package views;

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
import views.components.AdicionarAlunoPanel;
import views.components.AlunosTable;
import views.components.DisciplinasColumn;
import views.viewmodels.LobbyViewModel;
import views.viewmodels.ProfessorViewModel;

public class ProfessorView extends BricksScene {

    private final ProfessorViewModel vm;

    public ProfessorView(
        BricksApplication app,
        ProfessorViewModel vm,
        Professor professorSelecionado
    ) {
        super(app);
        use(vm);
        this.vm = vm;
        this.vm.professorSelecionado.set(professorSelecionado);
        vm.setDisciplinas();
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
                            vm.getIsSideColumnOpen().set(0, true);
                        }),
                        new Spacer(),
                        new Button("Voltar").onClick(() ->
                            app.navigateTo(new LobbyView(app, new LobbyViewModel()))
                        )
                    ),
                new Row()
                    .gap(8)
                    .children(
                        new DisciplinasColumn(vm).render(),
                        new AlunosTable(vm, true).render(),
                        new AdicionarAlunoPanel(vm, true).render()
                    )
            );
    }
}
