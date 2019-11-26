package contrato.com.activities.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import contrato.com.R;
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
        setContentView(R.layout.activity_painel_cliente);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        buscarCliente();
     }


    public Cliente buscarCliente(){
        Cliente cli = new Cliente();
        Retrofit retrofit = APIClient.getClient();
        ClienteResource clienteResource = retrofit.create(ClienteResource.class);
        Call<Cliente> get = clienteResource.getPorId(id);
        get.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Cliente cliente =  response.body();
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
            }
        });
        return cli;
    }

    public void minhasSolicitacoes(View view){
        Intent intent = new Intent(PainelCliente.this, TMinhasOrdens.class);
        startActivity(intent);
    }


    public void minhasOrdensServico(View view){
        Intent intent = new Intent(PainelCliente.this, TMinhasOrdens.class);
        startActivity(intent);
    }

}
