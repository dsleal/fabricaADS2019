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
import contrato.com.adapters.AdapterSolicitacao;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Solicitacao;
import contrato.com.resource.SolicitacaoResource;
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

    protected void onStart() {
        super.onStart();

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.get();
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvSolicitacao);
                    listSolicitacao = response.body();
                    AdapterSolicitacao adapterSolicitacao = new AdapterSolicitacao(getApplicationContext(), listSolicitacao);
                    minhaLista.setAdapter(adapterSolicitacao);
                    minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            Intent intent = new Intent(TSolicitacao.this, EditSolicitacao.class);
                            intent.putExtra("ID", listSolicitacao.get(arg2).getId());
                            startActivity(intent);
                            return true;
                        }
                    });
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TSolicitacao.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TSolicitacao.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TSolicitacao.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Toast.makeText(TSolicitacao.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TSolicitacao.this, Administrador.class));
    }

    private void atualizar() {
        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<List<Solicitacao>> get = solicitacaoResource.get();
        get.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvSolicitacao);
                    listSolicitacao = response.body();

                    AdapterSolicitacao adapterSolicitacao = new AdapterSolicitacao(getApplicationContext(), listSolicitacao);
                    minhaLista.setAdapter(adapterSolicitacao);
                    adapterSolicitacao.notifyDataSetChanged();
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TSolicitacao.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TSolicitacao.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TSolicitacao.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Log.e("error", "onFailure: " + t.getMessage());
            }
        });
    }
}