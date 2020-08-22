package alura.com.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alura.com.agenda.R;
import alura.com.agenda.database.AgendaDatabase;
import alura.com.agenda.database.dao.TelefoneDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO dao;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View newView = createView(viewGroup);
        Aluno aluno = alunos.get(position);
        vincula(newView, aluno);
        return newView;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNome());

        TextView tel = view.findViewById(R.id.item_aluno_tel);
        new BuscaPrimeiroTelefoneDoAlunoTask(dao, aluno.getId(), telefone ->{
            tel.setText(telefone.getNumero());
        }).execute();
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater.
                from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void update(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
