package contrato.com.activities.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.util.List;

import contrato.com.R;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Cliente;
import contrato.com.model.Solicitacao;
import contrato.com.model.StatusSolicitacao;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.SolicitacaoResource;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddCSolicitacoes extends AppCompatActivity {
    Integer idCliente;
    private Spinner tipoPrestador;
    private EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_csolicitacoes);
        carregarTipoPrestadores();

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("id", 0);
    }

    private void carregarTipoPrestadores() {
        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<List<TipoPrestador>> get = tipoPrestador.getAtivos();
        get.enqueue(new Callback<List<TipoPrestador>>() {
            @Override
            public void onResponse(Call<List<TipoPrestador>> call, Response<List<TipoPrestador>> response) {
                if (response.isSuccessful()) {
                    Spinner spinnerTP = (Spinner) findViewById(R.id.spinnerSTP);
                    List<TipoPrestador> tipoPrestadors = response.body();
                    ArrayAdapter<TipoPrestador> adapterSpinner = new ArrayAdapter<TipoPrestador>(AddCSolicitacoes.this, android.R.layout.simple_list_item_1, tipoPrestadors);
                    spinnerTP.setAdapter(adapterSpinner);
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(AddCSolicitacoes.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(AddCSolicitacoes.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(AddCSolicitacoes.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TipoPrestador>> call, Throwable t) {
                Toast.makeText(AddCSolicitacoes.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddCSolicitacoes.this, TMinhasSolicitacoes.class);
        intent.putExtra("id", idCliente);
        startActivity(intent);
    }

    public void adicionar(View view) {
        Solicitacao sol = getDadosTela();
        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> post = solicitacaoResource.post(sol);
        post.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                if (response.isSuccessful()) {
                    Solicitacao sol = response.body();
                    Toast.makeText(AddCSolicitacoes.this, "Solitação para um " + sol.getTipoPrestador().getDescricao() + " cadastrada!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddCSolicitacoes.this, TMinhasSolicitacoes.class);
                    intent.putExtra("ID", idCliente);
                    startActivity(intent);
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(AddCSolicitacoes.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(AddCSolicitacoes.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(AddCSolicitacoes.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
                Toast.makeText(AddCSolicitacoes.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    private Solicitacao getDadosTela() {
        Solicitacao sol = new Solicitacao();
        StatusSolicitacao stSol = new StatusSolicitacao();
        Cliente cli = new Cliente();

        //iniciando objetos
        //Status da solicitação
        stSol.setId(99991);
        //Cliente
        cli.setId(idCliente);

        descricao = findViewById(R.id.txtAddTpDesc);
        tipoPrestador = findViewById(R.id.spinnerSTP);

        //setar objetos
        sol.setStatusSolicitacao(stSol);
        sol.setCliente(cli);

        //setar dados da tela
        sol.setDescricao(descricao.getText().toString());
        sol.setTipoPrestador((TipoPrestador) tipoPrestador.getSelectedItem());
        sol.setData(new Date(System.currentTimeMillis()));

        return sol;
    }
}
