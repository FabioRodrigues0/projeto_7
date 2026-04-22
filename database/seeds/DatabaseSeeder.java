import fabiorodrigues.bricks.data.DB;

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
        int totalProfessores = (int) DB.query()
            .select("COUNT(*) as total")
            .from("professores")
            .executeRaw()
            .first()
            .getOrDefault("total", 0);

        int totalDisciplinas = (int) DB.query()
            .select("COUNT(*) as total")
            .from("disciplinas")
            .executeRaw()
            .first()
            .getOrDefault("total", 0);

        if (totalProfessores == 0) {
            DB.query()
                .insertInto("professores")
                .value("nome", "Prof. João")
                .value("genero", "Male")
                .value("idade", 55)
                .value("grau", "Doutor")
                .execute();

            DB.query()
                .insertInto("professores")
                .value("nome", "Prof. Ana")
                .value("genero", "Female")
                .value("idade", 66)
                .value("grau", "Mestre")
                .execute();

            DB.query()
                .insertInto("professores")
                .value("nome", "Dra. Joaquina")
                .value("genero", "Female")
                .value("idade", 38)
                .value("grau", "Doutor")
                .execute();

            DB.query()
                .insertInto("professores")
                .value("nome", "Prof. Joana")
                .value("genero", "Female")
                .value("idade", 41)
                .value("grau", "Mestre")
                .execute();

            DB.query()
                .insertInto("professores")
                .value("nome", "Prof. Luís")
                .value("genero", "Male")
                .value("idade", 26)
                .value("grau", "Mestre")
                .execute();
        }

        if (totalDisciplinas == 0) {
            DB.query()
                .insertInto("disciplinas")
                .value("nome", "Matematica")
                .value("descricao", "Disciplina Matematica")
                .value("id_professor", 1)
                .execute();

            DB.query()
                .insertInto("disciplinas")
                .value("nome", "Portugues")
                .value("descricao", "Disciplina Português")
                .value("id_professor", 2)
                .execute();

            DB.query()
                .insertInto("disciplinas")
                .value("nome", "Inglês")
                .value("descricao", "Disciplina Inglês")
                .value("id_professor", 3)
                .execute();

            DB.query()
                .insertInto("disciplinas")
                .value("nome", "Fisíca")
                .value("descricao", "Disciplina Fisíca")
                .value("id_professor", 4)
                .execute();

            DB.query()
                .insertInto("disciplinas")
                .value("nome", "Informática")
                .value("descricao", "Disciplina Informática")
                .value("id_professor", 5)
                .execute();
        }
    }
}
