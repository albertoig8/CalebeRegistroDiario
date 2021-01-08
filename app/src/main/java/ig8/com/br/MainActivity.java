package ig8.com.br;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

import ig8.com.br.database.DadosOpenHelper;
import ig8.com.br.dominio.entidades.Visitante;
import ig8.com.br.dominio.repositorio.VisitanteRepositorio;


public class MainActivity extends AppCompatActivity {

    private RecyclerView listDados;
    private FloatingActionButton fab;
    private ConstraintLayout layoutContentMain;

    private SQLiteDatabase conexao;

    private DadosOpenHelper dadosOpenHelper;

    private VisitanteRepositorio visitanteRepositorio;

    private VisitanteAdapter visitanteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        listDados = (RecyclerView)findViewById(R.id.listDados);

        layoutContentMain = (ConstraintLayout)findViewById(R.id.layoutContentMain);

        criarConexao();

        listDados.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listDados.setLayoutManager(linearLayoutManager);

        visitanteRepositorio = new VisitanteRepositorio(conexao);

        List<Visitante> dados = visitanteRepositorio.buscarTodos();

        visitanteAdapter = new VisitanteAdapter(dados);

        listDados.setAdapter(visitanteAdapter);

    }


    private void criarConexao() {

        try{
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();

        }catch(SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_certo, null);
            dlg.show();

        }
    }


    public void cadastrar(View view) {
        Intent it = new Intent(MainActivity.this, ActivityCadastroVisitante.class);
        startActivityForResult(it, 0);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 0) {
            List<Visitante> dados = visitanteRepositorio.buscarTodos();
            visitanteAdapter = new VisitanteAdapter(dados);
            listDados.setAdapter(visitanteAdapter);
        }

    }
}