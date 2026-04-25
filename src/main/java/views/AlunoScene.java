package views;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.Spacer;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.BricksScene;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.style.Modifier;
import models.Aluno;
import views.components.AdicionarAlunoPanel;
import views.components.AlunosTable;
import views.components.DisciplinasColumn;
import views.viewmodels.AlunoViewModel;
import views.viewmodels.LobbyViewModel;

public class AlunoScene extends BricksScene {
    private final AlunoViewModel vm;

    public AlunoScene(BricksApplication app, AlunoViewModel vm, Aluno alunoSelecionado) {
        super(app);
        use(vm);
        this.vm = vm;
        this.vm.alunoAtual.set(alunoSelecionado);
        vm.setDisciplinas();
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
                                        new Text("Área do Aluno").fontSize(22.0),
                                        new Button("Editar perfil").onClick(() -> vm.getIsSideColumnOpen().set(0, true)),
                                        new Spacer(),
                                        new Button("Voltar").onClick(() -> app.navigateTo(new LobbyScene(app, new LobbyViewModel())))
                                ),
                        new Row()
                                .gap(8)
                                .children(
                                        new DisciplinasColumn(vm).render(),
                                        new AlunosTable(vm, false).render(),  // sem actions
                                        new AdicionarAlunoPanel(vm, false).render() // sem campo nota
                                )
                );
    }
}
