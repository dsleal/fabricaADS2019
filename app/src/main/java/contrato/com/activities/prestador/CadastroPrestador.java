package contrato.com.activities.prestador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import contrato.com.R;
import contrato.com.activities.Cadastro;
import contrato.com.activities.Login;
import contrato.com.activities.MainActivity;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Endereco;
import contrato.com.model.Prestador;
import contrato.com.model.TipoPrestador;
import contrato.com.resource.PrestadorResource;
import contrato.com.resource.TipoPrestadorResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroPrestador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prestador);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CadastroPrestador.this, MainActivity.class));
    }

}
