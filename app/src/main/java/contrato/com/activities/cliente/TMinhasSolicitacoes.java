package contrato.com.activities.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.adapters.AdapterClienteSolicitacao;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Solicitacao;
import contrato.com.resource.SolicitacaoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TMinhasSolicitacoes extends AppCompatActivity {
    Integer idCliente;
    public List<Solicitacao> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<Solicitacao> listSolicitacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tminhas_solicitacoes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("id", 0);

        atualizar();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TMinhasSolicitacoes.this, AddCSolicitacoes.class);
                intent.putExtra("id", idCliente);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("id", 0);

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.getPorCliente(idCliente);
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvCSolicitacao);
                    listSolicitacao = response.body();
                    AdapterClienteSolicitacao adapterSolicitacao = new AdapterClienteSolicitacao(getApplicationContext(), listSolicitacao);
                    minhaLista.setAdapter(adapterSolicitacao);
                    minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            Intent intent = new Intent(TMinhasSolicitacoes.this, EditCSolicitacoes.class);
                            intent.putExtra("id", listSolicitacao.get(arg2).getCliente().getId());
                            intent.putExtra("solic", listSolicitacao.get(arg2).getId());
                            startActivity(intent);
                            return true;
                        }
                    });
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TMinhasSolicitacoes.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TMinhasSolicitacoes.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TMinhasSolicitacoes.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Toast.makeText(TMinhasSolicitacoes.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TMinhasSolicitacoes.this, PainelCliente.class);
        intent.putExtra("id", idCliente);
        startActivity(intent);
    }

    private void atualizar() {

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.getPorCliente(idCliente);
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvCSolicitacao);
                    listSolicitacao = response.body();

                    AdapterClienteSolicitacao adapterSolicitacao = new AdapterClienteSolicitacao(getApplicationContext(), listSolicitacao);
                    minhaLista.setAdapter(adapterSolicitacao);
                    adapterSolicitacao.notifyDataSetChanged();
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TMinhasSolicitacoes.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TMinhasSolicitacoes.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TMinhasSolicitacoes.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Toast.makeText(TMinhasSolicitacoes.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }
}
