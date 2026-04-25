import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.Effect;
import views.LobbyScene;
import views.viewmodels.LobbyViewModel;

/**
 * Ponto de entrada da aplicação Bricks. UI declarativa com estado reativo e
 * base de dados SQLite integrada.
 */
public class App extends BricksApplication {

    // ── Effects ───────────────────────────────────────────────────────────────

    private final Effect initDB = effect(() -> {
        DatabaseSchema.create();
        DatabaseSeeder.run();
    });

    // ── Estado persistente ────────────────────────────────────────────────────

    public final LobbyViewModel lobbyVM = new LobbyViewModel();

    {
        setTitle("Pauta de Alunos v2");
        setInitialScene(new LobbyScene(this, lobbyVM));
        setSize(1248, 768);
        // setTheme(BricksTheme.dark()); // descomenta para dark mode
    }

    @Override
    public Component root() {
        return currentScene() != null ? currentScene().render() : new Text("A carregar...");
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
