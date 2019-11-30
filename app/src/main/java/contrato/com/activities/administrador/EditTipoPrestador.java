package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import contrato.com.R;
import contrato.com.boostrap.APIClient;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditTipoPrestador extends AppCompatActivity {

    EditText codigo;
    EditText descricao;
    RadioButton rbEditAtivo;
    RadioButton rbEditDesabilitado;
    RadioGroup rbEditGroup;
    int id;
    Boolean ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tipo_prestador);

        codigo = findViewById(R.id.txtEditCod);
        descricao = findViewById(R.id.txtEditTpDesc);
        rbEditDesabilitado = findViewById(R.id.rbEditDesabilitado);
        rbEditAtivo = findViewById(R.id.rbEditAtivo);
        Intent intent = getIntent();
        id = intent.getIntExtra("ID",0);

        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<TipoPrestador> get = tipoPrestador.getPorId(id);
        get.enqueue(new Callback<TipoPrestador>() {
            @Override
            public void onResponse(Call<TipoPrestador> call, Response<TipoPrestador> response) {
                if (response.isSuccessful()) {
                    TipoPrestador tp = response.body();
                    codigo.setText(Integer.toString(tp.getId()));
                    descricao.setText(tp.getDescricao());
                    if(tp.isAtivo()){
                        rbEditDesabilitado.setChecked(false);
                        rbEditAtivo.setChecked(true);
                    }else{
                        rbEditDesabilitado.setChecked(true);
                        rbEditAtivo.setChecked(false);
                    }
                }
                else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditTipoPrestador.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditTipoPrestador.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditTipoPrestador.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
            @Override
            public void onFailure(Call<TipoPrestador> call, Throwable t) {
                Toast.makeText(EditTipoPrestador.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }

    public void alterar(View view){
        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        TipoPrestador tp = new TipoPrestador();
        tp.setDescricao(descricao.getText().toString());
        tp.setId(id);

        //Identificar radio button selecionado
        rbEditGroup = findViewById(R.id.rbEditGroup);
        int selectedRadioButtonID = rbEditGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
        String selectedRadioButtonText = selectedRadioButton.getText().toString();

        if(selectedRadioButtonText.equals("Ativo")){
            ativo = true;
        }
        else if (selectedRadioButtonText.equals("Desabilitado")){
            ativo = false;
        }
        tp.setAtivo(ativo);
        Call<TipoPrestador> alterar = tipoPrestador.put(id, tp);
        alterar.enqueue(new Callback<TipoPrestador>() {
            @Override
            public void onResponse(Call<TipoPrestador> call, Response<TipoPrestador> response) {
                if (response.isSuccessful()) {
                    TipoPrestador tPrest = response.body();
                    Toast.makeText(getBaseContext(), "Tipo de Prestador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditTipoPrestador.this, TTipoPrestador.class));
                }
                else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditTipoPrestador.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditTipoPrestador.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditTipoPrestador.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
            @Override
            public void onFailure(Call<TipoPrestador> call, Throwable t) {
                Toast.makeText(EditTipoPrestador.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }
    public void remover(View view){
        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<Void> delete = tipoPrestador.delete(id);
        delete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Tipo de Prestador removido com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditTipoPrestador.this, TTipoPrestador.class));
                }
                else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(EditTipoPrestador.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(EditTipoPrestador.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(EditTipoPrestador.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(EditTipoPrestador.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }
}
