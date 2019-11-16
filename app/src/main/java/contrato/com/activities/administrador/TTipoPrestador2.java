package contrato.com.activities.administrador;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.adapters.AdapterTipoPrestador;
import contrato.com.boostrap.APIClient;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TTipoPrestador2 extends AppCompatActivity {

    public TextView txtNome;
    public TextView txtEmail;
    public List<TipoPrestador> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<TipoPrestador> listTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttipo_prestador2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        atualizar();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TTipoPrestador2.this, AddTipoPrestador.class));
            }
        });
    }

    protected void onResume() {
        super.onResume();
        atualizar();
    }

    private void atualizar() {

        /*Pega a referencia do ENDPOINT e do converter(gson)
         * */
        Retrofit retrofit = APIClient.getClient();

        //Faz o 'bind' da instância do retrofit com interface
        //que contém as operações (GET,POST,PUT,DELETE)
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);

        //Faz a chamada do serviço
        Call<List<TipoPrestador>> get = tipoPrestador.get();

        get.enqueue(new Callback<List<TipoPrestador>>() {

            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                //Log.e("response retrofit", "onResponse: " + response.code() );

                //ENTRADA DE DADOS
                minhaLista = findViewById(R.id.lvTPPrestador);
                listTP = response.body();

                AdapterTipoPrestador adapterTipoPrestador = new AdapterTipoPrestador(getApplicationContext(), listTP);
                minhaLista.setAdapter(adapterTipoPrestador);
                adapterTipoPrestador.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {
                minhaLista = findViewById(R.id.lvTPPrestador);
            }
        });

    }


}
