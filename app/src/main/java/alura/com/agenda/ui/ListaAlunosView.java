package alura.com.agenda.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import alura.com.agenda.database.AgendaDatabase;
import alura.com.agenda.database.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.ui.adapter.ListaAlunosAdapter;
import alura.com.agenda.asynctask.BuscaAlunoTask;
import alura.com.agenda.asynctask.RemoveAlunoTask;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(context);
        dao = AgendaDatabase.getInstance(context).getRoomAlunoDAO();
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
        new BuscaAlunoTask(dao, adapter).execute();
    }

    private void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();
    }

    public void configurarAdapter(ListView listView) {

        listView.setAdapter(adapter);
    }

}
