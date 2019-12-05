package contrato.com.activities.administrador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

import contrato.com.R;
import contrato.com.boostrap.APIClient;
import contrato.com.model.OrdemPagamento;
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
        prestador = findViewById(R.id.txtOPPrestador);
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
                if (response.isSuccessful()) {
                    ordemPagamento = response.body();
                    cliente.setText(ordemPagamento.getOrdemServico().getSolicitacao().getCliente().getNome());
                    descricao.setText(ordemPagamento.getOrdemServico().getDescricao());
                    prestador.setText(ordemPagamento.getOrdemServico().getPrestador().getNome());
                    valor.setText(ordemPagamento.getValor().toString());

                    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
                    data.setText(dataFormatada.format(ordemPagamento.getData()));
                    if (ordemPagamento.getDataPagamento() != null) {
                        dataPagamento.setText(dataFormatada.format(ordemPagamento.getDataPagamento()));
                    }
                    habilitaPagamento(ordemPagamento);

                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditOrdemPagamento.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditOrdemPagamento.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditOrdemPagamento.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdemPagamento> call, Throwable t) {
                Toast.makeText(EditOrdemPagamento.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    public void habilitaPagamento(OrdemPagamento ordemPagamento) {
        btnOPPagar = findViewById(R.id.btnOPPagar);
        if (ordemPagamento.getDataPagamento() == null) {
            btnOPPagar.setEnabled(true);
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditOrdemPagamento.this, TOrdemPagamento.class));
    }

    public void pagar(View view) {

        ordemPagamento.setDataPagamento(new Date(System.currentTimeMillis()));
        Retrofit retrofit = APIClient.getClient();
        OrdemPagamentoResource ordemPagamentoResource = retrofit.create(OrdemPagamentoResource.class);
        Call<OrdemPagamento> cancelar = ordemPagamentoResource.put(id, ordemPagamento);
        cancelar.enqueue(new Callback<OrdemPagamento>() {
            @Override
            public void onResponse(Call<OrdemPagamento> call, Response<OrdemPagamento> response) {
                if (response.isSuccessful()) {
                    OrdemPagamento os = response.body();
                    Toast.makeText(getBaseContext(), "Pagamento da ordem " + os.getId() + " realizado!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditOrdemPagamento.this, TOrdemPagamento.class));
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditOrdemPagamento.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditOrdemPagamento.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditOrdemPagamento.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdemPagamento> call, Throwable t) {
                Toast.makeText(EditOrdemPagamento.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }
}
