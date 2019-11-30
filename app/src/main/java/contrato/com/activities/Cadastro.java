package contrato.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import contrato.com.R;
import contrato.com.activities.cliente.CadastroCliente;
import contrato.com.activities.prestador.CadastroPrestador;

public class Cadastro extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);
    }

    public void cadastroCliente(View view) {
        Intent intent = new Intent(Cadastro.this, CadastroCliente.class);
        startActivity(intent);
    }

}
