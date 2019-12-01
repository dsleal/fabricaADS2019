package contrato.com.activities.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import contrato.com.R;
import contrato.com.activities.Login;
import contrato.com.activities.MainActivity;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Cliente;
import contrato.com.resource.ClienteResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PainelCliente extends AppCompatActivity {
    Cliente cliente = new Cliente();
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_painel_cliente);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        // buscarCliente();
    }


    public Cliente buscarCliente() {
        Cliente cli = new Cliente();
        Retrofit retrofit = APIClient.getClient();
        ClienteResource clienteResource = retrofit.create(ClienteResource.class);
        Call<Cliente> get = clienteResource.getPorId(id);
        get.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente cliente = response.body();
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(PainelCliente.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(PainelCliente.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(PainelCliente.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(PainelCliente.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
        return cli;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PainelCliente.this, Login.class));
    }

    public void minhasSolicitacoes(View view) {
        Intent intent = new Intent(PainelCliente.this, TMinhasSolicitacoes.class);
        startActivity(intent);
    }


}
