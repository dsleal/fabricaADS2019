package contrato.com.activities.prestador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.activities.Login;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Endereco;
import contrato.com.model.Prestador;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.PrestadorResource;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroPrestador extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpf_Cpnpj;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtDataNascimento;
    private EditText edtIdentidade;
    private EditText edtEndereco;
    private Spinner edtBanco;
    private EditText edtAgencia;
    private EditText edtConta;
    private Spinner edtTipoPrestador;
    private EditText edtDataCadastro;


    private Spinner snpTipoPrestador;
    public List listTP;


    //depois modificamos
    public List<TipoPrestador> lista = new ArrayList<>();
    public ListView minhaLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_prestador);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);

        //carregar tipo prestadores

        //gerar dados
        tiposPrestadores();
/*
        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<List<TipoPrestador>> get = tipoPrestador.get();
        get.enqueue(new Callback<List<TipoPrestador>>() {
            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                //ENTRADA DE DADOS
                Spinner spinnerPrestador = (Spinner) findViewById(R.id.spinnerTipoPrestador);

                //listTP = response.body();
                List<TipoPrestador> tipoPrestadors = response.body();

                ArrayAdapter<TipoPrestador> adapterSpinner = new ArrayAdapter<TipoPrestador>(CadastroPrestador.this, android.R.layout.simple_list_item_1, tipoPrestadors);
                spinnerPrestador.setAdapter(adapterSpinner);


                //Spinner spinnerBanco = (Spinner) findViewById(R.id.spinnerBanco);
                // List<Banco> bancos = response.body();


            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {
                minhaLista = findViewById(R.id.lvTPPrestador);
            }
        });

 */
    }
    public void Banco(){

        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<List<TipoPrestador>> get = tipoPrestador.get();
        get.enqueue(new Callback<List<TipoPrestador>>() {
            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                //ENTRADA DE DADOS
                Spinner spinnerPrestador = (Spinner) findViewById(R.id.spinnerTipoPrestador);

                //listTP = response.body();
                List<TipoPrestador> tipoPrestadors = response.body();

                ArrayAdapter<TipoPrestador> adapterSpinner = new ArrayAdapter<TipoPrestador>(CadastroPrestador.this, android.R.layout.simple_list_item_1, tipoPrestadors);
                spinnerPrestador.setAdapter(adapterSpinner);


                //Spinner spinnerBanco = (Spinner) findViewById(R.id.spinnerBanco);
                // List<Banco> bancos = response.body();


            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {
                minhaLista = findViewById(R.id.lvTPPrestador);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad_prestador, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void limparPres(View view) {
        edtNome = findViewById(R.id.edtNome);
        edtCpf_Cpnpj = findViewById(R.id.edtCpf_Cpnpj);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);
        edtIdentidade = findViewById(R.id.edtIdentidade);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtBanco = findViewById(R.id.spinnerBanco);
        edtAgencia = findViewById(R.id.edtAgencia);
        edtConta = findViewById(R.id.edtConta);
        edtTipoPrestador = findViewById(R.id.spinnerTipoPrestador);
        edtDataCadastro = findViewById(R.id.edtDataCadastro);


        edtNome.setText(" ");
        edtCpf_Cpnpj.setText(" ");
        edtEmail.setText(" ");
        edtTelefone.setText(" ");
        edtDataNascimento.setText(" ");
        edtIdentidade.setText(" ");
        edtEndereco.setText(" ");
        //edtBanco.setText(" ");
        edtAgencia.setText(" ");
        edtConta.setText(" ");
        edtDataCadastro.setText(" ");
    }


    public void tiposPrestadores() {
        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<List<TipoPrestador>> get = tipoPrestador.get();
        get.enqueue(new Callback<List<TipoPrestador>>() {
            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                //ENTRADA DE DADOS
                Spinner spinnerPrestador = (Spinner) findViewById(R.id.spinnerTipoPrestador);

                //listTP = response.body();
                List<TipoPrestador> tipoPrestadors = response.body();

                ArrayAdapter<TipoPrestador> adapterSpinner = new ArrayAdapter<TipoPrestador>(CadastroPrestador.this, android.R.layout.simple_list_item_1, tipoPrestadors);
                spinnerPrestador.setAdapter(adapterSpinner);


                //Spinner spinnerBanco = (Spinner) findViewById(R.id.spinnerBanco);
                // List<Banco> bancos = response.body();


            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {
                minhaLista = findViewById(R.id.lvTPPrestador);
            }
        });
    }


    public void adicionarPres(View view) {

        Prestador prestador = getDadosTela();
        Retrofit retrofit = APIClient.getClient();
        PrestadorResource PrestadorResource = retrofit.create(contrato.com.resource.PrestadorResource.class);
        Call<Prestador> post = PrestadorResource.post(prestador);
        post.enqueue(new Callback<Prestador>() {
            @Override
            public void onResponse(Call<Prestador> call, Response<Prestador> response) {
                Prestador pres = response.body();
                Toast.makeText(CadastroPrestador.this, "Prestador  '" + pres.getNome() + " cadastrado!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(CadastroPrestador.this, Login.class));

            }

            @Override
            public void onFailure(Call<Prestador> call, Throwable throwable) {
                Toast.makeText(CadastroPrestador.this, "Erro ao cadastrar", Toast.LENGTH_LONG).show();
            }
        });

    }

    private Prestador getDadosTela() {

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");

        Endereco endereco = new Endereco();
        Prestador pres = new Prestador();


        edtNome = findViewById(R.id.edtNome);
        edtCpf_Cpnpj = findViewById(R.id.edtCpf_Cpnpj);
        edtEmail = findViewById(R.id.edtEmail);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);
        edtIdentidade = findViewById(R.id.edtIdentidade);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtTelefone = findViewById(R.id.edtTelefone);

        edtBanco = findViewById(R.id.spinnerBanco);
        edtAgencia = findViewById(R.id.edtAgencia);
        edtConta = findViewById(R.id.edtConta);
        edtTipoPrestador = findViewById(R.id.spinnerTipoPrestador);
        edtDataCadastro = findViewById(R.id.edtDataCadastro);


        endereco.setId(99990);
        pres.setEndereco(endereco);
        pres.setNome(edtNome.getText().toString());
        pres.setCpf_cnpj(edtCpf_Cpnpj.getText().toString());
        pres.setEmail(edtEmail.getText().toString());
        //cli.setDt_nascimento(this.dateFormat.format(cli.getDt_nascimento()));
        // cli.setDt_nascimento(edtDataNascimento.getText().toString());
        //pres.setDt_nascimento(edtDataNascimento.getText().toString());
        pres.setIdentidade(edtIdentidade.getText().toString());
        //pres.setTelefone(edtTelefone.getText().toString());

        return pres;

    }


}
