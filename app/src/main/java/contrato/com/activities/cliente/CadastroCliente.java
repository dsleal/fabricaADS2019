package contrato.com.activities.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

import contrato.com.R;
import contrato.com.activities.Login;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Cliente;
import contrato.com.model.Endereco;
import contrato.com.resource.ClienteResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpf_Cpnpj;
    private EditText edtEmail;
    private EditText edtIdentidade;
    private EditText edtLogradouro;
    private EditText edtCEP;
    private EditText edtCidade;
    private EditText edtEstado;
    private EditText edtTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
    }


    public void limparCli(View view) {
        setCampos();

        edtNome.setText(" ");
        edtCpf_Cpnpj.setText(" ");
        edtEmail.setText(" ");
        edtIdentidade.setText(" ");
        edtLogradouro.setText(" ");
        edtCEP.setText(" ");
        edtCidade.setText(" ");
        edtEstado.setText(" ");
        edtTelefone.setText(" ");
    }

    public void adicionarCli(View view) {
        Cliente cliente = getDadosTela();
        Retrofit retrofit = APIClient.getClient();
        ClienteResource clienteResource = retrofit.create(ClienteResource.class);
        Call<Cliente> post = clienteResource.post(cliente);
        post.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Cliente cli = response.body();
                Toast.makeText(CadastroCliente.this, "Tipo de prestador  '" + cli.getNome() + " cadastrado!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(CadastroCliente.this, Login.class));
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(CadastroCliente.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();

            }
        });

    }


    private Cliente getDadosTela() {

        setCampos();

        Endereco endereco = new Endereco();
        Cliente cli = new Cliente();

        /*
        endereco.setLogradouro(edtLogradouro.getText().toString());
        endereco.setCep(edtCEP.getText().toString());
        endereco.setCidade(edtCidade.getText().toString());
        endereco.setEstado(edtEstado.getText().toString());
         */
        endereco.setId(99991); //remover tem que corrigir no server

        cli.setEndereco(endereco);
        cli.setNome(edtNome.getText().toString());
        cli.setCpfCnpj(edtCpf_Cpnpj.getText().toString());
        cli.setEmail(edtEmail.getText().toString());
        cli.setDtNascimento(new Date(System.currentTimeMillis()));
        cli.setIdentidade(edtIdentidade.getText().toString());
        //cli.setTe(edtTelefone.getText().toString());
        cli.setDtCadastro(new Date(System.currentTimeMillis()));
        return cli;
    }

    private void setCampos(){
        edtNome = findViewById(R.id.edtNome);
        edtCpf_Cpnpj = findViewById(R.id.edtCpf_Cpnpj);
        edtEmail = findViewById(R.id.edtEmail);
        edtIdentidade = findViewById(R.id.edtIdentidade);
        edtLogradouro= findViewById(R.id.edtLogradouro);
        edtCEP = findViewById(R.id.edtCEP);
        edtCidade= findViewById(R.id.edtCidade);
        edtEstado = findViewById(R.id.edtEstado);
        edtTelefone = findViewById(R.id.edtTelefone);
    }
}