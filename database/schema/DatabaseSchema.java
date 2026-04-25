import fabiorodrigues.bricks.data.DB;

/**
 * Define o schema da base de dados. Chamado automaticamente no arranque via
 * Effect em App.java. Adiciona aqui as definições das tabelas.
 */
public class DatabaseSchema {

    private DatabaseSchema() { }

    /**
     * Cria as tabelas se não existirem. Seguro para chamar múltiplas vezes — usa
     * CREATE TABLE IF NOT EXISTS.
     */
    public static void create() {
        DB.query()
            .createTableIfNotExists("professores")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nome", "TEXT NOT NULL")
            .column("genero", "TEXT NOT NULL")
            .column("idade", "INTEGER NOT NULL")
            .column("grau", "TEXT NOT NULL")
            .execute();

        DB.query()
            .createTableIfNotExists("disciplinas")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nome", "TEXT NOT NULL")
            .column("descricao", "TEXT NOT NULL")
            .column("id_professor", "INTEGER NOT NULL")
            .execute();

        DB.query()
            .createTableIfNotExists("alunos")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nome", "TEXT NOT NULL")
            .column("genero", "TEXT NOT NULL")
            .column("idade", "INTEGER NOT NULL")
            .column("media", "FLOAT NOT NULL DEFAULT 0")
            .execute();

        DB.query()
            .createTableIfNotExists("notas_disciplina")
            .column("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
            .column("nota", "FLOAT NOT NULL")
            .column("id_aluno", "INTEGER NOT NULL")
            .column("id_disciplina", "INTEGER NOT NULL")
            .execute();
    }
}
