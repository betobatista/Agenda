package alura.com.agenda;

import android.app.Application;

import alura.com.agenda.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO dao = new AlunoDAO();
        dao.salvar(new Aluno("Beto", "19997622235", "betobatista09@gmail.com"));
        dao.salvar(new Aluno("Najara", "19997622235", "najaranfblemos@gmail.com"));
    }
}
