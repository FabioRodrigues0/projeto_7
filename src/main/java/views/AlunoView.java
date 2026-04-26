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
import views.viewmodels.AlunoViewModel;
import views.viewmodels.LobbyViewModel;

public class AlunoView extends BricksScene {

    private final AlunoViewModel vm;

    public AlunoView(BricksApplication app, AlunoViewModel vm, Aluno alunoSelecionado) {
        super(app);
        use(vm);
        this.vm = vm;
        this.vm.alunoAtual.set(alunoSelecionado);
        vm.carregarNotas(alunoSelecionado.getId());
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
                        new Button("Editar perfil").onClick(() -> {
                            this.vm.getNomeAluno().set(vm.alunoAtual.get().getNome());
                            this.vm.getGeneroAluno().set(vm.alunoAtual.get().getGenero());
                            this.vm.getIdadeAluno().set(
                                String.valueOf(vm.alunoAtual.get().getIdade())
                            );
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
                        new AlunosTable(vm, false).render(),
                        new AdicionarAlunoPanel(vm, false).render()
                    )
            );
    }
}
