package alura.com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import alura.com.agenda.R;
import alura.com.agenda.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String NOVO_ALUNO = "Novo aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(NOVO_ALUNO);

        inicializarCampos();
        configurarBotao(campoNome, campoTelefone, campoEmail);
    }

    private void inicializarCampos() {
        campoNome = findViewById(R.id.edt_aluno_nome);
        campoTelefone = findViewById(R.id.edt_aluno_telefone);
        campoEmail = findViewById(R.id.edt_aluno_email);
    }

    private void configurarBotao(final EditText campoNome, final EditText campoTelefone, final EditText campoEmail) {
        Button btnSalvar = findViewById(R.id.btn_aluno_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = criarAluno();
                salvarAluno(aluno);
            }
        });
    }

    private Aluno criarAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }

    private void salvarAluno(Aluno aluno) {
        dao.salvar(aluno);
        finish();
    }
}