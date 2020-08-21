package alura.com.agenda.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import alura.com.agenda.database.dao.RoomAlunoDAO;
import alura.com.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
    public abstract RoomAlunoDAO getRoomAlunoDAO();
}
