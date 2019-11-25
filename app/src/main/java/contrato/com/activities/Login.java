package contrato.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import contrato.com.R;
import contrato.com.activities.administrador.Administrador;
import contrato.com.activities.cliente.PainelCliente;
import contrato.com.activities.prestador.PainelPrestador;

public class Login extends AppCompatActivity {
    Spinner spinner;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = (Spinner) findViewById(R.id.spinnerLogin);
        String[] listItem = getResources().getStringArray(R.array.listLogin);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItem));

    }


    public void acessar(View view) {

        tipo = String.valueOf(spinner.getSelectedItem());

        if (tipo.equals("Administrador")) {
            startActivity(new Intent(Login.this, Administrador.class));
        } else if (tipo.equals("Cliente")) {
            startActivity(new Intent(Login.this, PainelCliente.class));
        } else if (tipo.equals("Prestador")) {
            startActivity(new Intent(Login.this, PainelPrestador.class));
        } else {
            Toast.makeText(this, "Escolha um tipo", Toast.LENGTH_LONG).show();
        }


    }


}

