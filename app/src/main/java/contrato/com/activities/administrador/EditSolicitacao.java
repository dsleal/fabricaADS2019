package contrato.com.activities.administrador;

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

public class EditSolicitacao extends AppCompatActivity {

    TextView codigo;
    TextView data;
    TextView status;
    TextView cliente;
    TextView endereco;
    TextView descricao;
    Button btnCancelar;
    Solicitacao solicitacao;
    Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_solicitacao);

        codigo = findViewById(R.id.txtSDCod);
        status = findViewById(R.id.txtSDStatus);
        cliente = findViewById(R.id.txtSDCliente);
        endereco = findViewById(R.id.txtSDEndereco);
        descricao = findViewById(R.id.txtSDDescricao);
        data = findViewById(R.id.txtSDData);

        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> get = solicitacaoResource.getPorId(id);
        get.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                solicitacao = response.body();
                codigo.setText(Long.toString(solicitacao.getId()));
                status.setText(solicitacao.getStatusSolicitacao().getDescricao());
                cliente.setText(solicitacao.getCliente().getNome());
                descricao.setText(solicitacao.getDescricao());
                endereco.setText(solicitacao.getCliente().getEndereco().getLogradouro() + ", " + solicitacao.getCliente().getEndereco().getCidade());


                SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
                data.setText(dataFormatada.format(solicitacao.getData()));
                habilitaCancelamento(solicitacao);
            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
            }
        });


    }

    public void habilitaCancelamento(Solicitacao solicitacao){
        btnCancelar = findViewById(R.id.btnSCancelar);
        if (solicitacao.getStatusSolicitacao().getId()==99991 || solicitacao.getStatusSolicitacao().getId()==99992){
            btnCancelar.setEnabled(true);
        }

    }


    public void cancelar(View view){

        StatusSolicitacao statusSolitacao = new StatusSolicitacao();
        statusSolitacao.setId(99994); //alterar para cancelado criar no banco a opção

        solicitacao.setStatusSolicitacao(statusSolitacao);

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> cancelar = solicitacaoResource.put(id, solicitacao);
        cancelar.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                Solicitacao solic = response.body();
                Toast.makeText(getBaseContext(), "Solicitação " + solic.getId() +" cancelada!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditSolicitacao.this, TSolicitacao.class));
            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
            }
        });


    }

}