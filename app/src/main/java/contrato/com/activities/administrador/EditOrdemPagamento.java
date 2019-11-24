package contrato.com.activities.administrador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import contrato.com.R;
import contrato.com.boostrap.APIClient;
import contrato.com.model.OrdemPagamento;
import contrato.com.model.OrdemPagamento;
import contrato.com.model.StatusServico;
import contrato.com.resource.OrdemPagamentoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditOrdemPagamento extends AppCompatActivity {
    TextView codigo;
    TextView data;
    TextView dataPagamento;
    TextView valor;
    TextView cliente;
    TextView prestador;
    TextView descricao;
    Button btnOPPagar;
    OrdemPagamento ordemPagamento;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ordem_pagamento);

        codigo = findViewById(R.id.txtOPId);
        cliente = findViewById(R.id.txtOPCliente);
        descricao = findViewById(R.id.txtOPDescricao);
        prestador = findViewById(R.id.txtOPDescricao);
        data = findViewById(R.id.txtOPData);
        dataPagamento = findViewById(R.id.txtOPDataPagamento);
        valor = findViewById(R.id.txtOPValor);
        
        
        Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0);

        Retrofit retrofit = APIClient.getClient();
        OrdemPagamentoResource OrdemPagamentoResource = retrofit.create(OrdemPagamentoResource.class);
        Call<OrdemPagamento> get = OrdemPagamentoResource.getPorId(id);
        get.enqueue(new Callback<OrdemPagamento>() {
            @Override
            public void onResponse(Call<OrdemPagamento> call, Response<OrdemPagamento> response) {
                ordemPagamento = response.body();

                codigo.setText(Long.toString(ordemPagamento.getId()));
                cliente.setText(ordemPagamento.getOrdemServico().getSolicitacao().getCliente().getNome());
                descricao.setText(ordemPagamento.getOrdemServico().getDescricao());
                prestador.setText(ordemPagamento.getOrdemServico().getPrestador().getNome());
                valor.setText(ordemPagamento.getValor().toString());


                SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
                data.setText(dataFormatada.format(ordemPagamento.getData()));
                dataPagamento.setText(dataFormatada.format(ordemPagamento.getDataPagamento()));
                habilitaPagamento(ordemPagamento);

            }

            @Override
            public void onFailure(Call<OrdemPagamento> call, Throwable t) {
            }
        });
    }

    public void habilitaPagamento(OrdemPagamento ordemPagamento){
        btnOPPagar = findViewById(R.id.btnOPPagar);
        if (ordemPagamento.getDataPagamento().equals(null)){
            btnOPPagar.setEnabled(true);
        }

    }

    public void pagar(View view){


    }
}
