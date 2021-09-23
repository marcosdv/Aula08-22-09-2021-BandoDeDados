package br.edu.alfaumuarama.aula08_bandodedados.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class TbAluno {

    //construtor da classe TbAluno
    public TbAluno(Context context) {
        //sql responsavel pela criacao da tabela, caso a mesma ainda nao exista
        String sSQL = "CREATE TABLE IF NOT EXISTS TbAluno (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome TEXT, ra INTEGER, cidade TEXT)";

        BancoDeDados.getInstance().executarSQL(context, sSQL);
    }

    private String addAspas(String texto) {
        return "'" + texto + "'";
    }

    private void inserir(Context context, Aluno aluno) {
        String sSQL =
                "INSERT INTO TbAluno (nome, ra, cidade) VALUES " +
                "(" +
                    addAspas(aluno.nome) + ", " +
                    aluno.RA + ", " +
                    addAspas(aluno.cidade) +
                ")";

        BancoDeDados.getInstance().executarSQL(context, sSQL);
    }

    private void alterar(Context context, Aluno aluno) {
        String sSQL =
                "UPDATE TbAluno SET " +
                "  nome = " + addAspas(aluno.nome) + ", " +
                "  ra = " + aluno.RA +
                "  cidade = " + addAspas(aluno.cidade) + " " +
                "WHERE ra = " + aluno.RA;

        BancoDeDados.getInstance().executarSQL(context, sSQL);
    }

    public void salvar(Context context, Aluno aluno) {

    }

    public void excluir(Context context, Aluno aluno) {
        String sSQL = "DELETE FROM TbAluno WHERE ra = " + aluno.RA;

        BancoDeDados.getInstance().executarSQL(context, sSQL);
    }

    public ArrayList<Aluno> buscar(String nome, int ra, String cidade) {
        BancoDeDados.getInstance().getMeuBanco()
                .rawQuery("SELECT * FROM TbAluno WHERE ra = " + ra, null);

        BancoDeDados.getInstance().getMeuBanco()
                .rawQuery("SELECT * FROM TbAluno WHERE ra = ?",
                        new String[] { String.valueOf(ra) });

        return null;
    }
}
