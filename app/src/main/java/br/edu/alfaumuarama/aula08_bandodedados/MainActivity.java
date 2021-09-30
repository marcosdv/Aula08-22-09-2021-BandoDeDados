package br.edu.alfaumuarama.aula08_bandodedados;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import br.edu.alfaumuarama.aula08_bandodedados.db.Aluno;
import br.edu.alfaumuarama.aula08_bandodedados.db.TbAluno;

public class MainActivity extends ListActivity {

    ArrayList<Aluno> listaAlunos;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                                                    CadAlunoActivity.class));
            }
        });

        buscarDados();
    }

    private void buscarDados() {
        //criando a instancia da classe TbAluno, e criando no banco caso nao exista
        TbAluno tbAluno = new TbAluno(MainActivity.this);

        //buscando os alunos cadastrados no banco de dados
        listaAlunos = tbAluno.buscar(MainActivity.this, "", 0, "");

        //criando a fonte de dados para a listagem
        ListAdapter adapter = new SimpleAdapter(
            MainActivity.this, //classe que controla a lista
            getListaAlunos(), //lista com os dados em formato ArrayList<HashMap>
            R.layout.aluno_listview, //modelo da linha da listagem
            new String[] { "nome", "ra", "cidade" }, //campos que vem da lista de dados
            new int[] { R.id.txtNome, R.id.txtRA, R.id.txtCidade } //campos visuais do modelo
        );

        //adicionando a fonte de dados no objeto de listagem da tela
        setListAdapter(adapter);
    }

    //metodo para converter a listaAluno para o formato array de hashmap
    private ArrayList<HashMap<String,String>> getListaAlunos() {
        ArrayList<HashMap<String,String>> listaRetorno = new ArrayList<>();

        for (int i = 0; i < listaAlunos.size(); i++)
            listaRetorno.add(listaAlunos.get(i).toHashMap());

        return listaRetorno;
    }
}