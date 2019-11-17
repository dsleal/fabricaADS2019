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

public class EditTipoPrestador extends AppCompatActivity {
    EditText codigo;
    EditText descricao;
    RadioButton rbEditHabilitado;
    RadioButton rbEditDesabilitado;
    RadioGroup rbEditGroup;
    int id;
    Boolean desabilitado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tipo_prestador);

        codigo = findViewById(R.id.txtEditCod);
        descricao = findViewById(R.id.txtEditTpDesc);
        rbEditDesabilitado = findViewById(R.id.rbEditDesabilitado);
        rbEditHabilitado = findViewById(R.id.rbEditHabilitado);
        Intent intent = getIntent();
        id = intent.getIntExtra("ID",0);

        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<TipoPrestador> get = tipoPrestador.getPorId(id);
        get.enqueue(new Callback<TipoPrestador>() {
            @Override
            public void onResponse(Call<TipoPrestador> call, Response<TipoPrestador> response) {
                TipoPrestador tp = response.body();
                codigo.setText(Integer.toString(tp.getId()));
                descricao.setText(tp.getDescricao());
                if(tp.isDesativado()){
                    rbEditDesabilitado.setChecked(true);
                    rbEditHabilitado.setChecked(false);
                }else{
                    rbEditDesabilitado.setChecked(false);
                    rbEditHabilitado.setChecked(true);
                }
            }

            @Override
            public void onFailure(Call<TipoPrestador> call, Throwable t) {
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

        if(selectedRadioButtonText.equals("Desabilitado")){
            desabilitado = true;
        }
        else if (selectedRadioButtonText.equals("Habilitado")){
            desabilitado = false;
        }
        tp.setDesativado(desabilitado);
        Call<TipoPrestador> alterar = tipoPrestador.put(id, tp);
        alterar.enqueue(new Callback<TipoPrestador>() {
            @Override
            public void onResponse(Call<TipoPrestador> call, Response<TipoPrestador> response) {
                TipoPrestador tPrest = response.body();
                Toast.makeText(getBaseContext(), "Tipo de Prestador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditTipoPrestador.this, TTipoPrestador.class));
            }

            @Override
            public void onFailure(Call<TipoPrestador> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Não foi possível alterar o tipo de prestador!", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getBaseContext(), "Tipo de Prestador removido com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditTipoPrestador.this, TTipoPrestador.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Não foi possível remover o tipo de prestador!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
