package contrato.com.activities.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class EditCSolicitacoes extends AppCompatActivity {

    Integer id;
    TextView codigo;
    TextView data;
    TextView status;
    TextView descricao;
    TextView tipoPrestador;
    TextView valorServico;
    Button btnEditSolAprovar;
    Button btnEditSolCancelar;

    Solicitacao solicitacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_csolicitacoes);

        codigo = findViewById(R.id.txtSolCod);
        data = findViewById(R.id.txtSolData);
        status = findViewById(R.id.txtSolStatus);
        descricao = findViewById(R.id.txtSolDesc);
        tipoPrestador = findViewById(R.id.txtSolTP);
        valorServico = findViewById(R.id.txtSolValor);
        btnEditSolAprovar  = findViewById(R.id.btnEditSolAprovar);
        btnEditSolCancelar = findViewById(R.id.btnEditSolCancelar);


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

                codigo.setText(solicitacao.getId()+" ");
                status.setText(solicitacao.getStatusSolicitacao().getDescricao());
                descricao.setText(solicitacao.getDescricao());
                data.setText(dataFormatada.format(solicitacao.getData()));

                if(solicitacao.getValor()!= null & solicitacao.getValor()!=0){
                    valorServico.setText(solicitacao.getValor().toString());
                }
                if(solicitacao.getTipoPrestador()!= null){
                    tipoPrestador.setText(solicitacao.getTipoPrestador().getDescricao());
                }
                habilitaAcoes();

            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
            }
        });

    }


    public void habilitaAcoes(){
        //cancelamento
        //status: aguard. orcamento ou aguard. aprovação
        if (solicitacao.getStatusSolicitacao().getId()==99991 | solicitacao.getStatusSolicitacao().getId()==99992  ){
            btnEditSolCancelar.setEnabled(true);
        }

        //aprovar
        //status: aguard. aprovação e com valor
        if (solicitacao.getStatusSolicitacao().getId()==99992 & solicitacao.getValor()!= null ){
            btnEditSolAprovar.setEnabled(true);
        }
    }

    public void cancelarSolicitacao(View view){
        StatusSolicitacao statusSolicitacao = new StatusSolicitacao();
        statusSolicitacao.setId(99995);

        solicitacao.setStatusSolicitacao(statusSolicitacao);

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> cancelar = solicitacaoResource.put(id, solicitacao);
        cancelar.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                Solicitacao sol = response.body();
                Toast.makeText(getBaseContext(), "Solicitação " + sol.getId() +" cancelada!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditCSolicitacoes.this, TMinhasSolicitacoes.class));
            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
            }
        });

    }
    public void aprovarSolicitacao(View view){
        StatusSolicitacao statusSolicitacao = new StatusSolicitacao();
        statusSolicitacao.setId(99993);

        solicitacao.setStatusSolicitacao(statusSolicitacao);

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> cancelar = solicitacaoResource.put(id, solicitacao);
        cancelar.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                Solicitacao sol = response.body();
                Toast.makeText(getBaseContext(), "Preço para solicitação " + sol.getId() +" aprovado! \n Será criada um ordem de serviço!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditCSolicitacoes.this, TMinhasSolicitacoes.class));
            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
            }
        });
    }


}
