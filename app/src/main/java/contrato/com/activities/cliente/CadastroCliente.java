package contrato.com.activities.cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import contrato.com.R;

public class CadastroCliente extends AppCompatActivity {

    private EditText edtNOme;
    private  EditText edtEndereco;
    private EditText edtTelefone;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edtNOme = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
