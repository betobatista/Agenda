package alura.com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
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

    // Teste GIT
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_lista_alunos_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = adapter.getItem(menuInfo.position);
            remove(aluno);
        }
        return super.onContextItemSelected(item);

    }

    private void configuraLista() {
        ListView listView = findViewById(R.id.listView);
        configurarAdapter(listView);
        configurarItemSeleciona(listView);
        registerForContextMenu(listView);
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
