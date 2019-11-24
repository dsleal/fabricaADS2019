package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.adapters.AdapterOrdemServico;
import contrato.com.boostrap.APIClient;
import contrato.com.model.OrdemServico;
import contrato.com.resource.OrdemServicoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TOrdemServico extends AppCompatActivity {

    public List<OrdemServico> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<OrdemServico> listOrdemServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tordem_servico);

        atualizar();
    }

    protected  void onStart(){
        super.onStart();
        Retrofit retrofit = APIClient.getClient();
        OrdemServicoResource OrdemServicoResource = retrofit.create(OrdemServicoResource.class);
        Call<List<OrdemServico>> get = OrdemServicoResource.get();
        get.enqueue(new Callback<List<OrdemServico>>() {
            @Override
            public void onResponse(Call<List<OrdemServico>> call, Response<List<OrdemServico>> response) {
                minhaLista = findViewById(R.id.lvOrdemServico);
                listOrdemServico = response.body();
                AdapterOrdemServico adapterOrdemServico = new AdapterOrdemServico(getApplicationContext(), listOrdemServico);
                minhaLista.setAdapter(adapterOrdemServico);
                minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        Intent intent = new Intent(TOrdemServico.this, EditOrdemServico.class);
                        intent.putExtra("ID",listOrdemServico.get(arg2).getId());
                        startActivity(intent);
                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<OrdemServico>> call, Throwable t) {}
        });
    }

    private void atualizar() {
        Retrofit retrofit = APIClient.getClient();
        OrdemServicoResource OrdemServicoResource = retrofit.create(OrdemServicoResource.class);
        Call<List<OrdemServico>> get = OrdemServicoResource.get();
        get.enqueue(new Callback<List<OrdemServico>>() {
            @Override
            public void onResponse(Call<List<OrdemServico>> call, Response<List<OrdemServico>> response) {
                minhaLista = findViewById(R.id.lvOrdemServico);
                listOrdemServico = response.body();

                AdapterOrdemServico adapterOrdemServico = new AdapterOrdemServico(getApplicationContext(), listOrdemServico);
                minhaLista.setAdapter(adapterOrdemServico);
                adapterOrdemServico.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrdemServico>> call, Throwable t) {
                Log.e("error", "onFailure: " + t.getMessage() );
            }
        });

    }
}
