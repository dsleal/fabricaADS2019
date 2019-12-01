package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import contrato.com.R;
import contrato.com.activities.Login;
import contrato.com.boostrap.APIClient;
import contrato.com.model.OrdemServico;
import contrato.com.model.StatusServico;
import contrato.com.resource.OrdemServicoResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditOrdemServico extends AppCompatActivity {
    TextView codigo;
    TextView data;
    TextView status;
    TextView cliente;
    TextView prestador;
    TextView endereco;
    TextView descricao;
    Button btnCancelar;
    OrdemServico ordemServico;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ordem_servico);

        codigo = findViewById(R.id.txtOSId);
        status = findViewById(R.id.txtOSStatus);
        cliente = findViewById(R.id.txtOSCliente);
        endereco = findViewById(R.id.txtOSEndereco);
        descricao = findViewById(R.id.txtOSDescricao);
        prestador = findViewById(R.id.txtOSPrestador);
        data = findViewById(R.id.txtOSData);

        Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0);

        Retrofit retrofit = APIClient.getClient();
        OrdemServicoResource OrdemServicoResource = retrofit.create(OrdemServicoResource.class);
        Call<OrdemServico> get = OrdemServicoResource.getPorId(id);
        get.enqueue(new Callback<OrdemServico>() {
            @Override
            public void onResponse(Call<OrdemServico> call, Response<OrdemServico> response) {
                if (response.isSuccessful()) {
                    ordemServico = response.body();
                    status.setText(ordemServico.getStatusServico().getDescricao());
                    cliente.setText(ordemServico.getSolicitacao().getCliente().getNome());
                    descricao.setText(ordemServico.getDescricao());
                    endereco.setText(ordemServico.getSolicitacao().getCliente().getEndereco().getLogradouro() + ", " + ordemServico.getSolicitacao().getCliente().getEndereco().getCidade());
                    prestador.setText(ordemServico.getPrestador().getNome());

                    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
                    data.setText(dataFormatada.format(ordemServico.getData()));
                    habilitaCancelamento(ordemServico);
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditOrdemServico.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditOrdemServico.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditOrdemServico.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdemServico> call, Throwable t) {
                Toast.makeText(EditOrdemServico.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditOrdemServico.this, TOrdemServico.class));
    }


    public void habilitaCancelamento(OrdemServico ordemServico) {
        btnCancelar = findViewById(R.id.btnOSCancelar);
        if (ordemServico.getStatusServico().getId() == 99991) {
            btnCancelar.setEnabled(true);
        }
    }

    public void cancelar(View view) {
        StatusServico statusServico = new StatusServico();
        statusServico.setId(99993);

        ordemServico.setStatusServico(statusServico);

        Retrofit retrofit = APIClient.getClient();
        OrdemServicoResource solicitacaoResource = retrofit.create(OrdemServicoResource.class);
        Call<OrdemServico> cancelar = solicitacaoResource.put(id, ordemServico);
        cancelar.enqueue(new Callback<OrdemServico>() {
            @Override
            public void onResponse(Call<OrdemServico> call, Response<OrdemServico> response) {
                if (response.isSuccessful()) {
                    OrdemServico os = response.body();
                    Toast.makeText(getBaseContext(), "Solicitação " + os.getId() + " cancelada!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditOrdemServico.this, TOrdemServico.class));
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditOrdemServico.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditOrdemServico.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditOrdemServico.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdemServico> call, Throwable t) {
                Toast.makeText(EditOrdemServico.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }
}
