package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddTipoPrestador extends AppCompatActivity {
    private boolean ativo;
    private EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tipo_prestador);
    }

    public void limpar(View view){
        descricao = findViewById(R.id.txtAddTpDesc);
        descricao.setText(" ");
    }

    public void adicionar(View view){
        TipoPrestador tp = getDadosTela();
        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<TipoPrestador> post = tipoPrestador.post(tp);
        post.enqueue(new Callback<TipoPrestador>() {
           @Override
            public void onResponse(Call<TipoPrestador> call, Response<TipoPrestador> response) {
               TipoPrestador tp = response.body();
               Toast.makeText(AddTipoPrestador.this, "Tipo de prestador  '" + tp.getDescricao() + "' cadastrado!", Toast.LENGTH_LONG).show();
               startActivity(new Intent(AddTipoPrestador.this, TTipoPrestador.class));
           }

            @Override
            public void onFailure(Call<TipoPrestador> call, Throwable t) {
                Toast.makeText(AddTipoPrestador.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();

            }
        });

    }


    private TipoPrestador getDadosTela(){

        TipoPrestador tp = new TipoPrestador();
        descricao = findViewById(R.id.txtAddTpDesc);

        //Identificar radio button selecionado
        RadioGroup rbGroup = (RadioGroup) findViewById(R.id.rbGroup);
        int selectedRadioButtonID = rbGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
        String selectedRadioButtonText = selectedRadioButton.getText().toString();
        if(selectedRadioButtonText.equals("Ativo")){
             ativo = true;
        }
        else if (selectedRadioButtonText.equals("Desabilitado")){
            ativo = false;
        }

        //setar dados informados no tipo prestador
        tp.setDescricao(descricao.getText().toString());
        tp.setAtivo(ativo);
        return tp;
    }
}
