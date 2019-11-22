package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import contrato.com.R;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Solicitacao;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.SolicitacaoResource;
import contrato.com.resource.TipoPrestadorResource;
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
    Long id;


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
        id = intent.getLongExtra("ID", 0);

        Retrofit retrofit = APIClient.getClient();
        SolicitacaoResource solicitacaoResource = retrofit.create(SolicitacaoResource.class);
        Call<Solicitacao> get = solicitacaoResource.getPorId(id);
        get.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                Solicitacao solicitacao = response.body();
                codigo.setText(Long.toString(solicitacao.getId()));
                status.setText(solicitacao.getStatusSolicitacao().getDescricao());
                cliente.setText(solicitacao.getCliente().getNome());
                descricao.setText(solicitacao.getDescricao());
                endereco.setText(solicitacao.getCliente().getEndereco().getLogradouro() + ", " + solicitacao.getCliente().getEndereco().getCidade());
                data.setText(solicitacao.getData().toString());

            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
            }
        });


    }
}