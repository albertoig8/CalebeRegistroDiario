package ig8.com.br.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ig8.com.br.dominio.entidades.Visitante;

public class   ScriptDLL {

    public static String getCriateTableVisitante(){

        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS VISITANTE ( ");
        sql.append("       CODIGO   INTEGER       PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("       NOME     VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("       ENDERECO VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("       NUMERO   VARCHAR (10) NOT NULL DEFAULT (''), ");
        sql.append("       TELEFONE VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("       DIA1     INTEGER       NOT NULL,");
        sql.append("       DIA2     INTEGER       NOT NULL,");
        sql.append("       DIA3     INTEGER       NOT NULL,");
        sql.append("       DIA4     INTEGER       NOT NULL,");
        sql.append("       DIA5     INTEGER       NOT NULL,");
        sql.append("       DIA6     INTEGER       NOT NULL,");
        sql.append("       DIA7     INTEGER       NOT NULL,");
        sql.append("       DIA8     INTEGER       NOT NULL,");
        sql.append("       DIA9     INTEGER       NOT NULL,");
        sql.append("       DIA10    INTEGER       NOT NULL,");
        sql.append("       DIA11    INTEGER       NOT NULL,");
        sql.append("       DIA12    INTEGER       NOT NULL,");
        sql.append("       DIA13    INTEGER       NOT NULL,");
        sql.append("       DIA14    INTEGER       NOT NULL,");
        sql.append("       DIA15    INTEGER       NOT NULL,");
        sql.append("       DIA16    INTEGER       NOT NULL,");
        sql.append("       DIA17    INTEGER       NOT NULL,");
        sql.append("       DIA18    INTEGER       NOT NULL,");
        sql.append("       DIA19    INTEGER       NOT NULL,");
        sql.append("       DIA20    INTEGER       NOT NULL )");
        return sql.toString();

    }

}
