package ig8.com.br;

import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import ig8.com.br.database.DadosOpenHelper;
import ig8.com.br.dominio.entidades.Visitante;
import ig8.com.br.dominio.repositorio.VisitanteRepositorio;

public class ActivityCadastroVisitante extends AppCompatActivity {

    private EditText editNome;
    private EditText editEndereco;
    private EditText editNumero;
    private EditText editTelefone;
    private CheckBox checkdia1;
    private CheckBox checkdia2;
    private CheckBox checkdia3;
    private CheckBox checkdia4;
    private CheckBox checkdia5;
    private CheckBox checkdia6;
    private CheckBox checkdia7;
    private CheckBox checkdia8;
    private CheckBox checkdia9;
    private CheckBox checkdia10;
    private CheckBox checkdia11;
    private CheckBox checkdia12;
    private CheckBox checkdia13;
    private CheckBox checkdia14;
    private CheckBox checkdia15;
    private CheckBox checkdia16;
    private CheckBox checkdia17;
    private CheckBox checkdia18;
    private CheckBox checkdia19;
    private CheckBox checkdia20;
    private ConstraintLayout layoutContentActivityCadastroVisitante;

    private VisitanteRepositorio visitanteRepositorio;

    private SQLiteDatabase conexao;

    private DadosOpenHelper dadosOpenHelper;

    private Visitante visitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_visitante);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editNome = (EditText)findViewById(R.id.editNome);
        editEndereco = (EditText)findViewById(R.id.editEndereco);
        editNumero = (EditText)findViewById(R.id.editNumero);
        editTelefone = (EditText)findViewById(R.id.editTelefone);
        checkdia1 = (CheckBox)findViewById(R.id.checkdia1);
        checkdia2 = (CheckBox)findViewById(R.id.checkdia2);
        checkdia3 = (CheckBox)findViewById(R.id.checkdia3);
        checkdia4 = (CheckBox)findViewById(R.id.checkdia4);
        checkdia5 = (CheckBox)findViewById(R.id.checkdia5);
        checkdia6 = (CheckBox)findViewById(R.id.checkdia6);
        checkdia7 = (CheckBox)findViewById(R.id.checkdia7);
        checkdia8 = (CheckBox)findViewById(R.id.checkdia8);
        checkdia9 = (CheckBox)findViewById(R.id.checkdia9);
        checkdia10 = (CheckBox)findViewById(R.id.checkdia10);
        checkdia11 = (CheckBox)findViewById(R.id.checkdia11);
        checkdia12 = (CheckBox)findViewById(R.id.checkdia12);
        checkdia13 = (CheckBox)findViewById(R.id.checkdia13);
        checkdia14 = (CheckBox)findViewById(R.id.checkdia14);
        checkdia15 = (CheckBox)findViewById(R.id.checkdia15);
        checkdia16 = (CheckBox)findViewById(R.id.checkdia16);
        checkdia17 = (CheckBox)findViewById(R.id.checkdia17);
        checkdia18 = (CheckBox)findViewById(R.id.checkdia18);
        checkdia19 = (CheckBox)findViewById(R.id.checkdia19);
        checkdia20 = (CheckBox)findViewById(R.id.checkdia20);

        layoutContentActivityCadastroVisitante = (ConstraintLayout)findViewById(R.id.layoutContentActivityCadastroVisitante);

        criarConexao();
        verificaParanmetro();

        // Criando a mascara para o campo de celular
        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)N-NNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTelefone, smf);
        editTelefone.addTextChangedListener(mtw);
        //Fim da mascara

    }


    private void verificaParanmetro(){
        Bundle bundle = getIntent().getExtras();

        visitante = new Visitante();

        if ( (bundle != null) && (bundle.containsKey("VISITANTE")) ) {

            visitante = (Visitante) bundle.getSerializable("VISITANTE");

            editNome.setText(visitante.nome);
            editEndereco.setText(visitante.endereco);
            editNumero.setText(visitante.numero);
            editTelefone.setText(visitante.telefone);
            checkdia1.setChecked(visitante.dia1);
            checkdia2.setChecked(visitante.dia2);
            checkdia3.setChecked(visitante.dia3);
            checkdia4.setChecked(visitante.dia4);
            checkdia5.setChecked(visitante.dia5);
            checkdia6.setChecked(visitante.dia6);
            checkdia7.setChecked(visitante.dia7);
            checkdia8.setChecked(visitante.dia8);
            checkdia9.setChecked(visitante.dia9);
            checkdia10.setChecked(visitante.dia10);
            checkdia11.setChecked(visitante.dia11);
            checkdia12.setChecked(visitante.dia12);
            checkdia13.setChecked(visitante.dia13);
            checkdia14.setChecked(visitante.dia14);
            checkdia15.setChecked(visitante.dia15);
            checkdia16.setChecked(visitante.dia16);
            checkdia17.setChecked(visitante.dia17);
            checkdia18.setChecked(visitante.dia18);
            checkdia19.setChecked(visitante.dia19);
            checkdia20.setChecked(visitante.dia20);


        }
    }


    private void criarConexao() {

        try{
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();

            visitanteRepositorio = new VisitanteRepositorio(conexao);

        }catch(SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_certo, null);
            dlg.show();

        }
    }

    private void confirmar() {

        if (validaCampos() == false) {

            try {

                if (visitante.codigo == 0) {
                    visitanteRepositorio.inserir(visitante);
                }
                else {
                    visitanteRepositorio.alterar(visitante);
                }

                finish();

            }catch(SQLException ex) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.title_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.action_certo, null);
                dlg.show();
            }
        }
    }


    private boolean validaCampos(){

        boolean res = false;

        String nome     = editNome.getText().toString();
        String endereco = editEndereco.getText().toString();
        String numero = editNumero.getText().toString();
        String telefone = editTelefone.getText().toString();
        boolean dia1    = checkdia1.isChecked();
        boolean dia2    = checkdia2.isChecked();
        boolean dia3    = checkdia3.isChecked();
        boolean dia4    = checkdia4.isChecked();
        boolean dia5    = checkdia5.isChecked();
        boolean dia6    = checkdia6.isChecked();
        boolean dia7    = checkdia7.isChecked();
        boolean dia8    = checkdia8.isChecked();
        boolean dia9    = checkdia9.isChecked();
        boolean dia10   = checkdia10.isChecked();
        boolean dia11   = checkdia11.isChecked();
        boolean dia12   = checkdia12.isChecked();
        boolean dia13   = checkdia13.isChecked();
        boolean dia14   = checkdia14.isChecked();
        boolean dia15   = checkdia15.isChecked();
        boolean dia16   = checkdia16.isChecked();
        boolean dia17   = checkdia17.isChecked();
        boolean dia18   = checkdia18.isChecked();
        boolean dia19   = checkdia19.isChecked();
        boolean dia20   = checkdia20.isChecked();





        visitante.nome     = nome;
        visitante.endereco = endereco;
        visitante.numero   = numero;
        visitante.telefone = telefone;
        visitante.dia1     = dia1;
        visitante.dia2     = dia2;
        visitante.dia3     = dia3;
        visitante.dia4     = dia4;
        visitante.dia5     = dia5;
        visitante.dia6     = dia6;
        visitante.dia7     = dia7;
        visitante.dia8     = dia8;
        visitante.dia9     = dia9;
        visitante.dia10    = dia10;
        visitante.dia11    = dia11;
        visitante.dia12    = dia12;
        visitante.dia13    = dia13;
        visitante.dia14    = dia14;
        visitante.dia15    = dia15;
        visitante.dia16    = dia16;
        visitante.dia17    = dia17;
        visitante.dia18    = dia18;
        visitante.dia19    = dia19;
        visitante.dia20    = dia20;


        if (res = isCampoVazio(nome)){
            editNome.requestFocus();
        }
        else
            if (res = isCampoVazio(endereco)){
                editEndereco.requestFocus();
            }
            else
            if (res = isCampoVazio(numero)){
                editNumero.requestFocus();
            }
        if (res){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.message_campos_vazios);
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();

        }
        return res;

    }


    private boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        return resultado;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_cadastro_visitante, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case android.R.id.home:
                finish();
                break;

            case R.id.action_ok:

                confirmar();

                break;

            case R.id.action_excluir:
                new AlertDialog.Builder(this)
                        .setTitle("Excluindo Visitante")
                        .setMessage("Você quer Excluir esse Visitante?")
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                visitanteRepositorio.excluir(visitante.codigo);
                                finish();
                            }
                        }).setNegativeButton("não", null) .show();


                break;

        }

        return super.onOptionsItemSelected(item);
    }
}