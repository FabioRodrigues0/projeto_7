package views.components;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.LazyColumn;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.style.Modifier;
import models.Disciplina;
import views.viewmodels.IAlunosTableViewModel;

public class DisciplinasColumn {

    private final IAlunosTableViewModel vm;

    public DisciplinasColumn(IAlunosTableViewModel vm) {
        this.vm = vm;
    }

    public Component render() {
        return new LazyColumn<Disciplina>()
                .gap(2)
                .modifier(new Modifier().width(150).fillMaxHeight())
                .items(vm.getDisciplinas().get())
                .emptyState(new Text("Nenhuma disciplina encontrada"))
                .item(d -> new Button(d.getNome()).onClick(() -> vm.selecionarDisciplina(d)));
    }
}
