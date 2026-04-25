package views;

import fabiorodrigues.bricks.components.Alert;
import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Dropdown;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.BricksScene;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.style.Modifier;
import views.viewmodels.AlunoViewModel;
import views.viewmodels.LobbyViewModel;
import views.viewmodels.ProfessorViewModel;

public class LobbyScene extends BricksScene {
    private final LobbyViewModel vm;

    public LobbyScene(BricksApplication app, LobbyViewModel vm) {
        super(app);
        use(vm);
        this.vm = vm;
        this.vm.getProfessores();
        this.vm.getAlunos();
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
                        new Dropdown<>(vm.listProfessores.get())
                                .label("Selecione perfil:")
                                .bindTo(vm.professorSelecionado),
                        new Button("Entrar como Professor").onClick(() -> {
                            if (vm.professorSelecionado.get() == null) {
                                Alert.warning("Aviso", "Selecione uma disciplina primeiro");
                                return;
                            }
                            ;
                            app.navigateTo(
                                    new ProfessorScene(app, new ProfessorViewModel(), vm.professorSelecionado.get())
                            );
                        }),
                        new Dropdown<>(vm.listAlunos.get())
                                .label("Selecione perfil:")
                                .bindTo(vm.alunoSelecionado),
                        new Button("Entrar como Aluno").onClick(() ->
                                app.navigateTo(new AlunoScene(app, new AlunoViewModel(), vm.alunoSelecionado.get()))
                        )
                );
    }
}
