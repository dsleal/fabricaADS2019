package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import contrato.com.R;
import contrato.com.activities.Cadastro;
import contrato.com.adapters.AdapterTipoPrestador;
import contrato.com.boostrap.APIClient;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddTipoPrestador extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tipo_prestador);
    }

    public void limpar(View view){
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
               Toast.makeText(AddTipoPrestador.this, "Tipo de prestador = " + tp.getDescricao() + " cadastrado!", Toast.LENGTH_LONG).show();
               startActivity(new Intent(AddTipoPrestador.this, TTipoPrestador2.class));
           }

            @Override
            public void onFailure(Call<TipoPrestador> call, Throwable t) {
                Toast.makeText(AddTipoPrestador.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();

            }
        });

    }


    private TipoPrestador getDadosTela(){
        TipoPrestador tp = new TipoPrestador();
        EditText descricao = findViewById(R.id.txtAddTpDesc);
        RadioButton habilitado = findViewById(R.id.rbHabilitado);
        RadioButton desabilitado = findViewById(R.id.rbDesabilitado);
        RadioGroup rbGroup = findViewById(R.id.rbGroup);
        

        rbGroup.is;


        radiobutton.getSelectedCheckbox();

        tp.setDescricao(descricao.getText().toString());
        tp.isDesativado()
        return tp;
        //tp.isDesativado(habilitado.getText();
    }
}
