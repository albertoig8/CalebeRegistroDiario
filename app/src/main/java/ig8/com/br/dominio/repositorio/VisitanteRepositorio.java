package ig8.com.br.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ig8.com.br.dominio.entidades.Visitante;

public class VisitanteRepositorio {

    private SQLiteDatabase conexao;


    public VisitanteRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }


    public void inserir(Visitante visitante){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", visitante.nome);
        contentValues.put("ENDERECO", visitante.endereco);
        contentValues.put("NUMERO", visitante.numero);
        contentValues.put("TELEFONE", visitante.telefone);
        contentValues.put("DIA1", visitante.dia1);
        contentValues.put("DIA2", visitante.dia2);
        contentValues.put("DIA3", visitante.dia3);
        contentValues.put("DIA4", visitante.dia4);
        contentValues.put("DIA5", visitante.dia5);
        contentValues.put("DIA6", visitante.dia6);
        contentValues.put("DIA7", visitante.dia7);
        contentValues.put("DIA8", visitante.dia8);
        contentValues.put("DIA9", visitante.dia9);
        contentValues.put("DIA10", visitante.dia10);
        contentValues.put("DIA11", visitante.dia11);
        contentValues.put("DIA12", visitante.dia12);
        contentValues.put("DIA13", visitante.dia13);
        contentValues.put("DIA14", visitante.dia14);
        contentValues.put("DIA15", visitante.dia15);
        contentValues.put("DIA16", visitante.dia16);
        contentValues.put("DIA17", visitante.dia17);
        contentValues.put("DIA18", visitante.dia18);
        contentValues.put("DIA19", visitante.dia19);
        contentValues.put("DIA20", visitante.dia20);


        conexao.insertOrThrow("VISITANTE", null, contentValues );

    }


    public void excluir(int codigo){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        conexao.delete("VISITANTE", "CODIGO = ? ", parametros );

    }


    public void alterar(Visitante visitante){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", visitante.nome);
        contentValues.put("ENDERECO", visitante.endereco);
        contentValues.put("NUMERO", visitante.numero);
        contentValues.put("TELEFONE", visitante.telefone);
        contentValues.put("DIA1", visitante.dia1);
        contentValues.put("DIA2", visitante.dia2);
        contentValues.put("DIA3", visitante.dia3);
        contentValues.put("DIA4", visitante.dia4);
        contentValues.put("DIA5", visitante.dia5);
        contentValues.put("DIA6", visitante.dia6);
        contentValues.put("DIA7", visitante.dia7);
        contentValues.put("DIA8", visitante.dia8);
        contentValues.put("DIA9", visitante.dia9);
        contentValues.put("DIA10", visitante.dia10);
        contentValues.put("DIA11", visitante.dia11);
        contentValues.put("DIA12", visitante.dia12);
        contentValues.put("DIA13", visitante.dia13);
        contentValues.put("DIA14", visitante.dia14);
        contentValues.put("DIA15", visitante.dia15);
        contentValues.put("DIA16", visitante.dia16);
        contentValues.put("DIA17", visitante.dia17);
        contentValues.put("DIA18", visitante.dia18);
        contentValues.put("DIA19", visitante.dia19);
        contentValues.put("DIA20", visitante.dia20);


        String[] parametros = new String[1];
        parametros[0] = String.valueOf(visitante.codigo);

        conexao.update("VISITANTE", contentValues, "CODIGO = ? ", parametros );

    }


    public List<Visitante> buscarTodos(){
        List<Visitante> visitantes = new ArrayList<Visitante>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NOME, ENDERECO, NUMERO, TELEFONE, DIA1, DIA2, DIA3, DIA4, DIA5, DIA6, DIA7, DIA8, DIA9, DIA10, DIA11, DIA12, DIA13, DIA14, DIA15, DIA16, DIA17, DIA18, DIA19, DIA20 ");
        sql.append(" FROM VISITANTE");
        sql.append(" ORDER BY NOME");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            do{

                Visitante vis = new Visitante();

                vis.codigo   = resultado.getInt( resultado.getColumnIndexOrThrow("CODIGO") );
                vis.nome     = resultado.getString( resultado.getColumnIndexOrThrow("NOME") );
                vis.endereco = resultado.getString( resultado.getColumnIndexOrThrow("ENDERECO") );
                vis.numero     = resultado.getString( resultado.getColumnIndexOrThrow("NUMERO") );
                vis.telefone = resultado.getString( resultado.getColumnIndexOrThrow("TELEFONE") );
                vis.dia1     = resultado.getInt(resultado.getColumnIndex("DIA1")) > 0;
                vis.dia2     = resultado.getInt(resultado.getColumnIndex("DIA2")) > 0;
                vis.dia3     = resultado.getInt(resultado.getColumnIndex("DIA3")) > 0;
                vis.dia4     = resultado.getInt(resultado.getColumnIndex("DIA4")) > 0;
                vis.dia5     = resultado.getInt(resultado.getColumnIndex("DIA5")) > 0;
                vis.dia6     = resultado.getInt(resultado.getColumnIndex("DIA6")) > 0;
                vis.dia7     = resultado.getInt(resultado.getColumnIndex("DIA7")) > 0;
                vis.dia8     = resultado.getInt(resultado.getColumnIndex("DIA8")) > 0;
                vis.dia9     = resultado.getInt(resultado.getColumnIndex("DIA9")) > 0;
                vis.dia10    = resultado.getInt(resultado.getColumnIndex("DIA10")) > 0;
                vis.dia11    = resultado.getInt(resultado.getColumnIndex("DIA11")) > 0;
                vis.dia12    = resultado.getInt(resultado.getColumnIndex("DIA12")) > 0;
                vis.dia13    = resultado.getInt(resultado.getColumnIndex("DIA13")) > 0;
                vis.dia14    = resultado.getInt(resultado.getColumnIndex("DIA14")) > 0;
                vis.dia15    = resultado.getInt(resultado.getColumnIndex("DIA15")) > 0;
                vis.dia16    = resultado.getInt(resultado.getColumnIndex("DIA16")) > 0;
                vis.dia17    = resultado.getInt(resultado.getColumnIndex("DIA17")) > 0;
                vis.dia18    = resultado.getInt(resultado.getColumnIndex("DIA18")) > 0;
                vis.dia19    = resultado.getInt(resultado.getColumnIndex("DIA19")) > 0;
                vis.dia20    = resultado.getInt(resultado.getColumnIndex("DIA20")) > 0;


                visitantes.add(vis);

            }while(resultado.moveToNext());

        }

        return visitantes;

    }




    public Visitante buscarVisitante(int codigo){

        Visitante visitante = new Visitante();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NOME, ENDERECO, NUMERO, TELEFONE, DIA1, DIA2, DIA3, DIA4, DIA5, DIA6, DIA7, DIA8, DIA9, DIA10, DIA11, DIA12, DIA13, DIA14, DIA15, DIA16, DIA17, DIA18, DIA19, DIA20 ");
        sql.append(" FROM VISITANTE");
        sql.append(" WHERE CODIGO");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            visitante.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO" ));
            visitante.nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME" ));
            visitante.endereco = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO" ));
            visitante.numero = resultado.getString(resultado.getColumnIndexOrThrow("NUMERO" ));
            visitante.telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE" ));
            visitante.dia1     = resultado.getInt(resultado.getColumnIndex("DIA1")) > 0;
            visitante.dia2     = resultado.getInt(resultado.getColumnIndex("DIA2")) > 0;
            visitante.dia3     = resultado.getInt(resultado.getColumnIndex("DIA3")) > 0;
            visitante.dia4     = resultado.getInt(resultado.getColumnIndex("DIA4")) > 0;
            visitante.dia5     = resultado.getInt(resultado.getColumnIndex("DIA5")) > 0;
            visitante.dia6     = resultado.getInt(resultado.getColumnIndex("DIA6")) > 0;
            visitante.dia7     = resultado.getInt(resultado.getColumnIndex("DIA7")) > 0;
            visitante.dia8     = resultado.getInt(resultado.getColumnIndex("DIA8")) > 0;
            visitante.dia9     = resultado.getInt(resultado.getColumnIndex("DIA9")) > 0;
            visitante.dia10    = resultado.getInt(resultado.getColumnIndex("DIA10")) > 0;
            visitante.dia11    = resultado.getInt(resultado.getColumnIndex("DIA11")) > 0;
            visitante.dia12    = resultado.getInt(resultado.getColumnIndex("DIA12")) > 0;
            visitante.dia13    = resultado.getInt(resultado.getColumnIndex("DIA13")) > 0;
            visitante.dia14    = resultado.getInt(resultado.getColumnIndex("DIA14")) > 0;
            visitante.dia15    = resultado.getInt(resultado.getColumnIndex("DIA15")) > 0;
            visitante.dia16    = resultado.getInt(resultado.getColumnIndex("DIA16")) > 0;
            visitante.dia17    = resultado.getInt(resultado.getColumnIndex("DIA17")) > 0;
            visitante.dia18    = resultado.getInt(resultado.getColumnIndex("DIA18")) > 0;
            visitante.dia19    = resultado.getInt(resultado.getColumnIndex("DIA19")) > 0;
            visitante.dia20    = resultado.getInt(resultado.getColumnIndex("DIA20")) > 0;


            return visitante;
        }

        return null;

    }


}
