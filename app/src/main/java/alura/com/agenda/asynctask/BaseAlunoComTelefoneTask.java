package alura.com.agenda.asynctask;

import android.os.AsyncTask;

import alura.com.agenda.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    protected void vinculaAlunoTelefone(int id, Telefone... telefones) {
        for (Telefone telefone :
                telefones) {
            telefone.setAlunoId(id);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizda();
    }

    public interface FinalizadaListener {
        void quandoFinalizda();
    }
}
