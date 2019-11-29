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
import contrato.com.activities.administrador.AddTipoPrestador;
import contrato.com.activities.cliente.EditCSolicitacoes;
import contrato.com.activities.cliente.TMinhasSolicitacoes;
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
    TextView codigo;
    TextView data;
    TextView status;
    TextView descricao;
    TextView tipoPrestador;
    TextView valorServico;
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
        btnEditSolValor = findViewById(R.id.btnEditSolValor);
        edtValor = findViewById(R.id.edtValor);


        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);


        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> get = solicitacaoResource.getPorId(id);
        get.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {

                solicitacao = response.body();

                SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");

                codigo.setText(solicitacao.getId() + " ");
                status.setText(solicitacao.getStatusSolicitacao().getDescricao());
                descricao.setText(solicitacao.getDescricao());
                data.setText(dataFormatada.format(solicitacao.getData()));

                habilitaAcoes();

            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
                Log.d("teste", "onFailure: ");
            }
        });

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
                    Solicitacao sol = response.body();
                    Toast.makeText(getBaseContext(), "Proposta para solicitação " + sol.getId() + " realizada! \nApós aprovação será criada a ordem serviço!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditPSolicitacoes.this, TSolicitacoesAbertas.class));
                }

                @Override
                public void onFailure(Call<Solicitacao> call, Throwable t) {
                }
            });
        } else {
            Toast.makeText(EditPSolicitacoes.this, "Informe o valor!", Toast.LENGTH_LONG).show();

        }

    }

}

