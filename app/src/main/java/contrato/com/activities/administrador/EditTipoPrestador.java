package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tipo_prestador);

        codigo = findViewById(R.id.txtEditCod);
        descricao = findViewById(R.id.txtEditTpDesc);
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID",0);


        Retrofit retrofit = APIClient.getClient();
        TipoPrestadorResource tipoPrestador = retrofit.create(TipoPrestadorResource.class);
        Call<TipoPrestador> get = tipoPrestador.get(id);
        get.enqueue(new Callback<TipoPrestador>() {
            @Override
            public void onResponse(Call<TipoPrestador> call, Response<TipoPrestador> response) {
                TipoPrestador tp = new TipoPrestador();
                codigo.setText(tp.getId());
                descricao.setText(tp.getDescricao());
            }

            @Override
            public void onFailure(Call<TipoPrestador> call, Throwable t) {
                Toast.makeText(EditTipoPrestador.this, "Tipo de prestador com código '" + Integer.toString(id) + "' nao encontrado!", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void alterar(View view){

    }
    public void remover(View view){}
}
