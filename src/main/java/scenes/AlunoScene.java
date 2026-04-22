package scenes;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.BricksScene;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.style.Modifier;
import models.Professor;

public class AlunoScene extends BricksScene {

    protected final State<Professor> professorSelecionado;

    public AlunoScene(BricksApplication app, State<Professor> professorSelecionado) {
        super(app);
        this.professorSelecionado = professorSelecionado;
    }

    @Override
    public Component render() {
        return new Column()
            .padding(20)
            .gap(12)
            .modifier(new Modifier().fillMaxWidth())
            .children(
                new Text("Área do Aluno").fontSize(22.0),
                new Button("Voltar").onClick(() -> app.navigateTo(new LobbyScene(app, professorSelecionado)))
            );
    }
}
