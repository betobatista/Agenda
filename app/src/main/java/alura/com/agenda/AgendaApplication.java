package alura.com.agenda;

import android.app.Application;

import androidx.room.Room;

import alura.com.agenda.dao.AlunoDAO;
import alura.com.agenda.database.AgendaDatabase;
import alura.com.agenda.database.dao.RoomAlunoDAO;
import alura.com.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AgendaDatabase database = Room.databaseBuilder(this, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build();

        RoomAlunoDAO dao = database.getRoomAlunoDAO();
        dao.salvar(new Aluno("Beto", "19997622235", "betobatista09@gmail.com"));
        dao.salvar(new Aluno("Najara", "19997622235", "najaranfblemos@gmail.com"));
    }
}
