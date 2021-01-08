package ig8.com.br;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ig8.com.br.dominio.entidades.Visitante;

public class VisitanteAdapter extends RecyclerView.Adapter<VisitanteAdapter.ViewHolderVisitante> {

    private List<Visitante> dados;

    public VisitanteAdapter(List<Visitante> dados) {
        this.dados = dados;
    }


    @NonNull
    @Override
    public VisitanteAdapter.ViewHolderVisitante onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_visitantes, parent, false);

        ViewHolderVisitante holderVisitante = new ViewHolderVisitante(view, parent.getContext());

        return holderVisitante;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitanteAdapter.ViewHolderVisitante holder, int position) {

        if ( (dados != null) && (dados.size() > 0) ) {

            Visitante visitante = dados.get(position);

            holder.textNome.setText(visitante.nome);
            holder.textTelefone.setText(visitante.telefone);
            holder.textEndereco.setText(visitante.endereco);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderVisitante extends RecyclerView.ViewHolder{

        public TextView textNome;
        public TextView textTelefone;
        public TextView textEndereco;

        public ViewHolderVisitante(@NonNull View itemView, final Context context) {
            super(itemView);

            textNome = (TextView) itemView.findViewById(R.id.textNome);
            textTelefone = (TextView) itemView.findViewById(R.id.textEndereco);
            textEndereco = (TextView) itemView.findViewById(R.id.textTelefone);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Visitante visitante = dados.get(getLayoutPosition());

                        Intent it = new Intent(context, ActivityCadastroVisitante.class);
                        it.putExtra("VISITANTE", visitante);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

                    }
                }
            });
        }


    }


}
