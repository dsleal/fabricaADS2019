package contrato.com.activities.prestador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import contrato.com.R;
import contrato.com.activities.MainActivity;

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
