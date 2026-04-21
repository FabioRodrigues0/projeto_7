import fabiorodrigues.bricks.data.DB;
import java.util.Map;

/**
 * Dados iniciais da base de dados. Chamar DatabaseSeeder.run() no Effect initDB
 * do App.java se necessário. Só deve correr quando a base de dados está vazia.
 */
public class DatabaseSeeder {

    private DatabaseSeeder() {}

    /**
     * Insere dados iniciais nas tabelas. Exemplo de uso no App.java:
     *
     * private final Effect initDB = effect(() -> { DatabaseSchema.create();
     * DatabaseSeeder.run(); });
     */
    public static void run() {
        // Exemplo — descomenta e adapta:
        DB.query()
            .insertInto("professores")
            .values(Map.of("nome", "Dr. Pedro", "genero", "Male", "idade", "55", "grau", "Doutor"))
            .execute();

        DB.query()
            .insertInto("professores")
            .values(Map.of("nome", "Prof. Ana", "genero", "Female", "idade", "66", "grau", "Mestre"))
            .execute();

        DB.query()
            .insertInto("professores")
            .values(Map.of("nome", "Dra. Joaquina", "genero", "Female", "idade", "38", "grau", "Doutor"))
            .execute();

        DB.query()
            .insertInto("professores")
            .values(Map.of("nome", "Prof. Joana", "genero", "Female", "idade", "41", "grau", "Mestre"))
            .execute();

        DB.query()
            .insertInto("professores")
            .values(Map.of("nome", "Prof. Luís", "genero", "Male", "idade", "26", "grau", "Mestre"))
            .execute();

        DB.query()
            .insertInto("disciplinas")
            .values(Map.of("nome", "Matematica", "desricacao", "Disciplina Matematica", "id_professor", "1"))
            .execute();

        DB.query()
            .insertInto("disciplinas")
            .values(Map.of("nome", "Portugues", "desricacao", "Disciplina Português", "id_professor", "2"))
            .execute();
        DB.query()
            .insertInto("disciplinas")
            .values(Map.of("nome", "Inglês", "desricacao", "Disciplina Inglês", "id_professor", "3"))
            .execute();
        DB.query()
            .insertInto("disciplinas")
            .values(Map.of("nome", "Fisíca", "desricacao", "Disciplina Fisíca", "id_professor", "4"))
            .execute();
        DB.query()
            .insertInto("disciplinas")
            .values(Map.of("nome", "Informática", "desricacao", "Disciplina Informática", "id_professor", "5"))
            .execute();
    }
}
