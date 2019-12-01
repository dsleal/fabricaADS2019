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
import contrato.com.model.OrdemServico;
import contrato.com.model.OrdemServico;

public class AdapterOrdemServico extends BaseAdapter {


    Context context;
    List<OrdemServico> colecao;
    LayoutInflater inflter;

    public AdapterOrdemServico(final Context applicationContext,
                               final List<OrdemServico> colecao) {
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

    private OrdemServico parsetItem(int i) {
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
                    inflate(R.layout.activity_itens_ordem_servico, viewGroup, false);
        }

        OrdemServico OrdemServico = parsetItem(i);

        TextView id;
        TextView data;
        TextView prestador;
        TextView cliente;

        id = view.findViewById(R.id.txtOSId);
        prestador = view.findViewById(R.id.txtOSPrestador);
        cliente = view.findViewById(R.id.txtOSCliente);
        data = view.findViewById(R.id.txtOSData);

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");

        data.setText(dataFormatada.format(OrdemServico.getData()));
        prestador.setText(OrdemServico.getPrestador().getNome());
        cliente.setText(OrdemServico.getSolicitacao().getCliente().getNome());
        id.setText(OrdemServico.getId() + " ");

        return view;
    }
}
