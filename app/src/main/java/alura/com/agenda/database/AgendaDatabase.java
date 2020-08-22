package alura.com.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import alura.com.agenda.database.converter.ConversorCalendar;
import alura.com.agenda.database.converter.ConversorTipoTelefone;
import alura.com.agenda.database.dao.AlunoDAO;
import alura.com.agenda.database.dao.TelefoneDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.model.Telefone;

import static alura.com.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract TelefoneDAO getTelefoneDAO();

    public abstract AlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
