package contrato.com.activities.cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import contrato.com.R;
import contrato.com.model.Cliente;

public class PainelCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_cliente);

        Cliente cli = (Cliente) getIntent().getSerializableExtra("cliente");
     }
}
