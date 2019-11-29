package contrato.com.activities.prestador;

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
import contrato.com.activities.cliente.EditCSolicitacoes;
import contrato.com.activities.cliente.TMinhasSolicitacoes;
import contrato.com.adapters.AdapterClienteSolicitacao;
import contrato.com.adapters.AdapterSolicitacao;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Solicitacao;
import contrato.com.resource.SolicitacaoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TSolicitacoesAbertas extends AppCompatActivity {

    public List<Solicitacao> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<Solicitacao> listSolicitacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsolicitacoes_abertas);

        atualizar();
    }


    protected  void onStart(){
        super.onStart();

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.getPorStatus();
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                minhaLista = findViewById(R.id.lvPSolicitacoesAbertas);
                listSolicitacao = response.body();
                AdapterClienteSolicitacao adapterSolicitacao = new AdapterClienteSolicitacao(getApplicationContext(), listSolicitacao);
                minhaLista.setAdapter(adapterSolicitacao);
                minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        Intent intent = new Intent(TSolicitacoesAbertas.this, EditPSolicitacoes.class);
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
        Call<List<Solicitacao>> get = solicitacaoResource.getPorStatus();
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                minhaLista = findViewById(R.id.lvPSolicitacoesAbertas);
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
