package alura.com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import alura.com.agenda.R;
import alura.com.agenda.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;

import static alura.com.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);
        setTitle(TITULO_APPBAR);
        configuraLista();
        configurarBotao();
        dao.salvar(new Aluno("Beto", "19997622235", "betobatista09@gmail.com"));
        dao.salvar(new Aluno("Najara", "19997622235", "najaranfblemos@gmail.com"));
    }

    private void configurarBotao() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInserir();
            }
        });
    }

    private void abreFormularioModoInserir() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    private void atualizaLista() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listView = findViewById(R.id.listView);
        configurarAdapter(listView);
        configurarItemSeleciona(listView);
        configurarItemLongoSeleciona(listView);
    }

    private void configurarItemLongoSeleciona(ListView listView) {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);
                remove(aluno);
                return true;
            }
        });
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configurarItemSeleciona(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);
                abreFormularioModoEditar(aluno);
            }
        });
    }

    private void abreFormularioModoEditar(Aluno aluno) {
        Intent intent = new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_ALUNO, aluno);
        startActivity(intent);
    }

    private void configurarAdapter(ListView listView) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        listView.setAdapter(adapter);
    }
}
