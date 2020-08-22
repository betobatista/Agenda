package alura.com.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import alura.com.agenda.model.TipoTelefone;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Criar nova tabela //
            database.execSQL("CREATE TABLE IF NOT EXISTS Aluno_novo (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT, telefone TEXT, email TEXT)");
            // Copias os dados para tabela nova //
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email) SELECT id, nome, telefone, email FROM Aluno");
            // Remove a tabela antiga //
            database.execSQL("DROP TABLE Aluno");
            // Renomeia a tabela nova //
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadatro INTEGER");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefoneFixo` TEXT, `email` TEXT, `momentoDeCadatro` INTEGER, `telefoneCelular` TEXT)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email, momentoDeCadatro) SELECT id, nome, telefone, email, momentoDeCadatro FROM Aluno");

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `email` TEXT, `momentoDeCadatro` INTEGER)");
            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, momentoDeCadatro) SELECT id, nome, email, momentoDeCadatro FROM Aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`alunoId` INTEGER NOT NULL)");
            database.execSQL("INSERT INTO Telefone (numero, alunoId) SELECT telefoneFixo, id FROM Aluno");
            database.execSQL("UPDATE Telefone SET tipo = ?", new TipoTelefone[]{TipoTelefone.FIXO});

            database.execSQL("DROP TABLE Aluno");
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };


    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3,
            MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6};
}