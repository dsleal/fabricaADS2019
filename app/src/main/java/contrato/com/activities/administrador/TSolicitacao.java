package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
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

public class TSolicitacao extends AppCompatActivity {
    public List<Solicitacao> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<Solicitacao> listSolicitacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsolicitacao);

        atualizar();

    }

    protected  void onStart(){

        super.onStart();

        /*

        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<List<TipoPrestador>> get = tipoPrestador.get();
        get.enqueue(new Callback<List<TipoPrestador>>() {
            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                minhaLista = findViewById(R.id.lvTPPrestador);
                listTP = response.body();
                AdapterTipoPrestador adapterTipoPrestador = new AdapterTipoPrestador(getApplicationContext(), listTP);
                minhaLista.setAdapter(adapterTipoPrestador);
                minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        Intent intent = new Intent(TTipoPrestador.this, EditTipoPrestador.class);
                        intent.putExtra("ID",listTP.get(arg2).getId());
                        startActivity(intent);
                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {}
        });


         */



    }

   private void atualizar() {
        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.get();
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                Log.e("response retrofit", "onResponse: " + response.code() );

                minhaLista = findViewById(R.id.lvSolicitacao);
                listSolicitacao = response.body();

                AdapterSolicitacao adapterSolicitacao = new AdapterSolicitacao(getApplicationContext(), listSolicitacao);
                minhaLista.setAdapter(adapterSolicitacao);
                adapterSolicitacao.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Log.e("error", "onFailure: " + t.getMessage() );
            }
        });

    }


    }
