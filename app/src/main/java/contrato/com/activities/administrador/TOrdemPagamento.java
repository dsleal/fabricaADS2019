package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.adapters.AdapterOrdemPagamento;
import contrato.com.boostrap.APIClient;
import contrato.com.model.OrdemPagamento;
import contrato.com.resource.OrdemPagamentoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TOrdemPagamento extends AppCompatActivity {

    public List<OrdemPagamento> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<OrdemPagamento> listOrdemPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tordem_pagamento);

        atualizar();
    }

    protected void onStart() {
        super.onStart();
        Retrofit retrofit = APIClient.getClient();
        OrdemPagamentoResource OrdemPagamentoResource = retrofit.create(OrdemPagamentoResource.class);
        Call<List<OrdemPagamento>> get = OrdemPagamentoResource.get();
        get.enqueue(new Callback<List<OrdemPagamento>>() {
            @Override
            public void onResponse(Call<List<OrdemPagamento>> call, Response<List<OrdemPagamento>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvOrdemPagamento);
                    listOrdemPagamento = response.body();
                    AdapterOrdemPagamento adapterOrdemPagamento = new AdapterOrdemPagamento(getApplicationContext(), listOrdemPagamento);
                    minhaLista.setAdapter(adapterOrdemPagamento);
                    minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            Intent intent = new Intent(TOrdemPagamento.this, EditOrdemPagamento.class);
                            intent.putExtra("ID", listOrdemPagamento.get(arg2).getId());
                            startActivity(intent);
                            return true;
                        }
                    });

                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TOrdemPagamento.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TOrdemPagamento.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TOrdemPagamento.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OrdemPagamento>> call, Throwable t) {
                Toast.makeText(TOrdemPagamento.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TOrdemPagamento.this, Administrador.class));
    }

    private void atualizar() {
        Retrofit retrofit = APIClient.getClient();
        OrdemPagamentoResource OrdemPagamentoResource = retrofit.create(OrdemPagamentoResource.class);
        Call<List<OrdemPagamento>> get = OrdemPagamentoResource.get();
        get.enqueue(new Callback<List<OrdemPagamento>>() {
            @Override
            public void onResponse(Call<List<OrdemPagamento>> call, Response<List<OrdemPagamento>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvOrdemPagamento);
                    listOrdemPagamento = response.body();

                    AdapterOrdemPagamento adapterOrdemPagamento = new AdapterOrdemPagamento(getApplicationContext(), listOrdemPagamento);
                    minhaLista.setAdapter(adapterOrdemPagamento);
                    adapterOrdemPagamento.notifyDataSetChanged();

                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TOrdemPagamento.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TOrdemPagamento.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TOrdemPagamento.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OrdemPagamento>> call, Throwable t) {
                Toast.makeText(TOrdemPagamento.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }
}
