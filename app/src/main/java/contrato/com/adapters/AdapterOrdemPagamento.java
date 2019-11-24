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
import contrato.com.model.OrdemPagamento;

public class AdapterOrdemPagamento extends BaseAdapter {


    Context context;
    List<OrdemPagamento> colecao;
    LayoutInflater inflter;

    public AdapterOrdemPagamento(final Context applicationContext,
                                 final List<OrdemPagamento> colecao) {
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

    private OrdemPagamento parsetItem(int i) {
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
                    inflate(R.layout.activity_itens_ordem_pagamento, viewGroup, false);
        }

        OrdemPagamento ordemPagamento = parsetItem(i);

        TextView id;
        TextView data;
        TextView valor;

        id = view.findViewById(R.id.txtOPId);
        valor = view.findViewById(R.id.txtOPValor);
        data = view.findViewById(R.id.txtOPData);

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");

        data.setText(dataFormatada.format(ordemPagamento.getData()));
        id.setText(ordemPagamento.getId() + " ");
        valor.setText(ordemPagamento.getValor().toString());

        return view;
    }
}
