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

public class AlunosTable {

    private final IAlunosTableViewModel vm;
    private final Boolean comActions;

    public AlunosTable(IAlunosTableViewModel vm, boolean comActions) {
        this.vm = vm;
        this.comActions = comActions;
    }

    private void apagarSelecionadas(DataTable<AlunoNota> tabela) {
        tabela
            .getSelected()
            .forEach(a -> this.vm.apagarNota(a.id(), vm.getDisciplinaSelecionada().get().getId()));
    }

    public Component render() {
        DataTable<AlunoNota> tabela = new DataTable<AlunoNota>()
            .searchable()
            .columnToggle()
            .column("Numero", a -> String.valueOf(a.id()), col -> col.width(100).bold())
            .column("Nome", AlunoNota::nome, col -> col.width(250).wrapText())
            .column(
                "Nota",
                a -> String.valueOf(a.nota()),
                col -> col.width(120).align(Align.CENTER)
            )
            .items(vm.getAlunosDisciplina())
            .selectable()
            .pageSize(15);

        tabela.toolbarAction(
            "Apagar selecionadas",
            () -> apagarSelecionadas(tabela),
            SelectionMode.REQUIRES_SELECTION
        );

        if (this.comActions) {
            tabela
                .actionColumn(
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
                        .onClick(a -> {
                            this.vm.apagarNota(a.id(), vm.getDisciplinaSelecionada().get().getId());
                        })
                );
        }
        return new Column().gap(0).modifier(new Modifier().fillMaxWidth()).children(tabela);
    }
}
