package contrato.com.activities.prestador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import contrato.com.R;
import contrato.com.activities.Login;

public class PainelPrestador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_painel_prestador);
    }

    public void minhasSolicitacoes(View view) {
        Intent intent = new Intent(PainelPrestador.this, TSolicitacoesAbertas.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PainelPrestador.this, Login.class));
    }


}
