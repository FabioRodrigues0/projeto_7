package views.components;

import fabiorodrigues.bricks.components.Alert;
import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Card;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Dropdown;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.Spacer;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.components.TextField;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.style.Modifier;

import java.util.List;

import models.enums.Gender;
import views.viewmodels.IAlunosTableViewModel;
import views.viewmodels.ProfessorViewModel;

public class AdicionarAlunoPanel {

    private final IAlunosTableViewModel vm;
    protected boolean comNota;

    public AdicionarAlunoPanel(IAlunosTableViewModel vm, boolean comNota) {
        this.vm = vm;
        this.comNota = comNota;
    }

    public Component render() {
        return new Column()
                .gap(0)
                .modifier(new Modifier().visible(vm.getIsSideColumnOpen().get(0)))
                .children(
                        new Card()
                                .padding(16)
                                .elevation(2)
                                .children(
                                        new Row().children(
                                                new Text((vm.getIsEditOrAdd().get() ? "Adicionar" : "Editar") + " Aluno").fontSize(15),
                                                new Spacer(),
                                                new Button("✕").onClick(() -> vm.getIsSideColumnOpen().set(0, false))
                                        ),
                                        new TextField()
                                                .label("Nome:")
                                                .placeholder("Nome do aluno")
                                                .bindTo(vm.getNomeAluno()),
                                        new TextField()
                                                .label("Idade:")
                                                .placeholder("Idade do aluno")
                                                .bindTo(vm.getIdadeAluno()),
                                        new Dropdown<>(List.of(Gender.MALE, Gender.FEMALE))
                                                .label("Tipo de nota:")
                                                .bindTo(vm.getGeneroAluno()),
                                        comNota ? new Button("Guardar").onClick(() -> {
                                                        if (vm.getNomeAluno().get().isEmpty() || vm.getNotaAluno().get().isEmpty()) {
                                                            Alert.warning("Aviso", "Preencha todos os campos");
                                                            return;
                                                        }
                                                            vm.guardarAluno();
                                                            vm.getIsSideColumnOpen().set(0, false);
                                                            vm.getNomeAluno().set("");
                                                            vm.getNotaAluno().set("");
                                                            vm.getIdadeAluno().set("");
                                                            vm.getGeneroAluno().set(Gender.NONE);
                                                    })
                                                : new TextField()
                                                .label("Nota:")
                                                .placeholder("Nota do aluno")
                                                .bindTo(vm.getNotaAluno()),
                                                vm.getIsEditOrAdd().get()
                                                        ? new Button("Adicionar").onClick(() -> {
                                                    if (vm.getNomeAluno().get().isEmpty() || vm.getNotaAluno().get().isEmpty()) {
                                                        Alert.warning("Aviso", "Preencha todos os campos");
                                                        return;
                                                    }
                                                    vm.adicionarAluno();
                                                    vm.getIsSideColumnOpen().set(0, false);
                                                    vm.getNomeAluno().set("");
                                                    vm.getNotaAluno().set("");
                                                    vm.getIdadeAluno().set("");
                                                    vm.getGeneroAluno().set(Gender.NONE);
                                                })
                                                        : new Button("Guardar").onClick(() -> {
                                                    if (vm.getNomeAluno().get().isEmpty() || vm.getNotaAluno().get().isEmpty()) {
                                                        Alert.warning("Aviso", "Preencha todos os campos");
                                                        return;
                                                    }
                                                    vm.guardarAluno();
                                                    vm.getIsSideColumnOpen().set(0, false);
                                                    vm.getNomeAluno().set("");
                                                    vm.getNotaAluno().set("");
                                                    vm.getIdadeAluno().set("");
                                                    vm.getGeneroAluno().set(Gender.NONE);
                                                })
                                )
                );
    }
}
