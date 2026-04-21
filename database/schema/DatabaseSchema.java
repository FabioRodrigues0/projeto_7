import fabiorodrigues.bricks.data.DB;

/**
 * Define o schema da base de dados. Chamado automaticamente no arranque via
 * Effect em App.java. Adiciona aqui as definições das tabelas.
 */
public class DatabaseSchema {

    private DatabaseSchema() {}

    /**
     * Cria as tabelas se não existirem. Seguro para chamar múltiplas vezes — usa
     * CREATE TABLE IF NOT EXISTS.
     */
    public static void create() {
        // Exemplo — descomenta e adapta:
        DB.query()
            .createTableIfNotExists("professores")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nome", "TEXT NOT NULL")
            .column("genero", "TEXT NOT NULL")
            .column("idade", "INTEGER NOT NULL")
            .column("grau", "TEXT NOT NULL")
            .execute();

        DB.query()
            .createTableIfNotExists("nota_disciplina")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nota", "float NOT NULL")
            .column("nome_disciplina", "TEXT NOT NULL")
            .execute();

        DB.query()
            .createTableIfNotExists("alunos")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nome", "TEXT NOT NULL")
            .column("genero", "TEXT NOT NULL")
            .column("idade", "INTEGER NOT NULL")
            .column("media", "FLOAT NOT NULL")
            .column("id_nota_disciplina", "INTEGER")
            .execute();

        DB.query()
            .createTableIfNotExists("disciplinas")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nome", "TEXT NOT NULL")
            .column("desricacao", "TEXT NOT NULL")
            .column("id_professor", "INTEGER")
            .execute();
    }
}
