package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import contrato.com.R;

public class Administrador extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_administrador);

       }


    public void tpPrestador(View view) {
        Intent intent = new Intent(Administrador.this, TTipoPrestador.class);
        startActivity(intent);
    }

    public void solicitacaoServido(View view) {
        Intent intent = new Intent(Administrador.this, TSolicitacao.class);
        startActivity(intent);
    }

    public void ordemServico(View view) {
        Intent intent = new Intent(Administrador.this, TOrdemServico.class);
        startActivity(intent);
    }

    public void ordemPagamento(View view) {
        Intent intent = new Intent(Administrador.this, TOrdemPagamento.class);
        startActivity(intent);
    }



}
