package contrato.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import contrato.com.R;
import contrato.com.model.TipoPrestador;

public class AdapterTipoPrestador extends BaseAdapter {


    Context context;
    List<TipoPrestador> colecao;
    LayoutInflater inflter;

    public AdapterTipoPrestador(final Context applicationContext,
                                final List<TipoPrestador> colecao) {
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

    private TipoPrestador parsetItem(int i){
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
                    inflate(R.layout.activity_itens_tipo_prestador, viewGroup, false);
        }

        TipoPrestador tipoPrestador = parsetItem(i);

        TextView descricao;

        descricao = view.findViewById(R.id.txtTpDesc);
        //campoEmail = view.findViewById(R.id.txtEmail);

        descricao.setText(tipoPrestador.getDescricao()!=null ? tipoPrestador.getDescricao() : "Sem registro");

        return view;
    }
}
