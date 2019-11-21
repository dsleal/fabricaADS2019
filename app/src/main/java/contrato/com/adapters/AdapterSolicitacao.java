package contrato.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import contrato.com.R;
import contrato.com.model.Solicitacao;
import contrato.com.model.TipoPrestador;

public class AdapterSolicitacao extends BaseAdapter {


    Context context;
    List<Solicitacao> colecao;
    LayoutInflater inflter;

    public AdapterSolicitacao(final Context applicationContext,
                              final List<Solicitacao> colecao) {
        this.context = applicationContext;
        this.colecao = colecao;

    }

    @Override
    public int getCount() {
        return this.colecao != null ? this.colecao.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return this.colecao.get(i);
    }

    private Solicitacao parsetItem(int i){
        return this.colecao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // inflate the layout for each list row
        //'Infla' o layout(pega a referencia) para ser trabalhada
        //no método
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.activity_itens_solicitacao, viewGroup, false);
        }

        Solicitacao solicitacao = parsetItem(i);

        TextView descricao;
        TextView data;
        TextView id;

        descricao = view.findViewById(R.id.txtSDesc);
        id = view.findViewById(R.id.txtSId);
        data = view.findViewById(R.id.txtSData);

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");

        data.setText(dataFormatada.format(solicitacao.getData()));
        descricao.setText(solicitacao.getDescricao());
        id.setText(solicitacao.getId()+" ");

        return view;
    }
}
