package views.components;

import fabiorodrigues.bricks.components.Align;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.DataTable;
import fabiorodrigues.bricks.components.SelectionMode;
import fabiorodrigues.bricks.components.TableAction;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.style.Modifier;
import models.dtos.AlunoNota;
import views.viewmodels.IAlunosTableViewModel;
import views.viewmodels.ProfessorViewModel;

public class AlunosTable {

    private final IAlunosTableViewModel vm;
    private final DataTable<AlunoNota> tabela;

    public AlunosTable(IAlunosTableViewModel vm, Boolean comActions) {
        this.vm = vm;
        this.tabela = new DataTable<AlunoNota>()
                .searchable()
                .columnToggle()
                .toolbarAction(
                        "Apagar selecionadas",
                        this::apagarSelecionadas,
                        SelectionMode.REQUIRES_SELECTION
                )
                .column("Numero",
                        a -> String.valueOf(a.id()),
                        col -> col.width(100).bold())
                .column("Nome",
                        AlunoNota::nome,
                        col -> col.width(250).wrapText())
                .column("Nota",
                        a -> String.valueOf(a.nota()),
                        col -> col.width(120).align(Align.CENTER))
                .items(vm.getAlunosDisciplina())
                .selectable()
                .pageSize(15);

        if (comActions) {
            tabela.actionColumn(
                    new TableAction<AlunoNota>()
                            .icon("fas-tags")
                            .tooltip("Editar Nota")
                            .onClick(a -> {
                                this.vm.getIdAluno().set(a.id());
                                this.vm.getNomeAluno().set(a.nome());
                                this.vm.getGeneroAluno().set(a.genero());
                                this.vm.getIdadeAluno().set(String.valueOf(a.idade()));
                                this.vm.getNotaAluno().set(String.valueOf(a.nota()));
                                this.vm.getIsEditOrAdd().set(false);
                                this.vm.getIsSideColumnOpen().set(0, true);
                            })
            )
                    .actionColumn(
                            new TableAction<AlunoNota>()
                                    .icon("fas-trash")
                                    .tooltip("Eliminar Aluno")
                                    .danger()
                                    .onClick(a -> this.vm.apagarNota(a.id(), vm.getDisciplinaSelecionada().get().getId()))
                    );
        }
    }

    private void apagarSelecionadas() {
        tabela.getSelected().forEach(a -> this.vm.apagarNota(a.id(), vm.getDisciplinaSelecionada().get().getId()));
    }

    public Component render() {
        return new Column()
            .gap(0)
            .modifier(new Modifier().fillMaxWidth())
            .children(tabela);
    }
}
