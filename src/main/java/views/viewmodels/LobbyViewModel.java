package views.models;

import java.util.List;

import fabiorodrigues.bricks.core.BricksViewModel;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.core.StateList;
import fabiorodrigues.bricks.data.DB;
import models.Professor;

public class LobbyViewModel extends BricksViewModel {
    public final StateList<Professor> listProfessores = stateList(List.of());
    public final State<Professor> professorSelecionado = state(null);

    public void getProfessores() {
        List<Professor> professores = DB.query()
                .select("id", "nome", "genero", "idade", "grau")
                .from("professores")
                .orderBy("id", "DESC")
                .execute(Professor.class);

        listProfessores.clear();
        listProfessores.add(
                new Professor() {
                    {
                        setId(0);
                        setNome("");
                    }
                }
        ); // opção para não selecionar nenhum professor
        listProfessores.addAll(professores);
    }
}
