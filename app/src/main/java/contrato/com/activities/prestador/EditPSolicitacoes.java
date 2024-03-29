package contrato.com.activities.prestador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import contrato.com.R;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Solicitacao;
import contrato.com.model.StatusSolicitacao;
import contrato.com.resource.SolicitacaoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditPSolicitacoes extends AppCompatActivity {

    Integer id;
    Integer idTpPrestador;
    Integer idStatusSol;
    TextView codigo;
    TextView data;
    TextView status;
    TextView descricao;
    TextView tipoPrestador;
    TextView valorServico;
    TextView endereco;
    EditText edtValor;
    Button btnEditSolValor;

    Solicitacao solicitacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_psolicitacoes);

        codigo = findViewById(R.id.txtSolCodP);
        data = findViewById(R.id.txtSolDataP);
        status = findViewById(R.id.txtSolStatusP);
        descricao = findViewById(R.id.txtSolDescP);
        tipoPrestador = findViewById(R.id.txtSolTP);
        valorServico = findViewById(R.id.txtValoP);
        edtValor = findViewById(R.id.edtValor);
        endereco = findViewById(R.id.txtSolEndP);
        btnEditSolValor = findViewById(R.id.btnEditSolValor);


        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        idTpPrestador = intent.getIntExtra("idTpPrestador", 0);
        idStatusSol = intent.getIntExtra("idStatusSol", 0);

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> get = solicitacaoResource.getPorId(id);
        get.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                if (response.isSuccessful()) {
                    solicitacao = response.body();
                    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");

                    codigo.setText(solicitacao.getId() + " ");
                    status.setText(solicitacao.getStatusSolicitacao().getDescricao());
                    descricao.setText(solicitacao.getDescricao());
                    data.setText(dataFormatada.format(solicitacao.getData()));
                    endereco.setText(solicitacao.getCliente().getEndereco().toString());

                    habilitaAcoes();
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditPSolicitacoes.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditPSolicitacoes.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditPSolicitacoes.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
                Toast.makeText(EditPSolicitacoes.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditPSolicitacoes.this, TSolicitacoesAbertas.class);
        intent.putExtra("idTpPrestador", idTpPrestador);
        intent.putExtra("idStatusSol", idStatusSol);
        startActivity(intent);
    }

    public void habilitaAcoes() {
        //propor valor
        //status: aguard. orcamento ou aguard. aprovação
        if (solicitacao.getStatusSolicitacao().getId() == 99991) {
            btnEditSolValor.setEnabled(true);
            valorServico.setVisibility(View.VISIBLE);
            edtValor.setVisibility(View.VISIBLE);
        }
    }

    public void aprovarSolicitacao(View view) {
        String valor = edtValor.getText().toString();
        if (valor.length() > 0 && Float.parseFloat(valor) != 0) {
            StatusSolicitacao statusSolicitacao = new StatusSolicitacao();
            statusSolicitacao.setId(99992);

            solicitacao.setStatusSolicitacao(statusSolicitacao);
            solicitacao.setValor(Float.parseFloat(valor));

            Retrofit retrofit = APIClient.getClient();
            SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
            Call<Solicitacao> cancelar = solicitacaoResource.put(id, solicitacao);
            cancelar.enqueue(new Callback<Solicitacao>() {
                @Override
                public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                    if (response.isSuccessful()) {
                        Solicitacao sol = response.body();
                        Toast.makeText(getBaseContext(), "Proposta para solicitação " + sol.getId() + " realizada! \nApós aprovação será criada a ordem serviço!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditPSolicitacoes.this, TSolicitacoesAbertas.class);
                        intent.putExtra("idTpPrestador", idTpPrestador);
                        intent.putExtra("idStatusSol", idStatusSol);
                        startActivity(intent);
                    } else {
                        switch (response.code()) {
                            case 404:
                                Toast.makeText(EditPSolicitacoes.this, "404 - not found", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                Toast.makeText(EditPSolicitacoes.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(EditPSolicitacoes.this, "unknown error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<Solicitacao> call, Throwable t) {
                    Toast.makeText(EditPSolicitacoes.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                    Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
                }
            });
        } else {
            Toast.makeText(EditPSolicitacoes.this, "Informe o valor!", Toast.LENGTH_LONG).show();
        }
    }
}
