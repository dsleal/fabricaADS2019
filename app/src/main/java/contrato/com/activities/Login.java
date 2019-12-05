package contrato.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import contrato.com.R;
import contrato.com.activities.administrador.Administrador;
import contrato.com.activities.administrador.EditOrdemServico;
import contrato.com.activities.administrador.TOrdemServico;
import contrato.com.activities.cliente.PainelCliente;
import contrato.com.activities.cliente.TMinhasSolicitacoes;
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
    EditText usuario;
    EditText senha;
    Integer id;
    Integer idCliente = 99999;
    Integer idTpPrestador= 99991;
    Integer idStatusSol= 99991;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.txtLUsuario);
        senha = findViewById(R.id.txtLSenha);
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
            if (usuario.getText().toString().equals("admin@gmail.com") && senha.getText().toString().equals("123")) {
                startActivity(new Intent(Login.this, Administrador.class));
            } else {
                Toast.makeText(Login.this, "Senha ou usuario incorretos", Toast.LENGTH_SHORT).show();
            }
        } else if (tipo.equals("Cliente")) {
            if (usuario.getText().toString().equals("cliente@gmail.com") && senha.getText().toString().equals("123")) {
                Intent intent = new Intent(Login.this, PainelCliente.class);
                intent.putExtra("id", idCliente);
                startActivity(intent);
            } else {
                Toast.makeText(Login.this, "Senha ou usuario incorretos", Toast.LENGTH_SHORT).show();
            }

        } else if (tipo.equals("Prestador")) {
            if (usuario.getText().toString().equals("prestador@gmail.com") && senha.getText().toString().equals("123")) {
                Intent intent = new Intent(Login.this, PainelPrestador.class);
                intent.putExtra("idStatusSol", idStatusSol);
                intent.putExtra("idTpPrestador", idTpPrestador);
                startActivity(intent);
            } else {
                Toast.makeText(Login.this, "Senha ou usuario incorretos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Escolha um tipo", Toast.LENGTH_LONG).show();
        }
    }
}

