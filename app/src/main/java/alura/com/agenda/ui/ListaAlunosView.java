package alura.com.agenda.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import alura.com.agenda.dao.AlunoDAO;
import alura.com.agenda.database.AgendaDatabase;
import alura.com.agenda.database.dao.RoomAlunoDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final RoomAlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(context);
        dao = Room
                .databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build()
                .getRoomAlunoDAO();
    }

    public void confirmarRemover(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Você tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno aluno = adapter.getItem(menuInfo.position);
                        remove(aluno);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaLista() {
        adapter.update(dao.todos());
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configurarAdapter(ListView listView) {

        listView.setAdapter(adapter);
    }

}
