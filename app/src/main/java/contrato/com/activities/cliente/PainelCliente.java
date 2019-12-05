package contrato.com.activities.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import contrato.com.R;
import contrato.com.activities.Login;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Cliente;
import contrato.com.resource.ClienteResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PainelCliente extends AppCompatActivity {
    Integer idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_painel_cliente);

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("id", 0);
    }


    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("id", 0);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(PainelCliente.this, Login.class));
    }

    public void minhasSolicitacoes(View view) {
        Intent intent = new Intent(PainelCliente.this, TMinhasSolicitacoes.class);
        intent.putExtra("id", idCliente);
        startActivity(intent);
    }


}
