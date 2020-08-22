package alura.com.agenda.asynctask;

import android.os.AsyncTask;

import alura.com.agenda.database.dao.TelefoneDAO;
import alura.com.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO dao;
    private final int alunoId;
    private final PrimeiroTelefoneEncotradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO dao, int alunoId, PrimeiroTelefoneEncotradoListener listener) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoALuno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        listener.quandoEncotrado(telefone);
    }

    public interface PrimeiroTelefoneEncotradoListener{
        void quandoEncotrado(Telefone telefone);
    }
}
