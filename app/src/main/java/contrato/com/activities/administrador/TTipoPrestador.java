package contrato.com.activities.administrador;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.activities.MainActivity;
import contrato.com.adapters.AdapterTipoPrestador;
import contrato.com.boostrap.APIClient;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TTipoPrestador extends AppCompatActivity {

    public TextView txtNome;
    public TextView txtEmail;
    public List<TipoPrestador> lista = new ArrayList<>();
    public ListView minhaLista;
    public List<TipoPrestador> listTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttipo_prestador);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        atualizar();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TTipoPrestador.this, AddTipoPrestador.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TTipoPrestador.this, Administrador.class));
    }

    protected void onStart() {
        super.onStart();

        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<List<TipoPrestador>> get = tipoPrestador.get();
        get.enqueue(new Callback<List<TipoPrestador>>() {
            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvTPPrestador);
                    listTP = response.body();
                    AdapterTipoPrestador adapterTipoPrestador = new AdapterTipoPrestador(getApplicationContext(), listTP);
                    minhaLista.setAdapter(adapterTipoPrestador);
                    minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            Intent intent = new Intent(TTipoPrestador.this, EditTipoPrestador.class);
                            intent.putExtra("ID", listTP.get(arg2).getId());
                            startActivity(intent);
                            return true;
                        }
                    });
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TTipoPrestador.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TTipoPrestador.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TTipoPrestador.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {
                Toast.makeText(TTipoPrestador.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });


    }

    protected void onResume() {
        super.onResume();
        atualizar();
    }

    private void atualizar() {
        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<List<TipoPrestador>> get = tipoPrestador.get();
        get.enqueue(new Callback<List<TipoPrestador>>() {
            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                if (response.isSuccessful()) {
                    minhaLista = findViewById(R.id.lvTPPrestador);
                    listTP = response.body();

                    AdapterTipoPrestador adapterTipoPrestador = new AdapterTipoPrestador(getApplicationContext(), listTP);
                    minhaLista.setAdapter(adapterTipoPrestador);
                    adapterTipoPrestador.notifyDataSetChanged();
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(TTipoPrestador.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(TTipoPrestador.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TTipoPrestador.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {
                Toast.makeText(TTipoPrestador.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });

    }


}
