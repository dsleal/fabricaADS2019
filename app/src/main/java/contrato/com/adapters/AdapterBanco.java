package contrato.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import contrato.com.R;
import contrato.com.model.Banco;

public class AdapterBanco extends BaseAdapter {

    Context context;
    List<Banco> colecao;
    LayoutInflater inflter;

    public AdapterBanco(final Context applicationContext,
                        final List<Banco> colecao) {
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

    private Banco parsetItem(int i) {
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

        Banco banco1 = parsetItem(i);

        TextView banco;
        // TextView ativo;
        TextView cod;


        banco = view.findViewById(R.id.txtTpDesc);
        //ativo = view.findViewById(R.id.txtTpAtivo);
        cod = view.findViewById(R.id.txtTpId);


        banco.setText(banco1.getBanco() != null ? banco1.getBanco() : "Sem registro");
        //ativo.setText(tipoPrestador.isAtivo() == true ? "ativo" : "desabilitado");
        cod.setText(banco1.getCod() + " ");

        return view;
    }


}
