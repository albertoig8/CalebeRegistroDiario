package ig8.com.br;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.LruCache;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ig8.com.br.database.DadosOpenHelper;
import ig8.com.br.dominio.entidades.Visitante;
import ig8.com.br.dominio.repositorio.VisitanteRepositorio;


public class MainActivity extends AppCompatActivity {

    private RecyclerView listDados;

    private SQLiteDatabase conexao;

    private VisitanteRepositorio visitanteRepositorio;

    private VisitanteAdapter visitanteAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        listDados = (RecyclerView) findViewById(R.id.listDados);

        ConstraintLayout layoutContentMain = (ConstraintLayout) findViewById(R.id.layoutContentMain);

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

        try {
            DadosOpenHelper dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();

        } catch (SQLException ex) {

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

    private Bitmap fotoDoRecyclerView(RecyclerView view){
        RecyclerView.Adapter adapter = view.getAdapter();

        Bitmap bitmapPronto = null;

        if (adapter != null){

            Paint paint = new Paint();
            int tamanhoDaLista = adapter.getItemCount();
            int altura = 0;
            int alturaVolatil = 0;
            final int tamanhoMaximoDoArquivo = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int tamanhoDoCache = tamanhoMaximoDoArquivo / 8;
            LruCache<String, Bitmap> bitmapCache = new LruCache<>(tamanhoDoCache);

            for (int x = 0; x < tamanhoDaLista; x++){
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(x));
                adapter.bindViewHolder(holder, x);

                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(),View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0,0, holder.itemView.getMeasuredWidth(),holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();

                Bitmap cacheDoBitmap = holder.itemView.getDrawingCache();
                if (cacheDoBitmap != null) {
                    bitmapCache.put(String.valueOf(x), cacheDoBitmap);
                }

                altura += holder.itemView.getMeasuredHeight();

            }

            bitmapPronto = Bitmap.createBitmap(view.getMeasuredWidth(), altura, Bitmap.Config.ARGB_8888);

            Canvas pagina = new Canvas(bitmapPronto);
            pagina.drawColor(Color.WHITE);

            for (int x = 0; x < tamanhoDaLista; x++){
                Bitmap bitmap = bitmapCache.get(String.valueOf(x));
                pagina.drawBitmap(bitmap, 0, alturaVolatil, paint);
                alturaVolatil += bitmap.getHeight();
                bitmap.recycle();
            }

        }

        return bitmapPronto;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            List<Visitante> dados = visitanteRepositorio.buscarTodos();
            visitanteAdapter = new VisitanteAdapter(dados);
            listDados.setAdapter(visitanteAdapter);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu_activity_main, menu);
       return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_gerar_pdf) {
            Toast.makeText(getApplicationContext(), "Tentando gerar PDF...", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}

