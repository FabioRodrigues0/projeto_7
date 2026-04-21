import fabiorodrigues.bricks.components.*;
import fabiorodrigues.bricks.core.*;
import fabiorodrigues.bricks.style.Modifier;
import java.util.Arrays;
import models.Disciplina;

/**
 * Ponto de entrada da aplicação Bricks. UI declarativa com estado reativo e
 * base de dados SQLite integrada.
 */
public class App extends BricksApplication {

    // ── Estado ────────────────────────────────────────────────────────────────

    private final State<String> titulo = state("Adicionar Aluno");
    private final State<String> nomeAluno = state("");
    private final State<Disciplina> disciplinaAluno = state(Disciplina.DEFAULT);
    private final State<String> classificacaoAluno = state("");

    {
        setTitle("Pauta de Alunos v2");
        // setTheme(BricksTheme.dark()); // descomenta para dark mode
    }

    // ── Effects ───────────────────────────────────────────────────────────────

    // Cria o schema da base de dados no arranque
    private final Effect initDB = effect(() -> {
        DatabaseSchema.create();
        DatabaseSeeder.run();
    });

    // ── root() ────────────────────────────────────────────────────────────────
    //
    public void guardarAluno() {}

    @Override
    public Component root() {
        return new Column()
            .padding(20)
            .gap(12)
            .modifier(new Modifier().fillMaxWidth())
            .children(
                new Row()
                    .gap(10)
                    .children(
                        new Icon("fas-users").size(24),
                        new Text(titulo.get()).modifier(new Modifier().fontSize(24).bold())
                    ),
                new TextField().label("Nome").bindTo(nomeAluno),
                new Dropdown<>(Arrays.asList(Disciplina.values()))
                    .label("Disciplina")
                    .bindTo(disciplinaAluno),
                new TextField().label("Classificação").bindTo(classificacaoAluno),
                new Button("Guardar").onClick(() -> guardarAluno())
            );
    }

    /**
     * Ponto de entrada da aplicação.
     *
     * @param args
     *            argumentos da linha de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}
