package alura.com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import alura.com.agenda.R;
import alura.com.agenda.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;

import static alura.com.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String ALUNO_NOVO = "Novo aluno";
    public static final String ALUNO_EDITA = "Edita aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializarCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_formulario_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_ALUNO)) {
            setTitle(ALUNO_EDITA);
            aluno = (Aluno) intent.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(ALUNO_NOVO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void inicializarCampos() {
        campoNome = findViewById(R.id.edt_aluno_nome);
        campoTelefone = findViewById(R.id.edt_aluno_telefone);
        campoEmail = findViewById(R.id.edt_aluno_email);
    }


    private void finalizaFormulario() {
        preencherAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salvar(aluno);
        }
        finish();
    }

    private void preencherAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}