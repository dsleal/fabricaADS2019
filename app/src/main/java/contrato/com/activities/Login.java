package contrato.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import contrato.com.R;
import contrato.com.activities.administrador.Administrador;
import contrato.com.activities.administrador.EditOrdemServico;
import contrato.com.activities.administrador.TOrdemServico;
import contrato.com.activities.cliente.PainelCliente;
import contrato.com.activities.prestador.PainelPrestador;
import contrato.com.adapters.AdapterOrdemServico;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Cliente;
import contrato.com.model.OrdemPagamento;
import contrato.com.model.OrdemServico;
import contrato.com.resource.ClienteResource;
import contrato.com.resource.OrdemPagamentoResource;
import contrato.com.resource.OrdemServicoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    Spinner spinner;
    String tipo;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = (Spinner) findViewById(R.id.spinnerLogin);
        String[] listItem = getResources().getStringArray(R.array.listLogin);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItem));

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Login.this, MainActivity.class));
    }


    public void acessar(View view) {

        tipo = String.valueOf(spinner.getSelectedItem());

        if (tipo.equals("Administrador")) {
            startActivity(new Intent(Login.this, Administrador.class));
        } else if (tipo.equals("Cliente")) {

            //Cliente cliente = buscarCliente();
            Intent intent = new Intent(Login.this, PainelCliente.class);
            intent.putExtra("id", 99997);
            startActivity(intent);

        } else if (tipo.equals("Prestador")) {
            startActivity(new Intent(Login.this, PainelPrestador.class));
        } else {
            Toast.makeText(this, "Escolha um tipo", Toast.LENGTH_LONG).show();
        }
    }


}

