package br.edu.alfaumuarama.aula08_bandodedados.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoDeDados {
    private SQLiteDatabase meuBanco;

    //criando um Singleton, para garantir que esta classe seja instanciada
    //  somente uma vez
    private static final BancoDeDados bancoDeDados = new BancoDeDados();

    public static BancoDeDados getInstance() {
        return bancoDeDados;
    }

    public SQLiteDatabase getMeuBanco() {
        return meuBanco;
    }

    private void criarBanco(Context context) {
        //cria o banco de dados, caso nao exista, e abre a conexao
        meuBanco = context.openOrCreateDatabase("meuBanco.db",
                Context.MODE_PRIVATE, null);
    }

    public void abrirBanco(Context context) {
        if (meuBanco == null) {
            //se o meuBanco nao estiver instanciado, chama o criarBanco
            criarBanco(context);
        }
        else {
            if (!meuBanco.isOpen()) { //if (meuBanco.isOpen() == false)
                //se a conexao estiver fechada, chama o criarBanco
                criarBanco(context);
            }
        }
    }

    public void fecharBanco() {
        //verifico se o banco esta instanciado na memoria
        if (meuBanco != null) {
            //verifica se o banco esta com a conexao aberta
            if (meuBanco.isOpen()) {
                //fecho a conexao do banco de dados
                meuBanco.close();
            }
        }
    }

    public void executarSQL(Context context, String sSQL) {
        try {
            //abrindo a conexao com o banco antes de executar o comando SQL
            abrirBanco(context);

            //executar o comando SQL passado por parametro
            meuBanco.execSQL(sSQL);

            //fechando a conexao com o banco depois de executar o comando SQL
            fecharBanco();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
