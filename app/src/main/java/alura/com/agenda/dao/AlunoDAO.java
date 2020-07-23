package alura.com.agenda.dao;

import java.util.ArrayList;
import java.util.List;
import alura.com.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Aluno aluno){
        Aluno encontrado = buscaAluno(aluno);
        if(encontrado != null){
            int posicao = alunos.indexOf(encontrado);
            alunos.set(posicao, aluno);
        }
    }

    public void remove(Aluno aluno){
        Aluno encontrado = buscaAluno(aluno);
        if(encontrado != null){
            alunos.remove(aluno);
        }
    }

    private Aluno buscaAluno(Aluno aluno) {
        for(Aluno a: alunos) {
            if(a.getId()==aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
