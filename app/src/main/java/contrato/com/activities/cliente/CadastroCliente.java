package contrato.com.activities.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText edtTelefone;
    private EditText edtDataNascimento;
    private EditText edtIdentidade;
    private EditText edtEndereco;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void limparCli(View view) {
        edtNome = findViewById(R.id.edtNome);
        edtCpf_Cpnpj = findViewById(R.id.edtCpf_Cpnpj);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);
        edtIdentidade = findViewById(R.id.edtIdentidade);
        edtEndereco = findViewById(R.id.edtEndereco);

        edtNome.setText(" ");
        edtCpf_Cpnpj.setText(" ");
        edtEmail.setText(" ");
        edtTelefone.setText(" ");
        edtDataNascimento.setText(" ");
        edtIdentidade.setText(" ");
        edtEndereco.setText(" ");
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


    private Cliente getDadosTela(){
        Endereco endereco = new Endereco();
        Cliente cli = new Cliente();

        edtNome = findViewById(R.id.edtNome);
        edtCpf_Cpnpj = findViewById(R.id.edtCpf_Cpnpj);
        edtEmail = findViewById(R.id.edtEmail);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);
        edtIdentidade = findViewById(R.id.edtIdentidade);
        edtEndereco = findViewById(R.id.edtEndereco);

        endereco.setId(99990);
        cli.setEndereco(endereco);
        cli.setNome(edtNome.getText().toString());
        cli.setCpf_cnpj(edtCpf_Cpnpj.getText().toString());
        cli.setEmail(edtEmail.getText().toString());
        //cli.setDt_nascimento(this.dateFormat.parseObject(edtDataNascimento.getText().toString());
        //cli.setDt_nascimento(new java.sql.Date(1990,10,01));//setDt_nascimento();
        cli.setIdentidade(edtIdentidade.getText().toString());

        return cli;
    }


}
