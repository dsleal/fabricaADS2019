package contrato.com.activities.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.activities.administrador.AddTipoPrestador;
import contrato.com.activities.administrador.EditTipoPrestador;
import contrato.com.activities.administrador.TTipoPrestador;
import contrato.com.adapters.AdapterClienteSolicitacao;
import contrato.com.adapters.AdapterSolicitacao;
import contrato.com.adapters.AdapterTipoPrestador;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Solicitacao;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.SolicitacaoResource;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TMinhasSolicitacoes extends AppCompatActivity {

    public List<Solicitacao> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<Solicitacao> listSolicitacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tminhas_solicitacoes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        atualizar();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TMinhasSolicitacoes.this, AddCSolicitacoes.class));

            }
        });
    }

    protected  void onStart(){
        super.onStart();

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.getPorCliente();
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                minhaLista = findViewById(R.id.lvCSolicitacao);
                listSolicitacao = response.body();
                AdapterClienteSolicitacao adapterSolicitacao = new AdapterClienteSolicitacao(getApplicationContext(), listSolicitacao);
                minhaLista.setAdapter(adapterSolicitacao);
                minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        Intent intent = new Intent(TMinhasSolicitacoes.this, EditCSolicitacoes.class);
                        intent.putExtra("ID",listSolicitacao.get(arg2).getId());
                        startActivity(intent);
                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {}
        });



    }


    private void atualizar() {

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.get();
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                minhaLista = findViewById(R.id.lvCSolicitacao);
                listSolicitacao = response.body();

                AdapterClienteSolicitacao adapterSolicitacao = new AdapterClienteSolicitacao(getApplicationContext(), listSolicitacao);
                minhaLista.setAdapter(adapterSolicitacao);
                adapterSolicitacao.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                minhaLista = findViewById(R.id.lvSolicitacao);
            }
        });

    }


}
