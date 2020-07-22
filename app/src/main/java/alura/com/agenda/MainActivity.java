package alura.com.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> alunos = new ArrayList<>(Arrays.asList("Beto", "Najara", "Cyro", "Zé"));
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos
        ));

    }
}
